package Requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteRequest {
    public static void main(String argc[]) throws IOException {
        DeleteRequest obj = new DeleteRequest();
        System.out.println("Testing 2 - Send Http DELETE request");
        obj.deleteItem("192.168.1.160","BoxAG", 1);
    }
    public void deleteItem(String ipAddress,String collection,  int id) throws IOException {
        String url="http://"+ipAddress+":8055/items/"+collection+"/"+id;
        HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
        http.setRequestMethod("DELETE");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
}
