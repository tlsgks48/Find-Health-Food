package com.example.tutorial1.TestRecyclerview;

public class Dictionary {

    private String id;
    private String English;
    private String Korean;

    public Dictionary(String id, String english, String korean) {
        this.id = id;
        English = english;
        Korean = korean;
    }

    public String getId() {
        return id;
    }

    public String getEnglish() {
        return English;
    }

    public String getKorean() {
        return Korean;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public void setKorean(String korean) {
        Korean = korean;
    }
}
