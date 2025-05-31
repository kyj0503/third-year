package com.example.project10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");

        Intent intent = getIntent();
        String operation = intent.getStringExtra("result");
        int num1 = intent.getIntExtra("Num1", 0);
        int num2 = intent.getIntExtra("Num2", 0);

        int resultValue = performOperation(num1, num2, operation);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent outIntent = new Intent();
            outIntent.putExtra("Hap", resultValue);
            setResult(RESULT_OK, outIntent);
            finish();
        });
    }

    private int performOperation(int num1, int num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num2 != 0 ? num1 / num2 : 0; // 0으로 나누기 방지
            default:
                throw new IllegalArgumentException("유효하지 않은 연산: " + operation);
        }
    }
}
