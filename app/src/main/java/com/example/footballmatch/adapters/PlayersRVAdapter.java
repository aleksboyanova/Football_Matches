package com.example.footballmatch.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class PlayersRVAdapter extends RecyclerView.Adapter<PlayersRVAdapter.ItemViewHolder> {
    private static final String TAG = "PlayersRVAdapter";

    private List<Players> playersList;
    List<Teams> teamsList;
    private Context context;
    DBHandler dbHandler;

    public PlayersRVAdapter(Context context, List<Players> playersList) {
        this.playersList = playersList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_playerslist, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Players player = playersList.get(position);
        holder.playerTextView.setText(player.get_firstName() + " " + player.get_lastName());

        holder.layoutPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamsList = dbHandler.getAllTeams();
                for (Teams team : teamsList) {
                    if (user.get_id() == team.get_ownerId()) {
                        dbHandler.updatePlayersTeam(team.get_teamId(), player.get_playerId());
                        removeAt(position);
                        notifyDataSetChanged();
                        PlayersRVAdapter.this.toast();
                    }
                }
            }
        });
    }

    public void removeAt(int position) {
        playersList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, playersList.size());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView playerTextView;
        Button layoutPlayerBtn;
        RecyclerView parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            playerTextView = itemView.findViewById(R.id.playerTextView);
            layoutPlayerBtn = itemView.findViewById(R.id.layoutPlayerBtn);
            parentLayout = itemView.findViewById(R.id.playersRecyclerView);
        }
    }

    private void toast() {
        Toast.makeText(context, "Added", Toast.LENGTH_LONG).show();
    }
}


