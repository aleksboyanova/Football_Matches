package com.example.footballmatch.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.footballmatch.MainActivity;
import com.example.footballmatch.R;
import com.example.footballmatch.activities.ChampionshipActivity;
import com.example.footballmatch.activities.FootballLeagueActivity;
import com.example.footballmatch.activities.FriendlyMatchesActivity;
import com.example.footballmatch.activities.MatchesResultsActivity;
import com.example.footballmatch.activities.MyTeamActivity;
import com.example.footballmatch.activities.PlayerMyTeamActivity;
import com.example.footballmatch.activities.PlayersActivity;
import com.example.footballmatch.activities.RefereesActivity;
import com.example.footballmatch.activities.TeamsActivity;
import com.example.footballmatch.activities.UpdateMatchActivity;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class MenuActivity extends AppCompatActivity {

    Users loginUser = user;

    GridLayout gridLayout;
    CardView resultsCardView;
    CardView teamsCardView;
    CardView playersCardView;
    CardView friendlyMCardView;
    CardView championshipCardView;
    CardView refereesCardView;
    CardView updateMyTeamCardView;
    CardView addChampCardView;
    CardView myTeamCardView;
    CardView updateMatchCardView;
    CardView logoutCardView;
    Button resultsButton;
    Button teamsBtn;
    Button playersBtn;
    Button friendlyMatchesButton;
    Button championshipButton;
    Button refereesBtn;
    Button logoutBtn;
    Button updateMyTeamBtn;
    Button addChampionshipBtn;
    Button myTeamBtn;
    Button updateMatchBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gridLayout = findViewById(R.id.gridLayout);
        resultsCardView = findViewById(R.id.resultsCardView);
        teamsCardView = findViewById(R.id.teamsCardView);
        playersCardView = findViewById(R.id.playersCardView);
        friendlyMCardView = findViewById(R.id.friendlyMCardView);
        championshipCardView = findViewById(R.id.championshipCardView);
        refereesCardView = findViewById(R.id.refereesCardView);
        updateMyTeamCardView = findViewById(R.id.updateMyTeamCardView);
        addChampCardView = findViewById(R.id.addChampCardView);
        myTeamCardView = findViewById(R.id.myTeamCardView);
        updateMatchCardView = findViewById(R.id.updateMatchCardView);
        logoutCardView = findViewById(R.id.logoutCardView);

        resultsButton = findViewById(R.id.resultsButton);
        teamsBtn = findViewById(R.id.teamsBtn);
        playersBtn = findViewById(R.id.playersBtn);
        friendlyMatchesButton = findViewById(R.id.friendlyMatchesButton);
        championshipButton = findViewById(R.id.championshipButton);
        refereesBtn = findViewById(R.id.refereesBtn);
        updateMyTeamBtn = findViewById(R.id.updateMyTeamBtn);
        addChampionshipBtn = findViewById(R.id.addChampionshipBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        myTeamBtn = findViewById(R.id.myTeamBtn);
        updateMatchBtn = findViewById(R.id.updateMatchBtn);
        dbHandler = new DBHandler(this);

        resultsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MatchesResultsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        logoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        updateMatchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateMatchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        addChampCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FootballLeagueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        myTeamCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayerMyTeamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        teamsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        updateMyTeamCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyTeamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        playersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayersActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        friendlyMCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendlyMatchesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        championshipCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChampionshipActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        refereesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RefereesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        updateMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateMatchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        addChampionshipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FootballLeagueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        myTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayerMyTeamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        teamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        updateMyTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyTeamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MatchesResultsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        playersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayersActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        friendlyMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendlyMatchesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        championshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChampionshipActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        refereesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RefereesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        List<Users> users = dbHandler.getAllUser();
        for (Users user : users) {
            if (user.get_username().equals(loginUser.get_username())) {
                if (user.is_isOrganizer()) {
                    updateMyTeamBtn.setVisibility(View.GONE);
                    playersBtn.setVisibility(View.GONE);
                    refereesBtn.setVisibility(View.GONE);
                    myTeamBtn.setVisibility(View.GONE);
                    updateMatchBtn.setVisibility(View.GONE);

                    gridLayout.removeView(updateMyTeamCardView);
                    gridLayout.removeView(playersCardView);
                    gridLayout.removeView(refereesCardView);
                    gridLayout.removeView(myTeamCardView);
                    gridLayout.removeView(updateMatchCardView);
                    return;
                }
            }
        }

        List<Teams> teams = dbHandler.getAllTeams();
        for (Teams team : teams) {
            if (team.get_ownerId() > 0 & team.get_ownerId() == loginUser.get_id()) {
                updateMatchBtn.setVisibility(View.GONE);
                refereesBtn.setVisibility(View.GONE);

                gridLayout.removeView(updateMatchCardView);
                gridLayout.removeView(refereesCardView);
                return;
            }
        }

        List<Referees> referees = dbHandler.getAllReferees();
        for (Referees referee : referees) {
            if (referee.get_refereeId() > 0 & referee.get_userId() == loginUser.get_id()) {
                playersBtn.setVisibility(View.GONE);
                friendlyMatchesButton.setVisibility(View.GONE);
                championshipButton.setVisibility(View.GONE);
                refereesBtn.setVisibility(View.GONE);
                updateMyTeamBtn.setVisibility(View.GONE);
                addChampionshipBtn.setVisibility(View.GONE);
                myTeamBtn.setVisibility(View.GONE);
                updateMatchBtn.setVisibility(View.GONE);

                gridLayout.removeView(playersCardView);
                gridLayout.removeView(friendlyMCardView);
                gridLayout.removeView(championshipCardView);
                gridLayout.removeView(refereesCardView);
                gridLayout.removeView(updateMyTeamCardView);
                gridLayout.removeView(addChampCardView);
                gridLayout.removeView(myTeamCardView);
                gridLayout.removeView(updateMatchCardView);
                return;
            }
        }

        List<Players> players = dbHandler.getAllPlayers();
        for (Players player : players) {
            if (player.get_playerId() > 0 & player.get_userId() == loginUser.get_id()) {
                playersBtn.setVisibility(View.GONE);
                friendlyMatchesButton.setVisibility(View.GONE);
                championshipButton.setVisibility(View.GONE);
                refereesBtn.setVisibility(View.GONE);
                updateMyTeamBtn.setVisibility(View.GONE);
                addChampionshipBtn.setVisibility(View.GONE);
                updateMatchBtn.setVisibility(View.GONE);

                gridLayout.removeView(playersCardView);
                gridLayout.removeView(friendlyMCardView);
                gridLayout.removeView(championshipCardView);
                gridLayout.removeView(refereesCardView);
                gridLayout.removeView(updateMyTeamCardView);
                gridLayout.removeView(addChampCardView);
                gridLayout.removeView(updateMatchCardView);
                return;
            }
        }
    }
}