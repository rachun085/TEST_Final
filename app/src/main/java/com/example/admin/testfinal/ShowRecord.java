package com.example.admin.testfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowRecord extends AppCompatActivity {

    TextView showid,showname,showsurname,showaddr;
    Button butback;
    String id,name,surname,address;
    String URL2="http://192.168.56.1/testfinal/query.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showrecord);

        showid = findViewById(R.id.shid);
        showname = findViewById(R.id.shname);
        showsurname = findViewById(R.id.shsurname);
        showaddr = findViewById(R.id.shaddr);
        butback = findViewById(R.id.butback);

        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowRecord.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        String url = URL2;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showlist(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ไม่สามารถดึงข้อมูลได้ โปรดตรวจสอบการเชื่อมต่อ", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showlist(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i<result.length(); i++){

                JSONObject collectData = result.getJSONObject(i);
                id = collectData.getString("id");
                name = collectData.getString("name");
                surname = collectData.getString("surname");
                address = collectData.getString("address");


                showid.setText(id);
                showname.setText(name);
                showsurname.setText(surname);
                showaddr.setText(address);
            }


        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }


}
