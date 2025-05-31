package com.cookandroid.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView editT;
    private Button btnReset, btnHour, btnMin;
    private Button[] numBtns = new Button[10];
    private TimePicker calTime;
    private int[] numBtnID = { R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4,
            R.id.btnNum5, R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9};
    private String Innum = "";
    private int HourN, MinN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HW3");

        editT = findViewById(R.id.editT);
        btnReset = findViewById(R.id.btnReset);
        btnHour = findViewById(R.id.btnHour);
        btnMin = findViewById(R.id.btnMin);
        calTime = findViewById(R.id.calTime);

        for (int i=0; i<numBtnID.length; i++) {
            numBtns[i] = findViewById(numBtnID[i]);
        }

        for (int i=0; i<numBtnID.length; i++) {
            final int index = i;
            numBtns[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Innum += numBtns[index].getText().toString();
                    editT.setText(Innum);
                }
            });
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editT.setText("");
                Innum = "";
            }
        });

        btnHour.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                if (!editT.getText().toString().isEmpty()) {
                    HourN = Integer.parseInt(editT.getText().toString());
                    if (HourN >= 0 && HourN <= 23) {
                        calTime.setHour(HourN);
                    } else {
                        Toast.makeText(getApplicationContext(), "시간은 0~23만 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "시간을 입력하시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                if (!editT.getText().toString().isEmpty()) {
                    MinN = Integer.parseInt(editT.getText().toString());
                    if (MinN >= 0 && MinN <= 59) {
                        calTime.setMinute(MinN);
                    } else {
                        Toast.makeText(getApplicationContext(), "분은 0~59만 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "분을 입력하시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
