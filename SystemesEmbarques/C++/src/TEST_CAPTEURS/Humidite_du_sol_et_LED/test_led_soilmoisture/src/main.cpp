#include <Arduino.h>

#include <Adafruit_NeoPixel.h>
 




//***** CONFIGURATION DES LEDs D'INDICATION *****//
#define PIN 21
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(3, PIN, NEO_GRB + NEO_KHZ800);

void controlLed(int red, int green, int blue, int frequence)
{ 
    pixels.setPixelColor(0, pixels.Color(red,green,blue)); 
    pixels.show(); 
    delay(frequence);
    pixels.setPixelColor(0, pixels.Color(0,0,0)); 
    pixels.show(); 
    delay(frequence); // a voir apres pour changer les delay() en timer qui ne bloque pas tout le programme, mais seulement la led
}
 
//***********************************************//




//***** CONFIGURATION DU CAPTEUR D'HUMIDITE DU SOL *****//

float readSoilMoisture()
{
return analogRead(4)*900/4095; 
}

//******************************************************//

void setup()
{
pixels.begin();
Serial.begin(9600);
}



void loop()
{

controlLed(0,  255,  0, 500);



}