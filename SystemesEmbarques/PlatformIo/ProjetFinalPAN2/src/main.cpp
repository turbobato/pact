#include <Arduino.h>
#include <Adafruit_NeoPixel.h>    
#include "WiFiManager.h"
WiFiManager wifiManager;
#define WEBSERVER_H
#include "ESPAsyncWebServer.h"
#include <BoxAg.hpp>

BoxAg boxAg;



void setup()
{
  Serial.begin(9600);
  boxAg.configSensors();
  boxAg.connect();          // conect the box to the internet
  boxAg.getMembersId();    // get all box elements id from database
  boxAg.sendIpToServer();  // 
  boxAg.getRequestFromServer();
  //boxAg.timeConfig();
}


void loop()
{
  boxAg.boxLoop();
  boxAg.watPlant();
  boxAg.giveLux();
  
}
