package com.example.doannv.ontap1.activity;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doannv.ontap1.R;

public class SheetActivity extends AppCompatActivity {
    LinearLayout llShera, llPre;
    TextView tvsheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        tvsheet = findViewById(R.id.tvsheet);
        tvsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
                final BottomSheetDialog dialog = new BottomSheetDialog(SheetActivity.this);
                dialog.setContentView(view);
                dialog.show();
                llShera = view.findViewById(R.id.llShare);
                llPre = view.findViewById(R.id.llpre);
                llShera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SheetActivity.this, "Đẹp", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                llPre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SheetActivity.this, "Ôn", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
