package com.example.tutorial1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorial1.Data.food1;
import com.example.tutorial1.Data.food2;
import com.example.tutorial1.Data.food3;
import com.example.tutorial1.Data.food4;
import com.example.tutorial1.Data.food5;
import com.example.tutorial1.Data.food6;
import com.example.tutorial1.Data.food7;
import com.example.tutorial1.Data.food8;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public static ArrayList<Item> mpersons;
    private LayoutInflater mInflate;
    private Context mcontext;

    public RecyclerViewAdapter(Context context, ArrayList<Item> persons) {
        this.mcontext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mpersons = persons;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item_layout, parent, false); // 뷰 생성(아이템 레이아웃을 기반으로)
        MyViewHolder viewHolder = new MyViewHolder(view); // 아이템 레이아웃을 기반으로 생성된 뷰를 뷰홀더에 인자로 넣어줌.
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) { // 포지션이 위치 아닐까???

        //데이터와 뷰를 바인딩.
        final Item data = mpersons.get(position); // 위치에 따라서 그에 맞는 데이터를 얻어오게 한다.

        holder.name.setText(mpersons.get(position).getName()); // 앞서 뷰홀더에 세팅해준 것을 각 위치에 맞는 것들로 보여주게 하기 위해서 세팅해준다.
        holder.imageView.setImageResource(mpersons.get(position).getPhoto());
        holder.summary.setText(mpersons.get(position).getSummary());
        holder.memo.setText(mpersons.get(position).getMemo());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((mpersons.get(position).name).equals("배즙")) {
                    Intent intent = new Intent(mcontext, food1.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("루테인")) {
                    Intent intent = new Intent(mcontext, food2.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("비타민C")) {
                    Intent intent = new Intent(mcontext, food3.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("천마")) {
                    Intent intent = new Intent(mcontext, food4.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("간건강 슈퍼케어")) {
                    Intent intent = new Intent(mcontext, food5.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("홍삼 액기스")) {
                    Intent intent = new Intent(mcontext, food6.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("오메가3")) {
                    Intent intent = new Intent(mcontext, food7.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }

                if((mpersons.get(position).name).equals("유산균")) {
                    Intent intent = new Intent(mcontext, food8.class);
                    intent.putExtra("position2",position);
                    mcontext.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mpersons.size();
    }

    // 마의뷰홀더에서 초기적으로 세팅을 해주는것,(item에 맞게),
    // 그런다음 onBindViewHolder 이곳에서 각 보이는 위치별로 화면에 보이게 세팅해줌..
    // getItemCount -> onCreateViewHolder -> MyViewHolder -> onBindViewHolder 순으로 들어오게됨.
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;
        public TextView summary;
        public TextView memo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name1);
            imageView = (ImageView) itemView.findViewById(R.id.image_bea);
            summary = (TextView) itemView.findViewById(R.id.tv_summary);
            memo = (TextView) itemView.findViewById(R.id.tv_memo);
        }

    }

    private void saveData(int position) {
        SharedPreferences user_email = mcontext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("user_food_comment",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mpersons);
        editor.putString(user_email.getString("Login_email","")+String.valueOf(position), json);
        editor.apply();
    }
}
