#include <Arduino.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BME280.h>
#include <WiFi.h>
#include <WebServer.h>
#include <aREST.h>


//CONFIGURATIONS HARDWARE
#define LED 2


//VARIABLES
//
int temperature;
int humidite;


// Associer un nom au capteur;
Adafruit_BME280 bme;
aREST rest = aREST();
/*
CONFIGURATIONS POUR LA CONNEXION INTERNET
*/

const char  *PASSWORD = "05DA0B3131";
const char * SSID = "NUMERICABLE-9729";
WiFiServer server(80);

/*
Fonctions concernant la lecture des grandeurs physiques
*/

//Lire et renvoyer la valeur de la temperature;
float readTemperature(){
  return bme.readTemperature();
}

//Lire et renvoyer la valeur de l'humidite;
float readHumidity(){
  return bme.readHumidity();
}

//Configuration des capteurs
void configCapteur(){
  bme.begin(0x77);
}


//Configurations Protocole Rest

void configRest(){

  // variables qui seront exposées dans l'API
  rest.variable("temperature",&temperature);
  rest.variable("humidite",&humidite);

  // Donner un ID et un nom à la carte 
  rest.set_name("BOX1");
  rest.set_id("1");

}

//Fonction pour initialiser le serveur

void initServer(){
  server.begin();
}  

void connectToWiFi() {
  Serial.print("Connecting to ");
  Serial.println(SSID);
  
  WiFi.begin(SSID, PASSWORD);
  
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
    // we can even make the ESP32 to sleep
  }
 
  Serial.print("Connected. IP: ");
  Serial.println(WiFi.localIP());
  initServer();
}


//Répondre aux requetes rest

void HandleRest(){
  WiFiClient client  = server.available();
  if(!client){
      return;
  }
  while (!client.available())
  {
    delay(1);
  }
  rest.handle(client);
}

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  configRest();
  connectToWiFi();
  configCapteur();
  pinMode(LED, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(LED,digitalRead(LED) ^1);
  temperature = bme.readTemperature();
  humidite = bme.readHumidity();

  Serial.print("Temperature = ");
  Serial.println(bme.readTemperature());

  Serial.print("Humidite = ");
  Serial.println(bme.readHumidity());

  delay(500);
}
