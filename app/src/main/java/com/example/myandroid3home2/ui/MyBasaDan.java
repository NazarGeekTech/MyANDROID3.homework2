package com.example.myandroid3home2.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myandroid3home2.R;

public class MyBasaDan extends AppCompatActivity {
    private TextView Film_Tv, tv_Title;
    private TextView tv_Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_basa_dan);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.hide();
        }
        init();
    }

    private void init() {
        tv_Title = findViewById(R.id.tv_title);
        Film_Tv = findViewById(R.id.Film_tv);
        tv_Description = findViewById(R.id.tv_description);
        Intent intent = getIntent();
        if (intent !=null){
            String film_id = intent.getStringExtra("Film_Id");
            String film_title = intent.getStringExtra("film_title");
            String film_desc = intent.getStringExtra("film_desc");

            Film_Tv.setText(film_id);
            tv_Title.setText(film_title);
            tv_Description.setText(film_desc);
        }
    }
}