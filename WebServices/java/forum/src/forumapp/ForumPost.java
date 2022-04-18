package forumapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ForumPost {
    
    @Expose(serialize = false, deserialize = true) private int id;
    @Expose(serialize = false, deserialize = true) private String status;
    @Expose(serialize = false, deserialize = true) private int sort;
    @Expose private String user;
    @Expose private String date;
    @Expose private String message;
    @Expose(serialize = false, deserialize = false) private URL url_Post;

    public ForumPost(String message, String user, String url_Post) {

        try {
            this.url_Post = new URL(url_Post);
            this.message = message;
            this.user = user;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            this.date = date;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public URL getURL() {
        return this.url_Post;
    }

    public void setURL(URL url_Post) {
        this.url_Post = url_Post;
    }

    public static ForumPost deserialize(String json) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(json, ForumPost.class);
    }

    public static String serialize(ForumPost post) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(post);
    }

    public int postMessage() {
        try {
            HttpURLConnection con = (HttpURLConnection) getURL().openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            String jsonInputString = serialize(this);
            System.out.println(jsonInputString);
            // String jsonInputString ="{\"user\": \""+user+"\",\"date\" :
            // \""+date+"\",\"message\" :\""+message+"\"}";
            con.connect();
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            return con.getResponseCode(); // return response code of the post method
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // in case of error, -1 is returned
        }

    }

    public ArrayList<ForumPost> getMessages() {
        ArrayList<ForumPost> res = new ArrayList<ForumPost>();
        try {
            HttpURLConnection con = (HttpURLConnection) getURL().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            String inputJson = response.toString();
            int n = inputJson.length();
            String finalJson = "";
            for (int i = 9; i < n - 2; i++) {
                finalJson = finalJson + inputJson.charAt(i);
            }
            inputJson = finalJson;
            ArrayList<String> framedJson = new ArrayList<String>();
            n = inputJson.length();
            boolean add = false;
            String intermediate = "";
            for (int i = 0; i < n; i++) {
                if (inputJson.charAt(i) == '{') {
                    add = true;
                }
                if (add == true) {
                    intermediate += inputJson.charAt(i);
                }
                if (inputJson.charAt(i) == '}') {
                    add = false;
                    framedJson.add(intermediate);
                    intermediate = "";
                }
            }
            int length = framedJson.size();
            for (int i = 0; i < length; i++) {
                ForumPost post = ForumPost.deserialize(framedJson.get(i));
                res.add(post);
            }
            System.out.println(framedJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
