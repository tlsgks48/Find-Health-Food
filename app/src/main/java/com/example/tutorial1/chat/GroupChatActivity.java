package com.example.tutorial1.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.tutorial1.Menu5;
import com.example.tutorial1.R;
import com.example.tutorial1.model.ChatModel;
import com.example.tutorial1.model.GroupChatModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupChatActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button SendMessageButton;
    private EditText userMessageInput;
    //   private ScrollView mScrollView;
    private TextView displayTextMessages;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef, GroupNameRef, GroupMessageKeyRef;

    private String currentGroupName, currentUserID, currentUserName, currentDate, currentTime, userName;

    private RecyclerView recyclerView;

    Boolean userCheck = false;

    int b = 0, c = 0;

    String[] t = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        currentGroupName = getIntent().getExtras().get("groupName").toString();
        //Toast.makeText(GroupChatActivity.this, "채팅방 이름은 "+currentGroupName, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userName = mAuth.getCurrentUser().getEmail();
        // 유저위치로
        UsersRef = FirebaseDatabase.getInstance().getReference().child("users");
        // 그룹안에 있는 채팅방이름 위치로..
        GroupNameRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName);

        recyclerView = (RecyclerView) findViewById(R.id.group_chat_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(GroupChatActivity.this));
        recyclerView.setAdapter(new RecyclerViewAdapter());

        InitializeMethods();

        GetUserInfo();


