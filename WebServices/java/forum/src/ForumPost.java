import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;

public class ForumPost {

    private String message;
    private String user;
    private Date date;
    private URL url_Post;

    public ForumPost(String message, String user, Date date, String url_Post){

        try {
            this.url_Post = new URL(url_Post);
            this.message = message;
            this.user = user;
            this.date = date;
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
}
