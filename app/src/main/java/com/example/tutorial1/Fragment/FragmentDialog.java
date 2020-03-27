package com.example.tutorial1.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutorial1.R;

public class FragmentDialog extends DialogFragment {

    private Fragment fragment;

    public FragmentDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmenu1_edit_box,container,false);

        Bundle args = getArguments();
        String value = args.getString("key");

        // 다이어프래그먼트를 종료시키려면? 물론 다이얼로그 바깥쪽을 터치하면 되지만
        // 종료하기 버튼으로도 종료.

        // 먼저 부모 프래그먼트를 받아온다.

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        // 버튼 이벤트 안에
        if(fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }

        return view;
    }
}
