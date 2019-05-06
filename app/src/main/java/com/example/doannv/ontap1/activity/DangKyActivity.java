package com.example.doannv.ontap1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.ontap1.R;
import com.example.doannv.ontap1.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
     Toolbar toolbardangky;
     EditText edtendk;
     EditText edmkdk;
     EditText edmkdkl;
     Button btnDangky;
     ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
        ActionToolbar();
        EventButton();


    }

    private void EventButton() {
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final   String Tendk = edtendk.getText().toString().trim();
                final  String Mkdk = edmkdk.getText().toString().trim();
                final  String NLMk = edmkdkl.getText().toString().trim();
                if (Tendk.equals("")){
                    edtendk.setError("Chưa có dữ liệu");
                    return;
                }else if (Mkdk.equals("")){
                    edmkdk.setError("Chưa có dữ liệu");
                    return;
                }else if (NLMk.equals("")){
                    edmkdkl.setError("Chưa có dữ liệu");
                    return;
                } else if (Mkdk.equals(NLMk)){
                    loadingBar.setMessage("Downloading json...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(true);
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandn, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response != null){
                                String taikhoan = "";
                                String matkhau = "";
                                String ID = "";
                                for (int i=0; i<response.length();i++){
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        taikhoan = jsonObject.getString("taikhoan");
                                        matkhau = jsonObject.getString("matkhau");
                                        ID = jsonObject.getString("id");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (Tendk.equals(taikhoan)){
                                        Toast.makeText(DangKyActivity.this, "Tài Khoản Đã được sử dụng", Toast.LENGTH_SHORT).show();
                                        loadingBar.hide();
                                        return;
                                    }
                                }

                            }
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandk, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(DangKyActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                                    loadingBar.hide();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap = new HashMap<>();
                                    hashMap.put("taikhoan",Tendk);
                                    hashMap.put("matkhau",Mkdk);
                                    return hashMap;
                                }
                            };
                            Intent intent = new Intent(DangKyActivity.this, LoginActivity.class);
                            startActivity(intent);
                            requestQueue.add(stringRequest);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
                    requestQueue1.add(jsonArrayRequest);


                }else {
                    edmkdkl.setError("Mật khẩu không trùng");
                    return;
                }
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbardangky);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardangky.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbardangky = (Toolbar) findViewById(R.id.toolbardangky);
        edtendk = (EditText) findViewById(R.id.edtendk);
        edmkdk = (EditText) findViewById(R.id.edmkdk);
        edmkdkl = (EditText) findViewById(R.id.edmkdkl);
        btnDangky = (Button) findViewById(R.id.btnDangky);
        loadingBar = new ProgressDialog(this);

    }
}
