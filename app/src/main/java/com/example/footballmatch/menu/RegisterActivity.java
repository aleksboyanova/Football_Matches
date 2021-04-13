package com.example.footballmatch.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballmatch.MainActivity;
import com.example.footballmatch.R;
import com.example.footballmatch.activities.TeamsActivity;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    CardView registerCardView;
    EditText usernameText;
    EditText firstNameText;
    EditText lastNameText;
    EditText passwordText;
    EditText repeatPasswordText;
    TextView roleTextView;
    Button registerPButton;
    RadioGroup radioGroup;
    RadioButton playerRadioBtn;
    RadioButton ownerRadioBtn;
    RadioButton refereeRadioBtn;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        registerCardView = findViewById(R.id.registerCardView);
        roleTextView = findViewById(R.id.roleTextView);
        usernameText = findViewById(R.id.usernameText);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        passwordText = findViewById(R.id.passwordText);
        repeatPasswordText = findViewById(R.id.repeatPasswordText);
        registerPButton = findViewById(R.id.registerPButton);
        radioGroup = findViewById(R.id.radioGroup);
        playerRadioBtn = findViewById(R.id.playerRadioBtn);
        ownerRadioBtn = findViewById(R.id.ownerRadioBtn);
        refereeRadioBtn = findViewById(R.id.refereeRadioBtn);
        dbHandler = new DBHandler(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        registerCardView.startAnimation(animation);

        registerPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateName() & validateUsername() & confirmPassword() & validatePassword() & isClickedRole() & usernameExists()) {
                    Users user = new Users();
                    String username = usernameText.getText().toString();
                    String password = passwordText.getText().toString();
                    user.set_username(username);
                    user.set_password(password);
                    user.set_isOrganizer(false);
                    dbHandler.addUser(user);


                    if (playerRadioBtn.isChecked()) {
                        Players player = new Players();
                        player.set_firstName(firstNameText.getText().toString());
                        player.set_lastName(lastNameText.getText().toString());
                        player.set_userId(dbHandler.getUser(username).get_id());
                        dbHandler.addPlayer(player);
                    }

                    if (ownerRadioBtn.isChecked()) {
                        Teams team = new Teams();
                        team.set_clubHeadquarters("");
                        team.set_stadium("");
                        team.set_state("free");
                        team.set_ownerId(dbHandler.getUser(username).get_id());
                        dbHandler.addTeam(team);
                    }

                    if (refereeRadioBtn.isChecked()) {
                        Referees referee = new Referees();
                        referee.set_refereeFName(firstNameText.getText().toString());
                        referee.set_refereeLName(lastNameText.getText().toString());
                        referee.set_refereeState("free");
                        referee.set_userId(dbHandler.getUser(username).get_id());
                        dbHandler.addReferee(referee);
                    }

                    toast();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }
        });
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    private boolean isClickedRole() {
        if (playerRadioBtn.isChecked() | ownerRadioBtn.isChecked() | refereeRadioBtn.isChecked()) {
            return true;
        } else {
            roleTextView.setError("Please choose role.");
            return false;
        }
    }

    private boolean confirmPassword() {
        String password = passwordText.getText().toString();
        String repeatPassword = repeatPasswordText.getText().toString();
        if (!password.equals(repeatPassword)) {
            Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateName() {
        String firstName = firstNameText.getText().toString();
        if (firstName.isEmpty()) {
            firstNameText.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean usernameExists() {
        String username = usernameText.getText().toString();
        List<Users> users = dbHandler.getAllUser();
        for (Users user : users) {
            if (user.get_username().equals(username)) {
                usernameText.setError("Username is already exists");
                return false;
            } else {
                continue;
            }
        }
        return true;
    }

    private boolean validateUsername() {
        String username = usernameText.getText().toString();

        if (username.isEmpty()) {
            usernameText.setError("Field cannot be empty");
            return false;
        } else if (username.length() >= 15) {
            usernameText.setError("Username too long");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String password = passwordText.getText().toString().trim();
        String passwordVal = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.isEmpty()) {
            passwordText.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            passwordText.setError("Password is too weak");
            return false;
        } else {
            return true;
        }
    }

    private void toast() {
        Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();
    }

    public void onTeamClick(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}