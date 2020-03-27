package com.example.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial1.GsonTest.User_Info;
import com.example.tutorial1.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Random;

public class join extends AppCompatActivity{

    private EditText ediEmail;
    private EditText ediPassword;
    private EditText ediPasswordConfirm;
    private EditText ediName;
    private Button buttonDone;
    String loginId,loginPwd,PwdConfirm;

    int r;
    String Email,Pwd;

    private FirebaseAuth mAuth;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        ediName = (EditText)findViewById(R.id.editName);
        ediEmail = (EditText)findViewById(R.id.editEmail);
        ediPassword = (EditText)findViewById(R.id.editPassword);
        ediPasswordConfirm = (EditText)findViewById(R.id.editPasswordConfirm);
        buttonDone = (Button)findViewById(R.id.buttonDone);

        mAuth = FirebaseAuth.getInstance();

        // 비밀번호 일치 검사
        ediPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = ediPassword.getText().toString();
                String confirm = ediPasswordConfirm.getText().toString();
                if(password.equals(confirm)) {
                    ediPassword.setBackgroundColor(Color.GREEN);
                    ediPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    ediPassword.setBackgroundColor(Color.RED);
                    ediPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Random ran = new Random();
        r = ran.nextInt(3000000);
        Email = "user"+String.valueOf(r);

        r = ran.nextInt(3000000);
        Pwd = "Pwd"+String.valueOf(r);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        // 처음에는 쉐어드프리퍼런스에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번째 인자는 값입니다.
        // 첨엔 값이 없으므로 키값은 원하는 것으로 하고 값을 null

        // 회원가입 눌렀을때
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String ID = ediEmail.getText().toString();
               final String PW = ediPassword.getText().toString();
                final String NAME = ediName.getText().toString();

                if(ID.indexOf(".com") == -1) {
                    Toast.makeText(join.this, "이메일 형식을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }
                // 이메일 입력확인
                if(ediEmail.getText().toString().length() == 0) {
                    Toast.makeText(join.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }
                // 비밀번호 입력확인
                if(ediPassword.getText().toString().length() == 0) {
                    Toast.makeText(join.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediPassword.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력확인
                if(ediPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(join.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!ediPassword.getText().toString().equals(ediPasswordConfirm.getText().toString())) {
                    Toast.makeText(join.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    ediPassword.setText("");
                    ediPasswordConfirm.setText("");
                    ediPassword.requestFocus();
                    return;
                }


                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                Gson gson = new Gson();
                String iw = auto.getString(ID,"");
                if (iw != ""){
                    Toast.makeText(join.this, "이메일이 중복되었습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }



                //파이어베이스 회원가입
                // firebaseUserCreate(ID,PW,NAME);

                mAuth.createUserWithEmailAndPassword(ID, PW)
                        .addOnCompleteListener(join.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(join.this, "이메일이 중복되었습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                UserModel userModel = new UserModel(NAME,ID,PW);
                String a = ID;
                a = a.replace("."," ");
                conditionRef.child(a).setValue(userModel);




                User_Info user_info = new User_Info(ID,PW,NAME);
                String json = gson.toJson(user_info);

                SharedPreferences.Editor join = auto.edit();
                join.putString(ID,json);
                join.apply();
                // 저장.


                // 쉐어드 안에 있는 모든 키,벨류 값을 보려면...
/*                Map<String,?> keys = auto.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()) {
                    Log.d("map join", entry.getKey() + ": "+entry.getValue().toString());
                    // 이메일 중복 확인
                    if (ediEmail.getText().toString().equals(entry.getValue().toString()) && !Email.equals(entry.getKey())) {
                        Toast.makeText(join.this, "이메일이 중복되었습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        Log.d("map email", entry.getKey() + ": "+entry.getValue().toString());
                        ediEmail.requestFocus();
                        return;
                    }
                }*/

                Intent result = new Intent();

                //Toast.makeText(join.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
                result.putExtra("email",ediEmail.getText().toString());
                result.putExtra("password", ediPassword.getText().toString());
                // 자신을 호출한 액티비티로 데이터를 보낸다.
               setResult(RESULT_OK, result);
               finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseUserCreate(final String email, final String password, final String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(join.this, "Firebase 회원가입을 완료했습니다.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(join.this, "이메일이 중복되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}
