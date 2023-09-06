package com.infyu.interview_task.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.infyu.interview_task.R;
import com.infyu.interview_task.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.setText("W");
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("e");
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("l");
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("c");
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("o");
            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("m");
            }
        }, 800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nameTextView.append("e");
            }
        }, 900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }, 1500);

    }

    private void init() {
        nameTextView = findViewById(R.id.name_textview);
    }
}