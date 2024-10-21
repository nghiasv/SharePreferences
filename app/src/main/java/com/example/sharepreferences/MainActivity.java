package com.example.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonSelect, buttonSave, buttonLoad;
    private int ImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        buttonSelect = findViewById(R.id.buttonSelect);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImageId != 0) {
                    saveImageToSharedPreferences(ImageId);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn hình ảnh trước!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromSharedPreferences();
            }
        });
    }

    private void showImageSelectionDialog() {
        final String[] imageOptions = {"Nicolas Jackson", "Cole Palmer", "Christopher Nkunku"};
        final int[] imageIds = {R.drawable.jackson, R.drawable.palmer, R.drawable.nkunku};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn hình ảnh cầu thủ");
        builder.setItems(imageOptions, (dialog, which) -> {
            ImageId = imageIds[which];
            imageView.setImageResource(ImageId);
        });

        builder.create().show();
    }

    private void saveImageToSharedPreferences(int imageResId) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("selected_image", imageResId);
        editor.apply();
        Toast.makeText(MainActivity.this, "Đã lưu ảnh", Toast.LENGTH_SHORT).show();
    }

    private void loadImageFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int imageId = sharedPreferences.getInt("selected_image", 0);

        if (imageId != 0) {
            imageView.setImageResource(imageId);
            Toast.makeText(MainActivity.this, "Đã tải hình ảnh!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Không có hình ảnh để tải!", Toast.LENGTH_SHORT).show();
        }
    }
}
