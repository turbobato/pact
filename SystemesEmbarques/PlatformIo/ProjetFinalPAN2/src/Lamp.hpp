#pragma once
#include <Arduino.h>

class Lamp{

    public:
    void configLamp();
    void setLuminosity(int luminosity);
    uint8_t getId();
    void setId(uint8_t id);
    private:
    float m_luminosity;
    uint8_t m_id;

};
