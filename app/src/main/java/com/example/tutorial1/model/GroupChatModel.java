package com.example.tutorial1.model;

import java.util.HashMap;
import java.util.Map;

public class GroupChatModel {

    public String userName;
    public String message;
    public String mDate;
    public String time;

    public GroupChatModel(String userName, String message, String mDate, String time) {
        this.userName = userName;
        this.message = message;
        this.mDate = mDate;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getmDate() {
        return mDate;
    }

    public String getTime() {
        return time;
    }
}
