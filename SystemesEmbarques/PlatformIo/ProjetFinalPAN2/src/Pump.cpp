#include "Pump.hpp"
#include<Arduino.h>
#define pump 4

void Pump::setState(bool state){
    digitalWrite(pump,state);
}

void Pump::configPump(){
    pinMode(pump,0x02); // set GPIO 4 as output
    setState(false);
}

uint8_t Pump::getId(){
    return m_id;
}

void Pump::setId(uint8_t id){
    m_id = id;
}