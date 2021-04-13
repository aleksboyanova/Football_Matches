package com.example.footballmatch.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmatch.R;
import com.example.footballmatch.activities.TeamDetailsActivity;
import com.example.footballmatch.classes.Teams;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Teams> teamsList;
    private Context context;
    public static String teamName = "";

    public RecyclerViewAdapter(Context context, List<Teams> teamsList) {
        this.teamsList = teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teamslist, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Teams team = teamsList.get(position);
        holder.teamTextView1.setText(team.get_clubHeadquarters());
        holder.layoutTeamBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamName = team.get_clubHeadquarters();
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView teamTextView1;
        Button layoutTeamBtn1;
        RecyclerView parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            teamTextView1 = itemView.findViewById(R.id.teamTextView1);
            layoutTeamBtn1 = itemView.findViewById(R.id.layoutTeamBtn1);
            parentLayout = itemView.findViewById(R.id.championshipRecyclerView);
        }
    }
}

