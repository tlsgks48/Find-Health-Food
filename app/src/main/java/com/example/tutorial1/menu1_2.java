package com.example.tutorial1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class menu1_2 extends AppCompatActivity {
    public static final String STATE_FOOD = "Pfood";
    public static final String STATE_TEXT = "Pfoodtext";
    public static final String STATE_TEXT2 = "Pfoodtext2";
    public static final String STATE_TEXT3 = "Pfoodtext3";
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    private EditText editText;

    int mfood = 0;
    String mtext = "";
    String mtext2 = "";
    String mtext3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1_2);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        editText = (EditText) findViewById(R.id.editText1);

/*        if (savedInstanceState == null) { // savedInstanceState가 null이면 아무것도 상태가 저장되있지 않은 상태이다.

        } else {
            mfood = savedInstanceState.getInt(STATE_FOOD);
            mtext = savedInstanceState.getString(STATE_TEXT);
            textView1.setText("추가된 건강식품 : " + mfood);
            textView2.setText(mtext);
        }*/

    }

    public void onFood(View view) {
        mfood++;
        textView1.setText("추가된 건강식품 : " + mfood);

        if ( mfood == 1) {
            mtext = editText.getText().toString();
            textView2.setText(mtext);
        }
        if ( mfood == 2) {
            mtext2 = editText.getText().toString();
            textView3.setText(mtext2);
        }
        if ( mfood == 3) {
            mtext3 = editText.getText().toString();
            textView4.setText(mtext3);
        }
    }

    // 화면이 의도치 않게 종료 되었을 경우, 그 상태를 저장하는 함수.
    @Override
    protected void onSaveInstanceState(Bundle outState) { // 안드로이드는 번들에다가 단순한 데이터를 담을 수 있다. outState 번들에다가 데이터를 담고, 위에 크리트에서
        outState.putInt(STATE_FOOD, mfood);                  // savedInstanceState에서 데이터를 복원한다.
        outState.putString(STATE_TEXT, mtext);
        outState.putString(STATE_TEXT2, mtext2);
        outState.putString(STATE_TEXT3, mtext3);

        super.onSaveInstanceState(outState);
    }
// onsavedInstanceState는 pause되고 난 후에 실행되고 다음에 stop이 실행된다. puase -> onsavedInstanceState -> stop
    //  그리고 다시 시작 되면은 Create -> start -> onRestoreInstanceState -> Resum순으로 실행된다.
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) { // 이 함수는 번들에 데이터가 저장되고 복원할 데이터가 있을때만 호출됨. 따라서 따로 널체크는 필요없다.
        super.onRestoreInstanceState(savedInstanceState);

        mfood = savedInstanceState.getInt(STATE_FOOD);
        mtext = savedInstanceState.getString(STATE_TEXT);
        mtext2 = savedInstanceState.getString(STATE_TEXT2);
        mtext3 = savedInstanceState.getString(STATE_TEXT3);
        textView1.setText("추가된 건강식품 : " + mfood);
        textView2.setText(mtext);
        textView3.setText(mtext2);
        textView4.setText(mtext3);
    }
}
