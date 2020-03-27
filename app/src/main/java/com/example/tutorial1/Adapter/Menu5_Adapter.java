package com.example.tutorial1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutorial1.R;

import java.util.ArrayList;

public class Menu5_Adapter extends RecyclerView.Adapter<Menu5_Adapter.MyViewHoder> {

    public static ArrayList<Menu5_chatroom_item> mList_menu5;
    private LayoutInflater mInflate;
    private Context mcontext;



    public class MyViewHoder extends RecyclerView.ViewHolder {
        public TextView chatRoom;
        public TextView textView_last_message;

        public MyViewHoder(@NonNull View view) {
            super(view);
            this.chatRoom = (TextView) view.findViewById(R.id.menu5_chatroom_textview_item);
            this.textView_last_message = (TextView) view.findViewById(R.id.chatitem_textview_lastMessage);
        }
    }

    public Menu5_Adapter(Context context, ArrayList<Menu5_chatroom_item> List_menu5) {
        this.mcontext = context;
        this.mInflate = LayoutInflater.from(context);
        mList_menu5 = List_menu5;
    }

    @NonNull
    @Override
    public Menu5_Adapter.MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflate.inflate(R.layout.menu5_chatroom_item,viewGroup,false);
        MyViewHoder viewHoder = new MyViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Menu5_Adapter.MyViewHoder holder, int position) {
        holder.chatRoom.setText(mList_menu5.get(position).getChatroom());

        holder.textView_last_message.setText("");

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, MessageActivity.class);
                mcontext.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mList_menu5.size();
    }


}
