#include <Arduino.h>
#include <Adafruit_NeoPixel.h>    
#include "WiFiManager.h"
WiFiManager wifiManager;
#define WEBSERVER_H
#include "ESPAsyncWebServer.h"
#include <BoxAg.hpp>

BoxAg boxAg;
#define RED_PLANTE 19
#define GREEN_PLANTE 17
#define BLUE_PLANTE  18


void setup()
{
  Serial.begin(9600);
  boxAg.configSensors();
  boxAg.connect();
  boxAg.getMembersId();
  boxAg.sendIpToServer();
  boxAg.getRequestFromServer();
}


void loop()
{
  boxAg.blinkLed();
}
