#include <Arduino.h>
//BIBLIOTHEQUE CAPTEUR SHT20 (TEMPERATURE ET HUMIDIYE DE L'AIR)
#include<DFRobot_SHT20.h>    
// BIBLIOTHEQUE CAPTEUR DE LUMINOSITE     
#include "DFRobot_VEML7700.h"     
// BIBLIOTHEQUE LEDS
#include <Adafruit_NeoPixel.h>    

// BIBLIOTHEQUES SERVEUR
#include <AsyncTCP.h>
#include <ESPAsyncWebServer.h>

//BIBLIOTHEQUE JSON
#include<ArduinoJson.h>


//Variable de synchronisation

float humiditySol=0;
float humidityAir=0;
float temperature=0;
float luminosity=0;

int red = 0;


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


void controlLed(int led,int red, int green, int blue)
{ 
    pixels.setPixelColor(led, pixels.Color(red,green,blue)); 
    pixels.show(); 
    //delay(frequence);
    //pixels.setPixelColor(0, pixels.Color(0,0,0)); 
    //pixels.show(); 
    //delay(frequence); // a voir apres pour changer les delay() en timer qui ne bloque pas tout le programme, mais seulement la led
}


/**************************************************************
 *FIN FONCTION COMMANDE DES LEDS D'ETAT**************************
 * ************************************************************/



/**************************************************************
*FONCTIONS CAPTEUR D'HUMIDITE DU SOL****************************
***************************************************************/

float readSoilMoisture()
{
  return (analogRead(35)*100/4095);  // 0-30 (sec) 30-70 (ça va) (70-95)
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


/**************************************************************
*CONFIGURATION CONNEXION INTERNET*******************************
***************************************************************/


//MOT DE PASS ET NOM DU RESEAU
const char* ssid = "pact51";
const char* password =  "2022Pact51!";

//Serveur sur le port 80
AsyncWebServer server(80);


/*********************************************************************
*FONCTIONS CONNEXION INTERNET ****************************************
**********************************************************************/

void connectRouter(){
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
  Serial.print("Connected to : ");
  Serial.println(WiFi.localIP());
  controlLed(1,0,100,0);
}

/*********************************************************************
*FIN DE FONCTION CONNEXION INTERNET ***********************************
**********************************************************************/


/*********************************************************************
*FONCTIONS REPONSE AUX REQUETES****************************************
**********************************************************************/

void sendMeasurements(){

  //REPONSE REQUETE DEMANDE DE LA TEMPERATURE
   server.on("/temperature", HTTP_GET, [](AsyncWebServerRequest * request) {
   request->send(200, "text/plain", "TEMPERATURE= "+String(temperature) + " Deg");
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

 
  server.begin();
}


/*********************************************************************
*FIN DE FONCTION REPONSE AUX REQUETES**********************************
**********************************************************************/


/*********************************************************************
*FONCTIONS COMMANDE DES LEDS D'ECLAIRAGE*******************************
**********************************************************************/

#define RED_PLANTE 19
#define GREEN_PLANTE 17
#define BLUE_PLANTE  18

//CONFIGURATION DES BROCHES
void configLedPlantes(){
  pinMode(RED_PLANTE ,OUTPUT);
  pinMode(GREEN_PLANTE,OUTPUT);
  pinMode (BLUE_PLANTE, OUTPUT);
}

// COMMANDE DES BROCHES
void controlPlantesLeds(bool redOn, bool greenOn, bool blueOn){
  digitalWrite(RED_PLANTE, redOn);
  digitalWrite(BLUE_PLANTE,blueOn);
  digitalWrite(GREEN_PLANTE,greenOn);
}

/*********************************************************************
*FIN DE FONCTION DE COMMANDE DES LEDS D'ECLAIRAGE ********************
**********************************************************************/

void setup()
{
//Configuration du capteur de Luminosité y compris initialization  
ConfigHumiditTemperature();

//Configuration du capteur de Humidite de l'air et temperature y compris initialization  
ConfigCapteurLum();


//Initialization de la Serie avec u BAUD RATE DE 9600bit/s
Serial.begin(9600);

connectRouter();

sendMeasurements();
configLedPlantes();
}







void loop()
{
  
  temperature = ReadTemperature();
  humidityAir = ReadAireHumidite();
  humiditySol = readSoilMoisture();
  luminosity =  ReadLuminosite();

  
  controlLed(0,red,100,100);
  if (red<100) red++;
  else red =0;

  controlPlantesLeds(true,false,false);
  delay(100);

  controlPlantesLeds(false,true,false);
  delay(100);
  controlPlantesLeds(false,false,true);
  delay(100);
  Serial.print("Temperature  = ");
  Serial.print(temperature );
  Serial.print("°C   ");
  Serial.print("Humidite l'air  = ");
  Serial.print(humidityAir);
  Serial.print("%    ");
  Serial.print("Luminosite  = ");
  Serial.print(luminosity);
  Serial.print("Lux    ");
  Serial.print("Humidi Sol =    ");
  Serial.print(humiditySol);
  Serial.println("%    ");

  
  
}
