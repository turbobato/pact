#include "BoxAg.hpp"
#include "HTTPClient.h"
#include "ArduinoJson.h"

using namespace std;

bool BoxAg::isConnected(){
    return m_internetState;
}

void BoxAg::connect(){
    WiFi.mode(WIFI_STA); // explicitly set mode, esp defaults to STA+AP
    m_internetState = m_wifiManager.autoConnect("MY GARDEN","password");
}

void BoxAg::setLampLuminosity(){

}

void BoxAg::setPumpState(){

}

void BoxAg::updateSensorsState(){
  
}

void BoxAg::restart(){
    m_wifiManager.resetSettings();
    ESP.restart();
}

void BoxAg::configSensors(){
    m_sht20.initSHT20();
    m_capteurLux.begin();
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

bool BoxAg::patchRequest(char* url,float value,String filed){
  HTTPClient http;
  StaticJsonDocument<200> json;
  String JsonParameters;
  int status;

  json[filed] = value;
  serializeJson(json,JsonParameters);


  http.begin(url);
  http.addHeader("Content-Type","application/json");
  status = http.PATCH(JsonParameters);
  http.end();

  return status ==200;
}

