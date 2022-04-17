#include "BoxAg.hpp"
#include "HTTPClient.h"
#include "ArduinoJson.h"
#include <Arduino.h>

using namespace std;
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
  HTTPClient http;
  StaticJsonDocument<200> json;
  String JsonParameters;
  int status;


  json[field] = value;
  serializeJson(json,JsonParameters);


  http.begin(url);
  http.addHeader("Content-Type","application/json");
  status = http.PATCH(JsonParameters);
  http.end();

  return status ==200;
}

/**
 * used to do send pathc requests when the values to change are strings
 * */

bool BoxAg::patchRequest(String url,String value,String field){
  HTTPClient http;
  StaticJsonDocument<200> json;
  String JsonParameters;

  int status;

  json[field] = value;
  serializeJson(json,JsonParameters);


  http.begin(url);
  http.addHeader("Content-Type","application/json");
  status = http.PATCH(JsonParameters);
  http.end();

  return status ==200;
}

/**
 * used to update sensors readings at database
 * */

void BoxAg::updateSensorsState(){
    patchRequestSensor("http://192.168.0.13:8055/items/Humidity_sensor/"+m_humiditySensorId,readHumidity(),"value");
    patchRequestSensor("http://192.168.0.13:8055/items/Light_Sensor/"+m_lightSensorId,readLuminosity(),"value");
    patchRequestSensor("http://192.168.0.13:8055/items/Temperature_sensor/"+m_temperatureSensorId,readTemperature(),"value");
}


/**
 * used to inform box address ip that way server know the box by this ip address
 * */
void BoxAg::sendIpToServer(){
    patchRequest("http://192.168.0.13:8055/items/BoxAG/"+id,WiFi.localIP().toString(),"IpAdress");
}


/**
 * used to get actuactors and sensors id to use with rest protocol requests
 * It will run only one time
 * */

void BoxAg::getMembersId(){
    HTTPClient http;
    StaticJsonDocument<350> JSONBuffer;
    http.begin("http://192.168.0.13:8055/items/BoxAG/"+id+"?fields=humidity.id,temperature.id,light.id,pump.id,lamp.id");
    http.GET();
    deserializeJson(JSONBuffer,http.getString());
    m_humiditySensorId = (uint8_t)JSONBuffer["data"]["humidity"][0]["id"];
    m_temperatureSensorId = (uint8_t)JSONBuffer["data"]["temperature"][0]["id"];
    m_lightSensorId = (uint8_t)JSONBuffer["data"]["light"][0]["id"];
    m_lamp.setId((uint8_t)JSONBuffer["data"]["lamp"][0]["id"]);
    m_pump.setId((uint8_t)JSONBuffer["data"]["pump"][0]["id"]);     
}

