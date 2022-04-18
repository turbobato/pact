package Requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest {

    public static void main(String[] args) throws Exception {

        PostRequest obj = new PostRequest();

        System.out.println("Testing 2 - Send Http POST request");
        obj.createPlant("192.168.1.160", "plant1",1,3,1,2);
    }

    public void createPlant(String serverIpAddress,String name, int state, int diagnosticBoxId, int waterBoxId, int lightBoxId) throws Exception {

        String jsonInput = "{\"name\": \""+name+"\",\"state\" : \""+state+"\", \"diagnosticBoxId\":\""+diagnosticBoxId+"\",\"waterBoxId\":\""+waterBoxId+"\", \"lightBoxId\":\""+lightBoxId+"\"}";
        System.out.println(jsonInput);
        sendPost("http://"+serverIpAddress+":8055/items/Plant",jsonInput);

    }

    public void createCity(String serverIpAddress, String city) throws Exception {
        String jsonInput = "{\"city\":\""+city+"\"}";
        sendPost("http://"+serverIpAddress+":8055/items/Plant",jsonInput);
    }

    private void sendPost(String url, String jsonInput) throws Exception {

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


        // Send post request
        httpClient.setDoOutput(true);

        try(OutputStream os = httpClient.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println(httpClient.getResponseCode());
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

    // create a plant





}

