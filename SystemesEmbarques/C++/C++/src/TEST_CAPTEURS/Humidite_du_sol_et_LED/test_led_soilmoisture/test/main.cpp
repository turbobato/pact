#include <Adafruit_NeoPixel.h>
 
#define PIN 21
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(3, PIN, NEO_GRB + NEO_KHZ800);
 

enum etat  {off, notconnected, connected, warning}; 
/*cette valeur sera commandée depuis l’application*/

typedef enum etat etat_t;




void setup()
{
pixels.begin();

}

void controlLed(int red, int green, int blue, int freq_delay) /*freq_delay = écart de clignotement en ms*/
{
	if (freq_delay==0) /*pas de clignotement*/
	{
		pixels.setPixelColor(0,pixels.Color(red, green, blue));
		pixels.show();
	}
	else
	{
		pixels.setPixelColor(0,pixels.Color(red, green, blue));
		pixels.show();
		delay(freq_delay);
		pixels.setPixelColor(0,pixels.Color(0, 0, 0));
		pixels.show();
		delay(freq_delay);
	}
}



void loop()
{
/*ajouter la fonction modifiant etat depuis l’exterieur*/

	switch(etat)
	{
	case off : 
		controlLed(0,0,0,0);
		break;
	case notconnected:
		controlLed(0,0,1,1000);
		break;
	case connected:
		controlLed(0,1,0.5,0); /*peut-etre eteindre le led pour faire un fonctionnement plus durable?*/
		break;
	default:
		controlLed(1,0,0,500);
		break;
	}

}