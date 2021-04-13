package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class MyTeamActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Users thisUser = user;

    CardView myTeamCV;
    ImageView createTeamImage;
    Spinner teamSpinner;
    EditText clubHeadquartersView;
    EditText stadiumTeamView;
    EditText stateTeamView;
    Button createTeamBtn;
    Teams team1 = new Teams();
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);

        myTeamCV = findViewById(R.id.myTeamCV);
        createTeamImage = findViewById(R.id.createTeamImage);
        clubHeadquartersView = findViewById(R.id.clubHeadquartersView);
        stadiumTeamView = findViewById(R.id.stadiumTeamView);
        stateTeamView = findViewById(R.id.stateTeamView);
        createTeamBtn = findViewById(R.id.createTeamBtn);
        teamSpinner = findViewById(R.id.teamSpinner);
        dbHandler = new DBHandler(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        myTeamCV.startAnimation(animation);

        teamSpinner.setOnItemSelectedListener(this);
        List<Teams> teams = dbHandler.getAllTeamsByOwnerId(user.get_id());
        List<String> teamNames = new ArrayList<>();

        for (Teams team : teams) {
            if (!team.get_clubHeadquarters().equals(null)) {
                teamNames.add(team.get_clubHeadquarters());
            } else {
                team.set_clubHeadquarters("");
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNames);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        teamSpinner.setAdapter(dataAdapter);

        String teamName = String.valueOf(teamSpinner.getSelectedItem());
        team1 = dbHandler.getTeamByName(teamName);

        clubHeadquartersView.setText(team1.get_clubHeadquarters());
        stadiumTeamView.setText(team1.get_stadium());
        stateTeamView.setText(team1.get_state());

        createTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyTeamActivity.this.validateClubName() & MyTeamActivity.this.clubExists(clubHeadquartersView.getText().toString())) {
                    List<Teams> teams = dbHandler.getAllTeams();
                    for (Teams team : teams) {
                        if (team.get_ownerId() == thisUser.get_id()) {
                            String clubHeadquarters = clubHeadquartersView.getText().toString();
                            String stadium = stadiumTeamView.getText().toString();
                            String state = stateTeamView.getText().toString();

                            dbHandler.updateTeam(clubHeadquarters, stadium, state, thisUser.get_id());
                        }
                    }

                    MyTeamActivity.this.toast();
                    Intent intent = new Intent(MyTeamActivity.this.getApplicationContext(), MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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

    private boolean validateClubName() {
        String clubHeadquarters = clubHeadquartersView.getText().toString();
        String stadium = stadiumTeamView.getText().toString();
        String state = stateTeamView.getText().toString();
        if (clubHeadquarters.isEmpty()) {
            clubHeadquartersView.setError("Field cannot be empty");
            return false;
        } else if (stadium.isEmpty()) {
            stadiumTeamView.setError("Field cannot be empty");
            return false;
        } else if (state.isEmpty()) {
            stateTeamView.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private boolean clubExists(String clubHeadquarters) {
        List<Teams> teams = dbHandler.getAllTeams();
        for (Teams team : teams) {
            String teamName = team.get_clubHeadquarters();
            if (teamName == null) {
                teamName = "";
            }
            if (teamName.equals(clubHeadquarters)) {
                clubHeadquartersView.setError("This club is already exists");
                return false;
            }
        }
        return true;
    }

    private void toast() {
        Toast.makeText(this, "Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}