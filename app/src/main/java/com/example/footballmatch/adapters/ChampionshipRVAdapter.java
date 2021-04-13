package com.example.footballmatch.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmatch.R;
import com.example.footballmatch.activities.EightTeamsTournamentActivity;
import com.example.footballmatch.activities.SixteenTeamsTournamentActivity;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class ChampionshipRVAdapter extends RecyclerView.Adapter<ChampionshipRVAdapter.ItemViewHolder> {
    private static final String TAG = "ChampionshipRVAdapter";

    Users thisUser = user;
    public static List<ChampionshipTeams> championshipTeams = new ArrayList<>();
    public static List<Teams> teams = new ArrayList<>();
    private List<Championship> championshipList;
    private Context context;
    DBHandler dbHandler;

    public ChampionshipRVAdapter(Context context, List<Championship> championshipList) {
        this.championshipList = championshipList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ChampionshipRVAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_championship, parent, false);
        ChampionshipRVAdapter.ItemViewHolder holder = new ChampionshipRVAdapter.ItemViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChampionshipRVAdapter.ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Championship championship = championshipList.get(position);

        if (user.is_isOrganizer()) {
            holder.viewBtn.setVisibility(View.GONE);
            holder.championshipNameTextV.setText(championship.get_name());
            holder.championshipDateTextV.setText(championship.get_date());
            holder.sizeChampTextV.setText(String.valueOf(championship.get_size()));
        } else {
            holder.viewBtn.setVisibility(View.GONE);
            championship = championshipList.get(position);
            int userId = thisUser.get_id();
            Teams thisTeam = dbHandler.getTeamByOwnerId(userId);
            List<ChampionshipTeams> champTeams = dbHandler.getAllChampionshipTeamsByTeamId(thisTeam.get_teamId());


            for (ChampionshipTeams champT : champTeams) {
                int champID = champT.get_championshipId();
                List<Championship> championships = dbHandler.getAllChampionship();
                for (Championship champ1 : championships) {
                    if (championship.get_championshipId() == champID) {
                        holder.championshipNameTextV.setText(championship.get_name());
                        holder.championshipDateTextV.setText(championship.get_date());
                        holder.sizeChampTextV.setText(String.valueOf(championship.get_size()));
                        holder.viewBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        int champId = championship.get_championshipId();
        championshipTeams = dbHandler.getChampionshipTeams(champId);

        List<Matches> matches = dbHandler.getMatchesByChampId(champId);

        if (championshipTeams.size() % 2 == 0 & championshipTeams.size() > 0) {
            if (!(matches.size() > 0)) {
                Matches match = new Matches();
                for (int j = 0; j < championshipTeams.size(); j += 2) {
                    ChampionshipTeams firstTeam = championshipTeams.get(j);
                    ChampionshipTeams secondTeam = championshipTeams.get(j + 1);
                    match.set_firstTeamId(firstTeam.get_teamId());
                    match.set_secondTeamId(secondTeam.get_teamId());
                    match.set_championshipId(champId);
                    match.set_date("");
                    match.set_hour("");
                    match.set_score("");
                    match.set_place("");
                    dbHandler.addMatch(match);
                }
            }
        }

        if (!(thisUser.is_isOrganizer())) {
            holder.champButton.setVisibility(View.GONE);
        }

        holder.champButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Championship championship1 = championshipList.get(position);
                int champId = championship1.get_championshipId();
                championshipTeams = dbHandler.getChampionshipTeams(champId);
                if (championship1.get_size() == 8 & championshipTeams.size() == 8) {
                    Intent intent = new Intent(context, EightTeamsTournamentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (championship1.get_size() == 8 & !(championshipTeams.size() == 8)) {
                    ChampionshipRVAdapter.this.toast();
                }

                if (championship1.get_size() == 16 & championshipTeams.size() == 16) {
                    Intent intent = new Intent(context, SixteenTeamsTournamentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (championship1.get_size() == 16 & !(championshipTeams.size() == 16)) {
                    ChampionshipRVAdapter.this.toast();
                }
            }
        });

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Championship championship1 = championshipList.get(position);
                int champId = championship1.get_championshipId();
                championshipTeams = dbHandler.getChampionshipTeams(champId);
                if (championship1.get_size() == 8 & championshipTeams.size() == 8) {
                    Intent intent = new Intent(context, EightTeamsTournamentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (championship1.get_size() == 8 & !(championshipTeams.size() == 8)) {
                    ChampionshipRVAdapter.this.toast();
                }

                if (championship1.get_size() == 16 & championshipTeams.size() == 16) {
                    Intent intent = new Intent(context, SixteenTeamsTournamentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (championship1.get_size() == 16 & !(championshipTeams.size() == 16)) {
                    ChampionshipRVAdapter.this.toast();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return championshipList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView championshipNameTextV;
        TextView championshipDateTextV;
        TextView sizeChampTextV;
        Button champButton;
        Button viewBtn;
        RelativeLayout parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            championshipNameTextV = itemView.findViewById(R.id.championshipNameTextV);
            championshipDateTextV = itemView.findViewById(R.id.championshipDateTextV);
            sizeChampTextV = itemView.findViewById(R.id.sizeChampTextV);
            champButton = itemView.findViewById(R.id.champButton);
            viewBtn = itemView.findViewById(R.id.viewBtn);
            parentLayout = itemView.findViewById(R.id.parent_layout_test);
        }
    }

    private void toast() {
        Toast.makeText(context, "Not enough teams registered.", Toast.LENGTH_LONG).show();
    }
}