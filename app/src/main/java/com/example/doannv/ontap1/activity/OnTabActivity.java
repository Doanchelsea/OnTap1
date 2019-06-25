package com.example.doannv.ontap1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doannv.ontap1.R;

public class OnTabActivity extends AppCompatActivity {
    Button btntap1,btntap2,btntap3,btntap4,btntap5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_tab);
        btntap1 = findViewById(R.id.btntab1);
        btntap2 = findViewById(R.id.btntab2);
        btntap3 = findViewById(R.id.btntab3);
        btntap4 = findViewById(R.id.btntab4);
        btntap5 = findViewById(R.id.btntab5);
        btntap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnTabActivity.this,TabsActivity.class);
                startActivity(intent);
            }
        });
        btntap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnTabActivity.this,Tabs2Activity.class);
                startActivity(intent);
            }
        });
        btntap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnTabActivity.this,Tabs3Activity.class);
                startActivity(intent);
            }
        });
        btntap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnTabActivity.this,Tabs4Activity.class);
                intent.putExtra("OK","ok1212");
                startActivity(intent);
            }
        });

    }
}
