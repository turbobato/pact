#include "Pump.hpp"
#include<Arduino.h>
#define pump 32
#define channel 1
#define freq 30000
#define resolution 8
#define velocity 140

/**
 *DEVKIT V4 (HUZZAH)
 * Hardware : 23 (17) (PWM)
 * ULN 2803A: 10 (12V), 9 (GND), 1 (23(17) (PWM))
 * 
*/

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