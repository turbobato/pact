package Sensors;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LightSensor {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String name;
    private float value;
    private int state;


    // Serializing and deserializing

    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public LightSensor deserialization(String jsonInput){
        LightSensor lightSensor = new Gson().fromJson(jsonInput,LightSensor.class);
        return lightSensor;
    }

    public LightSensor sendGet() throws Exception {
        String url = "http://192.168.1.160:8055/items/LightSensor";
        String httpsURL = url;
        URL myUrl = new URL(httpsURL);
        URLConnection conn = myUrl.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr); // br is the Json

        String inputLine;
        String inputJson = "";
        while ((inputLine = br.readLine()) != null) {
            inputJson = inputJson + inputLine;
        }
        //System.out.println(inputJson);
        br.close();
        // now we want to erase the term data that is in the beginning of the jsonInput
        int n = inputJson.length();
        String finalJson = "";
        for (int i=9;i<n-2;i++){
            finalJson =  finalJson+inputJson.charAt(i);
        }
        LightSensor lightSensor = new LightSensor();
        lightSensor.deserialization(finalJson);
        return lightSensor;
    }
    // Getters and Setters
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getState() {
        return state;
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

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setState(int state) {
        this.state = state;
    }
}
