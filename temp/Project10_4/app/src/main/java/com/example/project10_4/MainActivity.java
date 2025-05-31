package com.example.project10_4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 테스트");

        final String[] mid = {"히어로즈", "24시", "로스트", "스몰빌", "탐정몽크", "빅뱅이론", "프렌즈", "덱스터",
                "글리", "가쉽걸", "테이클", "슈퍼내추럴", "브이"};
        ListView list = findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mid);
        ListAdapter adaptor;
        list.setAdapter(adaptor);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getApplicationContext(), mid[arg2], Toast.LENGTH_SHORT.show();
            }
        });
    }
}