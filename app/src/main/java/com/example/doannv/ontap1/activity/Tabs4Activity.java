package com.example.doannv.ontap1.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.doannv.ontap1.HandlerButton;
import com.example.doannv.ontap1.R;

public class Tabs4Activity extends AppCompatActivity implements HandlerButton {
    ViewPager viewPager;
    TabLayout tabLayout;
    public static String ok;
    int[] tabIcons = {
            R.drawable.ic_hien,
            R.drawable.ic_tat,
            R.drawable.ic_gui
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs4);
         viewPager = findViewById(R.id.viewok);
         tabLayout = findViewById(R.id.tabok);
        PagerAdapter adapter = new ViewPage(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        Intent intent = getIntent();
        ok = intent.getStringExtra("OK");
    }

    @Override
    public void change(int position) {
        viewPager.setCurrentItem(position);
    }

}
