package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;

import com.example.footballmatch.R;
import com.example.footballmatch.adapters.ChampionshipRVAdapter;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class ChampionshipActivity extends AppCompatActivity {

    CardView champCardView;
    private List<Championship> championshipsList = new ArrayList<>();
    private List<ChampionshipTeams> championshipTeamsList = new ArrayList<>();
    List<Championship> champList = new ArrayList<>();
    TextView championshipTextView;
    Button addChampBtn;
    DBHandler dbHandler;

    RecyclerView recyclerView;
    ChampionshipRVAdapter recyclerViewAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_right_to_left, R.anim.layout_animation_down_to_up, R.anim.layout_animation_left_to_right};

    int i = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championship);

        champCardView = findViewById(R.id.champCardView);
        championshipTextView = findViewById(R.id.championshipTextView);
        addChampBtn = findViewById(R.id.addChampBtn);
        dbHandler = new DBHandler(this);

        recyclerView = findViewById(R.id.championshipRecyclerView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        champCardView.startAnimation(animation);

        champList = dbHandler.getAllChampionship();
        championshipTeamsList = dbHandler.getAllChampionshipTeams();

        championshipsList = dbHandler.getAllChampionship();
        populateData();
        initAdapter();


        if (!(user.is_isOrganizer())) {
            addChampBtn.setVisibility(View.GONE);
        }

        addChampBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddChampionshipActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void populateData() {

        for (int i = 0; i < 12; i++) {
            arrayList.add("Item " + i);
        }
    }

    private void initAdapter() {
        recyclerViewAdapter = new ChampionshipRVAdapter(this, championshipsList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }
}