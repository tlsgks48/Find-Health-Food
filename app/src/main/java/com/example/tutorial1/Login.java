package com.example.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial1.GsonTest.Address;
import com.example.tutorial1.GsonTest.FamilyMember;
import com.example.tutorial1.GsonTest.User_Info;
import com.example.tutorial1.model.UserModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 10; // 애가 10번 인것을 리절트액티비티로 넘겨준다.
    private EditText editEmail;
    Button button;
    Button button1;
    Button button_google;
    Button button_facebook;


    Button login_test;

    private EditText password;
    CheckBox auto_checkBox;
    ImageView imageView;
    private int i = 0;
    private boolean tt = true;
    private int a = 0;

    private GoogleSignInClient mGoogleSignInClient;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private CallbackManager mCallbackManager;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("users");

  //  EditText idconfirm;
   // EditText pwconfirm;
    String idconfirm="";
    String pwconfirm="";
    String loginId, loginPwd, idpwd, ID, PW;
    int check=0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
          //  updateThread();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Gson gson = new Gson();

        // 중요한건 ArrayList -> Json으로 변환 한 다음, 값을 가져올떄 : Json -> ArrayList로 변환을 어떻게 해야되는가?
        Address address = new Address("Germany", "Berlin");

        List<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("wife",30));
        family.add(new FamilyMember("Daughter",5));

        Employee employee = new Employee("John",30,"john@gmail.com", address,family);
        String json = gson.toJson(employee);


        //Json은 문자열 대신 문자열 형식임.
/*        String json = "{\"address\":{\"city\":\"New York\",\"country\":\"USA\"},\"age\":30,\"first_name\":\"John\",\"mail\":\"john@gmail.com\"}";
        Employee employee = gson.fromJson(json,Employee.class);

        // 어레이 리스트로 하는 방법은,
        Type familyType = new TypeToken<ArrayList<FamilyMember>>(){}.getType();
        ArrayList<FamilyMember> family = gson.fromJson(json, familyType);
        */

        editEmail = (EditText)findViewById(R.id.editEmail);
        button = (Button)findViewById(R.id.button3);
        button1 = (Button)findViewById(R.id.button4);
        password = (EditText)findViewById(R.id.editText2);
        button_google = (Button)findViewById(R.id.button_google);
        // button_facebook = (Button)findViewById(R.id.button_facebook);
        auto_checkBox = (CheckBox)findViewById(R.id.login_auto_checkBox);
        imageView = (ImageView)findViewById(R.id.login_imageView);


/*        login_test = (Button)findViewById(R.id.login_test);

        login_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();

                editor.remove("a@a.com");
                editor.remove("s@s.com");
                editor.remove("d@d.com");
                editor.remove("q@q.com");
                editor.remove("t@t.com");
                editor.remove("z@z.com");
                editor.remove("m@m.com");
                editor.remove("w@w.com");
                editor.remove("u@u.com");
                editor.apply();
            }
        });*/


        final SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        // 처음에는 쉐어드프리퍼런스에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번째 인자는 값입니다.
        // 첨엔 값이 없으므로 키값은 원하는 것으로 하고 값을 null
        loginId = auto.getString("inputId2",null);
        loginPwd = auto.getString("inputPwd2",null);

        // 파이어베이스 로그인 상태 체크
/*        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // 유저 로그인
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("email",idconfirm);
                    startActivityForResult(intent, 1001);
                    finish();
                }
                else {
                    // 유저 로그아웃웃
               }
            }
        };*/

        // 자동로그인 조건문, 자동로그인을 체크하고 로그인하면 inputId2에 ID랑 비밀번호를 저장해서, 값이 들어있으면, 자동로그인~~
        if(loginId !=null && loginPwd != null) {
            if(loginId.equals(auto.getString("inputId2",null)) && loginPwd.equals(auto.getString("inputPwd2",null))) {
                Toast.makeText(Login.this, loginId+"님 자동로그인 입니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                idconfirm = loginId;
                intent.putExtra("email",idconfirm);
                startActivityForResult(intent, 1001);
                finish();
            }
        }

        //자동 로그인 체크부분을 체크하는지?
        auto_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    check = 1;
                }
                else {
                    check = 0;
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        button_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


/*        // 페이스북 연동
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)findViewById(R.id.)
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });*/


        //
            // 로그인 버튼 누를때.
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 파이어베이스 로그인.
                    //firebaseLogin(editEmail.getText().toString(),password.getText().toString());


                        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

                        Gson gson = new Gson();

                        SharedPreferences.Editor autoLogin = auto.edit();

                        String iw = auto.getString(editEmail.getText().toString(),"");

                        // 이메일이랑 비번 친것이, 쉐어드에 저장되어 있는 ID라면 이메일이랑, 비번 체크.
                        if(iw != "") {
                            User_Info obj = gson.fromJson(iw,User_Info.class);
                            if ( editEmail.getText().toString().equals(obj.getmEmail()) && password.getText().toString().equals(obj.getmPassword())) {
                                if(check == 1) {
                                    autoLogin.putString("inputId2",obj.getmEmail());
                                    autoLogin.putString("inputPwd2",obj.getmPassword());
                                    autoLogin.commit();
                                }
                                Toast.makeText(Login.this, editEmail.getText().toString()+"님이 로그인 하였습니다.", Toast.LENGTH_SHORT).show();
                                // 다른 액티비티에서 User의 이메일을 공유하기 위해서, 이메일 만을 위한 쉐어드파일에 저장.
                                SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor email_user = user_email.edit();
                                email_user.putString("Login_email",obj.getmEmail());
                                email_user.commit();
                                // 저장 완료

                                firebaseLogin(editEmail.getText().toString(),password.getText().toString(),obj.getmName());

                                //Intent intent = new Intent(Login.this, MainActivity.class);
                                Intent intent = new Intent(Login.this, Menu5.class);
                                intent.putExtra("email",idconfirm);
                                startActivityForResult(intent, 1001);
                                finish();
                            }
                            else if (editEmail.getText().toString().equals(obj.getmEmail()) && !password.getText().toString().equals(obj.getmPassword())) {
                                Toast.makeText(Login.this, "비밀번호가 틀렸습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                password.setText("");
                                password.requestFocus();
                            }
                        }
                        else {
                            Toast.makeText(Login.this, "회원가입된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                            editEmail.setText("");
                            password.setText("");
                            editEmail.requestFocus();
                        }



/*                        Map<String,?> keys = auto.getAll();
                        for(Map.Entry<String,?> entry : keys.entrySet()) {
                            Log.d("map login", entry.getKey() + ": "+entry.getValue().toString());
                            idpwd = auto.getString(entry.getValue().toString(),"");
                            if(editEmail.getText().toString().equals(entry.getValue().toString())){
                                idconfirm = entry.getKey();
                                ID = entry.getValue().toString();
                                if (password.getText().toString().equals(idpwd)) {
                                    PW = idpwd;
                                }
                            }
                        }*/
                }
           });


        // 회원가입창으로 가는 회원가입 버튼 누를때.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),join.class);

                // SINGLE_TOP : 이미 만들어진게 있으면 그걸 쓰고, 없으면 만들어서 써라
               // startActivity(intent);

                // 동시에 사용 가능
                // intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // 인텐트를 보내면서 다음 액티비티로부터 데이터를 받기 위해 식별번호(1000)을 준다.
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(mAuthListener);
        //Toast.makeText(Login.this, "Start", Toast.LENGTH_SHORT).show();
        tt = true;

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (tt) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {
                    }
                }

            }
        });
        myThread.start();


