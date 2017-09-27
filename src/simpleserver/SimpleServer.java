package simpleserver;

import com.google.gson.Gson;
import javafx.geometry.Pos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;


class SimpleServer {
    //readme
    public static void main(String[] args) throws IOException {
        ServerSocket ding;
        Socket dong = null;
        String resource = null;
        try {
            ding = new ServerSocket(1299);
            System.out.println("Opened socket " + 1299);
            while (true) {

                String responseString = "";

                // keeps listening for new clients, one at a time
                try {
                    dong = ding.accept(); // waits for client here
                } catch (IOException e) {
                    System.out.println("Error opening socket");
                    System.exit(1);
                }

                InputStream stream = dong.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                try {
                    System.out.println("----------REQUEST START---------");

                    // read the first line to get the request method, URI and HTTP version
                    String line = in.readLine();

                    if (line.contains("/user")) {
                        Gson gson = new Gson();
                        JsonData data = gson.fromJson(new FileReader("./datafile.json"), JsonData.class); //move to top

                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        ResponseUsers response = new ResponseUsers("ok", time.toString(), data.user.length, data.user);
                        responseString = gson.toJson(response);
                    } else if (line.contains("/posts")) {
                        Gson gson = new Gson();
                        JsonData data = gson.fromJson(new FileReader("./datafile.json"), JsonData.class);

                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        ResponsePosts response = new ResponsePosts("ok", time.toString(), data.posts.length, data.posts);
                        responseString = gson.toJson(response);

                    } else if (line.contains("/posts?userid")) {
                        Gson gson = new Gson();
                        JsonData data = gson.fromJson(new FileReader("./datafile.json"), JsonData.class);

                        int index = line.indexOf("=");
                        String userid = line.substring(index+1, line.length()-1);
                        int useridInt = Integer.parseInt(userid);

                        Posts[] posts = data.posts;
                        Posts[] newPosts = new Posts[posts.length];
                        int count = 0;
                        for(int i= 0; i<posts.length; i++){
                            if(useridInt == posts[i].userid){
                                newPosts[count] = posts[i];
                                count++;
                            }

                        }
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        ResponsePosts response = new ResponsePosts("ok", time.toString(), newPosts.length, newPosts);
                        responseString = gson.toJson(response);

                    } else if (line.contains("/comments")) {
                        Gson gson = new Gson();
                        JsonData data = gson.fromJson(new FileReader("./datafile.json"), JsonData.class);

                        int index = line.indexOf("=");
                        String postid = line.substring(index+1, line.length()-1);
                        int postidInt = Integer.parseInt(postid);

                        Posts[] posts = data.posts;
                        Posts[] comments = new Posts[posts.length];
                        int count = 0;
                        for(int i= 0; i<posts.length; i++) {
                            if (postidInt == posts[i].postid) {
                                comments[count] = posts[i];
                                count++;
                            }
                        }
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        ResponsePosts response = new ResponsePosts("ok", time.toString(), comments.length, comments);
                        responseString = gson.toJson(response);

                    }

//
                System.out.println("----------REQUEST END---------\n\n");
            } catch(IOException e){
                System.out.println("Error reading");
                System.exit(1);
            }

            BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
            PrintWriter writer = new PrintWriter(out, true);  // char output to the client

            // every response will always have the status-line, date, and server name
            writer.println("HTTP/1.1 200 OK");
            writer.println("Server: TEST");
            writer.println("Connection: close");
            writer.println("Content-type: text/html");
            writer.println("");


            // Body of our response
            writer.println(responseString);

            dong.close();
        }
    } catch(
    IOException e)

    {
        System.out.println("Error opening socket");
        System.exit(1);
    }
}
}
