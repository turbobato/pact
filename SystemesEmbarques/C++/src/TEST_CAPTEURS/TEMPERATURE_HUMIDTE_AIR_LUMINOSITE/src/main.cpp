#include <Arduino.h>
#include<DFRobot_SHT20.h>         //BIBLIOTHEQUE CAPTEUR SHT20 (TEMPERATURE ET HUMIDIYE DE L'AIR)
#include "DFRobot_VEML7700.h"     // BIBLIOTHEQUE CAPTEUR DE LUMINOSITE

//LED QUI INDIQUE L'ETAT DE LA CARTE ESP32
#define LED 2
//FONCTION POUR CLIGNOTER LA LED
void blinkLED(int temps){
  digitalWrite(LED,digitalRead(LED)^1);
  delay(temps);
}


/****************************************************************
CONFIGURATIONS DU CAPTEUR STH20 (TEMPERATURE+HUMIDITE DE L'AIR***
*****************************************************************/

/**
 * Hardware Connections:
 * -VCC = 3.3V
 * -GND = GND
 
 * -SDA = 21 (ESP32) 
 * -SCL = 22 (ESP32)
 */

DFRobot_SHT20 sht20(&Wire, SHT20_I2C_ADDR);

void ConfigHumiditTemperature(){
  sht20.initSHT20(); // Init SHT20 Sensor
}

/**************************************************************
 *FIN CONFIGURETIONS DU CAPTEUR STH20**************************
 * ************************************************************/



/**************************************************************
*FONCTIONS DE LECTURE DES GRANDEURS ISSUES DU CAPTEUR**********
***************************************************************/

//LIRE LA TEMPERATURE
float ReadTemperature(){
  return sht20.readTemperature();
}

// LIRE L'HUMIDITE DE L'AIR
float ReadAireHumidite(){
  return sht20.readHumidity();
}


/*********************************************************************
*FIN DES FONCTIONS DE LECTURE DES GRANDEURS ISSUES DU CAPTEUR STH20***
**********************************************************************/



/********************************************************************
CONFIGURATIONS DU CAPTEUR  DE LUMINOSITE*****************************
*********************************************************************/

/**
 * Hardware Connections:
 * -VIN = 3.3V
 * -GND = GND
 
 * -SDA = 21 (ESP32) 
 * -SCL = 22 (ESP32)
 */

DFRobot_VEML7700 CapteurLux;

void ConfigCapteurLum(){
  CapteurLux.begin(); ; // Init SHT20 Sensor
}

/**************************************************************
 *FIN CONFIGURETIONS DU CAPTEUR STH20**************************
 * ************************************************************/



/**************************************************************
*FONCTIONS DE LECTURE DES GRANDEURS ISSUES DU CAPTEUR**********
***************************************************************/

//LIRE LA LUMINOSITE
float ReadLuminosite(){
  float lux;
  CapteurLux.getALSLux(lux);   // MESURE DE L'HUMIDITE
  return lux;
}


/*********************************************************************
*FIN DES FONCTIONS DE LECTURE DES GRANDEURS ISSUES DU CAPTEUR STH20***
**********************************************************************/

void setup()
{
//Configuration du capteur de Luminosité y compris initialization  
ConfigHumiditTemperature();

//Configuration du capteur de Humidite de l'air et temperature y compris initialization  
ConfigCapteurLum();

// Definir la broche a laquelle la LED est connectée comme une sortie
pinMode(LED, OUTPUT);

//Initialization de la Serie avec u BAUD RATE DE 9600bit/s
Serial.begin(9600);
}





void loop()
{
  
  
  Serial.print("Temperature  = ");
  Serial.print(ReadTemperature());
  Serial.print("°C   ");
  Serial.print("Humidite l'air  = ");
  Serial.print(ReadAireHumidite());
  Serial.print("%    ");
  Serial.print("Luminosite  = ");
  Serial.print(ReadLuminosite());
  Serial.println("Lux    ");


  blinkLED(100);

}