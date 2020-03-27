package com.example.tutorial1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorial1.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.tutorial1.MainActivity.Mainidconfirm;

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.FoodViewHolder> {
    public static ArrayList<Mon1_1_Item> mList;
    private Context mcontext;
    private int i;

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mon1_1_item,viewGroup,false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);

        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder viewholder, int position) {
        viewholder.user.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        viewholder.time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        viewholder.user.setText(mList.get(position).getUser());
        viewholder.id.setText(mList.get(position).getComment());
        viewholder.time.setText(mList.get(position).getTime());
    }

    @Override
    public int getItemCount()  {
        return (null != mList ? mList.size() : 0);
    }

    public Food_Adapter(Context context,ArrayList<Mon1_1_Item> list, int ii) {
        this.mcontext = context;
        this.mList = list;
        this.i = ii;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        protected TextView id;
        protected TextView user;
        protected TextView time;
        public FoodViewHolder(@NonNull View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.mon1_1_item_text);
            this.user = (TextView) view.findViewById(R.id.mon1_1_item_text_user);
            this.time = (TextView) view.findViewById(R.id.mon1_1_item_text_time);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
                Edit.setOnMenuItemClickListener(onEditMenu);
                Delete.setOnMenuItemClickListener(onEditMenu);

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001:
                        SharedPreferences user_email = mcontext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
                        if(mList.get(getAdapterPosition()).getUser().equals(user_email.getString("Login_email","")+"님")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                            View view = LayoutInflater.from(mcontext).inflate(R.layout.mon1_1_edit_box, null, false);
                            builder.setView(view);
                            final Button ButtonSubmit = (Button) view.findViewById(R.id.mon1_1_button_comment_dialog_submit);
                            final EditText editTextID = (EditText) view.findViewById(R.id.mon1_1_edittex_dialog_comment);

                            editTextID.setText(mList.get(getAdapterPosition()).getComment());
                            final AlertDialog dialog = builder.create();
                            // 수정 버튼 클릭하면 현재 UI에 입력되어 있는 내용으로
                            ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String strID = editTextID.getText().toString();

                                    // 현재 시간을 msec로 구한다.
                                    long now = System.currentTimeMillis();
                                    // 현재 시간을 date 변수에 저장.
                                    Date date = new Date(now);
                                    // 시간을 나타내는 포맷을 정한다.
                                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                    // newDate 변수에 값을 저장.
                                    String formatDate = sdfNow.format(date);

                                    SharedPreferences user_email = mcontext.getSharedPreferences("user_email", Context.MODE_PRIVATE);
                                    Mon1_1_Item dict = new Mon1_1_Item(strID, user_email.getString("Login_email", "") + "님", formatDate);

                                    mList.set(getAdapterPosition(), dict);

                                    notifyItemChanged(getAdapterPosition());
                                    saveData();
                                    dialog.dismiss();


                                }
                            });
                            dialog.show();
                        }
                        else {

                        }
                        break;

                    case 1002:
                        SharedPreferences user_email2 = mcontext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
                        if(mList.get(getAdapterPosition()).getUser().equals(user_email2.getString("Login_email","")+"님")) {
                            // 어레이리스트에서 해당 데이터 삭제
                            mList.remove(getAdapterPosition());

                            // 어댑터에서 리사이클러뷰에 반영.
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), mList.size());
                            saveData();
                            SharedPreferences app_food = mcontext.getSharedPreferences("app_attention2", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = app_food.edit();
                            int t = app_food.getInt("c" + String.valueOf(i), 0) - 1;
                            editor.putInt("c" + String.valueOf(i), t);
                            editor.apply();
                        }
                        else {

                        }
                        break;
                }
                return true;
            }
        };
    }
    private void saveData() {
        SharedPreferences user_email = mcontext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("user_food_comment",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString("a"+String.valueOf(i), json);
        editor.apply();
    }
}
