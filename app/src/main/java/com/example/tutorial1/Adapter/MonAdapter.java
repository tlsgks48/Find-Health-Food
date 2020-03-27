package com.example.tutorial1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorial1.R;
import com.example.tutorial1.Data.menu2_1;
import com.example.tutorial1.Data.menu2_2;
import com.example.tutorial1.Data.mon10_1;
import com.example.tutorial1.Data.mon10_2;
import com.example.tutorial1.Data.mon11;
import com.example.tutorial1.Data.mon12_1;
import com.example.tutorial1.Data.mon12_2;
import com.example.tutorial1.Data.mon1_1;
import com.example.tutorial1.Data.mon2_1;
import com.example.tutorial1.Data.mon2_2;
import com.example.tutorial1.Data.mon3_1;
import com.example.tutorial1.Data.mon5_1;
import com.example.tutorial1.Data.mon5_2;
import com.example.tutorial1.Data.mon6_1;
import com.example.tutorial1.Data.mon6_2;
import com.example.tutorial1.Data.mon7;
import com.example.tutorial1.Data.mon8_1;
import com.example.tutorial1.Data.mon8_2;
import com.example.tutorial1.Data.mon9_1;
import com.example.tutorial1.Data.mon9_2;

import java.util.ArrayList;

public class MonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static ArrayList<MonItem> mperson2;
    public static ArrayList<MonItem2> mperson2_2;
    private LayoutInflater mInflate2;
    private Context mcontext;

    public MonAdapter(Context context, ArrayList<MonItem> person, ArrayList<MonItem2> person2) {
        this.mcontext = context;
        this.mInflate2 = LayoutInflater.from(context);
        this.mperson2 = person;
        this.mperson2_2 = person2;
    }

    class MonViewHolder5 extends RecyclerView.ViewHolder {
        public TextView mon2;
        public MonViewHolder5(@NonNull View itemView) {
            super(itemView);

            mon2 = (TextView) itemView.findViewById(R.id.Menu4_2_Comment_item);
        }
    }

    class MonViewHolder6 extends RecyclerView.ViewHolder {
        public TextView name2;
        public ImageView imageView2;
        public TextView summary2;
        public TextView memo2;
        public MonViewHolder6(@NonNull View itemView) {
            super(itemView);

            name2 = (TextView) itemView.findViewById(R.id.Menu4_1_tv_name1);
            imageView2 = (ImageView) itemView.findViewById(R.id.Menu4_1_image_item);
            summary2 = (TextView) itemView.findViewById(R.id.Menu4_1_tv_summary);
            memo2 = (TextView) itemView.findViewById(R.id.Menu4_1_tv_memo);
        }
    }

    class MonViewHolder0 extends RecyclerView.ViewHolder {
        public TextView mon2;
        public TextView name2;
        public ImageView imageView2;
        public TextView summary2;
        public TextView memo2;

        public MonViewHolder0(@NonNull View itemView) {
            super(itemView);

/*            mon2 = (TextView) itemView.findViewById(R.id.mon_tv_month2_1);
            name2 = (TextView) itemView.findViewById(R.id.mon_tv_name2_1);
            imageView2 = (ImageView) itemView.findViewById(R.id.mon_image_item2_1);
            summary2 = (TextView) itemView.findViewById(R.id.mon_tv_summary2_1);
            memo2 = (TextView) itemView.findViewById(R.id.mon_tv_memo2_1);*/

            mon2 = (TextView) itemView.findViewById(R.id.mon_tv_month);
            name2 = (TextView) itemView.findViewById(R.id.mon_tv_name1);
            imageView2 = (ImageView) itemView.findViewById(R.id.mon_image_item);
            summary2 = (TextView) itemView.findViewById(R.id.mon_tv_summary);
            memo2 = (TextView) itemView.findViewById(R.id.mon_tv_memo);
        }
    }

    class MonViewHolder2 extends  RecyclerView.ViewHolder {
        public TextView mon2_1;
        public TextView name2_1;
        public ImageView imageView2_1;
        public TextView summary2_1;
        public TextView memo2_1;
        public TextView name2_2;
        public ImageView imageView2_2;
        public TextView summary2_2;
        public TextView memo2_2;
        public MonViewHolder2(@NonNull View itemView) {
            super(itemView);
            mon2_1 = (TextView) itemView.findViewById(R.id.mon_tv_month2_1);
            name2_1 = (TextView) itemView.findViewById(R.id.mon_tv_name2_1);
            imageView2_1 = (ImageView) itemView.findViewById(R.id.mon_image_item2_1);
            summary2_1 = (TextView) itemView.findViewById(R.id.mon_tv_summary2_1);
            memo2_1 = (TextView) itemView.findViewById(R.id.mon_tv_memo2_1);
            name2_2 = (TextView) itemView.findViewById(R.id.mon_tv_name2_2);
            imageView2_2 = (ImageView) itemView.findViewById(R.id.mon_image_item2_2);
            summary2_2 = (TextView) itemView.findViewById(R.id.mon_tv_summary2_2);
            memo2_2 = (TextView) itemView.findViewById(R.id.mon_tv_memo2_2);
        }
    }
    //  onCreateViewHolder 이전에 호출된다. onCreateViewHolder의 두번째 파라미터인 int에 전달되는 값을 설정하는것.
    //


    @Override
    public int getItemViewType(int position) {

        String is = mperson2.get(position).getMonMon();

        int a = 1;

        if(is != null) {
            a = 5;
        }
        else {
            a = 6;
        }

/*        if (position == 0) {
            a = 0;
        }
        else if (position == 2) {
            a = 0;
        }
        else if (position == 6) {
            a = 0;
        }
        else if (position == 10) {
            a = 0;
        }
        else {
            a = 2;
        }*/

        return a;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view1 = mInflate2.inflate(R.layout.mon_item, viewGroup, false);
        View view2 = mInflate2.inflate(R.layout.mon_item2, viewGroup, false);
        View view5 = mInflate2.inflate(R.layout.menu4_2_item, viewGroup, false);
        View view6 = mInflate2.inflate(R.layout.menu4_1_item, viewGroup, false);
        switch (viewType) {
            case 0:
                MonViewHolder0 monViewHolder0 = new MonViewHolder0(view1);
                return monViewHolder0;
            case 2:
                MonViewHolder2 monViewHolder2 = new MonViewHolder2(view2);
                return  monViewHolder2;
            case 5:
                MonViewHolder5 monViewHolder5 = new MonViewHolder5(view5);
                return  monViewHolder5;
            case 6:
                MonViewHolder6 monViewHolder6 = new MonViewHolder6(view6);
                return  monViewHolder6;


        }
        return null;
    }

    // 실제 각 뷰 홀더에 데이터를 연결해주는 함수, 포지션은 0부터 -length까지로 순차적으로 들어옴
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,final int position) {
        Log.e("type" , String.valueOf(holder.getItemViewType()));
        switch (holder.getItemViewType()) {
            case 0:
                MonViewHolder0 monViewHolder0 = (MonViewHolder0)holder;
                ((MonViewHolder0) holder).mon2.setText(mperson2_2.get(position).monMon2);
                ((MonViewHolder0) holder).name2.setText(mperson2_2.get(position).monName2);
                ((MonViewHolder0) holder).imageView2.setImageResource(mperson2_2.get(position).monPhoto2);
                ((MonViewHolder0) holder).summary2.setText(mperson2_2.get(position).monSummary2);
                ((MonViewHolder0) holder).memo2.setText(mperson2_2.get(position).monMemo2);

                ((MonViewHolder0) holder).imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mperson2_2.get(position).monPhoto2 == R.drawable.mon1_1_gamgul) {
                            Intent intent2 = new Intent(mcontext, mon1_1.class);
                            intent2.putExtra("position2",position);
                            mcontext.startActivity(intent2);
                        }

                        if(mperson2_2.get(position).monPhoto2 == R.drawable.mon3_1_busus) {
                            Intent intent2 = new Intent(mcontext, mon3_1.class);
                            intent2.putExtra("position",position);
                            mcontext.startActivity(intent2);
                        }
                    }
                });
                break;

            case 2:
                MonViewHolder2 monViewHolder2 = (MonViewHolder2)holder;
                ((MonViewHolder2) holder).mon2_1.setText(mperson2_2.get(position).monMon2);

                ((MonViewHolder2) holder).name2_1.setText(mperson2_2.get(position).monName2);
                ((MonViewHolder2) holder).imageView2_1.setImageResource(mperson2_2.get(position).monPhoto2);
                ((MonViewHolder2) holder).summary2_1.setText(mperson2_2.get(position).monSummary2);
                ((MonViewHolder2) holder).memo2_1.setText(mperson2_2.get(position).monMemo2);

                ((MonViewHolder2) holder).name2_2.setText(mperson2_2.get(position).monName2_2);
                ((MonViewHolder2) holder).imageView2_2.setImageResource(mperson2_2.get(position).monPhoto2_2);
                ((MonViewHolder2) holder).summary2_2.setText(mperson2_2.get(position).monSummary2_2);
                ((MonViewHolder2) holder).memo2_2.setText(mperson2_2.get(position).monMemo2_2);
                break;

            case 5:
                MonViewHolder5 monViewHolder5 = (MonViewHolder5)holder;
                ((MonViewHolder5) holder).mon2.setText(mperson2.get(position).monMon);
                break;

            case 6:
                MonViewHolder6 monViewHolder6 = (MonViewHolder6)holder;
                ((MonViewHolder6) holder).name2.setText(mperson2.get(position).monName);
                ((MonViewHolder6) holder).imageView2.setImageResource(mperson2.get(position).monPhoto);
                ((MonViewHolder6) holder).summary2.setText(mperson2.get(position).monSummary);
                ((MonViewHolder6) holder).memo2.setText(mperson2.get(position).monMemo);

                ((MonViewHolder6) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mperson2.get(position).monName.equals("감귤")) {
                            Intent intent = new Intent(mcontext, mon1_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("고사리")) {
                            Intent intent = new Intent(mcontext, mon2_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("시금치")) {
                            Intent intent = new Intent(mcontext, mon2_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("목이버섯")) {
                            Intent intent = new Intent(mcontext, mon3_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("아스파라거스")) {
                            Intent intent = new Intent(mcontext, menu2_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("더덕")) {
                            Intent intent = new Intent(mcontext, menu2_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("죽순")) {
                            Intent intent = new Intent(mcontext, mon5_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("오이")) {
                            Intent intent = new Intent(mcontext, mon5_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("가지")) {
                            Intent intent = new Intent(mcontext, mon6_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("보리")) {
                            Intent intent = new Intent(mcontext, mon6_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("애호박")) {
                            Intent intent = new Intent(mcontext, mon7.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("포도")) {
                            Intent intent = new Intent(mcontext, mon8_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("고구마순")) {
                            Intent intent = new Intent(mcontext, mon8_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("영지버섯")) {
                            Intent intent = new Intent(mcontext, mon9_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("흑미")) {
                            Intent intent = new Intent(mcontext, mon9_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("새송이버섯")) {
                            Intent intent = new Intent(mcontext, mon10_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("도라지")) {
                            Intent intent = new Intent(mcontext, mon10_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("무")) {
                            Intent intent = new Intent(mcontext, mon11.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("키위")) {
                            Intent intent = new Intent(mcontext, mon12_1.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                        if(mperson2.get(position).monName.equals("브로콜리")) {
                            Intent intent = new Intent(mcontext, mon12_2.class);
                            intent.putExtra("position2",position);
                            mcontext.startActivity(intent);
                        }
                    }
                });
                break;


        }

    }


    // 어댑터에서 현재 사용할 수 있는 항목 수.
    // 표시 할 항목이 포함된 컬렉션의 크기를 반환.
    @Override
    public int getItemCount() {
        return mperson2.size();
    }


}
