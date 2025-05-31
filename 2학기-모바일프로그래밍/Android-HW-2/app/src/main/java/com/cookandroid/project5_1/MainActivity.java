// 소프트웨어전공 202284011 김연재

package com.cookandroid.project5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText num;
    private Button cal;
    private TextView resultText;
    private String Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //1)edittext 2)button 3)textView

        //Layout 설정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        //Layout
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);

        //EditText
        num = new EditText(this);
        baseLayout.addView(num);

        //Button
        cal = new Button(this);
        cal.setText("약수 구하기");
        baseLayout.addView(cal);

        //TextView
        resultText = new TextView(this);
        resultText.setTextColor(Color.BLUE);
        baseLayout.addView(resultText);

        setContentView(baseLayout, params);
        //Button 누를 때 바뀌는 내용
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = num.getText().toString();

                // EditText에 입력값이 없을 경우
                if (Num.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "유효한 숫자를 입력하시오.", Toast.LENGTH_SHORT).show();
                    resultText.setText("");
                } else {
                    try {
                        int LocalNum = Integer.parseInt(Num);
                        int count = 0;
                        String Result = "";

                        // 약수 계산 (1과 자기 자신을 제외한 약수만 출력)
                        for (int i = 2; i < LocalNum; i++) {  // 2부터 LocalNum-1까지 탐색
                            if (LocalNum % i == 0) {
                                Result += i + ", ";
                                count++;
                            }
                        }

                        // 결과값 출력
                        if (Result.isEmpty() || count == 0) {
                            resultText.setText(Num + "의 약수: 없습니다.");
                        } else {
                            Result = Result.substring(0, Result.length() - 2); // 마지막 ", " 제거
                            resultText.setText(Num + "의 약수: " + Result + "입니다.");
                        }
                    } catch (NumberFormatException e) {
                        // 숫자가 아닌 경우 처리
                        Toast.makeText(getApplicationContext(), "유효한 숫자를 입력하시오.", Toast.LENGTH_SHORT).show();
                        resultText.setText("");
                    }
                }
            }
        });
    }
}