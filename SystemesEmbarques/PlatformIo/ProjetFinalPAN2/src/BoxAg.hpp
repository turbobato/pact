#pragma once

#include<DFRobot_SHT20.h>  
#include <Arduino.h>      
#include "DFRobot_VEML7700.h"
#include "SoilMoistureSensor.hpp"
#include "WiFiManager.h"


class BoxAg {

    public:
    void updateSensorsState();
    void setLampLuminosity();
    void setPumpState();
    bool isConnected();
    void connect();
    void restart();
    void configSensors();
    float readTemperature();
    float readHumidity();
    float readLuminosity();

    private:
    DFRobot_SHT20 m_sht20 {&Wire, SHT20_I2C_ADDR};
    DFRobot_VEML7700 m_capteurLux;
    bool patchRequest(char* url,float value,String field);
    bool m_internetState;
    WiFiManager m_wifiManager;
};
