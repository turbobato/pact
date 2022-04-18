package forumapp;

public class MainTest {
    
    public static void main(String[] args){
        ForumPost post = new ForumPost("Salut je teste encore dernier ","Guilhem Jazeron","http://137.194.149.210:8055/items/Messages");
        post.postMessage();
        System.out.println(post.getMessages());
    }

}
