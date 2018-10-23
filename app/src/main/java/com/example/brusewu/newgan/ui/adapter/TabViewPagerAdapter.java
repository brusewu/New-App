package com.example.brusewu.newgan.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.brusewu.newgan.utils.TabViewPagerAdapterItem;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<TabViewPagerAdapterItem> itemList;

    public TabViewPagerAdapter(FragmentManager fm, List<TabViewPagerAdapterItem> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @Override
    public Fragment getItem(int position) {
        return itemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }
}
