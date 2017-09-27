package simpleserver;

import org.omg.CORBA.PUBLIC_MEMBER;
import simpleserver.Comments;
import simpleserver.User;
import simpleserver.Posts;

public class JsonData {
    User[] user;
    Posts[] posts;
    Comments[] comments;

    public JsonData(User[] user, Posts[] posts, Comments[] comments){
        this.user = user;
        this.posts = posts;
        this.comments = comments;
    }


}