/*        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(Login.this, "데이터베이스 값 변할때 작동.", Toast.LENGTH_SHORT).show();
                String text = dataSnapshot.getValue(String.class);
                login_test_text.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, "데이터베이스 에러 발생.", Toast.LENGTH_SHORT).show();
            }
        });

        login_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionRef.setValue(editEmail.getText().toString());
            }
        });*/
    }

    @Override
    protected void onStop() {
        super.onStop();
   //     mAuth.addAuthStateListener(mAuthListener);
    }

    private void updateThread() {
        int mod = i % 7;

        switch (mod) {
            case 0:
                i++;
                imageView.setImageResource(R.drawable.logo);
                break;
            case 1:
                i++;
                imageView.setImageResource(R.drawable.bea);
                break;
            case 2:
                i++;
                imageView.setImageResource(R.drawable.eyefood_1);
                break;
            case 3:
                i++;
                imageView.setImageResource(R.drawable.bitamin_c);
                break;
            case 4:
                i++;
                imageView.setImageResource(R.drawable.gan);
                break;
            case 5:
                i++;
                imageView.setImageResource(R.drawable.omega3);
                break;
            case 6:
                i++;
                imageView.setImageResource(R.drawable.usangun);
                break;
        }
        // myi.setText(String.valueOf(i));
    }

    @Override
    protected void onPause() {
        super.onPause();
        tt = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    // onResume() 메서드는 Activity로 돌아올 경우 꼭 실행이 되는 메서드
    // startActivityForResult() 메서드를 실행시킬 경우 Activity로 돌아올 때 onResume() 메서드와 onActivityResult() 메서드 중
    // 어떤 것이 우선순위가 있는 것인지 확인해보지 않고서는 헷갈리기 쉬운 부분.
    // startActivityForResult() 메서드가 먼저 실행되고 onResume() 메서드가 나중 실행된다!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 페이스북
       // mCallbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);


        // setResult를 통해 받아온 요청번호, 상태, 데이터
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");
        if(requestCode == 1001) {
            finish();
        }

        if(requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(Login.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
            idconfirm = data.getStringExtra("email");
            pwconfirm = data.getStringExtra("password");
        }

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        // 인텐트로 넘어온 값이 10번이라면 구글 로그인이라는것.
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    // 파이어베이스로 값을 넘겨줌.
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "구글 로그인 연동. "+user.getEmail()+"님이 로그인 하였습니다.", Toast.LENGTH_SHORT).show();

                            SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor email_user = user_email.edit();
                            email_user.putString("Login_email",user.getEmail());
                            email_user.commit();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("email",idconfirm);
                            startActivityForResult(intent, 1001);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            // 실패했을때.
                            Toast.makeText(Login.this, "로그인 실패.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "페이스북 로그인 연동 성공.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "페이스북 로그인 연동 실패.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void firebaseLogin(final String email, final String password, final String name) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserModel userModel = new UserModel(name,email,password,user.getUid());
                            String a = email;
                            a = a.replace("."," ");
                            conditionRef.child(a).setValue(userModel);

                            // uid가 필요할지 모르니 쉐어드에 아이디별로 저장해두자.
                            SharedPreferences sharedUid = getSharedPreferences("firebaseUid", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedUid.edit();

                            SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);

                            editor.putString(user_email.getString("Login_email",""),user.getUid());
                            editor.apply();

                            Toast.makeText(Login.this, "firebase 로그인 연동 성공. ", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "firebase 로그인 연동 실패.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }



}
