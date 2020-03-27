package com.example.tutorial1;

public class Item {
     String name; // 이름
     int photo; // 이미지
     String summary; // 요약설명
     String memo; // 관심과 댓글

    public Item(String name, int photo, String summary, String memo) {
        this.name = name;
        this.photo = photo;
        this.summary = summary;
        this.memo = memo;
    }

    public String getName() { // 외부로 이름값을 리턴해서 내보내준다.
        return name;
    }

    public int getPhoto() { // 외부로 이미지 값을 리턴해서 보내준다.
        return photo;
    }

    public String getSummary() {
        return summary;
    }

    public String getMemo() {
        return memo;
    }

    public void setName(String name) { // 외부에서 받은 이름을을내부로 넣어준다.
        this.name = name;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setSummary(String summary) { // 외부에서 받은 요약설명을 내부로 넣어준다.
        this.summary = summary;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
