package com.example.tutorial1.Adapter;

public class Mon1_1_Item {

    private String comment;
    private String user;
    private String time;

    public Mon1_1_Item(String comment, String user, String time) {
        this.comment = comment;
        this.user = user;
        this.time = time;
    }

    public Mon1_1_Item(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
