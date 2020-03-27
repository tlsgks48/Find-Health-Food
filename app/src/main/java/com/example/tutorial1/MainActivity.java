package com.example.tutorial1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.tutorial1.Fragment.Ffmenu1;
import com.example.tutorial1.Fragment.FpageAdapter;

public class MainActivity extends AppCompatActivity {

    Ffmenu1 ffmenu1;
    public static String Mainidconfirm="";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 툴바 사용설정
        setSupportActionBar(toolbar);


        //탭 레이아웃 위쪽
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("오늘 건강식품 체크"));
        tabs.addTab(tabs.newTab().setText("이달의 음식"));
        tabs.addTab(tabs.newTab().setText("건강식품"));

        //탭 레이아웃 아래쪽

        Mainidconfirm = getIntent().getStringExtra("email");


        //어댑터설정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final FpageAdapter fpageAdapter = new FpageAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(fpageAdapter);

        //탭메뉴를 클릭하면 해당 프래그먼트로 변경-싱크화
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        /*tabs2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("나의 관심")){
                    Intent intent = new Intent(MainActivity.this,Menu4.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/

        /*
        ffmenu1 = new Ffmenu1();
        setDefaultfragment();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu1 ffmenu1 = new Ffmenu1();
                transaction.replace(R.id.main_scroll, ffmenu1);
                transaction.commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu2 ffmenu2 = new Ffmenu2();
                transaction.replace(R.id.main_scroll, ffmenu2);
                transaction.commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu3 ffmenu3 = new Ffmenu3();
                transaction.replace(R.id.main_scroll, ffmenu3);
                transaction.commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Menu4.class);

                startActivity(intent);

*//*                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu4 ffmenu4 = new Ffmenu4();
                transaction.replace(R.id.main_scroll, ffmenu4);
                transaction.commit();*//*
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Menu5.class);
                startActivity(intent);

*//*                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu5 ffmenu5 = new Ffmenu5();
                transaction.replace(R.id.main_scroll, ffmenu5);
                transaction.commit();*//*
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Menu6.class);
                intent.putExtra("email",Mainidconfirm);

*//*                intent.putExtra("email",Mainidconfirm);
                setResult(RESULT_OK,intent);*//*

                startActivity(intent);

*//*                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Ffmenu6 ffmenu6 = new Ffmenu6();
                transaction.replace(R.id.main_scroll, ffmenu6);
                transaction.commit();*//*
            }
        });


    }

    private void setDefaultfragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_scroll, ffmenu1);
        transaction.commit();

    }*/

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(resultCode == RESULT_OK) {
                Mainidconfirm = data.getStringExtra("email");
            }

    }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_menu4:
                Intent intent = new Intent(MainActivity.this,Menu4.class);
                startActivity(intent);
                return true;

            case R.id.toolbar_menu5:
                Intent intent2 = new Intent(MainActivity.this,Menu5.class);
                startActivity(intent2);
                return true;

            case R.id.toolbar_menu6:
                Intent intent3 = new Intent(MainActivity.this,Menu6.class);
                intent3.putExtra("email",Mainidconfirm);
                startActivity(intent3);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
