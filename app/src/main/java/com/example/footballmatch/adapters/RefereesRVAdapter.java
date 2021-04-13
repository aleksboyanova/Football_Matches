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
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;
import com.example.footballmatch.database.DBHandler;

import java.util.List;

import static com.example.footballmatch.MainActivity.user;

public class RefereesRVAdapter extends RecyclerView.Adapter<RefereesRVAdapter.ItemViewHolder> {
    private static final String TAG = "RefereesRVAdapter";

    private List<Referees> refereesList;
    private Context context;
    DBHandler dbHandler;
    Users thisUser = user;

    public RefereesRVAdapter(Context context, List<Referees> refereesList) {
        this.refereesList = refereesList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refereeslist, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Referees referee = refereesList.get(position);
        holder.refereeTextView.setText(referee.get_refereeFName() + " " + referee.get_refereeLName());
        holder.layoutRefereeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Teams> teams = dbHandler.getAllTeams();
                for (Teams team : teams) {
                    if (team.get_ownerId() == thisUser.get_id()) {
                        if (referee.get_refereeState().equals("free")) {
                            dbHandler.updateTeamsReferee(referee.get_refereeId(), team.get_ownerId());
                            dbHandler.changeRefereeState("busy ", referee.get_refereeId());
                            removeAt(position);
                            notifyDataSetChanged();
                            RefereesRVAdapter.this.toast();
                        } else {
                            holder.refereeTextView.setError("This referee is already busy.");
                        }
                    }
                }
            }
        });
    }

    public void removeAt(int position) {
        refereesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, refereesList.size());
    }

    @Override
    public int getItemCount() {
        return refereesList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView refereeTextView;
        Button layoutRefereeBtn;
        RecyclerView parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            refereeTextView = itemView.findViewById(R.id.refereeTextView);
            layoutRefereeBtn = itemView.findViewById(R.id.layoutRefereeBtn);
            parentLayout = itemView.findViewById(R.id.refereesRecyclerView);
        }
    }

    private void toast() {
        Toast.makeText(context, "Added", Toast.LENGTH_LONG).show();
    }
}
