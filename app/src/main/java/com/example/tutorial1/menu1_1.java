package com.example.tutorial1;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tutorial1.GsonTest.ArrayTest;
import com.example.tutorial1.TestRecyclerview.CustomAdapter;
import com.example.tutorial1.TestRecyclerview.Dictionary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class menu1_1 extends AppCompatActivity {

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1_1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_menu1_1_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // 메뉴1_1에서 리사이클러뷰의 데이터에 접근시 사용됩니다.
        //mArrayList = new ArrayList<>();

        loadData();

        // 리사이클러뷰를 위해 어댑터를 사용.
        mAdapter = new CustomAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        // 리사이클러뷰의 줄(row) 사이에 수평선을 넣기위해 사용.
       // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),mLinearLayoutManager.getOrientation());
      //  mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button buttonInsert = (Button)findViewById(R.id.button_menu1_1_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {

            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
            @Override
            public void onClick(View v) {

                // 2. 레이아웃 파일 edit_box.xml을 불러와서 화면에 다이얼로그를 보여준다.
                AlertDialog.Builder builder = new AlertDialog.Builder(menu1_1.this);
                View view = LayoutInflater.from(menu1_1.this).inflate(R.layout.edit_box, null, false);
                builder.setView(view);

                final Button ButtonSubmit = (Button) view.findViewById(R.id.edittext_dialog_submit);
                final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_english);
                final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                ButtonSubmit.setText("삽입");

                final AlertDialog dialog = builder.create();

                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // 4. 사용자가 입력한 내용을 가져와서
                        String strID = editTextID.getText().toString();
                        String strEnglish = editTextEnglish.getText().toString();
                        String strKorean = editTextKorean.getText().toString();

                        // 5. ArrayList에 추가하고
                        Dictionary dict = new Dictionary(strID,strEnglish,strKorean);
                        mArrayList.add(0,dict); // 첫번째 줄에 삽입
                       //  mArrayList.add(dict); // 마지막줄에 삽입.

                        // 6. 어댑터에서 리사이클러뷰에 반영하도록 합니다.
                        //mAdapter.notifyItemInserted(0);
                          mAdapter.notifyDataSetChanged();

                          saveData();

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
    private void saveData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedTest",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayList);
        editor.putString(user_email.getString("Login_email",""), json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedTest",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(user_email.getString("Login_email",""), null);
        Type type = new TypeToken<ArrayList<Dictionary>>() {}.getType();
        mArrayList = gson.fromJson(json, type);

        if (mArrayList == null) {
            mArrayList = new ArrayList<>();
        }
    }
}
