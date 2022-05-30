#include "SoilMoistureSensor.hpp"
#include <Arduino.h>
#define readPin 33
/**
 * @author Groupe 5.1
 * Hardware : bleu 35 ADC, rouge : +5V, noir: GND
 * 
*/

float SoilMoistureSensor::getMoisture(){
    return analogRead(readPin)*100/4095;
}

uint8_t SoilMoistureSensor::getId(){
    return m_id;
}

void SoilMoistureSensor::setId(uint8_t id){
    m_id = id;
}

