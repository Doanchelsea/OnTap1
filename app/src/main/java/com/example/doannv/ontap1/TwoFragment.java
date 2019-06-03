package com.example.doannv.ontap1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannv.ontap1.activity.OnTabActivity;
import com.example.doannv.ontap1.activity.Tabs3Activity;


public class TwoFragment extends Fragment{

    private View statics;
    TextView tvtwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        statics =  inflater.inflate(R.layout.fragment_two, container, false);
        tvtwo = statics.findViewById(R.id.tvtwo);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String Hinh = bundle.getString("ok2");
            Log.d("DDDDDDDDDDDD222",Hinh);
        }
        tvtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return statics;
    }

}