/*        FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(GroupChatActivity.this, "검사하나? ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/




        FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int a = 0;
                b = 0;

                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    if( currentUserID.equals(item.getKey())) {
                        a = 1;

                    }
                    t[b] = item.getKey();
                    b++;
                    Log.d("firebase Info", "getValue "+item.getValue()+"key "+item.getKey());

                }

                if(a == 1) {
                    // 기존 유저
                    //Toast.makeText(GroupChatActivity.this, "유저가 있는가? ", Toast.LENGTH_SHORT).show();
                }
                else if(a == 0 && c == 0){
                    t[b] = currentUserID;
                    b++;
                    // 새로운 유저
                    //Toast.makeText(GroupChatActivity.this, "유저가 없다. b는 "+b, Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).child(mAuth.getCurrentUser().getUid()).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(Menu5.this, strID+"이 생성되었습니다. ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    // 새 유저 메시지 방을 만들고..
                    // 입장 메세지를 다 보내면댐..
                    for(DataSnapshot item2 : dataSnapshot.getChildren()) {
                        Log.d("firebase Info2", "getValue "+item2.getValue()+"key "+item2.getKey());
                    }

                    // 입장했다는 메세지를 보내보자...
                    for(int i =0; i<b; i++) {
                        String messageKEY = GroupNameRef.child(t[i]).push().getKey(); // 그룹 채팅방 안에 유니크한 키를 주려고...

                        HashMap<String, Object> groupMessageKey = new HashMap<>();
                        GroupNameRef.child(t[i]).updateChildren(groupMessageKey);

                        // GroupNameRef은 말그대로 그룹안에 있는 채팅방별 이름, 이안에 메시지를 집어넣는것.
                        GroupMessageKeyRef = GroupNameRef.child(t[i]).child(messageKEY);

                        HashMap<String, Object> messageInfoMap = new HashMap<>();
                        messageInfoMap.put("userName", "admin@@@");
                        messageInfoMap.put("message", mAuth.getCurrentUser().getEmail()+"님이 입장하였습니다.");
                        messageInfoMap.put("date", "");
                        messageInfoMap.put("time", "");
                        GroupMessageKeyRef.updateChildren(messageInfoMap);
                    }
                }

                if(b == 0 ) {
                    FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).removeValue();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    Log.d("firebase Info3", "getValue "+item.getValue()+"key "+item.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // 쉐어드랑 써서 해보자...
       /* SharedPreferences user_email = getSharedPreferences(currentGroupName,MODE_PRIVATE);
        SharedPreferences.Editor editor = user_email.edit();
        String a = user_email.getString(currentUserID,"");
        if(a != "") {
            //Toast.makeText(GroupChatActivity.this, "기존 유저 "+currentUserID, Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(GroupChatActivity.this, "새로운 유저 "+currentUserID, Toast.LENGTH_SHORT).show();
            editor.putString(currentUserID,currentUserID);
            editor.apply();

            FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).child(mAuth.getCurrentUser().getUid()).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(Menu5.this, strID+"이 생성되었습니다. ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Map<String,?> keys = user_email.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()) {
                // entry.getValue().toString()
                String messageKEY = GroupNameRef.child(entry.getValue().toString()).push().getKey(); // 그룹 채팅방 안에 유니크한 키를 주려고...

                HashMap<String, Object> groupMessageKey = new HashMap<>();
                GroupNameRef.child(entry.getValue().toString()).updateChildren(groupMessageKey);

                // GroupNameRef은 말그대로 그룹안에 있는 채팅방별 이름, 이안에 메시지를 집어넣는것.
                GroupMessageKeyRef = GroupNameRef.child(entry.getValue().toString()).child(messageKEY);

                HashMap<String, Object> messageInfoMap = new HashMap<>();
                messageInfoMap.put("userName", "admin@@@");
                messageInfoMap.put("message", mAuth.getCurrentUser().getEmail()+"님이 입장하였습니다.");
                messageInfoMap.put("date", "");
                messageInfoMap.put("time", "");
                GroupMessageKeyRef.updateChildren(messageInfoMap);

            }
        }*/


        //


        // 메세지 보내기 버튼
        SendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveMessageInfoToDatabase();

                userMessageInput.setText("");
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void InitializeMethods() {

        mToolbar = (Toolbar) findViewById(R.id.group_chat_bar_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(currentGroupName);

        SendMessageButton = (Button) findViewById(R.id.send_message_button);
        userMessageInput = (EditText) findViewById(R.id.input_group_message);
        //    displayTextMessages = (TextView) findViewById(R.id.group_chat_text_display);
        //    mScrollView = (ScrollView) findViewById(R.id.my_scroll_view);
    }

    private void GetUserInfo() {
        String a = mAuth.getCurrentUser().getEmail();
        a = a.replace(".", " ");
        // UserRef는 데이터베이스의 유저위치....
        UsersRef.child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    currentUserName = dataSnapshot.child("userEmail").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SaveMessageInfoToDatabase() {
        // t[]랑 , b에 정보는 다 담겨져 있으니 그걸 쓰면댄다.

/*        SharedPreferences user_email = getSharedPreferences(currentGroupName,MODE_PRIVATE);
        Map<String,?> keys = user_email.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()) {
            // entry.getValue().toString()
        }*/



            for(int j=0; j<b; j++) {
                String message = userMessageInput.getText().toString();
                String messageKEY = GroupNameRef.child(t[j]).push().getKey(); // 그룹 채팅방 안에 유니크한 키를 주려고...

                if (TextUtils.isEmpty(message)) {
                    Toast.makeText(GroupChatActivity.this, "첫 메세지를 입력해주세요" + currentGroupName, Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    currentDate = currentDateFormat.format(calForDate.getTime());

                    Calendar calForTime = Calendar.getInstance();
                    SimpleDateFormat currentTimeFormat = new SimpleDateFormat("hh:mm a");
                    currentTime = currentTimeFormat.format(calForTime.getTime());

                    HashMap<String, Object> groupMessageKey = new HashMap<>();
                    GroupNameRef.child(t[j]).updateChildren(groupMessageKey);

                    // GroupNameRef은 말그대로 그룹안에 있는 채팅방별 이름, 이안에 메시지를 집어넣는것.
                    GroupMessageKeyRef = GroupNameRef.child(t[j]).child(messageKEY);

                    HashMap<String, Object> messageInfoMap = new HashMap<>();
                    messageInfoMap.put("userName", mAuth.getCurrentUser().getEmail());
                    messageInfoMap.put("message", message);
                    messageInfoMap.put("date", currentDate);
                    messageInfoMap.put("time", currentTime);
                    GroupMessageKeyRef.updateChildren(messageInfoMap);
                }
            }





    }

/*    private void testUser(DataSnapshot dataSnapshot) {
        Iterator iterator = dataSnapshot.getChildren().iterator();

        while (iterator.hasNext()) {
            String userUID = (String) ((DataSnapshot) iterator.next()).getValue();
            Toast.makeText(GroupChatActivity.this, " 무엇이 ? "+userUID, Toast.LENGTH_SHORT).show();
        }
    }*/


    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<GroupChatModel> comments;


        public RecyclerViewAdapter() {
            comments = new ArrayList<>();

            // 잠시 바꾸자...
            GroupNameRef.child(currentUserID).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    if (dataSnapshot.exists()) {
                        DisplayMessage(dataSnapshot);
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    if (dataSnapshot.exists()) {
                        DisplayMessage(dataSnapshot);
                    }
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        private void DisplayMessage(DataSnapshot dataSnapshot) {
            Iterator iterator = dataSnapshot.getChildren().iterator(); // 채팅방의 메시지들
            //displayTextMessages.setBackgroundResource(R.drawable.leftbubble);
            while (iterator.hasNext()) {
                String chatDate = (String) ((DataSnapshot) iterator.next()).getValue(); // 메세지속에서 데이트...
                String chatMessage = (String) ((DataSnapshot) iterator.next()).getValue(); // 메세지속에서 메세지...
                String chatTime = (String) ((DataSnapshot) iterator.next()).getValue(); // 메세지속에서 시간...
                String chatName = (String) ((DataSnapshot) iterator.next()).getValue(); // 메세지속에서 이름...

                GroupChatModel groupChatModel = new GroupChatModel(chatName, chatMessage, chatDate, chatTime);
                comments.add(groupChatModel);

                recyclerView.scrollToPosition(comments.size() - 1);


                //displayTextMessages.append(chatName + " :\n" + chatMessage + "\n" + chatTime + "     " + chatDate + "\n\n\n");


            }
            notifyDataSetChanged();
        }



        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu5_item_group_message, viewGroup, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MessageViewHolder messageViewHolder = ((MessageViewHolder) holder);

            if (comments.get(position).userName.equals(userName)) {
                // 내가 보낸 메세지
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
                messageViewHolder.textView_name.setVisibility(View.INVISIBLE);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
            }
            else if(comments.get(position).userName.equals("admin@@@")) {
                messageViewHolder.textView_message.setText(comments.get(position).message);
                // background_color에 background_holo_light색으로 맞춰놨음.....
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.background_color);
                messageViewHolder.textView_name.setVisibility(View.INVISIBLE);
                messageViewHolder.textView_message.setTextSize(22);
                messageViewHolder.linearLayout_main.setGravity(Gravity.CENTER);

            }
            else {

                messageViewHolder.textView_name.setText(comments.get(position).userName);
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
                messageViewHolder.textView_name.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);

            }
            messageViewHolder.textView_timestamp.setText(comments.get(position).mDate);


        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public TextView textView_name;
            public LinearLayout linearLayout_main;
            public TextView textView_timestamp;

            public MessageViewHolder(@NonNull View view) {
                super(view);
                textView_message = (TextView) view.findViewById(R.id.message_group_Item_textView_message);
                textView_name = (TextView) view.findViewById(R.id.message_group_Item_textview_name);
                linearLayout_main = (LinearLayout) view.findViewById(R.id.message_group_Item_linearlayout_main);
                textView_timestamp = (TextView) view.findViewById(R.id.message_group_Item_textview_timestamp);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.chatroom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // 채팅방 나가기 버튼을 누른다면......
            case R.id.toolbar_chatroom_menu:


/*                // 파이어베이스랑 쉐어드에 정보를 다 날려야함.....
                SharedPreferences user_email = getSharedPreferences(currentGroupName,MODE_PRIVATE);
                SharedPreferences.Editor editor = user_email.edit();
                editor.remove(currentUserID);
                editor.apply();*/

                for(int j=0; j<b; j++) {
                    String messageKEY = GroupNameRef.child(t[j]).push().getKey(); // 그룹 채팅방 안에 유니크한 키를 주려고...

                    HashMap<String, Object> groupMessageKey = new HashMap<>();
                    GroupNameRef.child(t[j]).updateChildren(groupMessageKey);

                    // GroupNameRef은 말그대로 그룹안에 있는 채팅방별 이름, 이안에 메시지를 집어넣는것.
                    GroupMessageKeyRef = GroupNameRef.child(t[j]).child(messageKEY);

                    HashMap<String, Object> messageInfoMap = new HashMap<>();
                    messageInfoMap.put("userName", "admin@@@");
                    messageInfoMap.put("message", mAuth.getCurrentUser().getEmail() + "님이 퇴장하였습니다.");
                    messageInfoMap.put("date", "");
                    messageInfoMap.put("time", "");
                    GroupMessageKeyRef.updateChildren(messageInfoMap);
                }

                c = 1;
                FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName).child(mAuth.getCurrentUser().getUid()).removeValue();

                // 삭제될때 여기서도 경로에 변경 사항이 있으니 온크리트 부분에서 바로 입장이 되어버리는것....

                Intent intent = new Intent(GroupChatActivity.this, Menu5.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
