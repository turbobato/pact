#include <Arduino.h>

#include <Adafruit_NeoPixel.h>
 
#define PIN 21
 
// When we setup the NeoPixel library, we tell it how many pixels, and which pin to use to send signals.
// Note that for older NeoPixel strips you might need to change the third parameter--see the strandtest
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(3, PIN, NEO_GRB + NEO_KHZ800);
 
void setup()
{

pixels.begin();

Serial.begin(9600);

}

void controlLed(int red, int green, int blue, int frequence)
{ 
    pixels.setPixelColor(0, pixels.Color(red,green,blue)); 
    pixels.show(); 
    delay(frequence);
    pixels.setPixelColor(0, pixels.Color(0,0,0)); 
    pixels.show(); 
    delay(frequence); // a voir apres pour changer les delay() en timer qui ne bloque pas tout le programme, mais seulement la led
}

float readSoilMoisture()
{
return analogRead(4)*900/4095; 
}


void loop()
{

controlLed(0,  255,  0, 500);



}