package forumapp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Forum {

    private URL url_BDD;
    private ArrayList<Topic> topics;


    public Forum(String url){
        try {
            this.url_BDD = new URL(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URL getUrl_BDD(){
        return url_BDD;
    }

    public void setUrl_BDD(URL url_BDD){
        this.url_BDD = url_BDD;
    }

}