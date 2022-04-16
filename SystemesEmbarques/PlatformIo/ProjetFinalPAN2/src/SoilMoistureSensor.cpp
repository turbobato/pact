#include "SoilMoistureSensor.hpp"
#include "Arduino.h"

using namespace std;

float SoilMoistureSensor::getMoisture(){
    return analogRead(35)*100/4095;
}

SoilMoistureSensor::SoilMoistureSensor(){

}
