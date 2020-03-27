package com.example.tutorial1.Adapter;

public class MonItem {
    int check = 1;
    String monMon; // 이달
     String monName; // 이름
     int monPhoto; // 이미지
     String monSummary; // 요약설명
     String monMemo; // 관심과 댓글

    public MonItem(String monName, int monPhoto, String monSummary, String monMemo) {
        this.monName = monName;
        this.monPhoto = monPhoto;
        this.monSummary = monSummary;
        this.monMemo = monMemo;
    }
    public MonItem(String monMon) {
        this.monMon = monMon;
    }


    public String getMonMon() {
        return monMon;
    }

    public String getMonName() {
        return monName;
    }

    public int getMonPhoto() {
        return monPhoto;
    }

    public String getMonSummary() {
        return monSummary;
    }

    public String getMonMemo() {
        return monMemo;
    }

    public void setMonMon(String monMon) {
        this.monMon = monMon;
    }

    public void setMonName(String monName) {
        this.monName = monName;
    }

    public void setMonPhoto(int monPhoto) {
        this.monPhoto = monPhoto;
    }

    public void setMonSummary(String monSummary) {
        this.monSummary = monSummary;
    }

    public void setMonMemo(String monMemo) {
        this.monMemo = monMemo;
    }
}
