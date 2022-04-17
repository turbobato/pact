#pragma once

#include<DFRobot_SHT20.h>  
#include <Arduino.h>      
#include "DFRobot_VEML7700.h"
#include "SoilMoistureSensor.hpp"
#include "WiFiManager.h"
#include "Lamp.hpp"
#include "Pump.hpp"


class BoxAg {

    public:
    void updateSensorsState();
    void setLampLuminosity(int luminosity$);
    void setPumpState(bool state);
    bool isConnected();
    void connect();
    void restart();
    void sendIpToServer();
    void configSensors();
    float readTemperature();
    float readHumidity();
    float readLuminosity();
    byte m_humiditySensorId;
    byte m_temperatureSensorId;
    byte m_lightSensorId;
    void getMembersId();

    private:
    DFRobot_SHT20 m_sht20 {&Wire, SHT20_I2C_ADDR};
    DFRobot_VEML7700 m_capteurLux;
    Lamp m_lamp;
    Pump m_pump;
    SoilMoistureSensor m_moistureSensor;
    
    bool patchRequestSensor(String url,float value, String field);
    bool patchRequest(String url,String value,String field);
    bool m_internetState;
    WiFiManager m_wifiManager;
};
