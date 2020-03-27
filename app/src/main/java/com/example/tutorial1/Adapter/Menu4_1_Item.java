package com.example.tutorial1.Adapter;

public class Menu4_1_Item {

    String Menu4_1Name; // 이름
    int Menu4_1Photo; // 이미지
    String Menu4_1Summary; // 요약설명
    String Menu4_1Memo; // 관심과 댓글

    public Menu4_1_Item(String menu4_1Name, int menu4_1Photo, String menu4_1Summary, String menu4_1Memo) {
        Menu4_1Name = menu4_1Name;
        Menu4_1Photo = menu4_1Photo;
        Menu4_1Summary = menu4_1Summary;
        Menu4_1Memo = menu4_1Memo;
    }

    public String getMenu4_1Name() {
        return Menu4_1Name;
    }

    public int getMenu4_1Photo() {
        return Menu4_1Photo;
    }

    public String getMenu4_1Summary() {
        return Menu4_1Summary;
    }

    public String getMenu4_1Memo() {
        return Menu4_1Memo;
    }

    public void setMenu4_1Name(String menu4_1Name) {
        Menu4_1Name = menu4_1Name;
    }

    public void setMenu4_1Photo(int menu4_1Photo) {
        Menu4_1Photo = menu4_1Photo;
    }

    public void setMenu4_1Summary(String menu4_1Summary) {
        Menu4_1Summary = menu4_1Summary;
    }

    public void setMenu4_1Memo(String menu4_1Memo) {
        Menu4_1Memo = menu4_1Memo;
    }
}
