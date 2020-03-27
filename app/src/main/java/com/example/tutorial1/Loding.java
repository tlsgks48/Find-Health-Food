package com.example.tutorial1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Loding extends AppCompatActivity {

    TextView textView;
    ProgressBar progress;
    // 백그라운드 Task 정의
    BackgroundTask task;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        textView = (TextView) findViewById(R.id.loding_textView);
        progress = (ProgressBar) findViewById(R.id.loding_progressBar);

        task = new BackgroundTask();
        // excute로 실행.
        task.execute(100);
    }

    class BackgroundTask extends AsyncTask<Integer , Integer, Integer> { // doin, onprogressUpdata, onPostExecute의 매개변수 자료형.


        @Override
        protected void onPreExecute() {
            value = 0;
            progress.setProgress(value);
        }


        // 스레드의 주작업 구현
       @Override
        protected Integer doInBackground(Integer... integers) {
            // isCancelled()=> Task가 취소되었을때 즉 cancel 당할때까지 반복
           while (isCancelled() == false) {
               value++;
               if(value >= 100) {
                   break;
               }
               else {
                   publishProgress(value);
               }
               try {
                   Thread.sleep(10);
               } catch (InterruptedException ex) { }
           }
            return value;
        }

        // 중간중간 진행사항을 UI에 업데이트
        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0].intValue());
            textView.setText("로딩중 "+values[0].toString()+"%");
        }

        // 이 Task에서 수행되던 작업이 종료되었을 때 호출.
        @Override
        protected void onPostExecute(Integer integer) {
            progress.setProgress(0);
            textView.setText("로딩 완료");
            Toast.makeText(Loding.this,"로딩 완료", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Loding.this, Login.class);
            startActivity(intent);
        }

        @Override
        protected void onCancelled() {
            progress.setProgress(0);
            Toast.makeText(Loding.this,"로딩 취소", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
