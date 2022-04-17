#pragma once
#include<Arduino.h>
class Pump
{
private:
    bool state;
    uint8_t m_id;
public:
    void setState(bool state);
    void configPump();
    uint8_t getId();
    void setId(uint8_t id);
    
};


