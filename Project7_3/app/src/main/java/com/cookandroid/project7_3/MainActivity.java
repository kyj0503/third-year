package com.cookandroid.project7_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = (Button) findViewById(R.id.button1);

        imageView = findViewById(R.id.imageView1);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] versionArray = new String[] {"강아지", "고양이", "말", "토끼"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("좋아하는 동물은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        button1.setText(versionArray[i]);

                        switch (i) {
                            case 0:
                                imageView.setImageResource(R.drawable.dog);
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.cat);
                                break;
                            case 2:
                                imageView.setImageResource(R.drawable.horse);
                                break;
                            case 3:
                                imageView.setImageResource(R.drawable.rabbit);
                                break;
                        }
                    }
                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });
    }

}