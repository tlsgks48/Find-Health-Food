package com.example.tutorial1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tutorial1.Adapter.Menu4_1_Adapter;
import com.example.tutorial1.Adapter.Menu4_1_Item;

import java.util.ArrayList;
import java.util.Map;

import static com.example.tutorial1.Data.mon1_1.mArrayList;
import static com.example.tutorial1.Adapter.MonAdapter.mperson2;
import static com.example.tutorial1.RecyclerViewAdapter.mpersons;
public class Menu4 extends AppCompatActivity {
    int b;
    private ArrayList<Menu4_1_Item> mArrayList4_1;
    private Menu4_1_Adapter menu4_1_adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu4);

        if (mArrayList == null) {
            b = 0;
        }
        else {
            b = mArrayList.size();
        }

       // Toast.makeText(Menu4.this, "관심은"+mon1_1OnOff+" 댓글은 "+b, Toast.LENGTH_SHORT).show();


        mRecyclerView = (RecyclerView) findViewById(R.id.menu4_1_recyclerview_main_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList4_1 = new ArrayList<>();
        menu4_1_adapter = new Menu4_1_Adapter(this,mArrayList4_1);
        mRecyclerView.setAdapter(menu4_1_adapter);

        SharedPreferences user_email = getSharedPreferences("user_email",Context.MODE_PRIVATE);

        SharedPreferences app_food = getSharedPreferences("app_attention", Context.MODE_PRIVATE);

        SharedPreferences user_position = getSharedPreferences("user_position", Context.MODE_PRIVATE);
        Map<String,?> keys = user_position.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()) {
            Log.d("map login", entry.getKey() + ": " + entry.getValue().toString());
            for(int i = 1; i<32; i++) {
                String q = user_email.getString("Login_email","")+String.valueOf(i);
                if(entry.getKey().equals(q)) {
                    Menu4_1_Item dict = new Menu4_1_Item(mperson2.get(Integer.parseInt(entry.getValue().toString())).getMonName(), mperson2.get(Integer.parseInt(entry.getValue().toString())).getMonPhoto(), mperson2.get(Integer.parseInt(entry.getValue().toString())).getMonSummary(), "관심(" + app_food.getInt("a"+entry.getValue().toString(), 0) + ") 댓글(" + app_food.getInt("c"+entry.getValue().toString(), 0) + ")");
                    mArrayList4_1.add(dict);
                    menu4_1_adapter.notifyDataSetChanged();
                }
            }
        }

        SharedPreferences app_food2 = getSharedPreferences("app_attention2", Context.MODE_PRIVATE);

        SharedPreferences user_position2 = getSharedPreferences("user_position2", Context.MODE_PRIVATE);
        Map<String,?> keys2 = user_position2.getAll();
        for(Map.Entry<String,?> entry : keys2.entrySet()) {
            Log.d("map login", entry.getKey() + ": " + entry.getValue().toString());
            for(int i = 0; i<20; i++) {
                String q = user_email.getString("Login_email","")+String.valueOf(i);
                if(entry.getKey().equals(q)) {
                    Menu4_1_Item dict = new Menu4_1_Item(mpersons.get(Integer.parseInt(entry.getValue().toString())).getName(), mpersons.get(Integer.parseInt(entry.getValue().toString())).getPhoto(), mpersons.get(Integer.parseInt(entry.getValue().toString())).getSummary(), "관심(" + app_food2.getInt("a"+entry.getValue().toString(), 0) + ") 댓글(" + app_food2.getInt("c"+entry.getValue().toString(), 0) + ")");
                    mArrayList4_1.add(dict);
                    menu4_1_adapter.notifyDataSetChanged();
                }
            }
        }


/*        if ( mon1_1OnOff == 1) {
            Menu4_1_Item dict = new Menu4_1_Item(mperson2.get(MonPosition1_1).getMonName(), mperson2.get(MonPosition1_1).getMonPhoto(), mperson2.get(MonPosition1_1).getMonSummary(), "관심(" + mon1_1OnOff + ") 댓글(" + b + ")");

            mArrayList4_1.add(dict);
            menu4_1_adapter.notifyDataSetChanged();
        }*/



    }
}
