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
import android.widget.Button;

import com.example.footballmatch.R;
import com.example.footballmatch.adapters.MyTeamPlayerRVAdapter;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class PlayerMyTeamActivity extends AppCompatActivity {

    private static final String TAG = "PlayerMyTeamActivity";

    CardView playerMyTeamCardView;
    private List<Players> playersList = new ArrayList<>();
    private List<Players> players;
    List<Teams> teamsList;
    private DBHandler db = new DBHandler(this);
    Button teamNameBtn;
    Button teamNBtn;
    Button stadiumNameBtn;
    Button stadiumNBtn;
    Button playersNameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_my_team);

        playerMyTeamCardView = findViewById(R.id.playerMyTeamCardView);
        teamNameBtn = findViewById(R.id.teamNameBtn);
        stadiumNameBtn = findViewById(R.id.stadiumNameBtn);
        playersNameBtn = findViewById(R.id.playersNameBtn);
        teamNBtn = findViewById(R.id.teamNBtn);
        stadiumNBtn = findViewById(R.id.stadiumNBtn);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        playerMyTeamCardView.startAnimation(animation);

        teamsList = db.getAllTeams();
        players = db.getAllPlayers();
        for (Teams team : teamsList) {
            if (team.get_ownerId() == user.get_id()) {
                teamNBtn.setText(team.get_clubHeadquarters());
                stadiumNBtn.setText(team.get_stadium());
                for (Players player1 : players) {
                    if (player1.get_teamId() == team.get_teamId()) {
                        playersList.add(player1);
                    }
                    if (player1.get_userId() == user.get_id() & player1.get_teamId() == team.get_teamId()) {
                        playersList.add(player1);
                    }
                }
            }
        }

        initRecyclerView();
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //It is use to finish current activity
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.teamPlayersRecyclerView);
        MyTeamPlayerRVAdapter adapter = new MyTeamPlayerRVAdapter(this, playersList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}