package com.example.tutorial1.Fragment;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial1.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ffmenu1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Ffmenu1Item> mList;
    private Context mContext;
    private Context mContext2;
    private LayoutInflater minflater;

    static String sta1 = "";


    class Ffmenu1ViewHolder1 extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected CheckBox chId;
        private TextView time;
        private TextView eat_count;
        private TextView checktime;
        public Ffmenu1ViewHolder1(@NonNull View view) {
            super(view);
            this.chId = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item_1);
            this.time = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_hour_min);
            this.eat_count = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_eat_count);
            this.checktime = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_time);

            view.setOnCreateContextMenuListener(this); // 현재 클래스에서 구현한다고 설정.
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줌.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }
        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: // 5. 수정 항목 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box 파일을 사용.
                        View view2 = LayoutInflater.from(mContext).inflate(R.layout.fmenu1_edit_box, null, false);
                        builder.setView(view2);

                        final Button ButtonSubmit = (Button) view2.findViewById(R.id.fmenu1_button_dialog_submit);
                        final EditText editTextID = (EditText) view2.findViewById(R.id.fmenu1_edittex_dialog_id);
                        final EditText editTextMin = (EditText) view2.findViewById(R.id.spinner_fmenu1_edittext_min);
                        final EditText editTextMin2 = (EditText) view2.findViewById(R.id.spinner2_fmenu1_edittext_min);
                        final EditText editTextMin3 = (EditText) view2.findViewById(R.id.spinner3_fmenu1_edittext_min);


                        final Spinner mSpinner = (Spinner) view2.findViewById(R.id.spinner_fmenu1_food);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);

                        final Spinner mSpinner_am_fm = (Spinner) view2.findViewById(R.id.spinner_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_AM_FM));
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_am_fm.setAdapter(adapter2);

                        final Spinner mSpinner_hour = (Spinner) view2.findViewById(R.id.spinner_fmenu1_hour);
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_hour));
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_hour.setAdapter(adapter3);

                        final Spinner mSpinner_min = (Spinner) view2.findViewById(R.id.spinner_fmenu1_min);
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_min));
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_min.setAdapter(adapter4);

                        final Spinner mSpinner_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_count);
                        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_count));
                        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_count.setAdapter(adapter5);

                        final Spinner mSpinner_eat_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_eat_count);
                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_eat_count));
                        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_eat_count.setAdapter(adapter6);

                        // 2회째
                        final Spinner mSpinner2_am_fm = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_AM_FM));
                        adapter2_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_am_fm.setAdapter(adapter2_2);

                        final Spinner mSpinner2_hour = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_hour);
                        ArrayAdapter<String> adapter2_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_hour));
                        adapter2_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_hour.setAdapter(adapter2_3);

                        final Spinner mSpinner2_min = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_min);
                        ArrayAdapter<String> adapter2_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_min));
                        adapter2_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_min.setAdapter(adapter2_4);

                        final Spinner mSpinner2_eat_count = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_eat_count);
                        ArrayAdapter<String> adapter2_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_eat_count));
                        adapter2_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_eat_count.setAdapter(adapter2_6);

                        // 3회째

                        final Spinner mSpinner3_am_fm = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_AM_FM));
                        adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_am_fm.setAdapter(adapter3_2);

                        final Spinner mSpinner3_hour = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_hour);
                        ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_hour));
                        adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_hour.setAdapter(adapter3_3);

                        final Spinner mSpinner3_min = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_min);
                        ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_min));
                        adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_min.setAdapter(adapter3_4);

                        final Spinner mSpinner3_eat_count = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_eat_count);
                        ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_eat_count));
                        adapter3_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_eat_count.setAdapter(adapter3_6);
                        //

                        // 1횟째의 복용 횟수의 따른 나머지 2,3 스피너 비활성화 조건문
                        mSpinner_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);

                                }
                                else if(position == 1){
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else if(position == 2){
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else {
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(true);
                                    mSpinner3_eat_count.setEnabled(true);
                                    mSpinner3_hour.setEnabled(true);
                                    mSpinner3_min.setEnabled(true);
                                    editTextMin3.setFocusableInTouchMode(true);
                                    editTextMin3.setFocusable(true);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextID.setText("");
                                    editTextID.requestFocus();
                                }
                                else {
                                    editTextID.setText(mSpinner.getItemAtPosition(position).toString());
                                    editTextID.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextMin.setText("");
                                    editTextMin.requestFocus();

                                }
                                else{
                                    editTextMin.setText(mSpinner_min.getItemAtPosition(position).toString());
                                    editTextMin.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여준다.
                        editTextID.setText(mList.get(getAdapterPosition()).getCheckname());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strTime = mSpinner_am_fm.getSelectedItem().toString()+" "+mSpinner_hour.getSelectedItem().toString()+" : "+editTextMin.getText().toString();

                                Ffmenu1Item dict = new Ffmenu1Item(strID,strTime,mSpinner_eat_count.getSelectedItem().toString()+" 복용");

                                // 8. 리스트어레이에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);

                                // 9. 어댑터에서 리사이클러뷰에 반영하도록 한다.
                                notifyItemChanged(getAdapterPosition());
                                saveData();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002: // 10. 삭제 선택시

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

    //

    class Ffmenu1ViewHolder2 extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected CheckBox chId2_1;
        private TextView time2_1;
        private TextView eat_count2_1;
        private TextView checktime2_1;

        protected CheckBox chId2_2;
        private TextView time2_2;
        private TextView eat_count2_2;
        private TextView checktime2_2;

        public Ffmenu1ViewHolder2(@NonNull View view) {
            super(view);

            this.chId2_1 = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item2_1);
            this.time2_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_hour_min);
            this.eat_count2_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_eat_count);
            this.checktime2_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_time);

            this.chId2_2 = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item2_2_2);
            this.time2_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_hour_min_2);
            this.eat_count2_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_eat_count_2);
            this.checktime2_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item2_time_2);

            view.setOnCreateContextMenuListener(this); // 현재 클래스에서 구현한다고 설정.
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줌.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: // 5. 수정 항목 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box 파일을 사용.
                        View view2 = LayoutInflater.from(mContext).inflate(R.layout.fmenu1_edit_box, null, false);
                        builder.setView(view2);

                        final Button ButtonSubmit = (Button) view2.findViewById(R.id.fmenu1_button_dialog_submit);
                        final EditText editTextID = (EditText) view2.findViewById(R.id.fmenu1_edittex_dialog_id);
                        final EditText editTextMin = (EditText) view2.findViewById(R.id.spinner_fmenu1_edittext_min);
                        final EditText editTextMin2 = (EditText) view2.findViewById(R.id.spinner2_fmenu1_edittext_min);
                        final EditText editTextMin3 = (EditText) view2.findViewById(R.id.spinner3_fmenu1_edittext_min);


                        final Spinner mSpinner = (Spinner) view2.findViewById(R.id.spinner_fmenu1_food);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);

                        final Spinner mSpinner_am_fm = (Spinner) view2.findViewById(R.id.spinner_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_AM_FM));
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_am_fm.setAdapter(adapter2);

                        final Spinner mSpinner_hour = (Spinner) view2.findViewById(R.id.spinner_fmenu1_hour);
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_hour));
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_hour.setAdapter(adapter3);

                        final Spinner mSpinner_min = (Spinner) view2.findViewById(R.id.spinner_fmenu1_min);
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_min));
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_min.setAdapter(adapter4);

                        final Spinner mSpinner_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_count);
                        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_count));
                        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_count.setAdapter(adapter5);

                        final Spinner mSpinner_eat_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_eat_count);
                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_eat_count));
                        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_eat_count.setAdapter(adapter6);

                        // 2회째
                        final Spinner mSpinner2_am_fm = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_AM_FM));
                        adapter2_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_am_fm.setAdapter(adapter2_2);

                        final Spinner mSpinner2_hour = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_hour);
                        ArrayAdapter<String> adapter2_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_hour));
                        adapter2_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_hour.setAdapter(adapter2_3);

                        final Spinner mSpinner2_min = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_min);
                        ArrayAdapter<String> adapter2_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_min));
                        adapter2_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_min.setAdapter(adapter2_4);

                        final Spinner mSpinner2_eat_count = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_eat_count);
                        ArrayAdapter<String> adapter2_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_eat_count));
                        adapter2_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_eat_count.setAdapter(adapter2_6);

                        // 3회째

                        final Spinner mSpinner3_am_fm = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_AM_FM));
                        adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_am_fm.setAdapter(adapter3_2);

                        final Spinner mSpinner3_hour = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_hour);
                        ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_hour));
                        adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_hour.setAdapter(adapter3_3);

                        final Spinner mSpinner3_min = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_min);
                        ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_min));
                        adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_min.setAdapter(adapter3_4);

                        final Spinner mSpinner3_eat_count = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_eat_count);
                        ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_eat_count));
                        adapter3_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_eat_count.setAdapter(adapter3_6);
                        //

                        // 2번째 활성화...
                        mSpinner_count.setSelection(2);

                        // 1횟째의 복용 횟수의 따른 나머지 2,3 스피너 비활성화 조건문
                        mSpinner_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);

                                }
                                else if(position == 1){
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else if(position == 2){
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else {
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(true);
                                    mSpinner3_eat_count.setEnabled(true);
                                    mSpinner3_hour.setEnabled(true);
                                    mSpinner3_min.setEnabled(true);
                                    editTextMin3.setFocusableInTouchMode(true);
                                    editTextMin3.setFocusable(true);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextID.setText("");
                                    editTextID.requestFocus();
                                }
                                else {
                                    editTextID.setText(mSpinner.getItemAtPosition(position).toString());
                                    editTextID.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextMin.setText("");
                                    editTextMin.requestFocus();

                                }
                                else{
                                    editTextMin.setText(mSpinner_min.getItemAtPosition(position).toString());
                                    editTextMin.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여준다.
                        editTextID.setText(mList.get(getAdapterPosition()).getCheckname());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strTime = mSpinner_am_fm.getSelectedItem().toString()+" "+mSpinner_hour.getSelectedItem().toString()+" : "+editTextMin.getText().toString();

                                Ffmenu1Item dict = new Ffmenu1Item(strID,strTime,mSpinner_eat_count.getSelectedItem().toString()+" 복용");

                                // 8. 리스트어레이에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);

                                // 9. 어댑터에서 리사이클러뷰에 반영하도록 한다.
                                notifyItemChanged(getAdapterPosition());
                                saveData();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002: // 10. 삭제 선택시

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


    //

    class Ffmenu1ViewHolder3 extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        protected CheckBox chId3_1;
        private TextView time3_1;
        private TextView eat_count3_1;
        private TextView checktime3_1;

        protected CheckBox chId3_2;
        private TextView time3_2;
        private TextView eat_count3_2;
        private TextView checktime3_2;

        protected CheckBox chId3_3;
        private TextView time3_3;
        private TextView eat_count3_3;
        private TextView checktime3_3;
        public Ffmenu1ViewHolder3(@NonNull View view) {
            super(view);

            this.chId3_1 = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item3_1);
            this.time3_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_hour_min);
            this.eat_count3_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_eat_count);
            this.checktime3_1 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_time);

            this.chId3_2 = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item3_1_2);
            this.time3_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_hour_min_2);
            this.eat_count3_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_eat_count_2);
            this.checktime3_2 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_time_2);

            this.chId3_3 = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item3_1_3);
            this.time3_3 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_hour_min_3);
            this.eat_count3_3 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_eat_count_3);
            this.checktime3_3 = (TextView) view.findViewById(R.id.checkbox_fmenu1_item3_time_3);

            view.setOnCreateContextMenuListener(this); // 현재 클래스에서 구현한다고 설정.

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줌.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }
        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: // 5. 수정 항목 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box 파일을 사용.
                        View view2 = LayoutInflater.from(mContext).inflate(R.layout.fmenu1_edit_box, null, false);
                        builder.setView(view2);

                        final Button ButtonSubmit = (Button) view2.findViewById(R.id.fmenu1_button_dialog_submit);
                        final EditText editTextID = (EditText) view2.findViewById(R.id.fmenu1_edittex_dialog_id);
                        final EditText editTextMin = (EditText) view2.findViewById(R.id.spinner_fmenu1_edittext_min);
                        final EditText editTextMin2 = (EditText) view2.findViewById(R.id.spinner2_fmenu1_edittext_min);
                        final EditText editTextMin3 = (EditText) view2.findViewById(R.id.spinner3_fmenu1_edittext_min);


                        final Spinner mSpinner = (Spinner) view2.findViewById(R.id.spinner_fmenu1_food);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);

                        final Spinner mSpinner_am_fm = (Spinner) view2.findViewById(R.id.spinner_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_AM_FM));
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_am_fm.setAdapter(adapter2);

                        final Spinner mSpinner_hour = (Spinner) view2.findViewById(R.id.spinner_fmenu1_hour);
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_hour));
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_hour.setAdapter(adapter3);

                        final Spinner mSpinner_min = (Spinner) view2.findViewById(R.id.spinner_fmenu1_min);
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_min));
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_min.setAdapter(adapter4);

                        final Spinner mSpinner_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_count);
                        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_count));
                        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_count.setAdapter(adapter5);

                        final Spinner mSpinner_eat_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_eat_count);
                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_eat_count));
                        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_eat_count.setAdapter(adapter6);

                        // 2회째
                        final Spinner mSpinner2_am_fm = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_AM_FM));
                        adapter2_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_am_fm.setAdapter(adapter2_2);

                        final Spinner mSpinner2_hour = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_hour);
                        ArrayAdapter<String> adapter2_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_hour));
                        adapter2_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_hour.setAdapter(adapter2_3);

                        final Spinner mSpinner2_min = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_min);
                        ArrayAdapter<String> adapter2_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_min));
                        adapter2_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_min.setAdapter(adapter2_4);

                        final Spinner mSpinner2_eat_count = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_eat_count);
                        ArrayAdapter<String> adapter2_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_eat_count));
                        adapter2_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_eat_count.setAdapter(adapter2_6);

                        // 3회째

                        final Spinner mSpinner3_am_fm = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_AM_FM));
                        adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_am_fm.setAdapter(adapter3_2);

                        final Spinner mSpinner3_hour = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_hour);
                        ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_hour));
                        adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_hour.setAdapter(adapter3_3);

                        final Spinner mSpinner3_min = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_min);
                        ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_min));
                        adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_min.setAdapter(adapter3_4);

                        final Spinner mSpinner3_eat_count = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_eat_count);
                        ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_eat_count));
                        adapter3_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_eat_count.setAdapter(adapter3_6);
                        //

                        // 3번째 활성화...
                        mSpinner_count.setSelection(3);

                        // 1횟째의 복용 횟수의 따른 나머지 2,3 스피너 비활성화 조건문
                        mSpinner_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);

                                }
                                else if(position == 1){
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else if(position == 2){
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else {
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(true);
                                    mSpinner3_eat_count.setEnabled(true);
                                    mSpinner3_hour.setEnabled(true);
                                    mSpinner3_min.setEnabled(true);
                                    editTextMin3.setFocusableInTouchMode(true);
                                    editTextMin3.setFocusable(true);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextID.setText("");
                                    editTextID.requestFocus();
                                }
                                else {
                                    editTextID.setText(mSpinner.getItemAtPosition(position).toString());
                                    editTextID.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextMin.setText("");
                                    editTextMin.requestFocus();

                                }
                                else{
                                    editTextMin.setText(mSpinner_min.getItemAtPosition(position).toString());
                                    editTextMin.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여준다.
                        editTextID.setText(mList.get(getAdapterPosition()).getCheckname());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strTime = mSpinner_am_fm.getSelectedItem().toString()+" "+mSpinner_hour.getSelectedItem().toString()+" : "+editTextMin.getText().toString();

                                Ffmenu1Item dict = new Ffmenu1Item(strID,strTime,mSpinner_eat_count.getSelectedItem().toString()+" 복용");

                                // 8. 리스트어레이에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);

                                // 9. 어댑터에서 리사이클러뷰에 반영하도록 한다.
                                notifyItemChanged(getAdapterPosition());
                                saveData();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002: // 10. 삭제 선택시

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

    @Override
    public int getItemViewType(int position) {
        int a = 0;
        int is = mList.get(position).getCount();
        if(is == 2) {
            a = 2;
        }
        else if (is == 3){
            a = 3;
        }
        else {
            a = 1;
        }
        return a;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view1 = minflater.inflate(R.layout.fmenu1_item, viewGroup, false);
        View view2 = minflater.inflate(R.layout.fmenu1_item2, viewGroup, false);
        View view3 = minflater.inflate(R.layout.fmenu1_item3, viewGroup, false);
        switch (viewType) {
            case 1:
                Ffmenu1ViewHolder1 ffmenu1ViewHolder1 = new Ffmenu1ViewHolder1(view1);
                return ffmenu1ViewHolder1;
            case 2:
                Ffmenu1ViewHolder2 ffmenu1ViewHolder2 = new Ffmenu1ViewHolder2(view2);
                return ffmenu1ViewHolder2;
            case 3:
                Ffmenu1ViewHolder3 ffmenu1ViewHolder3 = new Ffmenu1ViewHolder3(view3);
                return ffmenu1ViewHolder3;


        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 1:
                Ffmenu1ViewHolder1 ffmenu1ViewHolder1 = (Ffmenu1ViewHolder1)holder;
                final Ffmenu1Item ffmenu1Item = mList.get(position);

                ((Ffmenu1ViewHolder1) holder).chId.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder1) holder).chId.setText(mList.get(position).getCheckname());
                ((Ffmenu1ViewHolder1) holder).time.setText(mList.get(position).getTime());
                ((Ffmenu1ViewHolder1) holder).eat_count.setText(mList.get(position).getEat_count());

                ((Ffmenu1ViewHolder1) holder).checktime.setText(mList.get(position).getChecktime());


                ((Ffmenu1ViewHolder1) holder).chId.setOnCheckedChangeListener(null); // null값으로 우선 초기화

                ((Ffmenu1ViewHolder1) holder).chId.setChecked(ffmenu1Item.isSelected()); // 체크박스들을 초기화 해준뒤

                // 앞으로 사용자가 체크할 값에 대해서 체크 이벤트를 달아서 setSelect를 해줌으로써 체크박스를 할수 있게됨.
                ((Ffmenu1ViewHolder1) holder).chId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item.setSelected(isChecked);

                        if (ffmenu1Item.isSelected() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder1) holder).checktime.setText(formatDate);
                            mList.get(position).setChecktime(formatDate);

                            SharedPreferences user_email = (mContext).getSharedPreferences("user_email",Context.MODE_PRIVATE);
                            SharedPreferences user_check = (mContext).getSharedPreferences("menu1_check",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = user_check.edit();
                            editor.putString(user_email.getString("Login_email","")+String.valueOf(position),formatDate);
                            editor.apply();
                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder1) holder).checktime.setText("");
                            mList.get(position).setChecktime("");
                        }
                        saveData();
                    }
                });
                break;
            case 2:
                Ffmenu1ViewHolder2 ffmenu1ViewHolder2 = (Ffmenu1ViewHolder2)holder;
                final Ffmenu1Item ffmenu1Item2 = mList.get(position);

                ((Ffmenu1ViewHolder2) holder).chId2_1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder2) holder).chId2_1.setText(mList.get(position).getCheckname());

                ((Ffmenu1ViewHolder2) holder).chId2_2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder2) holder).chId2_2.setText(mList.get(position).getCheckname());

                ((Ffmenu1ViewHolder2) holder).time2_1.setText(mList.get(position).getTime());
                ((Ffmenu1ViewHolder2) holder).eat_count2_1.setText(mList.get(position).getEat_count());
                ((Ffmenu1ViewHolder2) holder).time2_2.setText(mList.get(position).getTime2());
                ((Ffmenu1ViewHolder2) holder).eat_count2_2.setText(mList.get(position).getEat_count2());

                ((Ffmenu1ViewHolder2) holder).checktime2_1.setText(mList.get(position).getChecktime2_1());
                ((Ffmenu1ViewHolder2) holder).checktime2_2.setText(mList.get(position).getChecktime2_2());

                ((Ffmenu1ViewHolder2) holder).chId2_1.setOnCheckedChangeListener(null); // null값으로 우선 초기화
                ((Ffmenu1ViewHolder2) holder).chId2_2.setOnCheckedChangeListener(null); // null값으로 우선 초기화

                ((Ffmenu1ViewHolder2) holder).chId2_1.setChecked(ffmenu1Item2.isSelected2_1()); // 체크박스들을 초기화 해준뒤
                ((Ffmenu1ViewHolder2) holder).chId2_2.setChecked(ffmenu1Item2.isSelected2_2()); // 체크박스들을 초기화 해준뒤

                // 앞으로 사용자가 체크할 값에 대해서 체크 이벤트를 달아서 setSelect를 해줌으로써 체크박스를 할수 있게됨.
                ((Ffmenu1ViewHolder2) holder).chId2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item2.setSelected2_1(isChecked);

                        if (ffmenu1Item2.isSelected2_1() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder2) holder).checktime2_1.setText(formatDate);
                            mList.get(position).setChecktime2_1(formatDate);

                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder2) holder).checktime2_1.setText("");
                            mList.get(position).setChecktime2_1("");
                        }
                        saveData();
                    }
                });

                ((Ffmenu1ViewHolder2) holder).chId2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item2.setSelected2_2(isChecked);

                        if (ffmenu1Item2.isSelected2_2() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder2) holder).checktime2_2.setText(formatDate);
                            mList.get(position).setChecktime2_2(formatDate);
                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder2) holder).checktime2_2.setText("");
                            mList.get(position).setChecktime2_2("");
                        }
                        saveData();
                    }
                });
                break;
            case 3:
                Ffmenu1ViewHolder3 ffmenu1ViewHolder3 = (Ffmenu1ViewHolder3)holder;
                final Ffmenu1Item ffmenu1Item3 = mList.get(position);

                ((Ffmenu1ViewHolder3) holder).chId3_1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder3) holder).chId3_1.setText(mList.get(position).getCheckname());

                ((Ffmenu1ViewHolder3) holder).chId3_2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder3) holder).chId3_2.setText(mList.get(position).getCheckname());

                ((Ffmenu1ViewHolder3) holder).chId3_3.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                ((Ffmenu1ViewHolder3) holder).chId3_3.setText(mList.get(position).getCheckname());

                ((Ffmenu1ViewHolder3) holder).time3_1.setText(mList.get(position).getTime());
                ((Ffmenu1ViewHolder3) holder).eat_count3_1.setText(mList.get(position).getEat_count());
                ((Ffmenu1ViewHolder3) holder).time3_2.setText(mList.get(position).getTime2());
                ((Ffmenu1ViewHolder3) holder).eat_count3_2.setText(mList.get(position).getEat_count2());
                ((Ffmenu1ViewHolder3) holder).time3_3.setText(mList.get(position).getTime3());
                ((Ffmenu1ViewHolder3) holder).eat_count3_3.setText(mList.get(position).getEat_count3());


                ((Ffmenu1ViewHolder3) holder).checktime3_1.setText(mList.get(position).getChecktime3_1());
                ((Ffmenu1ViewHolder3) holder).checktime3_2.setText(mList.get(position).getChecktime3_2());
                ((Ffmenu1ViewHolder3) holder).checktime3_3.setText(mList.get(position).getChecktime3_3());

                ((Ffmenu1ViewHolder3) holder).chId3_1.setOnCheckedChangeListener(null); // null값으로 우선 초기화
                ((Ffmenu1ViewHolder3) holder).chId3_2.setOnCheckedChangeListener(null); // null값으로 우선 초기화
                ((Ffmenu1ViewHolder3) holder).chId3_3.setOnCheckedChangeListener(null); // null값으로 우선 초기화

                ((Ffmenu1ViewHolder3) holder).chId3_1.setChecked(ffmenu1Item3.isSelected3_1()); // 체크박스들을 초기화 해준뒤
                ((Ffmenu1ViewHolder3) holder).chId3_2.setChecked(ffmenu1Item3.isSelected3_2()); // 체크박스들을 초기화 해준뒤
                ((Ffmenu1ViewHolder3) holder).chId3_3.setChecked(ffmenu1Item3.isSelected3_3()); // 체크박스들을 초기화 해준뒤

                // 앞으로 사용자가 체크할 값에 대해서 체크 이벤트를 달아서 setSelect를 해줌으로써 체크박스를 할수 있게됨.
                ((Ffmenu1ViewHolder3) holder).chId3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item3.setSelected3_1(isChecked);

                        if (ffmenu1Item3.isSelected3_1() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder3) holder).checktime3_1.setText(formatDate);
                            mList.get(position).setChecktime3_1(formatDate);

                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder3) holder).checktime3_1.setText("");
                            mList.get(position).setChecktime3_1("");
                        }
                        saveData();
                    }
                });

                ((Ffmenu1ViewHolder3) holder).chId3_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item3.setSelected3_2(isChecked);

                        if (ffmenu1Item3.isSelected3_2() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder3) holder).checktime3_2.setText(formatDate);
                            mList.get(position).setChecktime3_2(formatDate);
                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder3) holder).checktime3_2.setText("");
                            mList.get(position).setChecktime3_2("");
                        }
                        saveData();
                    }
                });

                ((Ffmenu1ViewHolder3) holder).chId3_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ffmenu1Item3.setSelected3_3(isChecked);

                        if (ffmenu1Item3.isSelected3_3() == true) {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크",Toast.LENGTH_SHORT).show();
                            // 현재 시간을 msec로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재 시간을 date 변수에 저장.
                            Date date = new Date(now);
                            // 시간을 나타내는 포맷을 정한다.
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // newDate 변수에 값을 저장.
                            String formatDate = sdfNow.format(date);

                            ((Ffmenu1ViewHolder3) holder).checktime3_3.setText(formatDate);
                            mList.get(position).setChecktime3_3(formatDate);
                        }
                        else {
                            // Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제",Toast.LENGTH_SHORT).show();
                            ((Ffmenu1ViewHolder3) holder).checktime3_3.setText("");
                            mList.get(position).setChecktime3_3("");
                        }
                        saveData();
                    }
                });

                break;
        }

    }

    public Ffmenu1Adapter(Context context, ArrayList<Ffmenu1Item> List) {

        this.mContext = context;
        this.mList = List;
        this.minflater = LayoutInflater.from(context);
    }

    private void saveData() {
        SharedPreferences user_email = (mContext).getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = (mContext).getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(user_email.getString("Login_email",""), json);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }




   /*
    // 1. 컨텍스트 메뉴를 사용하려면 리사이클러뷰.뷰홀더를 상속받은 클래스에서
    // OnCreateContextMenuListener 리스너를 구현해야 함.
    public class Ffmenu1ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected CheckBox chId;
        private TextView time;
        private TextView eat_count;
        private TextView checktime;

        public Ffmenu1ViewHolder(@NonNull View view) {
            super(view);
            this.chId = (CheckBox) view.findViewById(R.id.checkbox_fmenu1_item_1);
            this.time = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_hour_min);
            this.eat_count = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_eat_count);
            this.checktime = (TextView) view.findViewById(R.id.checkbox_fmenu1_item_time);

            view.setOnCreateContextMenuListener(this); // 현재 클래스에서 구현한다고 설정.
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줌.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001: // 5. 수정 항목 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box 파일을 사용.
                        View view2 = LayoutInflater.from(mContext).inflate(R.layout.fmenu1_edit_box, null, false);
                        builder.setView(view2);

                        final Button ButtonSubmit = (Button) view2.findViewById(R.id.fmenu1_button_dialog_submit);
                        final EditText editTextID = (EditText) view2.findViewById(R.id.fmenu1_edittex_dialog_id);
                        final EditText editTextMin = (EditText) view2.findViewById(R.id.spinner_fmenu1_edittext_min);
                        final EditText editTextMin2 = (EditText) view2.findViewById(R.id.spinner2_fmenu1_edittext_min);
                        final EditText editTextMin3 = (EditText) view2.findViewById(R.id.spinner3_fmenu1_edittext_min);


                        final Spinner mSpinner = (Spinner) view2.findViewById(R.id.spinner_fmenu1_food);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(adapter);

                        final Spinner mSpinner_am_fm = (Spinner) view2.findViewById(R.id.spinner_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_AM_FM));
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_am_fm.setAdapter(adapter2);

                        final Spinner mSpinner_hour = (Spinner) view2.findViewById(R.id.spinner_fmenu1_hour);
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_hour));
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_hour.setAdapter(adapter3);

                        final Spinner mSpinner_min = (Spinner) view2.findViewById(R.id.spinner_fmenu1_min);
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_min));
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_min.setAdapter(adapter4);

                        final Spinner mSpinner_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_count);
                        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_count));
                        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_count.setAdapter(adapter5);

                        final Spinner mSpinner_eat_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_eat_count);
                        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList_eat_count));
                        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_eat_count.setAdapter(adapter6);

                        // 2회째
                        final Spinner mSpinner2_am_fm = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter2_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_AM_FM));
                        adapter2_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_am_fm.setAdapter(adapter2_2);

                        final Spinner mSpinner2_hour = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_hour);
                        ArrayAdapter<String> adapter2_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_hour));
                        adapter2_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_hour.setAdapter(adapter2_3);

                        final Spinner mSpinner2_min = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_min);
                        ArrayAdapter<String> adapter2_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_min));
                        adapter2_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_min.setAdapter(adapter2_4);

                        final Spinner mSpinner2_eat_count = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_eat_count);
                        ArrayAdapter<String> adapter2_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList2_eat_count));
                        adapter2_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner2_eat_count.setAdapter(adapter2_6);

                        // 3회째

                        final Spinner mSpinner3_am_fm = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_AM_FM);
                        ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_AM_FM));
                        adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_am_fm.setAdapter(adapter3_2);

                        final Spinner mSpinner3_hour = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_hour);
                        ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_hour));
                        adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_hour.setAdapter(adapter3_3);

                        final Spinner mSpinner3_min = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_min);
                        ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_min));
                        adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_min.setAdapter(adapter3_4);

                        final Spinner mSpinner3_eat_count = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_eat_count);
                        ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mContext.getResources().getStringArray(R.array.restaurantList3_eat_count));
                        adapter3_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner3_eat_count.setAdapter(adapter3_6);
                        //

                        // 1횟째의 복용 횟수의 따른 나머지 2,3 스피너 비활성화 조건문
                        mSpinner_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);

                                }
                                else if(position == 1){
                                    mSpinner2_am_fm.setEnabled(false);
                                    mSpinner2_eat_count.setEnabled(false);
                                    mSpinner2_hour.setEnabled(false);
                                    mSpinner2_min.setEnabled(false);
                                    editTextMin2.setClickable(false);
                                    editTextMin2.setFocusable(false);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else if(position == 2){
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(false);
                                    mSpinner3_eat_count.setEnabled(false);
                                    mSpinner3_hour.setEnabled(false);
                                    mSpinner3_min.setEnabled(false);
                                    editTextMin3.setClickable(false);
                                    editTextMin3.setFocusable(false);
                                }
                                else {
                                    mSpinner2_am_fm.setEnabled(true);
                                    mSpinner2_eat_count.setEnabled(true);
                                    mSpinner2_hour.setEnabled(true);
                                    mSpinner2_min.setEnabled(true);
                                    editTextMin2.setFocusableInTouchMode(true);
                                    editTextMin2.setFocusable(true);

                                    mSpinner3_am_fm.setEnabled(true);
                                    mSpinner3_eat_count.setEnabled(true);
                                    mSpinner3_hour.setEnabled(true);
                                    mSpinner3_min.setEnabled(true);
                                    editTextMin3.setFocusableInTouchMode(true);
                                    editTextMin3.setFocusable(true);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextID.setText("");
                                    editTextID.requestFocus();
                                }
                                else {
                                    editTextID.setText(mSpinner.getItemAtPosition(position).toString());
                                    editTextID.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        mSpinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0) {

                                }
                                else if(position == 1) {
                                    editTextMin.setText("");
                                    editTextMin.requestFocus();

                                }
                                else{
                                    editTextMin.setText(mSpinner_min.getItemAtPosition(position).toString());
                                    editTextMin.requestFocus();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여준다.
                        editTextID.setText(mList.get(getAdapterPosition()).getCheckname());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            @Override
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strTime = mSpinner_am_fm.getSelectedItem().toString()+" "+mSpinner_hour.getSelectedItem().toString()+" : "+editTextMin.getText().toString();

                                Ffmenu1Item dict = new Ffmenu1Item(strID,strTime,mSpinner_eat_count.getSelectedItem().toString()+" 복용");

                                // 8. 리스트어레이에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);

                                // 9. 어댑터에서 리사이클러뷰에 반영하도록 한다.
                                notifyItemChanged(getAdapterPosition());
                                saveData();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002: // 10. 삭제 선택시

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

    public Ffmenu1Adapter(Context context, ArrayList<Ffmenu1Item> List) {

        this.mContext = context;
        this.mList = List;
        this.minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Ffmenu1ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = minflater.inflate(R.layout.fmenu1_item, viewGroup, false);
        Ffmenu1ViewHolder viewHolder = new Ffmenu1ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Ffmenu1ViewHolder viewholder, final int position) {
        final Ffmenu1Item ffmenu1Item = mList.get(position);

        viewholder.chId.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        viewholder.chId.setText(mList.get(position).getCheckname());
        viewholder.time.setText(mList.get(position).getTime());
        viewholder.eat_count.setText(mList.get(position).getEat_count());

        viewholder.chId.setOnCheckedChangeListener(null); // null값으로 우선 초기화

        viewholder.chId.setChecked(ffmenu1Item.isSelected()); // 체크박스들을 초기화 해준뒤

        // 앞으로 사용자가 체크할 값에 대해서 체크 이벤트를 달아서 setSelect를 해줌으로써 체크박스를 할수 있게됨.
        viewholder.chId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ffmenu1Item.setSelected(isChecked);

                if (ffmenu1Item.isSelected() == true) {
                    Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크됫나",Toast.LENGTH_SHORT).show();
                    // 현재 시간을 msec로 구한다.
                    long now = System.currentTimeMillis();
                    // 현재 시간을 date 변수에 저장.
                    Date date = new Date(now);
                    // 시간을 나타내는 포맷을 정한다.
                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    // newDate 변수에 값을 저장.
                    String formatDate = sdfNow.format(date);

                    viewholder.checktime.setText(formatDate);
                }
                else {
                    Toast.makeText(mContext,mList.get(position).getCheckname()+" 체크 해제됫나",Toast.LENGTH_SHORT).show();
                    viewholder.checktime.setText("");
                }
                saveData();
            }
        });




    }
    private void saveData() {
        SharedPreferences user_email = (mContext).getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = (mContext).getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        editor.putString(user_email.getString("Login_email",""), json);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }*/

}
