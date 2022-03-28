public class MainTest {
    
    public static void main(String[] args){
        ForumTest forum = new ForumTest("http://192.168.1.101:8055/items/Messages");
        forum.postMessage("Bonjour Aymane !", "Guilhem JAZERON");
    }

}
