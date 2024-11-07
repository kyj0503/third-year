package com.cookandroid.project8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    String filename;
    DatePicker dp;
    Button btnWrite;
    EditText edtDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        edtDiary = (EditText) findViewById(R.id.edtDiary);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        filename = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_" + Integer.toString(cDay) + ".txt";
        String str = readDiary(filename);
        edtDiary.setText(str);
        btnWrite.setEnabled(true);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                filename = Integer.toString(year) + "_" + Integer.toString(month + 1) + "_" + Integer.toString(day) + ".txt";
                String str = readDiary(filename);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filename == null) {
                    Toast.makeText(getApplicationContext(), "파일 이름이 설정되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    FileOutputStream outfs = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getApplicationContext(), filename + "이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    String readDiary(String filename) {
        String diaryStr=null;
        FileInputStream infs;
        try {
            infs=openFileInput(filename);
            byte txt[]=new byte[500];
            infs.read(txt);
            infs.close();
            diaryStr=(new String(txt)).trim();
            btnWrite.setText("수정하기");
        } catch (FileNotFoundException e) {
            edtDiary.setHint("일기없음");
            btnWrite.setText("새로 저장");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diaryStr;
    }
}