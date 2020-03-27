package com.example.tutorial1.chat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial1.Menu5;
import com.example.tutorial1.R;
import com.example.tutorial1.model.ChatModel;
import com.example.tutorial1.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MessageActivity extends AppCompatActivity {

    private String destinationUid; // 채팅방에서 대화를 신청 당한사람..

    private Button button;
    private EditText editText;

    private String uid; // 채팅방에서 대화를 신청한 사람.
    private String chatRoomUid;

    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    int peopleCount = 0;

    // 로그인_이메일처럼, 채팅방의 라스트_메세지도 단 하나만 만들어서 라스트 메시지로 보여주면 되지 않을까?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);

        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(MessageActivity.this, "uid는 "+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        SharedPreferences sharedUid = getSharedPreferences("firebaseUid", Activity.MODE_PRIVATE);

        uid = mAuth.getCurrentUser().getUid(); // 채팅을 요구하는 아이디 즉 단말기에 로그인된 UID
        // 임의로 s@s.com의 uid를 넣자.

        SharedPreferences user_email = getSharedPreferences("user_email",MODE_PRIVATE);
        // 여기다가 if문 걸어서 채팅 당하는 아이디를 바꾸게하자.
        if(user_email.getString("Login_email","").equals("a@a.com")) {
            destinationUid = sharedUid.getString("s@s.com",""); // 채팅을 당하는 아이디
        }
        else if (user_email.getString("Login_email","").equals("s@s.com")) {
            destinationUid = sharedUid.getString("a@a.com",""); // 채팅을 당하는 아이디
        }


        button = (Button)findViewById(R.id.Message_chat_menu5_send);
        editText = (EditText) findViewById(R.id.Message_chat_menu5_editText);

        recyclerView = (RecyclerView)findViewById(R.id.messageAcitivy_recyclerview);
        // 전송 버튼을 누른다면..
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 채팅방의 유저들을 집어넣자.
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(uid,true);
                chatModel.users.put(destinationUid,true);



                if(chatRoomUid == null) {
                    button.setEnabled(false); // 자잘한 버그를 방지하기 위해서
                    // push를 넣어줘야 채팅방의 이름이 임의적으로 생김.
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) { // 콜백을 달아줘가지고 데이터가 완료가 되었을때 체크를 해주면 완벽하게 됨.
                            checkChatRoom();
                        }
                    });
                }
                else {
                    ChatModel.Comment comment = new ChatModel.Comment();
                    comment.uid = uid;
                    comment.message = editText.getText().toString();
                    comment.timestamp = ServerValue.TIMESTAMP;
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            editText.setText(""); // 콜백 사용하지않고 그냥 editText.setText("")해도 무관, 더완벽하게 하기위해.
                        }
                    });

                }
            }
        });
        checkChatRoom();
    }

    void checkChatRoom() {

        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    ChatModel chatModel = item.getValue(ChatModel.class); // users의 유저들이 있는지
                        if(chatModel.users.containsKey(destinationUid)) {
                            chatRoomUid = item.getKey(); // 방아이디를 체크.


                            button.setEnabled(true); // 받아왔을때 버튼을 살리는것.
                            recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                            recyclerView.setAdapter(new RecyclerViewAdapter());
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<ChatModel.Comment> comments;
        UserModel userModel;
        public RecyclerViewAdapter() {
            comments = new ArrayList<>();


            String a = mAuth.getCurrentUser().getEmail();
            a = a.replace("."," ");
            FirebaseDatabase.getInstance().getReference().child("users").child(a).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userModel = dataSnapshot.getValue(UserModel.class);
                    getMassageList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        // 메세지를 읽어들이는 함수.
        void getMassageList() {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments");

            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // 읽어 들인 데이터는 이쪽으로 넘어옴.
                    comments.clear(); // 데이터가 추가될때마다 서버에서 모든 데이터를 다 보내주기 때문에, 데이터가 쌓이게 됨.
                    Map<String,Object> readUsersMap = new HashMap<>();

                    for(DataSnapshot item : dataSnapshot.getChildren()) { // 포문을 자동적으로 돌게 해주고
                        String key = item.getKey(); // 키값 받아오고
                        ChatModel.Comment comment_origin = item.getValue(ChatModel.Comment.class);
                        ChatModel.Comment comment_motify = item.getValue(ChatModel.Comment.class);
                        // 나 읽엇어 라는 태그를 달아줘야함.
                        comment_motify.readUsers.put(uid,true);

                        readUsersMap.put(key,comment_motify); // 애가 읽은 내용을 가지고 있는것,
                        comments.add(comment_origin);
                    }
                    // comments에 물어보면 된다.
                    // 마지막줄의 리드유저에 내가 있니?
                    if(!comments.get(comments.size() - 1).readUsers.containsKey(uid)) {
                        // 없을 경우 서버한테 그냥 보고하면 되고



                        // 서버가 반영 된 것을 알 수 있게 하자.
                        FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").updateChildren(readUsersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // 메세지가 갱신
                                notifyDataSetChanged();

                                recyclerView.scrollToPosition(comments.size() - 1);
                            }
                        });
                    } else {
                        // 없을 경우에는 그냥 읽게 하면 됨.
                        notifyDataSetChanged();
                        recyclerView.scrollToPosition(comments.size() - 1);
                    }



                    // 채팅룸의 라스트 메시지 테스트 해본것들....
/*                   String a = comments.get(comments.size()-1).message;

                    long unixTime = (long) comments.get(comments.size()-1).timestamp;
                    Date date = new Date(unixTime);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                    String time = simpleDateFormat.format(date);
                    Toast.makeText(MessageActivity.this, "메시지는 "+a+ " 시간은 "+time, Toast.LENGTH_SHORT).show();*/
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });  // 챗룸의 방이름을 가져와서 그안에 있는 코멘트만 가져오면 됨.
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu5_item_message,viewGroup,false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MessageViewHolder messageViewHolder =  ((MessageViewHolder)holder);

            // 조건문으로 인하여 내 메시지 말풍성과 상대 메시지 말풍선을 다르게 함.
            if(comments.get(position).uid.equals(uid)) {
                // 내가 보낸 메세지
             messageViewHolder.textView_message.setText(comments.get(position).message);
             messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
             messageViewHolder.linearLayout_destination.setVisibility(View.INVISIBLE); // 내 메시지일 경우 감춰둔다.
             messageViewHolder.textView_message.setTextSize(25);
             messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
             setReadCounter(position,messageViewHolder.textView_readCounter_left);
            }
            else {
                // 상대방이 보낸 메세지
                messageViewHolder.imageView_profile.setImageResource(R.drawable.profil_image);
                messageViewHolder.textView_name.setText(userModel.userName);
                messageViewHolder.linearLayout_destination.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
                setReadCounter(position,messageViewHolder.textView_readCounter_right);

            }
            long unixTime = (long) comments.get(position).timestamp;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            messageViewHolder.textView_timestamp.setText(time);

        }

        void setReadCounter(final int position, final TextView textView) { // 서버에 읽은 수가 얼마나 있는지 물어보는 함수지만, 계속 물어보면 서버에 부담이 가니, 딱한번만 읽게 하게 바꿔보자.
            if(peopleCount == 0) {
                FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // 맵의 스트링과 불린 부분이, 파이어베이스의 chatroom에 있는 users의 왼쪽과 오른쪽값을 해쉬맵으로 받겠다고 한것.
                        Map<String, Boolean> users = (Map<String, Boolean>) dataSnapshot.getValue();
                        peopleCount = users.size(); // 0인지 물어본 다음에 peopleCount에 유저 사이즈를 넣어주면 됨.
                        // 읽지 않은 사람에 대한 값.
                        int count = peopleCount - comments.get(position).readUsers.size(); // 코멘트를 읽은 사람에 대한 카운트를 가져올수가 있음. , 여기서 user.size()를 뺴면 읽지 않은 사람을 구할 수 있음.
                        if (count > 0) {
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(String.valueOf(count));
                        } else {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }else {
                int count = peopleCount - comments.get(position).readUsers.size(); // 코멘트를 읽은 사람에 대한 카운트를 가져올수가 있음. , 여기서 user.size()를 뺴면 읽지 않은 사람을 구할 수 있음.
                if (count > 0) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(count));
                } else {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public TextView textView_name;
            public ImageView imageView_profile;
            public LinearLayout linearLayout_destination;
            public LinearLayout linearLayout_main;
            public TextView textView_timestamp;
            public TextView textView_readCounter_left;
            public TextView textView_readCounter_right;

            public MessageViewHolder(View view) {
                super(view);
                textView_message = (TextView) view.findViewById(R.id.messageItem_textView_message);
                textView_name = (TextView) view.findViewById(R.id.messageItem_textview_name);
                imageView_profile = (ImageView) view.findViewById(R.id.messageItem_imageview_profile);
                linearLayout_destination = (LinearLayout) view.findViewById(R.id.messageItem_linearlayout_destination);
                linearLayout_main = (LinearLayout) view.findViewById(R.id.messageItem_linearlayout_main);
                textView_timestamp = (TextView) view.findViewById(R.id.messageItem_textview_timestamp);
                textView_readCounter_left = (TextView) view.findViewById(R.id.messageItem_textview_readCounter_left);
                textView_readCounter_right = (TextView) view.findViewById(R.id.messageItem_textview_readCounter_right);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        databaseReference.removeEventListener(valueEventListener);
        finish();

    }
}
