package com.example.tutorial1.GsonTest;

import com.example.tutorial1.TestRecyclerview.Dictionary;

import java.util.ArrayList;

public class ArrayTest {

    private String mName;
    private int mPosition;
    private ArrayList<Dictionary> mArrayList;

    public ArrayTest(ArrayList<Dictionary> arrayList, String name, int position) {
        mArrayList = arrayList;
        mName = name;
        mPosition = position;
    }

    public String getmName() {
        return mName;
    }

    public int getmPosition() {
        return mPosition;
    }
}
