package com.example.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial1.GsonTest.User_Info;
import com.example.tutorial1.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class menu3_1 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText ediEmail;
    private EditText ediPassword;
    private EditText ediPasswordConfirm;
    private EditText ediName;
    private Button buttonDone;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu3_1);

        ediName = (EditText)findViewById(R.id.profil_fix_editName);
        ediEmail = (EditText)findViewById(R.id.profil_fix_editEmail);
        ediPassword = (EditText)findViewById(R.id.profil_fix_editPassword);
        ediPasswordConfirm = (EditText)findViewById(R.id.profil_fix_editPasswordConfirm);
        buttonDone = (Button)findViewById(R.id.profil_fix_buttonDone);

        mAuth = FirebaseAuth.getInstance();



        buttonDone.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final String ID = ediEmail.getText().toString();
                final String PW = ediPassword.getText().toString();
                final String NAME = ediName.getText().toString();

                if(ID.indexOf(".com") == -1) {
                    Toast.makeText(menu3_1.this, "이메일 형식을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }
                // 이메일 입력확인
                if(ediEmail.getText().toString().length() == 0) {
                    Toast.makeText(menu3_1.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }
                // 비밀번호 입력확인
                if(ediPassword.getText().toString().length() == 0) {
                    Toast.makeText(menu3_1.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediPassword.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력확인
                if(ediPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(menu3_1.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    ediPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!ediPassword.getText().toString().equals(ediPasswordConfirm.getText().toString())) {
                    Toast.makeText(menu3_1.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    ediPassword.setText("");
                    ediPasswordConfirm.setText("");
                    ediPassword.requestFocus();
                    return;
                }

                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);

                Gson gson = new Gson();

                String iw = auto.getString(ID,"");
                if (iw != ""){
                    Toast.makeText(menu3_1.this, "이메일이 중복되었습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    ediEmail.requestFocus();
                    return;
                }

                User_Info user_info = new User_Info(ID,PW,NAME);
                String json = gson.toJson(user_info);

                SharedPreferences.Editor join = auto.edit();

                join.putString(ID,json);
                join.remove(user_email.getString("Login_email",""));
                join.apply();

                String b = mAuth.getCurrentUser().getEmail();
                b = b.replace("."," ");
                conditionRef.child(b).removeValue();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateEmail(ID).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(menu3_1.this, "회원정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                UserModel userModel = new UserModel(NAME,ID,PW,user.getUid());
                String a = ID;
                a = a.replace("."," ");


                conditionRef.child(a).setValue(userModel);


                SharedPreferences.Editor editor = user_email.edit();
                editor.putString("Login_email",ID);
                editor.apply();

                Intent intent = new Intent(menu3_1.this,Menu6.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
