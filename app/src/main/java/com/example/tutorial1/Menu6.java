package com.example.tutorial1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu6 extends AppCompatActivity implements View.OnClickListener{
    TextView textView1;
    TextView textView2;
    TextView textView3;
    String M6idconfirm="";

    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    Button camerabtn;
    Button loadbtn;
    TextView picturebtn;
    TextView profilName;
    final static int TAKE_PICTURE = 1;

    String mCurrentPhotoPath;
    final static int REQUEST_TAKE_PHOTO = 1;
    final static int PICK_IMAGE_REQUEST = 2;
    static final String TAG2 = "Menu6";
    private FirebaseAuth mAuth;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu6);

        M6idconfirm = getIntent().getStringExtra("email");

        textView1 = (TextView)findViewById(R.id.Logout);
        textView2 = (TextView)findViewById(R.id.profil);
        textView3 = (TextView)findViewById(R.id.LogDistroy);

        imageView = (ImageView)findViewById(R.id.profil_imageView);
        //camerabtn = (Button)findViewById(R.id.profil_camera);
        //loadbtn = (Button)findViewById(R.id.profil_load_image);
        picturebtn = (TextView)findViewById(R.id.profil_picture_select);
        profilName = (TextView)findViewById(R.id.profil_name);


        mAuth = FirebaseAuth.getInstance();
        picturebtn.setOnClickListener(this);

        SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
        profilName.setText(user_email.getString("Login_email","")+"님");

        SharedPreferences profil = getSharedPreferences("profil", Activity.MODE_PRIVATE);
        String proImage = profil.getString(user_email.getString("Login_email",""),"");

        // 프로필 사진에 저장한 값이 있다면.. 나오게끔 조건문..
        if(proImage != "") {
            Bitmap bitmap = StringToBitMap(proImage);
            imageView.setImageBitmap(bitmap);
        }



//        camerabtn.setOnClickListener(this);
   //     loadbtn.setOnClickListener(this);

/*        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "권한 설정 완료");

            }
            else {
                Log.d(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }*/

        // 로그아웃 텍뷰
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 파이어베이스 로그아웃 부분
                mAuth.signOut();

                //

                // 쉐어드프리펀스에 저장된 값들을 로그아웃 버튼을 누르면 삭제하기위해
                // 쉐어드프리펀스를 불러오고. 메인에서 만든 이름으로로
                Intent intent = new Intent(Menu6.this,Login.class);
                startActivity(intent);
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();

                SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
                // editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지움.
                Toast.makeText(Menu6.this, user_email.getString("Login_email","")+"님이 로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();
                editor.remove("inputId2");
                editor.remove("inputPwd2");
                editor.apply();

            }
        });
        // 프로필 수정
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu6.this,menu3_1.class); // menu3_1은 프로필 수정 액티비티.
                startActivity(intent);
            }
        });
        // 회원탈퇴
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Menu6.this, "계정이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        String a = mAuth.getCurrentUser().getEmail();
                        a = a.replace("."," ");
                        conditionRef.child(a).removeValue();

                        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = auto.edit();
                        editor.remove(mAuth.getCurrentUser().getEmail());
                        editor.apply();

                        Intent intent3 = new Intent(Menu6.this, Login.class);
                        startActivity(intent3);
                    }
                };
                DialogInterface.OnClickListener cancel = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(Menu6.this)
                        .setTitle("회원탈퇴를 하시겠습니까?")
                        .setPositiveButton("취소",cancel)
                        .setNegativeButton("확인",ok)
                        .show();

            }
        });
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission: "+ permissions[0]+ "was" + grantResults[0]);
        }
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
/*            case R.id.profil_camera:
                // 카메라 앱을 여는 소스
                dispatchTakePictureIntent();

*//*                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,TAKE_PICTURE);*//*
                break;
            case R.id.profil_load_image:

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // ACTION_PIC과 차이점?
                intent.setType("image/*"); // 이미지만 보이게
                // 인텐트 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);
                break;*/
            case R.id.profil_picture_select:
                DialogInterface.OnClickListener cameraListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dispatchTakePictureIntent();
                    }
                };
                DialogInterface.OnClickListener albumListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // ACTION_PIC과 차이점?
                        intent.setType("image/*"); // 이미지만 보이게
                        // 인텐트 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);
                    }
                };

                DialogInterface.OnClickListener cancelListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("취소",cancelListner)
                        .setNeutralButton("사진촬영",cameraListner)
                        .setNegativeButton("앨범선택",albumListner)
                        .show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
/*            case TAKE_PICTURE:
                if(resultCode == RESULT_OK && data.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if(bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
                break;*/
            case REQUEST_TAKE_PHOTO:
                try {
                    switch (requestCode) {
                        case REQUEST_TAKE_PHOTO:
                            if(resultCode == RESULT_OK) {
                                File file = new File(mCurrentPhotoPath);
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),Uri.fromFile(file));
                                if(bitmap != null) {
                                    ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                                    Bitmap rotatedBitmap = null;
                                    switch (orientation) {
                                        case ExifInterface.ORIENTATION_ROTATE_90:
                                            rotatedBitmap = rotateImage(bitmap,90);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_180:
                                            rotatedBitmap = rotateImage(bitmap,180);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_270:
                                            rotatedBitmap = rotateImage(bitmap,270);
                                        case ExifInterface.ORIENTATION_NORMAL:
                                            default:
                                                rotatedBitmap = bitmap;
                                    }
                                    String profilImage = BitMapToString(rotatedBitmap);
                                    SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
                                    SharedPreferences profil = getSharedPreferences("profil", Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = profil.edit();
                                    editor.putString(user_email.getString("Login_email",""),profilImage);
                                    editor.apply();
                                    imageView.setImageBitmap(rotatedBitmap);
                                }
                            }
                            break;
                    }

                } catch (Exception error) {
                    error.printStackTrace();
                }
            case PICK_IMAGE_REQUEST:
                // 이미지 선택작업을 후의 결과 처리
                try {
                    // 이미지를 하나 골랐을때
                    if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                        // data에서 절대경로로 이미지를 가져옴
                        Uri uri = data.getData();

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        // 이미지가 한계이상(?) 크면 불러 오지 못하므로 사이즈를 줄여 준다.
                        int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024,nh,true);
                        String profilImage = BitMapToString(scaled);
                        SharedPreferences user_email = getSharedPreferences("user_email", Activity.MODE_PRIVATE);
                        SharedPreferences profil = getSharedPreferences("profil", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = profil.edit();
                        editor.putString(user_email.getString("Login_email",""),profilImage);
                        editor.apply();


                        imageView.setImageBitmap(scaled);
                    }
                    else {
                        //Toast.makeText(this,"취소 되었습니다.",Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(this,"로딩 오류.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;


        }

    }
    /*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            M6idconfirm = data.getStringExtra("email");
        }

    }*/

// 카메라로 촬영한 이미지를 파일로 저장해주는 함수.
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg",storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // 카메라 인텐트를 실행하는 부분을 별도 함수로..
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if(photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.tutorial1.fileprovider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
public static Bitmap rotateImage(Bitmap source, float angle) {
    Matrix matrix = new Matrix();
    matrix.postRotate(angle);
    return Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
}

public String BitMapToString(Bitmap bitmap) {
    int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024,nh,true);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    scaled.compress(Bitmap.CompressFormat.PNG,50,baos);
    byte [] b = baos.toByteArray();
    String temp = Base64.encodeToString(b,Base64.DEFAULT);
    return temp;
}

public Bitmap StringToBitMap(String encodeString) {
        try {
            byte [] encodeByte = Base64.decode(encodeString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            return bitmap;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
}

}
