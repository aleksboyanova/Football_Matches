package com.example.footballmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;
import com.example.footballmatch.menu.RegisterActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Users user = new Users();

    EditText usernameLoginText;
    EditText passwordLoginText;
    ImageView ballImageView;
    Button loginButton;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        ballImageView = findViewById(R.id.ballImageView);
        usernameLoginText = findViewById(R.id.usernameLoginText);
        passwordLoginText = findViewById(R.id.passwordLoginText);
        loginButton = findViewById(R.id.loginButton);
        dbHandler = new DBHandler(this);

        List<Users> users = dbHandler.checkAdmin();
        if (users.size() < 1) {
            Users user = new Users();
            user.set_username("Aleksandra7");
            user.set_password("Alex#1477");
            user.set_isOrganizer(true);
            dbHandler.addUser(user);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                String username = usernameLoginText.getText().toString();
                String password = passwordLoginText.getText().toString();
                if (dbHandler.checkUser(username, password)) {
                    user = dbHandler.getUser(username);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                } else {
                    usernameLoginText.setError("Invalid username or password.");
                    passwordLoginText.setError("Invalid username or password.");
                }
            }
        });
    }

    public void onLoginClick(View View) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }

    public void registerClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }
}