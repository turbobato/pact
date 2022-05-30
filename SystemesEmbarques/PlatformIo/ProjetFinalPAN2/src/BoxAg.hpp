#pragma once

#include<DFRobot_SHT20.h>  
#include <Arduino.h>   
#include "HTTPClient.h" 
#include "DFRobot_VEML7700.h"
#include "SoilMoistureSensor.hpp"
#include "WiFiManager.h"
#include "Lamp.hpp"
#include "Pump.hpp"

#define WEBSERVER_H
#include <ESPAsyncWebServer.h> 


class BoxAg {

    public:
    void updateSensorsState();
    void setLampLuminosity(int luminosity$);
    void setPumpState(bool state);
    bool isConnected();
    void connect();
    void restart();
    int m_test;
    void sendIpToServer();
    void configSensors();
    float readTemperature();
    float readHumidity();
    float readLuminosity();
    byte m_humiditySensorId;
    byte m_temperatureSensorId;
    byte m_lightSensorId;
    byte m_humiditySoilId;
    byte m_humidityMax;
    byte m_humdityMin;
    Pump m_pump;
    void getMembersId();
    void getRequestFromServer();
    void blinkLed();
    void setPumpOn();
    void watPlant();
    bool isWatering();
    void countTime();
    void timeConfig();
    void giveLux();
    bool getMode();

    private:
    DFRobot_SHT20 m_sht20 {&Wire, SHT20_I2C_ADDR};
    DFRobot_VEML7700 m_capteurLux;
    Lamp m_lamp;
    SoilMoistureSensor m_moistureSensor;
    
    const char* m_ntpServer = "pool.ntp.org";
    const long  m_gmtOffset_sec = 3600;
    const int   m_daylightOffset_sec = 3600;

    bool patchRequestSensor(String url,float value, String field);
    bool patchRequest(String url,String value,String field);
    bool m_internetState;
    bool m_mode;
    WiFiManager m_wifiManager;
    HTTPClient m_http;
    AsyncWebServer m_server{80};
};
