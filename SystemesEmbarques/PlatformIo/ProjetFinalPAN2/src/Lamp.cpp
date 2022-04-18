#include "Lamp.hpp"
#include <Arduino.h>
#define lampPin 4
#define channel 0
#define freq 500
#define resolution 8

void Lamp::setLuminosity(int luminosity){
    ledcWrite(channel,luminosity);
}

void Lamp::configLamp(){ 
    ledcSetup(channel,freq,resolution);  
    pinMode(lampPin,0x02);   //GPIO 17 as output
    ledcAttachPin(lampPin,0);// attach lampPin to channel 0
    setLuminosity(0);
}

uint8_t Lamp::getId(){
    return m_id;
}

void Lamp::setId(uint8_t id){
    m_id = id;
}