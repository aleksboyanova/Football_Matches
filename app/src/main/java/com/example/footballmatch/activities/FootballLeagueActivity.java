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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;


import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class FootballLeagueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CardView leagueCardView;
    TextView signUpTextView;
    TextView teamNameText;
    TextView champNameText;
    Spinner spinner;
    Spinner spinnerChamp;
    Button signUpBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_league);

        leagueCardView = findViewById(R.id.leagueCardView);
        spinner = findViewById(R.id.spinner);
        spinnerChamp = findViewById(R.id.spinnerChamp);
        teamNameText = findViewById(R.id.teamNameText);
        signUpTextView = findViewById(R.id.signUpTextView);
        champNameText = findViewById(R.id.champNameText);
        signUpBtn = findViewById(R.id.signUpBtn);
        dbHandler = new DBHandler(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        leagueCardView.startAnimation(animation);

        spinner.setOnItemSelectedListener(this);
        spinnerChamp.setOnItemSelectedListener(this);

        int ownerId = user.get_id();
        List<Teams> teamsList = dbHandler.getAllTeamsByOwnerId(ownerId);
        List<String> teamNameP = new ArrayList<>();

        for (Teams team : teamsList) {
            teamNameP.add(team.get_clubHeadquarters());
        }

        List<Teams> teams = dbHandler.getAllTeams();
        List<String> teamNames = new ArrayList<>();

        for (Teams team : teams) {
            teamNames.add(team.get_clubHeadquarters());
        }

        List<Championship> championships = dbHandler.getAllChampionship();
        List<String> champNames = new ArrayList<>();

        for (Championship championship : championships) {
            champNames.add(championship.get_name());
        }

        if (user.is_isOrganizer()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNames);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, champNames);

            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerChamp.setAdapter(dataAdapter2);

        } else if (!user.is_isOrganizer()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNameP);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, champNames);

            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerChamp.setAdapter(dataAdapter2);
        }


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamName = String.valueOf(spinner.getSelectedItem());
                String champName = String.valueOf(spinnerChamp.getSelectedItem());
                Teams team = dbHandler.getTeamByName(teamName);
                Championship championship = dbHandler.getChampionshipByName(champName);
                ChampionshipTeams championshipTeam = new ChampionshipTeams();
                int champId = championship.get_championshipId();
                List<ChampionshipTeams> championshipTeams = dbHandler.getChampionshipTeams(champId);

                if (teamIsNotRegistered()) {
                    if (!(championshipTeams.size() >= championship.get_size())) {
                        championshipTeam.set_teamId(team.get_teamId());
                        championshipTeam.set_championshipId(championship.get_championshipId());
                        dbHandler.addChampionshipTeam(championshipTeam);
                        FootballLeagueActivity.this.toast();
                        Intent intent = new Intent(FootballLeagueActivity.this.getApplicationContext(), MenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        champNameText.setError("There is no more place for this championship.");
                        FootballLeagueActivity.this.toastError();
                    }
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

    private boolean teamIsNotRegistered() {
        String teamName = String.valueOf(spinner.getSelectedItem());
        String champName = String.valueOf(spinnerChamp.getSelectedItem());
        Teams team1 = dbHandler.getTeamByName(teamName);
        Championship championship = dbHandler.getChampionshipByName(champName);
        int champId = championship.get_championshipId();
        List<ChampionshipTeams> championshipTeams = dbHandler.getAllChampionshipTeams();
        for (ChampionshipTeams championshipTeam1 : championshipTeams) {
            if (team1.get_teamId() == championshipTeam1.get_teamId() & champId == championshipTeam1.get_championshipId()) {
                teamNameText.setError("This team is already registered.");
                return false;
            }
        }
        return true;
    }

    private void toast() {
        Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
    }

    private void toastError() {
        Toast.makeText(this, "There is no more place for this championship.", Toast.LENGTH_LONG).show();
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