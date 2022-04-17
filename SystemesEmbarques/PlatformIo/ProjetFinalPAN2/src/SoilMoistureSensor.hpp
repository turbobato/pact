#pragma once
#include<Arduino.h>
class SoilMoistureSensor
{
private:
    uint8_t m_id;
public:
    float getMoisture();
    void setId(uint8_t id);
    uint8_t getId();
};


