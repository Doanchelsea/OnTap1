package com.example.doannv.ontap1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.doannv.ontap1.OneFragment;
import com.example.doannv.ontap1.TwoFragment;

public class ViewPage extends FragmentPagerAdapter {
    static String ok1;
    public ViewPage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ok1 = Tabs4Activity.ok;
                Log.d("ddddddđ",ok1);
                OneFragment oneFragment = new OneFragment();
                Bundle bundle = new Bundle();
                bundle.putString("ok","HH");
                oneFragment.setArguments(bundle);
                return oneFragment;
            case 1:
                TwoFragment twoFragment = new TwoFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("ok2","HH");
                twoFragment.setArguments(bundle1);
                return twoFragment;
            case 2:
                return new OneFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
