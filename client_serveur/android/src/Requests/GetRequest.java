package Requests;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetRequest {

    private JSONObject sendGet(String url) throws Exception {
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

    // let's test it with a list of cities

    public static void main(String[] args) throws Exception {
        GetRequest obj = new GetRequest();
        JSONObject inputJson = obj.sendGet("http://192.168.1.160:8055/items/City");
        System.out.println("The result of the get request");

        System.out.println(inputJson.getJSONArray("data").getJSONObject(1).getString("city"));

    }
}
