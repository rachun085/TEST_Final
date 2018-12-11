package com.example.admin.testfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    EditText edtid, edtname, edtsurname, edtaddr;
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner spnsection;
    String URL = "http://192.168.1.168/testfinal/insert.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtid = findViewById(R.id.edtid);
        edtname = findViewById(R.id.edtname);
        edtsurname = findViewById(R.id.edtsurname);
        edtaddr = findViewById(R.id.edtaddr);
        button = findViewById(R.id.buttonsave);
        spnsection = findViewById(R.id.spnsection);
        radioGroup = findViewById(R.id.radio);

        String[] gender = getResources().getStringArray(R.array.section);
        ArrayAdapter<String> adaptergender = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gender);
        spnsection.setAdapter(adaptergender);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertAsyn().execute(URL);
            }
        });
    }

    private class InsertAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                int selectedgender = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedgender);

                OkHttpClient _okHttpClient = new OkHttpClient();
                RequestBody _requestBody = new FormBody.Builder()
                        .add("id", edtid.getText().toString())
                        .add("name", edtname.getText().toString())
                        .add("surname", edtsurname.getText().toString())
                        .add("address", edtaddr.getText().toString())
                        .add("section", spnsection.getSelectedItem().toString())
                        .add("gender", radioButton.getText().toString())
                        .build();

                Request _request = new Request.Builder().url(strings[0]).post(_requestBody).build();
                _okHttpClient.newCall(_request).execute();
                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                Toast.makeText(MainActivity.this, "บันทึกข้อมูลเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, ShowRecord.class);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(MainActivity.this, "ไม่สามารถบันทึกข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
