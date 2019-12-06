package com.bytes.tech.awizom.ecommerceproject.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bytes.tech.awizom.ecommerceproject.fragment.FirstFragment;
import com.bytes.tech.awizom.ecommerceproject.fragment.TabFragment;
import com.bytes.tech.awizom.ecommerceproject.fragment.ThirdFragment;

class  ViewPagerAdapter extends FragmentPagerAdapter {

    private String title[] = {"One", "Two", "Three"};

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return TabFragment.getInstance(position);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return FirstFragment.getInstance(position);
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.getInstance(position);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
