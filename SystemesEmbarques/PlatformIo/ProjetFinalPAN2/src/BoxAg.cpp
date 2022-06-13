#include "BoxAg.hpp"
#include "HTTPClient.h"
#include <Arduino.h>
#include <AsyncTCP.h>
#include <ESPAsyncWebServer.h>
#include <AsyncJson.h>
#define pump 
#define ledInternet 18
#define global_restart 39
#define seuil 30
#define ledOnOff 17
/**
 * The ID of the box
 * Hardware : (I2C)
 * LED (GPIO 2)
 * */
String id ="12"; 
bool mode = false;
byte humMax = 0;
byte humMin = 0;;
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
    digitalWrite(ledOnOff,1);
    WiFi.mode(WIFI_STA); // explicitly set mode, esp defaults to STA+AP
    m_internetState = m_wifiManager.autoConnect("MY GARDEN","password");
    digitalWrite(16,1);         //used to synchronise
    Serial.println(WiFi.localIP());
}

void BoxAg::setLampLuminosity(int luminosity){
     m_lamp.setLuminosity(luminosity);
}

void BoxAg::setPumpState(bool state){
   // digitalWrite(LED,state);
    m_pump.setState(state);
}


void BoxAg::restart(){
    m_wifiManager.resetSettings();
    digitalWrite(ledInternet,0);
    ESP.restart();
}

void BoxAg::configSensors(){
    pinMode(16,OUTPUT);
    pinMode(ledOnOff,OUTPUT);
    pinMode(global_restart,INPUT);
    m_sht20.initSHT20();
    m_capteurLux.begin();
    m_lamp.configLamp();
    m_pump.configPump();
    pinMode(ledInternet,OUTPUT);
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
  m_http.addHeader("Authorization","Bearer aaaaax"); 
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
  m_http.addHeader("Authorization","Bearer aaaaax");
  m_http.addHeader("Content-Type","application/json");
  status = m_http.PATCH(JsonParameters);
  m_http.end();

  return status ==200;
}

/**
 * used to update sensors readings at database
 * */

void BoxAg::updateSensorsState(){
    patchRequestSensor("http://192.168.1.160:8055/items/Humidity_Air_Sensor/"+String(m_humiditySensorId),readHumidity(),"value");
    patchRequestSensor("http://192.168.1.160:8055/items/Light_Sensor/"+String(m_lightSensorId),readLuminosity(),"value");
    patchRequestSensor("http://192.168.1.160:8055/items/Temperature_sensor/"+String(m_temperatureSensorId),readTemperature(),"value");
    patchRequestSensor("http://192.168.1.160:8055/items/Humidity_Soil_Sensor/"+String(m_humiditySoilId),m_moistureSensor.getMoisture(),"value");
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
    m_http.begin("http://192.168.1.160:8055/items/BoxAG/"+id+"?fields=humidity_air,temperature,light,pump,lamp,humidity_soil");
    m_http.addHeader("Authorization","Bearer aaaaax");
    m_http.GET();
    deserializeJson(JSONBuffer,m_http.getString());
    m_humiditySensorId = (uint8_t)JSONBuffer["data"]["humidity_air"]; //[0]["id"];
    m_temperatureSensorId = (uint8_t)JSONBuffer["data"]["temperature"]; // [0]["id"];
    m_lightSensorId = (uint8_t)JSONBuffer["data"]["light"]; //[0]["id"];
    m_lamp.setId((uint8_t)JSONBuffer["data"]["lamp"]); // [0]["id"]);
    m_pump.setId((uint8_t)JSONBuffer["data"]["pump"]); //[0]["id"]); 
    m_humiditySoilId =   (uint8_t)JSONBuffer["data"]["humidity_soil"]; //[0]["id"];  

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


  AsyncCallbackJsonWebHandler *handlerMode = new AsyncCallbackJsonWebHandler("/changeMode", [](AsyncWebServerRequest *requestMode, JsonVariant &json) {
    StaticJsonDocument<200> data;
    requestMode->send(200, "ok");
    
    if (json.is<JsonArray>())
    {
      data = json.as<JsonArray>();
    }
    else if (json.is<JsonObject>())
    {
      data = json.as<JsonObject>();
    }  
    humMax = data["humidityMax"] ;
    humMin = data["humMin"];
    mode=data["mode"];
    Serial.print("humMax = ");Serial.print(humMax);
    Serial.print("humMin = ");Serial.print(humMin);
    
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
    m_server.addHandler(handlerMode);
    m_server.begin();
}

void BoxAg::blinkLed(){
    static unsigned long previousTime = 0;
    static unsigned long previousTimeLed =0;   //used to 
    

    if(millis()-previousTime>=4000){
         previousTime = millis();
         updateSensorsState();
    }

     if(millis()-previousTimeLed>=50){
         previousTimeLed = millis();
         if(WiFi.status() == WL_CONNECTED)
            digitalWrite(ledInternet,digitalRead(ledInternet)^1);
        else
            digitalWrite(ledInternet,0);
    }

     if(digitalRead(global_restart)) restart();


    if(millis()<previousTime) previousTime = 0;
    if(millis()<previousTimeLed) previousTimeLed = 0;
}


void BoxAg::giveLux(){
    m_mode = mode;
    if(m_mode){
        if(readLuminosity()<=20) setLampLuminosity(80); 
        else setLampLuminosity(0);
    }  
}

void BoxAg::watPlant(){
    m_humidityMax = humMax;
    m_humdityMin = humMin;
    m_mode = mode;
    if(m_mode){
        if(m_moistureSensor.getMoisture()>m_humidityMax) m_pump.setState(false);
        else
        if(m_moistureSensor.getMoisture()<m_humdityMin) m_pump.setState(true);  
/*
        if(40>m_humidityMax) m_pump.setState(false);
        else
        if(60<m_humdityMin) m_pump.setState(true);  */

    }      
}

bool BoxAg::isWatering(){
    return digitalRead(16); 
}

bool BoxAg::getMode(){
    return m_mode;
}


void BoxAg::timeConfig(){
    configTime(m_gmtOffset_sec, m_daylightOffset_sec, m_ntpServer);
}

void BoxAg::countTime(){
  
  
}