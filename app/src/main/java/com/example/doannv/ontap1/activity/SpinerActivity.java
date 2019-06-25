package com.example.doannv.ontap1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doannv.ontap1.R;

import java.util.ArrayList;

public class SpinerActivity extends AppCompatActivity {
    Button btnspinner;
    String d = "Lập trình1";
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner);
        spinner = findViewById(R.id.spinner);
        btnspinner = findViewById(R.id.btnspinner);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Lập trình1");
        arrayList.add("Lập Trình2");
        arrayList.add("Lập Trình3");
        arrayList.add("Lập Trình4");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpinerActivity.this, arrayList.get(position), Toast.LENGTH_SHORT).show();
                d = arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OKKKKKKKKK",d);
            }
        });

    }
}
