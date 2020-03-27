package com.example.tutorial1.model;

public class UserModel {
    public String userName;
    public String userEmail;
    public String userPassWord;
    public String uid;

    public UserModel(String userName,String userEmail, String userPassWord,String uid) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassWord = userPassWord;
        this.uid = uid;
    }

    public UserModel(String userName,String userEmail, String userPassWord) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassWord = userPassWord;
    }

    public UserModel() {

    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public String getUid() {
        return uid;
    }
}
