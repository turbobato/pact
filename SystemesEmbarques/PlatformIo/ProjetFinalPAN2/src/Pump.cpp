#include "Pump.hpp"
#include<Arduino.h>
#define pump 17
#define channel 1
#define freq 1000
#define resolution 8
#define velocity 140



void Pump::setState(bool state){
    m_state = state;
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

bool Pump::getState(){
    return m_state;
}