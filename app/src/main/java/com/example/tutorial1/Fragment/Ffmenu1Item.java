package com.example.tutorial1.Fragment;

import android.widget.Spinner;
import android.widget.TextView;

public class Ffmenu1Item {
    private String time;
    private String time2;
    private String time3;
    private String eat_count;
    private String eat_count2;
    private String eat_count3;
    private String checktime;
    private String checktime2_1;
    private String checktime2_2;

    private String checktime3_1;
    private String checktime3_2;
    private String checktime3_3;

    private boolean isSelected;
    private boolean isSelected2_1;
    private boolean isSelected2_2;

    private boolean isSelected3_1;
    private boolean isSelected3_2;
    private boolean isSelected3_3;

    private String checkname;
    private int count;

    //편집
    public Ffmenu1Item(String checkname, String time, String eat_count) {
        this.checkname = checkname;
        this.time = time;
        this.eat_count = eat_count;
    }

    // 1
    public Ffmenu1Item(String checkname, String time, String eat_count, int count, String checktime) {
        this.checkname = checkname;
        this.time = time;
        this.eat_count = eat_count;
        this.count = count;
        this.checktime = checktime;
    }

    // 2
    public Ffmenu1Item(String checkname, String time, String time2,String eat_count,String eat_count2, int count, String checktime2_1, String checktime2_2) {
        this.checkname = checkname;
        this.time = time;
        this.time2 = time2;
        this.eat_count = eat_count;
        this.eat_count2 = eat_count2;
        this.count = count;
        this.checktime2_1 = checktime2_1;
        this.checktime2_2 = checktime2_2;
    }

    // 3
    public Ffmenu1Item(String checkname, String time, String time2, String time3, String eat_count, String eat_count2, String eat_count3,int count, String checktime3_1, String checktime3_2,String checktime3_3) {
        this.checkname = checkname;
        this.time = time;
        this.time2 = time2;
        this.time3 = time3;
        this.eat_count = eat_count;
        this.eat_count2 = eat_count2;
        this.eat_count3 = eat_count3;
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    public String getCheckname() {
        return checkname;
    }

    public String getTime() {
        return time;
    }

    public String getTime2() {
        return time2;
    }

    public String getTime3() {
        return time3;
    }

    public String getEat_count() {
        return eat_count;
    }

    public String getEat_count2() {
        return eat_count2;
    }

    public String getEat_count3() {
        return eat_count3;
    }



    public boolean isSelected() {
        return isSelected;
    }

    public boolean isSelected2_1() {
        return isSelected2_1;
    }

    public boolean isSelected2_2() {
        return isSelected2_2;
    }

    public boolean isSelected3_1() {
        return isSelected3_1;
    }

    public boolean isSelected3_2() {
        return isSelected3_2;
    }

    public boolean isSelected3_3() {
        return isSelected3_3;
    }

    public void setSelected3_1(boolean selected3_1) {
        isSelected3_1 = selected3_1;
    }

    public void setSelected3_2(boolean selected3_2) {
        isSelected3_2 = selected3_2;
    }

    public void setSelected3_3(boolean selected3_3) {
        isSelected3_3 = selected3_3;
    }

    public void setSelected2_1(boolean selected2_1) {
        isSelected2_1 = selected2_1;
    }

    public void setSelected2_2(boolean selected2_2) {
        isSelected2_2 = selected2_2;
    }

    public String getChecktime() {
        return checktime;
    }

    public String getChecktime2_1() {
        return checktime2_1;
    }

    public String getChecktime2_2() {
        return checktime2_2;
    }

    public String getChecktime3_1() {
        return checktime3_1;
    }

    public String getChecktime3_2() {
        return checktime3_2;
    }

    public String getChecktime3_3() {
        return checktime3_3;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public void setChecktime2_1(String checktime2_1) {
        this.checktime2_1 = checktime2_1;
    }

    public void setChecktime2_2(String checktime2_2) {
        this.checktime2_2 = checktime2_2;
    }

    public void setChecktime3_1(String checktime3_1) {
        this.checktime3_1 = checktime3_1;
    }

    public void setChecktime3_2(String checktime3_2) {
        this.checktime3_2 = checktime3_2;
    }

    public void setChecktime3_3(String checktime3_3) {
        this.checktime3_3 = checktime3_3;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname;
    }
}
