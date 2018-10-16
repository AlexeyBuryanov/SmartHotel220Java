package com.alexeyburyanov.smarthotel.ui.myroom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alexeyburyanov.smarthotel.ui.myroom.ambientset.AmbientSetFragment;
import com.alexeyburyanov.smarthotel.ui.myroom.findmyroom.FindMyRoomFragment;
import com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed.WhatYouNeedFragment;

public class MyRoomPagerAdapter extends FragmentStatePagerAdapter {

    private int _tabCount;

    public MyRoomPagerAdapter(FragmentManager fm) {
        super(fm);
        _tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AmbientSetFragment.newInstance();
            case 1:
                return WhatYouNeedFragment.newInstance();
            case 2:
                return FindMyRoomFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return _tabCount; }
    public void setCount(int count) {
        _tabCount = count;
    }
}
