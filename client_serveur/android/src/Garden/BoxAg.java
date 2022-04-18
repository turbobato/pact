package Garden;

import Actuator.Lamp;
import Actuator.Pump;
import Sensors.HumiditySensor;
import Sensors.LightSensor;
import Sensors.TemperatureSensor;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BoxAg {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String name;
    private HumiditySensor humidity;
    private LightSensor light;
    private TemperatureSensor temperature;
    private Lamp lamp;
    private Pump pump;

    // to create a boxAG we use the constructor BoxAG(), and then use setters to do whatever we want

    // Serializing and deserializing
    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public BoxAg deserialization(String jsonInput){
        BoxAg boxAg = new Gson().fromJson(jsonInput,BoxAg.class);
        return boxAg;
    }

    // Post

    // Post
    public void sendPost(String url, String name, HumiditySensor humidity, LightSensor light, TemperatureSensor temperature, Lamp lamp, Pump pump ) throws Exception {

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpClient.setRequestProperty("Accept", "application/json");
        String jsonInput = "{\"name\": \""+name+"\",\"humidity\": \""+humidity+"\",\"light\": \""+light+"\",\"temperature\": \""+temperature+"\",\"lamp\": \""+lamp+"\",\"pump\": \""+pump+"\"}";

        // Send post request
        httpClient.setDoOutput(true);

        try(OutputStream os = httpClient.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println(responseCode);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream(),"utf-8"))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }

    }
    // Getters
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getSort() {
        return sort;
    }

    public String getUser_created() {
        return user_created;
    }

    public String getUser_updated() {
        return user_updated;
    }

    public String getName() {
        return name;
    }

    public HumiditySensor getHumiditySensor() {
        return humidity;
    }

    public LightSensor getLightSensor() {
        return light;
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperature;
    }

    public Lamp getLamp() {
        return lamp;
    }

    public Pump getPump() {
        return pump;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setHumiditySensor(HumiditySensor humidity) {
        this.humidity = humidity;
    }

    public void setLightSensor(LightSensor light) {
        this.light = light;
    }

    public void setTemperatureSensor(TemperatureSensor temperature) {
        this.temperature = temperature;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    public void setPump(Pump pump) {
        this.pump = pump;
    }


}
