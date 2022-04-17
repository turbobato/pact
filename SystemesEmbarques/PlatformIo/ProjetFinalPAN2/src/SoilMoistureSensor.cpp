#include "SoilMoistureSensor.hpp"
#include <Arduino.h>


float SoilMoistureSensor::getMoisture(){
    return analogRead(35)*100/4095;
}

uint8_t SoilMoistureSensor::getId(){
    return m_id;
}

void SoilMoistureSensor::setId(uint8_t id){
    m_id = id;
}

