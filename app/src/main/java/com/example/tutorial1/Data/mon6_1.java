package com.example.tutorial1.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial1.Adapter.Mon1_1_Adapter;
import com.example.tutorial1.Adapter.Mon1_1_Item;
import com.example.tutorial1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class mon6_1 extends AppCompatActivity {

    public static ArrayList<Mon1_1_Item> mArrayList_mon6_1;
    public static Mon1_1_Adapter mAdapter;
    public static RecyclerView mRecyclerView;
    public static LinearLayoutManager mLinearLayoutManager;
    private int count = -1;

    public static int mon6_1OnOff = 0; // 관심 등록됬는지
    String mtext="";
    public  static int MonPosition1_1 = 0; // 감귤의 포지션
    public int b = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mon6_1);
        MonPosition1_1 = getIntent().getIntExtra("position2",9); // 포지션 값을 받았는지...

        final TextView textView1 = (TextView)findViewById(R.id.mon6_1_star_off);
        final ImageView imageView1 = (ImageView)findViewById(R.id.mon6_1_image_star);
        final LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.mon6_1_Linear_star_off);

        mRecyclerView = (RecyclerView) findViewById(R.id.mon6_1_recyclerview_main_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // mon1_1 액티비티에서 리사이클러뷰의 데이터에 접근시 사용.
        //mArrayList_mon6_1 = new ArrayList<>();
        loadData();

        mAdapter = new Mon1_1_Adapter(this,mArrayList_mon6_1,14);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        final SharedPreferences user_email = getSharedPreferences("user_email",Context.MODE_PRIVATE);
        final SharedPreferences user_star = getSharedPreferences("user_star",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = user_star.edit();

        SharedPreferences user_position = getSharedPreferences("user_position",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = user_position.edit();

        final SharedPreferences app_food = getSharedPreferences("app_attention", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor3 = app_food.edit();

        // 유저에 따른 별값
        int aa = user_star.getInt(user_email.getString("Login_email","")+"14",0);

        // 앱에 따른 고정적 별값
        b = app_food.getInt("a14",0);
        mon6_1OnOff = aa;

        if(mon6_1OnOff == 0) {
            imageView1.setImageResource(R.drawable.star_off);
        }
        else {
            imageView1.setImageResource(R.drawable.star_on);
        }


        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mon6_1OnOff == 0) {
                    mon6_1OnOff = 1;
                    editor.putInt(user_email.getString("Login_email","")+"14",1);
                    editor.apply();
                    editor2.putInt(user_email.getString("Login_email","")+"14",MonPosition1_1);
                    editor2.apply();

                    b++;
                    editor3.putInt("a14",b);
                    editor3.apply();
                    imageView1.setImageResource(R.drawable.star_on);
                    Toast.makeText(mon6_1.this, "관심 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mon6_1OnOff = 0;
                    editor.putInt(user_email.getString("Login_email","")+"14",0);
                    editor.apply();
                    editor2.remove(user_email.getString("Login_email","")+"14");
                    editor2.apply();

                    b--;
                    editor3.putInt("a14",b);
                    editor3.apply();
                    imageView1.setImageResource(R.drawable.star_off);
                    Toast.makeText(mon6_1.this, "관심 등록이 해제 되었습니다. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonInsert = (Button)findViewById(R.id.mon6_1_comment_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.mon6_1editText_comment);
                mtext = editText.getText().toString();

                // 현재 시간을 msec로 구한다.
                long now = System.currentTimeMillis();
                // 현재 시간을 date 변수에 저장.
                Date date = new Date(now);
                // 시간을 나타내는 포맷을 정한다.
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // newDate 변수에 값을 저장.
                String formatDate = sdfNow.format(date);

                SharedPreferences user_email = getSharedPreferences("user_email",Context.MODE_PRIVATE);
                Mon1_1_Item dict = new Mon1_1_Item(mtext,user_email.getString("Login_email","")+"님",formatDate);

                mArrayList_mon6_1.add(dict);
                mAdapter.notifyDataSetChanged(); // 추가한것을 리사이클러뷰에 적용.
                saveData();
                int t = app_food.getInt("c14",0) + 1;
                editor3.putInt("c14",t);
                editor3.apply();
                //Toast.makeText(food1.this, "사이즈는  "+mArrayList_food1.size(), Toast.LENGTH_SHORT).show();
                editText.setText("");
                InputMethodManager minputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                minputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);
                mRecyclerView.requestFocus();

            }
        });
    }

    private void saveData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("user_mon_comment",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayList_mon6_1);
        editor.putString("a"+"14", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("user_mon_comment",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("a"+"14", null);
        Type type = new TypeToken<ArrayList<Mon1_1_Item>>() {}.getType();
        mArrayList_mon6_1 = gson.fromJson(json, type);

        if (mArrayList_mon6_1 == null) {
            mArrayList_mon6_1 = new ArrayList<>();
        }
    }
}