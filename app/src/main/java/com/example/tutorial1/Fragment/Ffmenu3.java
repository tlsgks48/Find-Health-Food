package com.example.tutorial1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutorial1.Item;
import com.example.tutorial1.R;
import com.example.tutorial1.RecyclerViewAdapter;

import java.util.ArrayList;

public class Ffmenu3 extends Fragment{

    private ArrayList<Item> items = new ArrayList<>();

    private Context context;
    RecyclerViewAdapter adapter;
    int a=0; // 감귤 액티비티에 댓글 얼마나 달렷는지..

    public Ffmenu3() {

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmenu3,container,false);
        context = view.getContext();

        initDataset();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(context, items);
        recyclerView.setAdapter(adapter);

        return view;
    }

    // 사용할 데이터를 미리 준비해 놓는다. 준비하는 형태에 따라 구현방법이 조금 달라 질 수 있다.
    // 1. 수동으로 Item을 입력해서 추가 하도록 할 수 있다.
    // 2. 온라인에서 DB에서 일괄 가져 올 수도 있다.
    // 어떻게든 items 배열에 데이터를 형식에 맞게 넣어 어댑터와 연결만 하면 화면에 내용이 출력될것이다.

    private void initDataset() {
        // 초기화
        items.clear();

        SharedPreferences user_email = context.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences app_food = context.getSharedPreferences("app_attention2", Context.MODE_PRIVATE);

        // 아이템에는 3개의 아이템 데이터 객체가 들어가 있다.
        items.add(new Item("배즙", R.drawable.bea,"종합건강식품.","관심("+app_food.getInt("a0",0)+") 댓글("+app_food.getInt("c0",0)+")"));
        items.add(new Item("루테인", R.drawable.eyefood_1,"눈 건강식품.","관심("+app_food.getInt("a1",0)+") 댓글("+app_food.getInt("c1",0)+")"));
        items.add(new Item("비타민C", R.drawable.bitamin_c,"종합건강식품.","관심("+app_food.getInt("a2",0)+") 댓글("+app_food.getInt("c2",0)+")"));
        items.add(new Item("천마", R.drawable.chunma,"종합건강식품.","관심("+app_food.getInt("a3",0)+") 댓글("+app_food.getInt("c3",0)+")"));
        items.add(new Item("간건강 슈퍼케어", R.drawable.gan,"간 건강식품.","관심("+app_food.getInt("a4",0)+") 댓글("+app_food.getInt("c4",0)+")"));
        items.add(new Item("홍삼 액기스", R.drawable.hongsam,"원기 회복 식품.","관심("+app_food.getInt("a5",0)+") 댓글("+app_food.getInt("c5",0)+")"));
        items.add(new Item("오메가3", R.drawable.omega3,"혈액순환 건강식품.","관심("+app_food.getInt("a6",0)+") 댓글("+app_food.getInt("c6",0)+")"));
        items.add(new Item("유산균", R.drawable.usangun,"장 건강식품.","관심("+app_food.getInt("a7",0)+") 댓글("+app_food.getInt("c7",0)+")"));
    }


    @Override
    public void onResume() {
        super.onResume();

        initDataset();

        adapter.notifyDataSetChanged();
    }
}
