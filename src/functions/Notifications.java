package functions;

import userModules.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Notifications {
    private User user;
    private String message;
    private boolean isRead;

    private HashMap<String, ArrayList<Notifications>> userNotifications = new HashMap<>();

    public Notifications(User user, String message){
        this.user = user;
        this.message = message;

    }

    public void addNotification(String email, Notifications notification){

    }
    public User getUser(){
        return user;
    }
    public void setUser(User u){
        user = u;
    }

    public String getMessage(){
        return message;
    }
    public void setString(String m){
        message = m;
    }

    public boolean getIsRead() {
        return isRead;
    }

    // set this to true when the message is read
    public void setIsRead(){
        isRead = true;
    }

}
