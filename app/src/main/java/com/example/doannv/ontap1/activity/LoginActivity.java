package com.example.doannv.ontap1.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.ontap1.R;
import com.example.doannv.ontap1.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    ProgressDialog loadingBar;
     EditText edTaikhoan;
     EditText edMatkhau;
     Button btnDangnhap;
    View footerview;
    RelativeLayout progressBar;
     TextView tvDangky;
     CheckBox checkBox;
    private SharedPreferences preferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        EventButton();
            String username = preferences.getString("username", "");
            String password = preferences.getString("password", "");
            edTaikhoan.setText(username);
            edMatkhau.setText(password);
            if (username.equals("")){
                checkBox.setChecked(false);
            }else {
                checkBox.setChecked(true);
        }
    }

    private void EventButton() {
        btnDangnhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                final String taikhoan1 = edTaikhoan.getText().toString().trim();
                final String matkhau1 = edMatkhau.getText().toString().trim();

                if (taikhoan1.equals("")){
                    edTaikhoan.setError("Chưa có dữ liệu");
                    return;
                }
                if (matkhau1.equals("")){
                    edMatkhau.setError("Chưa có dữ liệu");
                    return;
                }
                loadingBar.setMessage("Downloading json...");
                loadingBar.show();
                loadingBar.setCanceledOnTouchOutside(true);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandn, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null){
                            String taikhoan = "";
                            String matkhau = "";
                            String hoten = "";
                            String ID = "";
                            for (int i=0; i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    taikhoan = jsonObject.getString("taikhoan");
                                    matkhau = jsonObject.getString("matkhau");
                                    hoten = jsonObject.getString("hoten");
                                    ID = jsonObject.getString("id");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (taikhoan1.equals(taikhoan) && matkhau1.equals(matkhau) ){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("login",hoten);
                                    if (checkBox.isChecked()){
                                        preferences.edit().putString("username", taikhoan1).commit();
                                        preferences.edit().putString("password", matkhau1).commit();
                                    }else {
                                        preferences.edit().putString("username", "").commit();
                                        preferences.edit().putString("password", "").commit();
                                    }
                                    startActivity(intent);
                                    loadingBar.hide();
                                    return;
                                }
                            }


                        }
                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        loadingBar.hide();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonArrayRequest);
            }
        });
        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        loadingBar = new ProgressDialog(this);
        edTaikhoan = (EditText) findViewById(R.id.edTaikhoan);
        edMatkhau = (EditText) findViewById(R.id.edMatkhau);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        tvDangky = (TextView) findViewById(R.id.tvDangky);
        checkBox = findViewById(R.id.checkBox);
        preferences = getSharedPreferences("Account", Context.MODE_PRIVATE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.passs, null);
        progressBar = findViewById(R.id.menu_ok);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
