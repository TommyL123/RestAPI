package simpleserver;

public class ResponsePosts {
    String status;
    String timeStamp;
    int entries;
    Posts[] posts;

    public ResponsePosts(String status, String timeStamp, int entries, Posts[] posts){
        this.status = status;
        this.timeStamp = timeStamp;
        this.entries = entries;
        this.posts = posts;
    }
}
