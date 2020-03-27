package com.example.tutorial1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorial1.R;

import java.util.ArrayList;

public class test_double_Item_adapter {

    /*
    package com.example.tutorial1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorial1.Item;
import com.example.tutorial1.R;

import java.util.ArrayList;

    public class MonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<MonItem> mperson2;
        private ArrayList<MonItem2> mperson2_2;
        private LayoutInflater mInflate2;
        private Context mcontext;

        public MonAdapter(Context context, ArrayList<MonItem> person, ArrayList<MonItem2> person2) {
            this.mcontext = context;
            this.mInflate2 = LayoutInflater.from(context);
            this.mperson2 = person;
            this.mperson2_2 = person2;
        }

        class MonViewHolder0 extends RecyclerView.ViewHolder {
            public TextView mon2;
            public TextView name2;
            public ImageView imageView2;
            public TextView summary2;
            public TextView memo2;
            public MonViewHolder0(@NonNull View itemView) {
                super(itemView);
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
            int a = 1;
            if (position == 0) {
                a = 2;
            }
            if (position == 1) {
                a = 0;
            }
            if (position == 2) {
                a = 2;
            }



            return a;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view1 = mInflate2.inflate(R.layout.mon_item, viewGroup, false);
            View view2 = mInflate2.inflate(R.layout.mon_item2, viewGroup, false);
            switch (viewType) {
                case 0:
                    com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0 monViewHolder0 = new com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0(view1);
                    return monViewHolder0;
                case 2:
                    com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2 monViewHolder2 = new com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2(view2);
                    return  monViewHolder2;

            }
            return null;
        }

        // 실제 각 뷰 홀더에 데이터를 연결해주는 함수, 포지션은 0부터 -length까지로 순차적으로 들어옴
        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,final int position) {
            Log.e("type" , String.valueOf(holder.getItemViewType()));
            switch (holder.getItemViewType()) {
                case 0:
                    com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0 monViewHolder0 = (com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0)holder;
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0) holder).mon2.setText(mperson2.get(position).monMon);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0) holder).name2.setText(mperson2.get(position).monName);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0) holder).imageView2.setImageResource(mperson2.get(position).monPhoto);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0) holder).summary2.setText(mperson2.get(position).monSummary);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder0) holder).memo2.setText(mperson2.get(position).monMemo);
                    break;

                case 2:
                    com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2 monViewHolder2 = (com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2)holder;
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).mon2_1.setText(mperson2_2.get(position).monMon2);

                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).name2_1.setText(mperson2_2.get(position).monName2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).imageView2_1.setImageResource(mperson2_2.get(position).monPhoto2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).summary2_1.setText(mperson2_2.get(position).monSummary2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).memo2_1.setText(mperson2_2.get(position).monMemo2);

                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).name2_2.setText(mperson2_2.get(position).monName2_2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).imageView2_2.setImageResource(mperson2_2.get(position).monPhoto2_2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).summary2_2.setText(mperson2_2.get(position).monSummary2_2);
                    ((com.example.tutorial1.Adapter.MonAdapter.MonViewHolder2) holder).memo2_2.setText(mperson2_2.get(position).monMemo2_2);
                    break;




            }

        }

        @Override
        public int getItemCount() {
            return (mperson2.size()+mperson2_2.size());
        }


    }
*/
}
