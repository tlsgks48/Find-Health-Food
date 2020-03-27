package com.example.tutorial1.TestRecyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorial1.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;
    private Context mContext;

    // 1. 컨텍스트 메뉴를 사용하라면 리사이클러뷰.ViewHolder를 상속받은 클래스에서
    // OnCreateContextMenuListener 리스너를 구현해야 한다.

    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        this.mContext = context;
        this.mList = list;
    }

    // 리사이클러뷰에 새로운 데이터를 보여주기 위해 필요한 뷰홀더를 생성해야 할 때 호출.

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu1_1_list, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    // 어댑터의 특정 위치(포지션)에 있는 데이터를 보여줘야 할때 호출.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);

        viewholder.id.setText(mList.get(position).getId());
        viewholder.english.setText(mList.get(position).getEnglish());
        viewholder.korean.setText(mList.get(position).getKorean());

    }

    @Override
    public int getItemCount() { // 널이 아니면 mList의 사이즈, 널이면 0
        return (null != mList ? mList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected TextView id;
        protected TextView english;
        protected TextView korean;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.textview_recyclerview_menu1_1_id);
            this.english = (TextView) view.findViewById(R.id.textview_recyclerview_menu1_1_english);
            this.korean = (TextView) view.findViewById(R.id.textview_recyclerview_menu1_1_korean);

            view.setOnCreateContextMenuListener(this); // 2. OnCreateContextMenuListener 리스너를 현재 클래스에서 구현한다고 설정.
        }

        private void saveData() {
            SharedPreferences user_email = (mContext).getSharedPreferences("user_email",Context.MODE_PRIVATE);
            SharedPreferences sharedPreferences = (mContext).getSharedPreferences("sharedTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(mList);
            editor.putString(user_email.getString("Login_email",""), json);
            editor.apply();
        }

        private void deleteData() {
            SharedPreferences sharedPreferences = (mContext).getSharedPreferences("sharedTest",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(mList);
            editor.remove("task list");

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분하게 됩니다.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private  final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: // 5. 편집 항목을 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용.
                        View view = LayoutInflater.from(mContext).inflate(R.layout.edit_box,null,false);
                        builder.setView(view);

                        final Button ButtonSubmit = (Button) view.findViewById(R.id.edittext_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_english);
                        final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextID.setText(mList.get(getAdapterPosition()).getId());
                        editTextEnglish.setText(mList.get(getAdapterPosition()).getEnglish());
                        editTextKorean.setText(mList.get(getAdapterPosition()).getKorean());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            // 7. 수정 버튼을 클릭하면 현재 Ui에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strEnglish = editTextEnglish.getText().toString();
                                String strKorean = editTextKorean.getText().toString();

                                Dictionary dict = new Dictionary(strID,strEnglish,strKorean);

                                // 8. ListArray에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);

                                // 9. 어댑터에서 리사이클러뷰에 반영하도록 합니다.
                                notifyItemChanged(getAdapterPosition());
                                saveData();

                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                        break;
                    case 1002: // 10. 삭제 항목을 선택시

                        // 11. 어레이리스트에서 해당 데이터를 삭제하고
                        mList.remove(getAdapterPosition());

                        // 12. 어댑터에서 리사이클러뷰에 반영하도록 합니다.
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());
                        saveData();
                        break;
                }
                return true;
            }
        };
    }
}
