package com.cookandroid.android_hw_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private RadioGroup AnimalGroup, ColorGroup;
    private Button btnShow;
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("과제 #4");

        AnimalGroup = (RadioGroup) findViewById(R.id.AnimalGroup);

        ColorGroup = (RadioGroup) findViewById(R.id.ColorGroup);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setView(dialogView);

                TextView AniText = dialogView.findViewById(R.id.AniText);
                TextView imgText = dialogView.findViewById(R.id.imgText);
                ImageView Image = dialogView.findViewById(R.id.Image);

                int selected_Animal = AnimalGroup.getCheckedRadioButtonId();
                int selected_Color = ColorGroup.getCheckedRadioButtonId();

                if (selected_Animal == -1) {
                    Toast.makeText(MainActivity.this, "동물을 선택하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selected_Color == -1) {
                    Toast.makeText(MainActivity.this, "색상을 선택하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String animalName = "";
                if (selected_Animal != -1) {
                    RadioButton Animal_id = findViewById(selected_Animal);
                    animalName = Animal_id.getText().toString();
                    switch (animalName) {
                        case "강아지":
                            AniText.setText("선택한 동물: "+ animalName);
                            Image.setImageResource(R.drawable.dog);
                            imgText.setText(animalName);
                            break;
                        case "고양이":
                            AniText.setText("선택한 동물: "+ animalName);
                            Image.setImageResource(R.drawable.cat);
                            imgText.setText(animalName);
                            break;
                        case "토끼":
                            AniText.setText("선택한 동물: "+ animalName);
                            Image.setImageResource(R.drawable.rabbit);
                            imgText.setText(animalName);
                            break;
                        case "말":
                            AniText.setText("선택한 동물: "+ animalName);
                            Image.setImageResource(R.drawable.horse);
                            imgText.setText(animalName);
                            break;
                    }
                }

                String colorName = "";
                if(selected_Color != -1) {
                    RadioButton Color_id = findViewById(selected_Color);
                    colorName = Color_id.getText().toString();
                    switch(colorName) {
                        case "RED":
                            imgText.setTextColor(Color.RED);
                            break;
                        case "GREEN":
                            imgText.setTextColor(Color.GREEN);
                            break;
                        case "BLUE":
                            imgText.setTextColor(Color.BLUE);
                            break;
                    }
                }

                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnimalGroup.clearCheck();
                        ColorGroup.clearCheck();
                    }
                });
                dlg.show();
            }
        });

    }
}