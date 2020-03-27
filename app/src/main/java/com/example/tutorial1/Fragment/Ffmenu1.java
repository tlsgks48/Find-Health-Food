package com.example.tutorial1.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tutorial1.Fragment.Ffmenu1Adapter;
import com.example.tutorial1.Fragment.Ffmenu1Item;
import com.example.tutorial1.Item;
import com.example.tutorial1.MainActivity;
import com.example.tutorial1.R;
import com.example.tutorial1.TestRecyclerview.Dictionary;
import com.example.tutorial1.menu1_2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class Ffmenu1 extends Fragment {
    // MainActivity activity = (MainActivity) getActivity();
    View view;

    private ArrayList<Ffmenu1Item> mArrayList;
    private Ffmenu1Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManger;
    private int count = 0;
    private Context mContext;


    public Ffmenu1() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fmenu1, container, false);

        final Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fmenu1_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManger = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLinearLayoutManger);

        //mArrayList = new ArrayList<>();
        // 쉐어드 정보 불러오기
        SharedPreferences user_email = context.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = context.getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("menu1_check",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(user_email.getString("Login_email",""), null);
        Type type = new TypeToken<ArrayList<Ffmenu1Item>>() {}.getType();
        mArrayList = gson.fromJson(json, type);


        if (mArrayList == null) {
            mArrayList = new ArrayList<>();
        }
        for(int i=0; i<mArrayList.size(); i++) {
            if(mArrayList.get(i).getCount() == 1) {
                if (mArrayList.get(i).isSelected() == true) {
                    String aa = sharedPreferences2.getString(user_email.getString("Login_email", "") + String.valueOf(i), "");
                    mArrayList.get(i).setChecktime(aa);
                    //Toast.makeText(getContext(), "들어왓나", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //
        mAdapter = new Ffmenu1Adapter(context,mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        // 수평선 넣기
         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),mLinearLayoutManger.getOrientation());
          mRecyclerView.addItemDecoration(dividerItemDecoration);

        // 1. 화면에 음식추가하기 버튼을 클릭하면
        Button buttonInsert = (Button) view.findViewById(R.id.fmenu1_button_food_insert);


        // 건강식품 추가하기 클릭.... 누르면 다이얼 로그가 보여짐.
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 2. 다이얼로그에 있는 fmenu1_edit_box를 불러와서 화면에 다이럴로그 보여준다.
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // getActivity() 와 다른점이 무엇일까?
                View view2 = LayoutInflater.from(context).inflate(R.layout.fmenu1_edit_box, null, false);
                // builder.setTitle("건강식품 추가");

                final Button ButtonSubmit = (Button) view2.findViewById(R.id.fmenu1_button_dialog_submit);
                final EditText editTextID = (EditText) view2.findViewById(R.id.fmenu1_edittex_dialog_id);
                final EditText editTextMin = (EditText) view2.findViewById(R.id.spinner_fmenu1_edittext_min);
                final EditText editTextMin2 = (EditText) view2.findViewById(R.id.spinner2_fmenu1_edittext_min);
                final EditText editTextMin3 = (EditText) view2.findViewById(R.id.spinner3_fmenu1_edittext_min);

                //

                final Spinner mSpinner = (Spinner) view2.findViewById(R.id.spinner_fmenu1_food);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                final Spinner mSpinner_am_fm = (Spinner) view2.findViewById(R.id.spinner_fmenu1_AM_FM);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList_AM_FM));
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_am_fm.setAdapter(adapter2);

                final Spinner mSpinner_hour = (Spinner) view2.findViewById(R.id.spinner_fmenu1_hour);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList_hour));
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_hour.setAdapter(adapter3);

                final Spinner mSpinner_min = (Spinner) view2.findViewById(R.id.spinner_fmenu1_min);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList_min));
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_min.setAdapter(adapter4);

                final Spinner mSpinner_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_count);
                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList_count));
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_count.setAdapter(adapter5);

                final Spinner mSpinner_eat_count = (Spinner) view2.findViewById(R.id.spinner_fmenu1_eat_count);
                ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList_eat_count));
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_eat_count.setAdapter(adapter6);

                // 2회째
                final Spinner mSpinner2_am_fm = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_AM_FM);
                ArrayAdapter<String> adapter2_2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList2_AM_FM));
                adapter2_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner2_am_fm.setAdapter(adapter2_2);

                final Spinner mSpinner2_hour = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_hour);
                ArrayAdapter<String> adapter2_3 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList2_hour));
                adapter2_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner2_hour.setAdapter(adapter2_3);

                final Spinner mSpinner2_min = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_min);
                ArrayAdapter<String> adapter2_4 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList2_min));
                adapter2_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner2_min.setAdapter(adapter2_4);

                final Spinner mSpinner2_eat_count = (Spinner) view2.findViewById(R.id.spinner2_fmenu1_eat_count);
                ArrayAdapter<String> adapter2_6 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList2_eat_count));
                adapter2_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner2_eat_count.setAdapter(adapter2_6);

                // 3회째

                final Spinner mSpinner3_am_fm = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_AM_FM);
                ArrayAdapter<String> adapter3_2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList3_AM_FM));
                adapter3_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner3_am_fm.setAdapter(adapter3_2);

                final Spinner mSpinner3_hour = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_hour);
                ArrayAdapter<String> adapter3_3 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList3_hour));
                adapter3_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner3_hour.setAdapter(adapter3_3);

                final Spinner mSpinner3_min = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_min);
                ArrayAdapter<String> adapter3_4 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList3_min));
                adapter3_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner3_min.setAdapter(adapter3_4);

                final Spinner mSpinner3_eat_count = (Spinner) view2.findViewById(R.id.spinner3_fmenu1_eat_count);
                ArrayAdapter<String> adapter3_6 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.restaurantList3_eat_count));
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
                            count = 1;

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

                            count = 1;
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

                            count = 2;
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

                            count = 3;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                builder.setView(view2);


                ButtonSubmit.setText("추가");

                final AlertDialog dialog = builder.create();

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

                mSpinner2_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0) {

                        }
                        else if(position == 1) {
                            editTextMin2.setText("");
                            editTextMin2.requestFocus();

                        }
                        else{
                            editTextMin2.setText(mSpinner2_min.getItemAtPosition(position).toString());
                            editTextMin2.requestFocus();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                mSpinner3_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0) {

                        }
                        else if(position == 1) {
                            editTextMin3.setText("");
                            editTextMin3.requestFocus();

                        }
                        else{
                            editTextMin3.setText(mSpinner3_min.getItemAtPosition(position).toString());
                            editTextMin3.requestFocus();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면면
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a,b,c;

                        if(editTextMin.getText().toString().contains("분")) {
                            //패스
                            a = editTextMin.getText().toString();
                        }
                        else {
                            // 없으면 분을 붙이자.
                            a = editTextMin.getText().toString() + "분";
                        }


                        if(editTextMin2.getText().toString().contains("분")) {
                            //패스
                            b = editTextMin2.getText().toString();
                        }
                        else {
                            // 없으면 분을 붙이자.
                            b = editTextMin2.getText().toString() + "분";
                        }


                        if(editTextMin3.getText().toString().contains("분")) {
                            //패스
                            c = editTextMin3.getText().toString();
                        }
                        else {
                            // 없으면 분을 붙이자.
                            c = editTextMin3.getText().toString() + "분";
                        }

                        // 4. 사용자가 입력한 내용을 가져와서
                        // String strID = editTextID.getText().toString();
                        String strID = editTextID.getText().toString();

                        String strTime = mSpinner_am_fm.getSelectedItem().toString()+" "+mSpinner_hour.getSelectedItem().toString()+" : "+a;
                        String strTime2 = mSpinner2_am_fm.getSelectedItem().toString()+" "+mSpinner2_hour.getSelectedItem().toString()+" : "+b;
                        String strTime3 = mSpinner3_am_fm.getSelectedItem().toString()+" "+mSpinner3_hour.getSelectedItem().toString()+" : "+c;

                        if ( count == 2) {
                            // 5. ArrayList에 추가하고
                            Ffmenu1Item dict = new Ffmenu1Item(strID, strTime,strTime2, mSpinner_eat_count.getSelectedItem().toString() + " 복용",mSpinner2_eat_count.getSelectedItem().toString() + " 복용", count,"","");
                            mArrayList.add(dict);
                        }
                        else if (count == 3) {
                            Ffmenu1Item dict = new Ffmenu1Item(strID, strTime,strTime2,strTime3, mSpinner_eat_count.getSelectedItem().toString() + " 복용",mSpinner2_eat_count.getSelectedItem().toString() + " 복용",mSpinner3_eat_count.getSelectedItem().toString() + " 복용", count,"","","");
                            mArrayList.add(dict);
                        }
                        else {
                            // 5. ArrayList에 추가하고
                            Ffmenu1Item dict = new Ffmenu1Item(strID, strTime, mSpinner_eat_count.getSelectedItem().toString() + " 복용", count,"");
                            mArrayList.add(dict);
                        }


                        // 6. 어댑터에서 리사이클러뷰에 반영하도록 한다.
                        mAdapter.notifyDataSetChanged();


                        // 쉐어드 저장
                        SharedPreferences user_email = context.getSharedPreferences("user_email",Context.MODE_PRIVATE);
                        SharedPreferences sharedPreferences = context.getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(mArrayList);
                        editor.putString(user_email.getString("Login_email",""), json);
                        editor.apply();
                        //
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
//// 구분선...


/*       Button button = (Button) view.findViewById(R.id.btn_f);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"추가하기",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), menu1_2.class);

                getActivity().startActivity(intent);
            }
        }
        );*/


        return view;
    }

    @Override
    public void onDestroyView() {
      //  Toast.makeText(getContext(),"삭제되나",Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

    private void saveData() {
        SharedPreferences user_email = mContext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mArrayList);
        editor.putString(user_email.getString("Login_email",""), json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences user_email = mContext.getSharedPreferences("user_email",Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("menu1_shared",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(user_email.getString("Login_email",""), null);
        Type type = new TypeToken<ArrayList<Dictionary>>() {}.getType();
        mArrayList = gson.fromJson(json, type);

        if (mArrayList == null) {
            mArrayList = new ArrayList<>();
        }


    }
}
