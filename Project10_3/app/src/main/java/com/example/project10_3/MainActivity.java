package com.example.project10_3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtNum1 = findViewById(R.id.edtNum1);
        EditText edtNum2 = findViewById(R.id.edtNum2);
        RadioGroup rdoGroup = findViewById(R.id.rdoGroup);
        Button btnNewActivity = findViewById(R.id.btnNewActivity);

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        int hap = result.getData().getIntExtra("Hap", 0);
                        Toast.makeText(getApplicationContext(), "결과: " + hap, Toast.LENGTH_LONG).show();
                    }
                }
        );

        btnNewActivity.setOnClickListener(v -> {
            String operation = null;

            int checkedRadioButtonId = rdoGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.rdoAdd) {
                operation = "+";
            } else if (checkedRadioButtonId == R.id.rdoMinus) {
                operation = "-";
            } else if (checkedRadioButtonId == R.id.rdoMul) {
                operation = "*";
            } else if (checkedRadioButtonId == R.id.rdoDiv) {
                operation = "/";
            }

            if (operation == null) {
                Toast.makeText(this, "연산을 선택하세요!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int num1 = Integer.parseInt(edtNum1.getText().toString());
                int num2 = Integer.parseInt(edtNum2.getText().toString());
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("result", operation);
                intent.putExtra("Num1", num1);
                intent.putExtra("Num2", num2);
                resultLauncher.launch(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "숫자를 입력하세요!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
