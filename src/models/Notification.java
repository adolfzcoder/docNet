package models;

import storage.SystemManager;

import java.util.ArrayList;

public class Notification {
    private User user;
    private String message;
    private boolean isRead;

    private static ArrayList<Notification> usersNotifications = new ArrayList<>();

    public Notification(User user, String message){
        this.user = user;
        this.message = message;

    }

    public void addNotification(Notification notification){
        SystemManager.addNotification(notification);
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