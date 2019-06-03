package com.example.doannv.ontap1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OneFragment extends Fragment{
    private HandlerButton handlerButton;
    private View statics;
    TextView tvone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        statics =  inflater.inflate(R.layout.fragment_one, container, false);

        tvone = statics.findViewById(R.id.tvone);

        tvone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerButton.change(1);
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            String Hinh = bundle.getString("ok");
            Log.d("DDDDDDDDDDDD",Hinh);
        }
        return statics;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        handlerButton = (HandlerButton) context;
    }
}