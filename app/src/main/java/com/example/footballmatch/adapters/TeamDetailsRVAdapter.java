package com.example.footballmatch.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

public class TeamDetailsRVAdapter extends RecyclerView.Adapter<TeamDetailsRVAdapter.ViewHolder> {
    private static final String TAG = "TeamDetailsRVAdapter";

    private List<Players> playersList;
    List<Teams> teamsList;
    private Context context;
    DBHandler dbHandler;

    public TeamDetailsRVAdapter(Context context, List<Players> playersList) {
        this.playersList = playersList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public TeamDetailsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teamplayers, parent, false);
        TeamDetailsRVAdapter.ViewHolder holder = new TeamDetailsRVAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TeamDetailsRVAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Players player = playersList.get(position);

        teamsList = dbHandler.getAllTeams();

        for (Players players : playersList) {
            for (Teams team : teamsList) {
                if (player.get_teamId() == team.get_teamId()) {
                    holder.playerNameText.setText(player.get_firstName() + " " + player.get_lastName());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView playerNameText;
        RecyclerView parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerNameText = itemView.findViewById(R.id.playerNameText);
            parentLayout = itemView.findViewById(R.id.teamDetailPlayersRecyclerView);
        }
    }
}
