package com.infyu.interview_task.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.infyu.interview_task.MainActivity;
import com.infyu.interview_task.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText loginName, password;
    Context context;
    Button login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        init();
        preferences = getSharedPreferences("userLogin", 0);
        Boolean islogin = preferences.getBoolean("userlogin", false);
        Log.e("login", islogin + "");
        if (islogin) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    // initialize view from xml files
    public void init() {
        loginName = findViewById(R.id.email_id_edittext);
        password = findViewById(R.id.password_edittext);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (loginName.getText().toString().equals("Fininfocom")) {
                        if (password.getText().toString().equals("Fin@123")) {
                            preferences = context.getSharedPreferences("userLogin", 0);
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putBoolean("userlogin", true);
                            edit.commit();
                            context.startActivity(new Intent(context, MainActivity.class));
                            Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(context, "Wrong password!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Username does not match, please enter correct username!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    // check validation for empty input field
    public boolean isValid() {
        Log.e("is valid", "yes");
        if (loginName.getText().toString().equals("") && loginName.getText().toString().length() < 9) {
            Toast.makeText(context, "Username must be 10 characters", Toast.LENGTH_SHORT).show();
            Log.e("is valid in if", "yes in if");
            return false;
        }
        if (password.getText().toString().equals("")) {
            Toast.makeText(context, "Password must be 7 Characters with 1UpperCase Alphabet and 1SpecialCharacter and Numeric", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidPassword(password.getText().toString())) {
            Toast.makeText(context, "Password must be 7 Characters with 1UpperCase Alphabet and 1SpecialCharacter and Numeric", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        // Define the regular expression pattern for the password
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{7,}$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(password);

        // Check if the password matches the pattern
        return matcher.matches();
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}