package simpleserver;

public class ResponseComments {
    String status;
    String timeStamp;
    int entries;
    Comments[] comments;

    public ResponseComments(String status, String timeStamp, int entries, Comments[] comments){
        this.status = status;
        this.timeStamp = timeStamp;
        this.entries = entries;
        this.comments = comments;
    }
}
