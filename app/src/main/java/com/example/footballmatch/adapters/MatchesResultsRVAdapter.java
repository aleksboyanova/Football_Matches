package com.example.footballmatch.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmatch.R;
import com.example.footballmatch.activities.UpdateMatchActivity;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class MatchesResultsRVAdapter extends RecyclerView.Adapter<MatchesResultsRVAdapter.ItemViewHolder> {
    private static final String TAG = "MatchesResultRVAdapter";

    public static int matchId;
    public static Matches matchUpdate;
    private List<Matches> matchesList;
    private Context context;
    DBHandler dbHandler;

    public MatchesResultsRVAdapter(Context context, List<Matches> matchesList) {
        this.matchesList = matchesList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_matchesresults, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        if (!user.is_isOrganizer()) {
            holder.editMatchBtn.setVisibility(View.GONE);
        }
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Matches match = matchesList.get(position);

        Teams firstTeam = dbHandler.getTeam(match.get_firstTeamId());
        Teams secondTeam = dbHandler.getTeam(match.get_secondTeamId());

        if (match.get_score() != null & !(match.get_score()).equals("")) {
            holder.firstTeamBtn.setText(firstTeam.get_clubHeadquarters());
            holder.secondTeamBtn.setText(secondTeam.get_clubHeadquarters());
            holder.scoreBtn.setText(match.get_score());
        } else {
            holder.firstTeamBtn.setText(firstTeam.get_clubHeadquarters());
            holder.secondTeamBtn.setText(secondTeam.get_clubHeadquarters());
            holder.scoreBtn.setText(" - ");
        }

        for (Matches match1 : matchesList) {
            int champId = match.get_championshipId();
            if (champId != 0) {
                Championship champ = dbHandler.getChampionshipById(champId);
                holder.leagueBtn.setText(champ.get_name());
            } else {
                holder.leagueBtn.setText(" - ");
            }
        }

        holder.editMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchUpdate = matchesList.get(position);
                matchId = matchUpdate.get_matchId();
                Intent intent = new Intent(context, UpdateMatchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        Button firstTeamBtn;
        Button secondTeamBtn;
        Button scoreBtn;
        Button leagueBtn;
        Button editMatchBtn;
        RecyclerView parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            firstTeamBtn = itemView.findViewById(R.id.firstTeamBtn);
            secondTeamBtn = itemView.findViewById(R.id.secondTeamBtn);
            scoreBtn = itemView.findViewById(R.id.scoreBtn);
            leagueBtn = itemView.findViewById(R.id.leagueBtn);
            editMatchBtn = itemView.findViewById(R.id.editMatchBtn);
            parentLayout = itemView.findViewById(R.id.matchesResultRecyclerView);
        }
    }

}

