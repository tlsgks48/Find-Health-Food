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

import com.example.tutorial1.Adapter.MonAdapter;
import com.example.tutorial1.Adapter.MonItem;
import com.example.tutorial1.Adapter.MonItem2;
import com.example.tutorial1.R;
import com.example.tutorial1.Data.mon1_1;

import java.util.ArrayList;

public class Ffmenu2 extends Fragment {
    ArrayList<MonItem> items1 = new ArrayList<>();
    public static ArrayList<MonItem2> items2 = new ArrayList<>();
    MonAdapter monAdapter;

    private Context context;

    mon1_1 mon1_1 = new mon1_1();

    public static int fmenu2_mon1_1_onoff = 0;

    // private View view;

    int a=0; // 감귤 액티비티에 댓글 얼마나 달렷는지..

    public Ffmenu2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmenu2,container, false);
        // view = inflater.inflate(R.layout.fmenu2,container, false);

        context = view.getContext();


        //
        initDataset();

        //
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mon_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        monAdapter = new MonAdapter(context, items1, items2);
        recyclerView.setAdapter(monAdapter);

        return view;
    }

    private void initDataset() {

        // 초기화
        items1.clear();
        items2.clear();

        SharedPreferences user_email = context.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences app_food = context.getSharedPreferences("app_attention", Context.MODE_PRIVATE);


        items1.add(new MonItem("제철음식 - 1월"));
        items1.add(new MonItem("감귤",R.drawable.mon1_1_gamgul,"과일류>감귤","관심("+app_food.getInt("a1",0)+") 댓글("+app_food.getInt("c1",0)+")"));
        items1.add(new MonItem("제철음식 - 2월"));
        items1.add(new MonItem("고사리",R.drawable.mon2_1_gosari,"양치류>고사리","관심("+app_food.getInt("a3",0)+") 댓글("+app_food.getInt("c3",0)+")"));
        items1.add(new MonItem("시금치",R.drawable.mon2_1_sigm,"채소류>시금치","관심("+app_food.getInt("a4",0)+") 댓글("+app_food.getInt("c4",0)+")"));
        items1.add(new MonItem("제철음식 - 3월"));
        items1.add(new MonItem("목이버섯",R.drawable.mon3_1_busus,"버섯류>목이버섯","관심("+app_food.getInt("a6",0)+") 댓글("+app_food.getInt("c6",0)+")"));
        items1.add(new MonItem("제철음식 - 4월"));
        items1.add(new MonItem("아스파라거스",R.drawable.mon4_1_aspara,"채소류>엽채류","관심("+app_food.getInt("a8",0)+") 댓글("+app_food.getInt("c8",0)+")"));
        items1.add(new MonItem("더덕",R.drawable.mon4_1_duduc,"채소류>더덕","관심("+app_food.getInt("a9",0)+") 댓글("+app_food.getInt("c9",0)+")"));
        items1.add(new MonItem("제철음식 - 5월"));
        items1.add(new MonItem("죽순",R.drawable.mon5_1_juksun,"채소류>죽순","관심("+app_food.getInt("a11",0)+") 댓글("+app_food.getInt("c11",0)+")"));
        items1.add(new MonItem("오이",R.drawable.mon5_2_oi,"식물>속씨식물","관심("+app_food.getInt("a12",0)+") 댓글("+app_food.getInt("c12",0)+")"));
        items1.add(new MonItem("제철음식 - 6월"));
        items1.add(new MonItem("가지",R.drawable.mon6_1_gaji,"식물>쌍떡잎식물","관심("+app_food.getInt("a14",0)+") 댓글("+app_food.getInt("c14",0)+")"));
        items1.add(new MonItem("보리",R.drawable.mon6_2_bory,"곡물류>보리","관심("+app_food.getInt("a15",0)+") 댓글("+app_food.getInt("c15",0)+")"));
        items1.add(new MonItem("제철음식 - 7월"));
        items1.add(new MonItem("애호박",R.drawable.mon7_1_ehobak,"채소류>호박","관심("+app_food.getInt("a17",0)+") 댓글("+app_food.getInt("c17",0)+")"));
        items1.add(new MonItem("제철음식 - 8월"));
        items1.add(new MonItem("포도",R.drawable.mon8_1_podo,"과실류>포도","관심("+app_food.getInt("a19",0)+") 댓글("+app_food.getInt("c19",0)+")"));
        items1.add(new MonItem("고구마순",R.drawable.mon8_2_gogumason,"채소류>고구마순","관심("+app_food.getInt("a20",0)+") 댓글("+app_food.getInt("c20",0)+")"));
        items1.add(new MonItem("제철음식 - 9월"));
        items1.add(new MonItem("영지버섯",R.drawable.mon9_1_yongji,"균류>영지버섯","관심("+app_food.getInt("a22",0)+") 댓글("+app_food.getInt("c22",0)+")"));
        items1.add(new MonItem("흑미",R.drawable.mon9_2_hkmi,"곡물류>쌀","관심("+app_food.getInt("a23",0)+") 댓글("+app_food.getInt("c23",0)+")"));
        items1.add(new MonItem("제철음식 - 10월"));
        items1.add(new MonItem("새송이버섯",R.drawable.mon10_1_sesongiee,"버섯류>새송이버섯","관심("+app_food.getInt("a25",0)+") 댓글("+app_food.getInt("c25",0)+")"));
        items1.add(new MonItem("도라지",R.drawable.mon10_2_drajy,"채소류>도라지","관심("+app_food.getInt("a26",0)+") 댓글("+app_food.getInt("c26",0)+")"));
        items1.add(new MonItem("제철음식 - 11월"));
        items1.add(new MonItem("무",R.drawable.mon11,"채소류>배추과","관심("+app_food.getInt("a28",0)+") 댓글("+app_food.getInt("c28",0)+")"));
        items1.add(new MonItem("제철음식 - 12월"));
        items1.add(new MonItem("키위",R.drawable.mon12,"과실류>키위","관심("+app_food.getInt("a30",0)+") 댓글("+app_food.getInt("c30",0)+")"));
        items1.add(new MonItem("브로콜리",R.drawable.mon12_2_brocol,"채소류>브로콜리","관심("+app_food.getInt("a31",0)+") 댓글("+app_food.getInt("c31",0)+")"));


    }

    @Override
    public void onResume() {
        super.onResume();

        initDataset();

        monAdapter.notifyDataSetChanged();

       // Toast.makeText(getContext(), "Resume 댓글 수"+a, Toast.LENGTH_SHORT).show();
    }
}
