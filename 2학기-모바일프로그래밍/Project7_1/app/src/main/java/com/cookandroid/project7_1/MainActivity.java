package com.cookandroid.project7_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextAngle;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("제주도 풍경");

        editTextAngle = findViewById(R.id.editTextAngle);
        imageView = findViewById(R.id.imageView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        SubMenu subMenu = menu.addSubMenu("그림 선택");
        subMenu.add(0, 1, 0, "한라산");
        subMenu.add(0, 2, 0, "추자도");
        subMenu.add(0, 3, 0, "범섬");

        menu.add(0, 4, 0, "그림 회전");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                imageView.setImageResource(R.drawable.hallasan);
                return true;
            case 2:
                imageView.setImageResource(R.drawable.chujado);
                return true;
            case 3:
                imageView.setImageResource(R.drawable.beomseom);
                return true;
            case 4:
                float angle = Float.parseFloat(editTextAngle.getText().toString());
                imageView.setRotation(angle);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
