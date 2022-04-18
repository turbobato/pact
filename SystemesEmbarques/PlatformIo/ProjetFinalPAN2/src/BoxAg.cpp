#include "BoxAg.hpp"
#include "HTTPClient.h"
#include <Arduino.h>
#include <AsyncTCP.h>
#include <ESPAsyncWebServer.h>
#include <AsyncJson.h>
#define pump 
#define LED 2
#define seuil 30
/**
 * The ID of the box
 * */
String id ="3"; 

/**
 * Allow us to know if the box is connected or not to the internet
 * */

bool BoxAg::isConnected(){
    return m_internetState;
}

/**
 * used to connect our box to internet
 * */

void BoxAg::connect(){
    WiFi.mode(WIFI_STA); // explicitly set mode, esp defaults to STA+AP
    m_internetState = m_wifiManager.autoConnect("MY GARDEN","password");
    Serial.println(WiFi.localIP());
}

void BoxAg::setLampLuminosity(int luminosity){
     m_lamp.setLuminosity(luminosity);
}

void BoxAg::setPumpState(bool state){
    m_pump.setState(state);
}


void BoxAg::restart(){
    m_wifiManager.resetSettings();
    ESP.restart();
}

void BoxAg::configSensors(){
    m_sht20.initSHT20();
    m_capteurLux.begin();
    m_lamp.configLamp();
    m_pump.configPump();
    pinMode(LED,OUTPUT);
}

float BoxAg::readTemperature(){
    return m_sht20.readTemperature();
}

float BoxAg::readHumidity(){
    return m_sht20.readHumidity();
}

float BoxAg::readLuminosity(){
    float lux;
    m_capteurLux.getALSLux(lux);
    return lux;
}

/**
 * used to do send pathc requests when the values to change are float
 * */

bool BoxAg::patchRequestSensor(String url,float value,String field){
  StaticJsonDocument<200> json;
  String JsonParameters;
  uint8_t status;


  json[field] = value;
  serializeJson(json,JsonParameters);


  m_http.begin(url);
  m_http.addHeader("Content-Type","application/json");
  status = m_http.PATCH(JsonParameters);
  m_http.end();

  return status ==200;
}

/**
 * used to do send pathc requests when the values to change are strings
 * */

bool BoxAg::patchRequest(String url,String value,String field){
  StaticJsonDocument<200> json;
  String JsonParameters;

  uint8_t status;

  json[field] = value;
  serializeJson(json,JsonParameters);
  m_http.begin(url);
  m_http.addHeader("Content-Type","application/json");
  status = m_http.PATCH(JsonParameters);
  m_http.end();

  return status ==200;
}

/**
 * used to update sensors readings at database
 * */

void BoxAg::updateSensorsState(){
    patchRequestSensor("http://192.168.1.160:8055/items/Humidity_sensor/"+String(m_humiditySensorId),readHumidity(),"value");
    patchRequestSensor("http://192.168.1.160:8055/items/Light_Sensor/"+String(m_lightSensorId),readLuminosity(),"value");
    patchRequestSensor("http://192.168.1.160:8055/items/Temperature_sensor/"+String(m_temperatureSensorId),readTemperature(),"value");
}


/**
 * used to inform box address ip that way server know the box by this ip address
 * */
void BoxAg::sendIpToServer(){
    patchRequest("http://192.168.1.160:8055/items/BoxAG/"+id,WiFi.localIP().toString(),"IpAdress");
}


/**
 * used to get actuactors and sensors id to use with rest protocol requests
 * It will run only one time
 * */

void BoxAg::getMembersId(){
    StaticJsonDocument<350> JSONBuffer;
    m_http.begin("http://192.168.1.160:8055/items/BoxAG/"+id+"?fields=humidity.id,temperature.id,light.id,pump.id,lamp.id");
    m_http.GET();
    deserializeJson(JSONBuffer,m_http.getString());
    m_humiditySensorId = (uint8_t)JSONBuffer["data"]["humidity"][0]["id"];
    m_temperatureSensorId = (uint8_t)JSONBuffer["data"]["temperature"][0]["id"];
    m_lightSensorId = (uint8_t)JSONBuffer["data"]["light"][0]["id"];
    m_lamp.setId((uint8_t)JSONBuffer["data"]["lamp"][0]["id"]);
    m_pump.setId((uint8_t)JSONBuffer["data"]["pump"][0]["id"]);     
}


void BoxAg::getRequestFromServer(){
  
  /**
   * When server asks to turn pump opn
   * */
   
    m_server.on("/pumpOn", HTTP_POST, 
    
    [](AsyncWebServerRequest * request) {
        request->send(200, "ok");
        BoxAg box;
        box.setPumpState(true);
        Serial.println("PumpOn");
  }
  
  );


  

 

  /**
   * When server asks to turn pump off
   * */

    m_server.on("/pumpOFF", HTTP_POST, [](AsyncWebServerRequest * request) {
        request->send(200, "ok");
        BoxAg box;
        box.setPumpState(false);
        Serial.println("PumpOFF");
        
  });

    AsyncCallbackJsonWebHandler *handler = new AsyncCallbackJsonWebHandler("/lampLight", [](AsyncWebServerRequest *request, JsonVariant &json) {
    StaticJsonDocument<200> data;
    request->send(200, "ok");
    BoxAg box;
    if (json.is<JsonArray>())
    {
      data = json.as<JsonArray>();
    }
    else if (json.is<JsonObject>())
    {
      data = json.as<JsonObject>();
    }  
    box.setLampLuminosity(data["value"]);  
  });
    m_server.addHandler(handler);
    m_server.begin();
}

void BoxAg::blinkLed(){
    static unsigned long previousTime = 0;

    if(millis()-previousTime>=4000){
         previousTime = millis();
         digitalWrite(LED,digitalRead(LED)^1);
         updateSensorsState();
    }
    if(millis()<previousTime) previousTime = 0;
}


void BoxAg::watPlant(){
    Serial.println(m_moistureSensor.getMoisture());
    if(m_moistureSensor.getMoisture()>=30)
        m_pump.setState(false);
}

bool BoxAg::isWatering(){
    return false; //digitalRead(17);
}



