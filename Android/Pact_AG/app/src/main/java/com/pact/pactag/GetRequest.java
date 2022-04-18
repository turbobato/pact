package com.pact.pactag;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetRequest {

    public JSONObject sendGet(String url) throws Exception {
        String httpsURL = url;
        URL myUrl = new URL(httpsURL);
        URLConnection conn = myUrl.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String inputLine;
        String inputJson = "";
        while ((inputLine = br.readLine()) != null) {
            inputJson = inputJson + inputLine;
        }
        //System.out.println(inputJson);
        br.close();

        System.out.println(inputJson);
        JSONObject jsonObject = new JSONObject(inputJson);
        return jsonObject;

    }

}



