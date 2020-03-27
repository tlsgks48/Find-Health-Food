package com.example.tutorial1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tutorial1.Adapter.Menu5_Adapter;
import com.example.tutorial1.Adapter.Menu5_chatroom_item;
import com.example.tutorial1.TestRecyclerview.CustomAdapter;
import com.example.tutorial1.TestRecyclerview.Dictionary;
import com.example.tutorial1.chat.GroupChatActivity;
import com.example.tutorial1.model.ChatModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Menu5 extends AppCompatActivity {

    String M5idconfirm="";
    Button chatRommAdd;

    private ArrayList<Menu5_chatroom_item> mArrayList;
    private Menu5_Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private FirebaseAuth mAuth;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("users");

    private DatabaseReference GroupRef;

    private ListView list_View;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_groups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu5);


        mAuth = FirebaseAuth.getInstance();
        
        IntializeFields();

        GroupRef = FirebaseDatabase.getInstance().getReference().child("Groups");

       // Toast.makeText(Menu5.this, "Firebase의 이메일은 "+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

/*        mRecyclerView = (RecyclerView) findViewById(R.id.menu5_Recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // 메뉴1_1에서 리사이클러뷰의 데이터에 접근시 사용됩니다.
        mArrayList = new ArrayList<>();
        //loadData();


        // 리사이클러뷰를 위해 어댑터를 사용.
        mAdapter = new Menu5_Adapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);*/

        RetrieveAndDisplayGroup();

        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String currentGroupName = adapterView.getItemAtPosition(position).toString();
                Intent groupChatIntent = new Intent(Menu5.this, GroupChatActivity.class);
                groupChatIntent.putExtra("groupName", currentGroupName);
                startActivity(groupChatIntent);
            }
        });

        chatRommAdd = (Button)findViewById(R.id.menu5_chatroom_add);

        chatRommAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.getCurrentUser().getUid();
               // Toast.makeText(Menu5.this, "uid는 "+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                // 2. 레이아웃 파일 edit_box.xml을 불러와서 화면에 다이얼로그를 보여준다.
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu5.this);
                View view = LayoutInflater.from(Menu5.this).inflate(R.layout.menu5_editbox, null, false);
                builder.setView(view);

                final Button ButtonSubmit = (Button) view.findViewById(R.id.menu5_editbox_submit);
                final EditText editTextID = (EditText) view.findViewById(R.id.menu5_editbox_chatroom);

                ButtonSubmit.setText("추가");

                final AlertDialog dialog = builder.create();

                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        ChatModel chatModel = new ChatModel();
                        chatModel.users.put(mAuth.getCurrentUser().getUid(),true);

                        HashMap<String, Object> messageInfoMap = new HashMap<>();
                        messageInfoMap.put("userUID", mAuth.getCurrentUser().getUid());

                        // 4. 사용자가 입력한 내용을 가져와서
                        final String strID = editTextID.getText().toString();

                        SharedPreferences user_email = getSharedPreferences(strID,MODE_PRIVATE);
                        SharedPreferences.Editor editor = user_email.edit();
                        editor.putString(mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getUid());
                        editor.apply();

 /*                       FirebaseDatabase.getInstance().getReference().child("Groups").child(strID).child("users").setValue(messageInfoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });*/

                        FirebaseDatabase.getInstance().getReference().child("Groups").child(strID).child(mAuth.getCurrentUser().getUid()).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(Menu5.this, strID+"이 생성되었습니다. ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



                        // 5. ArrayList에 추가하고
                       // Menu5_chatroom_item dict = new Menu5_chatroom_item(strID);
                        //mArrayList.add(0,dict); // 첫번째 줄에 삽입
                        //  mArrayList.add(dict); // 마지막줄에 삽입.

                        // 6. 어댑터에서 리사이클러뷰에 반영하도록 합니다.
                        //mAdapter.notifyItemInserted(0);
                        //mAdapter.notifyDataSetChanged();

                        //saveData();

/*                        SharedPreferences lastMessage = getSharedPreferences("chatroomLastMessage",MODE_PRIVATE);
                        SharedPreferences.Editor editor = lastMessage.edit();
                        editor.putString(strID,"");
                        editor.apply();*/

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void IntializeFields() {
        list_View = (ListView) findViewById(R.id.menu5_list_view);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_groups);
        list_View.setAdapter(arrayAdapter);
    }

    private void RetrieveAndDisplayGroup() {

        GroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator iterator = dataSnapshot.getChildren().iterator(); // Groups안에 있는 자식들(즉 채팅방들)을 뜻하는거같음?

                while (iterator.hasNext())
                {
                    set.add(((DataSnapshot)iterator.next()).getKey()); // getKey는 그룹안에 있는 채팅방 이름들을 뜻함.
                }
                list_of_groups.clear();
                list_of_groups.addAll(set);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void saveData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedChat",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayList);
        editor.putString(user_email.getString("Login_email",""), json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedChat",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(user_email.getString("Login_email",""), null);
        Type type = new TypeToken<ArrayList<Menu5_chatroom_item>>() {}.getType();
        mArrayList = gson.fromJson(json, type);

        if (mArrayList == null) {
            mArrayList = new ArrayList<>();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
