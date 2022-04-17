#include <Arduino.h>
//BIBLIOTHEQUE CAPTEUR SHT20 (TEMPERATURE ET HUMIDIYE DE L'AIR)
//#include<DFRobot_SHT20.h>    
// BIBLIOTHEQUE CAPTEUR DE LUMINOSITE     
//#include "DFRobot_VEML7700.h"     
// BIBLIOTHEQUE LEDS
#include <Adafruit_NeoPixel.h>    

// BIBLIOTHEQUES SERVEUR
//#include <AsyncTCP.h>


#include "WiFiManager.h"
WiFiManager wifiManager;

#define WEBSERVER_H
#include "ESPAsyncWebServer.h"

//BIBLIOTHEQUE JSON
//#include<ArduinoJson.h>
//#include <ESPAsyncWebServer.h>
//#include <AsyncJson.h>


// BIBLIOTHEQUE HTTP 

//#include <HTTPClient.h>

#include <BoxAg.hpp>

BoxAg boxAg;






/****************************************************************
CONFIGURATIONS LED ADRESSABLE***
*****************************************************************/
#define PIN 25  
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(3, PIN, NEO_GRB + NEO_KHZ800);
void ConfigLED(){
  pixels.begin();
}




/**************************************************************
*FONCTIONS COMMANDE DES LEDS D'ETAT****************************
***************************************************************/
/**
 * Hardware Connections:
 * VCC = 5V
 * GND = GND
 * PWM = 25 (ESP32) , avec R = 4700ohms (expected 330ohms)
 */





/**************************************************************
*FONCTIONS CAPTEUR D'HUMIDITE DU SOL****************************
***************************************************************/
/**
 * Hardware Connections:
 * VCC = 5V
 * GND = GND
 
 * ADC 12 bits = 35 (ESP32) 
 */



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






//Serveur sur le port 80
 AsyncWebServer server(80);


/*********************************************************************
*FONCTIONS CONNEXION INTERNET ****************************************
**********************************************************************/



/*********************************************************************
*FIN DE FONCTION CONNEXION INTERNET ***********************************
**********************************************************************/


/*********************************************************************
*FONCTIONS REPONSE AUX REQUETES****************************************
**********************************************************************/


/*
void sendMeasurements(){

  //REPONSE REQUETE DEMANDE DE LA TEMPERATURE
   server.on("/temperature", HTTP_GET, [](AsyncWebServerRequest * request) {
   request->send(200, "text/plain", "TEMPERATURE=  Deg");
   Serial.println("Reçu de directus!!");
  });
  
  //REPONSE REQUETE DEMANDE DE L'HUMIDITE DE L'AIR
  server.on("/humidityair", HTTP_GET, [](AsyncWebServerRequest * request) {

    request->send(200, "text/plain", "HUMIDITE DE L'AIR = "+String(humidityAir) + " %");
  });

  //REPONSE REQUETE DEMANDE DE L'HUMIDITÉ DU SOL
   server.on("/humiditysoil", HTTP_GET, [](AsyncWebServerRequest * request) {
   
   request->send(200, "text/plain", "HUMIDITE DU SOL = "+String(humiditySol) + "%");
  });

  //REPONSE REQUETE DEMANDE DE LA LUMINOSITÉ
   server.on("/luminosity", HTTP_GET, [](AsyncWebServerRequest * request) {
   
   request->send(200, "text/plain", "LUMINOSITE : = "+String(luminosity) + " Lux");
  });

  // REPONSE JSON CONTENANT LES VALEURS DE TOUTES LES GRANDEURS
    server.on("/allstatus", HTTP_GET, [](AsyncWebServerRequest *request){
      AsyncResponseStream *response = request->beginResponseStream("application/json");
      DynamicJsonDocument json(1024);
      json["status"] = "ON EST LA GROUPE 5.1";
      json["temperature"] = temperature;
      json["luminosité"] = luminosity;
      json["Humidité de l'air  = "] = humidityAir;
      json["Humidité du sol  = "] = humiditySol;
      serializeJson(json, *response);
      request->send(response);
    });


     //REPONSE REQUETE DEMANDE DE LA TEMPERATURE
    server.on( "/testPut",   HTTP_POST,   [](AsyncWebServerRequest * request){},   NULL,  [](AsyncWebServerRequest * request, uint8_t *data, size_t len, size_t index, size_t total) {
      Serial.println("Reçu de directus");
      request->send(200);
  });


  // TEST

  AsyncCallbackJsonWebHandler *handler = new AsyncCallbackJsonWebHandler("/post", [](AsyncWebServerRequest *request, JsonVariant &json) {
    StaticJsonDocument<200> data;
    if (json.is<JsonArray>())
    {
      data = json.as<JsonArray>();
    }
    else if (json.is<JsonObject>())
    {
      data = json.as<JsonObject>();
    }
    String response;
    serializeJson(data, response);
    Serial.print("POSTJSON");
    Serial.println(response);
  });
  server.addHandler(handler);
  server.begin();
}

*/




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
}







void loop()
{
  
}
