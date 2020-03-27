package com.example.tutorial1.Fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.tutorial1.Fragment.Ffmenu1;
import com.example.tutorial1.Fragment.Ffmenu2;
import com.example.tutorial1.Fragment.Ffmenu3;
import com.example.tutorial1.Fragment.Ffmenu4;
import com.example.tutorial1.Fragment.Ffmenu5;
import com.example.tutorial1.Fragment.Ffmenu6;

public class FpageAdapter extends FragmentPagerAdapter {
    int mNumOfTabs; // 탭의 갯수
    public FpageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Ffmenu1 tab1 = new Ffmenu1();
                return tab1;
            case 1:
                Ffmenu2 tab2 = new Ffmenu2();
                return tab2;
            case 2:
                Ffmenu3 tab3 = new Ffmenu3();
                return tab3;
            default:
                return null;
        }
            // 리턴 널;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 이 페이지를 2페이지 이상 넘김시 자동으로 호출되어 해당 뷰를 자동으로 지워져서 초기화가 되는것.
        // super.destroyItem(container, position, object);
    }
}


