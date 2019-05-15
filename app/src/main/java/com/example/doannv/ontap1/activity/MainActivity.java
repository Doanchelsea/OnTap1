package com.example.doannv.ontap1.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerlayout;

    RecyclerView recyclerview;
    NavigationView navigationView;
    Toolbar toolbarhome;
    String DateTime;
    RelativeLayout relativeLayout;
    String TimeDate;
    Bitmap bitmap;

    TextView tvName,tvName1;
    ImageView imageView;
    int  REQUET_FODER = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
//            case R.id.nav_home:
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.nav_thongtin:
//                Toast.makeText(this, "Thông Tin", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.nav_lichsu:
//                Toast.makeText(this, "Lịch Sử", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.nav_messingger:
//                Toast.makeText(this, "Nhắn Tin", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.nav_setiing:
//                Toast.makeText(this, "Cài Đặt", Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    private void AnhXa() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);


        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelector(menuItem);
                return false;
            }
        });
        View navView = navigationView.inflateHeaderView(R.layout.header);
        tvName = navView.findViewById(R.id.tvName);
        tvName1 = navView.findViewById(R.id.tvName1);
        imageView = navView.findViewById(R.id.profile_image);
        Picasso.get().load(R.drawable.ic_avatar)
                .placeholder(R.drawable.ic_back2)
                .error(R.drawable.ic_back2)
                .into(imageView);

        final Intent intent = getIntent();
        final String hoten = intent.getStringExtra("login");
        tvName.setText(hoten);

        toolbarhome = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbarhome);
        getSupportActionBar().setTitle("Home");

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerlayout ,R.string.drawer_open,R.string.drawer_close);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateTime = dateFormat.format(calendar.getTime());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm");
        TimeDate = timeFormat.format(calendar.getTime());
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUET_FODER);
            }
        });

        tvName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdananhok, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdananhok, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("imageok","");
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        String imageData = imageToString(bitmap);
                        hashMap.put("imageok",imageData);
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
// cấp quyền cho truy cập
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUET_FODER){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                  Intent intent = new  Intent(Intent.ACTION_PICK);
//                Intent intent = new  Intent(Intent.ACTION_ATTACH_DATA);
                Intent intent = new  Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),REQUET_FODER);
            }
            else{
                Toast.makeText(this, "Bạn ko có quyền truy cập thư viện", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
// hiện thị ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUET_FODER && resultCode == RESULT_OK && data!= null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                 bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // pphuowngthuc post anh
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[]  imageBytes = outputStream.toByteArray();
            String edcodeImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
            return  edcodeImage;
    }
}
