#pragma once
#include<Arduino.h>
class Pump
{
private:
    bool m_state;
    uint8_t m_id;
public:
    void setState(bool state);
    void configPump();
    uint8_t getId();
    bool getState();
    void setId(uint8_t id);
    
};


