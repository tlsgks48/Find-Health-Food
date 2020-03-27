package com.example.tutorial1.GsonTest;

import com.google.gson.annotations.SerializedName;

public class User_Info {
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("name")
    private String mName;

    public User_Info(String email, String password, String name) {
        mEmail = email;
        mPassword = password;
        mName = name;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmName() {
        return mName;
    }

    public String getmPassword() {
        return mPassword;
    }
}
