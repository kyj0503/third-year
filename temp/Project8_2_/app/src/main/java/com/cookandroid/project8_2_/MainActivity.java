package com.cookandroid.project8_2_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileFilter;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    myPictureView myPicture;
    TextView tvNum, tvSize;
    int curNum;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, MODE_PRIVATE);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        myPicture = findViewById(R.id.myPictureView1);
        tvNum = findViewById(R.id.tvNum);
        tvSize = findViewById(R.id.tvSize);

        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/Pictures").listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isFile();
                    }
                }
        );

        imageFname = imageFiles[0].toString();
        myPicture.imagePath = imageFname;
        tvSize.setText(Integer.toString(imageFiles.length));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum <= 0) {
                    curNum = imageFiles.length - 1;
                } else {
                    curNum--;
                }
                int buff = curNum + 1;
                tvNum.setText(Integer.toString(buff));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum >= imageFiles.length - 1) {
                    curNum = 0;
                } else {
                    curNum++;
                }
                int buff = curNum + 1;
                tvNum.setText(Integer.toString(buff));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });
    }
}
