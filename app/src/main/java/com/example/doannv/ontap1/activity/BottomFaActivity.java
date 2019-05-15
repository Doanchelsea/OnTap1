package com.example.doannv.ontap1.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannv.ontap1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BottomFaActivity extends AppCompatActivity {
    Button btnnav;
    TextView ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_fa);

        btnnav = findViewById(R.id.btnnav);
        ok = findViewById(R.id.tvTG);
        btnnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//        btnnav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigationViewFa.setVisibility(View.VISIBLE);
//                overridePendingTransition(R.anim.ani_enter,R.anim.ani_exit);
//            }
//        });
//        relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigationViewFa.setVisibility(View.INVISIBLE);
//            }
//        });
    }



}
