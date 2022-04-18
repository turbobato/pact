package forumapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import com.google.gson.Gson;

public class ForumTest {

    private URL url;

    public ForumTest(String url){
        try {
            this.url = new URL(url);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void setUrl(URL url){
        this.url = url;
    }

    public URL getUrl(){
        return url;
    }
    //posts the string message as message, user as String user 
    public void postMessage(String message, String user){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        String date = dtf.format(now);
        try {
            HttpURLConnection con = (HttpURLConnection) getUrl().openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            String jsonInputString = "{\"user\": \""+user+"\",\"date\" : \""+date+"\",\"message\" : \""+message+"\"}";
            con.connect();
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getMessage(){
        try {
            HttpURLConnection con = (HttpURLConnection) getUrl().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    
    }

}