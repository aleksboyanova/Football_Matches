package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.footballmatch.R;
import com.example.footballmatch.adapters.RefereesRVAdapter;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.database.DBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RefereesActivity extends AppCompatActivity {

    private static final String TAG = "RefereesActivity";

    private List<Referees> refereesList = new ArrayList<>();
    DBHandler db = new DBHandler(this);

    FloatingActionButton fab;
    RecyclerView recyclerView;
    RefereesRVAdapter recyclerViewAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_right_to_left, R.anim.layout_animation_down_to_up, R.anim.layout_animation_left_to_right};

    int i = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referees);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.refereesRecyclerView);

        refereesList = db.getAllReferees();
        populateData();
        initAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i < animationList.length - 1) {
                    i++;
                } else {
                    i = 0;
                }
                runAnimationAgain();

            }
        });

        fab.setVisibility(View.GONE);

    }

    private void populateData() {

        for (int i = 0; i < 12; i++) {
            arrayList.add("Item " + i);
        }
    }

    private void initAdapter() {
        recyclerViewAdapter = new RefereesRVAdapter(this, refereesList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void runAnimationAgain() {

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, animationList[i]);

        recyclerView.setLayoutAnimation(controller);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
}