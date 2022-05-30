/*
  Rui Santos
  Complete project details at https://RandomNerdTutorials.com/esp32-cam-post-image-photo-server/
  

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files.
  

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
*/
/*#include <base64.h>*/

#include <Arduino.h>
#include <WiFi.h>
#include "soc/soc.h"
#include "soc/rtc_cntl_reg.h"
#include "esp_camera.h"
#include <base64.h>
#include <HTTPClient.h>
#include "WiFiManager.h"
#define led_flash 4
esp_err_t err;
camera_config_t config;
String boxId = "12";

WiFiManagerParameter boxAgId("my_text","BoxAG Identifier","id",2);


WiFiClient client;

// CAMERA_MODEL_AI_THINKER
#define PWDN_GPIO_NUM     32
#define RESET_GPIO_NUM    -1
#define XCLK_GPIO_NUM      0
#define SIOD_GPIO_NUM     26
#define SIOC_GPIO_NUM     27

#define Y9_GPIO_NUM       35
#define Y8_GPIO_NUM       34
#define Y7_GPIO_NUM       39
#define Y6_GPIO_NUM       36
#define Y5_GPIO_NUM       21
#define Y4_GPIO_NUM       19
#define Y3_GPIO_NUM       18
#define Y2_GPIO_NUM        5
#define VSYNC_GPIO_NUM    25
#define HREF_GPIO_NUM     23
#define PCLK_GPIO_NUM     22

const int timerInterval = 3000;    // time between each HTTP POST image
unsigned long previousMillis = 0;   // last time image was sent
WiFiManager m_wifiManager;
String sendPhoto() {
  String getAll;
  String getBody;

  camera_fb_t * fb = NULL;
  fb = esp_camera_fb_get();
  if(!fb) {
    Serial.println("Camera capture failed");
    delay(1000);
    ESP.restart();
  }
  
  size_t size = fb->len;
  String buffer = base64::encode((uint8_t *) fb->buf, fb->len); 
  String payload = "{\"camera\": \"" + buffer + "\"}";
  buffer = " ";
  HTTPClient http;
  http.begin("http://192.168.1.160:8055/items/BoxAG/12");
  http.addHeader("Content-Type", "application/json");     
  http.addHeader("Authorization","Bearer aaaaax"); 
  int response_code = http.PATCH(payload);
  Serial.print("Code  = "); Serial.println(response_code);

  esp_camera_fb_return(fb);
  return getBody;
}

/**
 * USED TO CONFIG CAM
 * */

void configCam(){
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0 = Y2_GPIO_NUM;
  config.pin_d1 = Y3_GPIO_NUM;
  config.pin_d2 = Y4_GPIO_NUM;
  config.pin_d3 = Y5_GPIO_NUM;
  config.pin_d4 = Y6_GPIO_NUM;
  config.pin_d5 = Y7_GPIO_NUM;
  config.pin_d6 = Y8_GPIO_NUM;
  config.pin_d7 = Y9_GPIO_NUM;
  config.pin_xclk = XCLK_GPIO_NUM;
  config.pin_pclk = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href = HREF_GPIO_NUM;
  config.pin_sscb_sda = SIOD_GPIO_NUM;
  config.pin_sscb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn = PWDN_GPIO_NUM;
  config.pin_reset = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.pixel_format = PIXFORMAT_JPEG;
   // init with high specs to pre-allocate larger buffers
  if(psramFound()){
    config.frame_size = FRAMESIZE_QVGA;
    config.jpeg_quality = 5;  //0-63 lower number means higher quality
    config.fb_count = 2;
  } else {
    config.frame_size = FRAMESIZE_QVGA;
    config.jpeg_quality = 5;  //0-63 lower number means higher quality
    config.fb_count = 1;
  }
}


/**
 * USED TO INIT CAM
 * */
void initCam(){
  err = esp_camera_init(&config);
  if (err != ESP_OK) {
    Serial.printf("Camera init failed with error 0x%x", err);
    delay(1000);
    ESP.restart();
  }
}



/**
 * connect the camera to internet
 * */

void connect(){
    WiFi.mode(WIFI_STA); // explicitly set mode, esp defaults to STA+AP
    m_wifiManager.addParameter(&boxAgId);
    m_wifiManager.autoConnect("CAMERA PACT'AG","password");
    Serial.print("ESP32-CAM IP Address: ");
    Serial.println(WiFi.localIP());
}

void setup() {
  configCam();
  initCam();
  pinMode(led_flash,OUTPUT);
  digitalWrite(led_flash,true);
  connect();
  WRITE_PERI_REG(RTC_CNTL_BROWN_OUT_REG, 0); 
  Serial.begin(9600);  
  sendPhoto(); 
}


void loop() {
  Serial.print("camera");
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= timerInterval) {
    sendPhoto();
    previousMillis = currentMillis;
  }
}
