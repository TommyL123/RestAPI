package simpleserver;

public class Response {
    String status;
    String timeStamp;
    int entries;
    User[] users;

    public Response(String status, String timeStamp, int entries, User[] users){
        this.status = status;
        this.timeStamp = timeStamp;
        this.entries = entries;
        this.users = users;
    }
}
