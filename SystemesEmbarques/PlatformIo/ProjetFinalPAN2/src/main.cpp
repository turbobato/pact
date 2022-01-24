#include <Arduino.h>
#include<DFRobot_SHT20.h>         //BIBLIOTHEQUE CAPTEUR SHT20 (TEMPERATURE ET HUMIDIYE DE L'AIR)
#include "DFRobot_VEML7700.h"     // BIBLIOTHEQUE CAPTEUR DE LUMINOSITE

#include <Adafruit_NeoPixel.h>    // BIBLIOTHEQUE LEDS



/****************************************************************
CONFIGURATIONS LED ADRESSABLE***
*****************************************************************/
#define PIN 25  
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(3, PIN, NEO_GRB + NEO_KHZ800);
void ConfigLED(){
  pixels.begin();
}

/**************************************************************
 *FIN CONFIGURETIONS DU CAPTEUR STH20**************************
 * ************************************************************/



/**************************************************************
*FONCTIONS COMMANDE DES LEDS D'ETAT****************************
***************************************************************/


void controlLed(int red, int green, int blue, int frequence)
{ 
    pixels.setPixelColor(0, pixels.Color(red,green,blue)); 
    pixels.show(); 
    delay(frequence);
    pixels.setPixelColor(0, pixels.Color(0,0,0)); 
    pixels.show(); 
    delay(frequence); // a voir apres pour changer les delay() en timer qui ne bloque pas tout le programme, mais seulement la led
}


/**************************************************************
 *FIN FONCTION COMMANDE DES LEDS D'ETAT**************************
 * ************************************************************/



/**************************************************************
*FONCTIONS CAPTEUR D'HUMIDITE DU SOL****************************
***************************************************************/

float readSoilMoisture()
{
  return (analogRead(4)*100/4095);  // 0-30 (sec) 30-70 (ça va) (70-95)
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
  Serial.print("Lux    ");
  Serial.print("Humidi Sol =    ");
  Serial.print(readSoilMoisture());
  Serial.println("%    ");


}
