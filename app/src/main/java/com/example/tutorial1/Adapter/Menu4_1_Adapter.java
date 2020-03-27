package com.example.tutorial1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorial1.R;
import com.example.tutorial1.Data.food1;
import com.example.tutorial1.Data.food2;
import com.example.tutorial1.Data.food3;
import com.example.tutorial1.Data.food4;
import com.example.tutorial1.Data.food5;
import com.example.tutorial1.Data.food6;
import com.example.tutorial1.Data.food7;
import com.example.tutorial1.Data.food8;
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

public class Menu4_1_Adapter extends RecyclerView.Adapter<Menu4_1_Adapter.Menu4_1ViewHolder> {
    private ArrayList<Menu4_1_Item> mList4_1;
    private Context context;

    public class Menu4_1ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;
        public TextView summary;
        public TextView memo;
        public Menu4_1ViewHolder(@NonNull View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.Menu4_1_tv_name1);
            this.imageView = (ImageView) view.findViewById(R.id.Menu4_1_image_item);
            this.summary = (TextView) view.findViewById(R.id.Menu4_1_tv_summary);
            this.memo = (TextView) view.findViewById(R.id.Menu4_1_tv_memo);
        }
    }

    public Menu4_1_Adapter(Context context,ArrayList<Menu4_1_Item> list) {
        this.context = context;
        this.mList4_1 = list;
    }


    @NonNull
    @Override
    public Menu4_1_Adapter.Menu4_1ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu4_1_item,viewGroup,false);
        Menu4_1ViewHolder viewHolder = new Menu4_1ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Menu4_1_Adapter.Menu4_1ViewHolder viewholder, final int position) {
        viewholder.name.setText(mList4_1.get(position).Menu4_1Name);
        viewholder.imageView.setImageResource(mList4_1.get(position).Menu4_1Photo);
        viewholder.summary.setText(mList4_1.get(position).Menu4_1Summary);
        viewholder.memo.setText(mList4_1.get(position).Menu4_1Memo);

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList4_1.get(position).Menu4_1Name.equals("감귤")) {
                    Intent intent = new Intent(context, mon1_1.class);
                    intent.putExtra("position2",1);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("고사리")) {
                    Intent intent = new Intent(context, mon2_1.class);
                    intent.putExtra("position2",3);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("시금치")) {
                    Intent intent = new Intent(context, mon2_2.class);
                    intent.putExtra("position2",4);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("목이버섯")) {
                    Intent intent = new Intent(context, mon3_1.class);
                    intent.putExtra("position2",6);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("아스파라거스")) {
                    Intent intent = new Intent(context, menu2_1.class);
                    intent.putExtra("position2",8);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("더덕")) {
                    Intent intent = new Intent(context, menu2_2.class);
                    intent.putExtra("position2",9);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("죽순")) {
                    Intent intent = new Intent(context, mon5_1.class);
                    intent.putExtra("position2",11);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("오이")) {
                    Intent intent = new Intent(context, mon5_2.class);
                    intent.putExtra("position2",12);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("가지")) {
                    Intent intent = new Intent(context, mon6_1.class);
                    intent.putExtra("position2",14);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("보리")) {
                    Intent intent = new Intent(context, mon6_2.class);
                    intent.putExtra("position2",15);
                    context.startActivity(intent);

                }
                if(mList4_1.get(position).Menu4_1Name.equals("애호박")) {
                    Intent intent = new Intent(context, mon7.class);
                    intent.putExtra("position2",17);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("포도")) {
                    Intent intent = new Intent(context, mon8_1.class);
                    intent.putExtra("position2",19);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("고구마순")) {
                    Intent intent = new Intent(context, mon8_2.class);
                    intent.putExtra("position2",20);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("영지버섯")) {
                    Intent intent = new Intent(context, mon9_1.class);
                    intent.putExtra("position2",22);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("흑미")) {
                    Intent intent = new Intent(context, mon9_2.class);
                    intent.putExtra("position2",23);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("새송이버섯")) {
                    Intent intent = new Intent(context, mon10_1.class);
                    intent.putExtra("position2",25);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("도라지")) {
                    Intent intent = new Intent(context, mon10_2.class);
                    intent.putExtra("position2",26);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("무")) {
                    Intent intent = new Intent(context, mon11.class);
                    intent.putExtra("position2",28);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("키위")) {
                    Intent intent = new Intent(context, mon12_1.class);
                    intent.putExtra("position2",30);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("브로콜리")) {
                    Intent intent = new Intent(context, mon12_2.class);
                    intent.putExtra("position2",31);
                    context.startActivity(intent);
                }

                if(mList4_1.get(position).Menu4_1Name.equals("배즙")) {
                    Intent intent = new Intent(context, food1.class);
                    intent.putExtra("position2",0);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("루테인")) {
                    Intent intent = new Intent(context, food2.class);
                    intent.putExtra("position2",1);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("비타민C")) {
                    Intent intent = new Intent(context, food3.class);
                    intent.putExtra("position2",2);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("천마")) {
                    Intent intent = new Intent(context, food4.class);
                    intent.putExtra("position2",3);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("간건강 슈퍼케어")) {
                    Intent intent = new Intent(context, food5.class);
                    intent.putExtra("position2",4);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("홍삼 액기스")) {
                    Intent intent = new Intent(context, food6.class);
                    intent.putExtra("position2",5);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("오메가3")) {
                    Intent intent = new Intent(context, food7.class);
                    intent.putExtra("position2",6);
                    context.startActivity(intent);
                }
                if(mList4_1.get(position).Menu4_1Name.equals("유산균")) {
                    Intent intent = new Intent(context, food8.class);
                    intent.putExtra("position2",7);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mList4_1 ? mList4_1.size() : 0);
    }


}
