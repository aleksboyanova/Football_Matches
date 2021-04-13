package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballmatch.R;
import com.example.footballmatch.adapters.TeamDetailsRVAdapter;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.adapters.RecyclerViewAdapter.teamName;

public class TeamDetailsActivity extends AppCompatActivity {

    TextView messageText;
    ImageView teamNameImage;
    ImageView stadiumImage;
    ImageView detailPlayersImage;
    CardView teamDetailsCardView;
    String thisTeamName = teamName;
    private List<Players> playersList = new ArrayList<>();
    private List<Players> players;
    List<Teams> teamsList;
    TextView clubNameTextView;
    TextView stadiumTeamText1;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        messageText = findViewById(R.id.messageText);
        teamNameImage = findViewById(R.id.teamNameImage);
        stadiumImage = findViewById(R.id.stadiumImage);
        detailPlayersImage = findViewById(R.id.detailPlayersImage);
        teamDetailsCardView = findViewById(R.id.teamDetailsCardView);
        clubNameTextView = findViewById(R.id.clubNameTextView);
        stadiumTeamText1 = findViewById(R.id.stadiumTeamText1);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        teamDetailsCardView.startAnimation(animation);

        Teams team = dbHandler.getTeamByName(thisTeamName);
        clubNameTextView.setText(team.get_clubHeadquarters());
        stadiumTeamText1.setText(team.get_stadium());

        teamsList = dbHandler.getAllTeams();
        players = dbHandler.getAllPlayers();
        for (Teams team1 : teamsList) {
            for (Players player1 : players) {
                if (player1.get_teamId() == team1.get_teamId() & team.get_teamId() == player1.get_teamId()) {
                    playersList.add(player1);
                }
            }
        }

        if(playersList.isEmpty()){
            messageText.setText("Not players added yet.");
        }

        initRecyclerView();

    }

    public void onTeamClick(View view) {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.teamDetailPlayersRecyclerView);
        TeamDetailsRVAdapter adapter = new TeamDetailsRVAdapter(this, playersList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}