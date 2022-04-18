package Actuator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pump {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String name;
    private int state;


    // Serializing and deserializing
    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public Pump deserialization(String jsonInput){
        Pump pump = new Gson().fromJson(jsonInput,Pump.class);
        return pump;
    }

    // Post

    // Post
    public void sendPost(String url, String name, int state) throws Exception {

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpClient.setRequestProperty("Accept", "application/json");
        String jsonInput = "{\"name\": \""+name+"\",\"state\" : \""+state+"\"}";

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

    // Getters and Setters


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

    public int getState() {
        return state;
    }

    // The user can set only the name and the state

    public void setName(String name) {
        this.name = name;
    }

    public void setState(int state) {
        this.state = state;
    }
}
