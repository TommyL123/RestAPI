package simpleserver;

public class ResponseUsers {
    String status;
    String timeStamp;
    int entries;
    User[] users;

    public ResponseUsers(String status, String timeStamp, int entries, User[] users){
        this.status = status;
        this.timeStamp = timeStamp;
        this.entries = entries;
        this.users = users;
    }
}
