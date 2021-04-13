package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;
import com.example.footballmatch.menu.MenuActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.footballmatch.adapters.MatchesResultsRVAdapter.matchUpdate;
import static com.example.footballmatch.adapters.MatchesResultsRVAdapter.matchId;


public class UpdateMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "UpdateMatchActivity";

    CardView updateMatchCardView1;
    Matches match = matchUpdate;
    DatePickerDialog picker;
    TextView updateMatchText;
    EditText dateEditText;
    EditText placeEditText;
    EditText hourEditText;
    EditText scoreEditText;
    TextView winnerIdText;
    TextView refereeIdText;
    Spinner spinnerReferee;
    Spinner spinnerWinner;
    Button updateBtn;
    DBHandler dbHandler;
    List<Matches> matches;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);

        updateMatchCardView1 = findViewById(R.id.updateMatchCardView1);
        spinnerWinner = findViewById(R.id.spinnerWinner);
        spinnerReferee = findViewById(R.id.spinnerReferee);
        updateMatchText = findViewById(R.id.updateMatchText);
        dateEditText = findViewById(R.id.dateEditText);
        placeEditText = findViewById(R.id.placeEditText);
        hourEditText = findViewById(R.id.hourEditText);
        scoreEditText = findViewById(R.id.scoreEditText);
        winnerIdText = findViewById(R.id.winnerIdText);
        refereeIdText = findViewById(R.id.refereeIdText);
        updateBtn = findViewById(R.id.updateBtn);
        dbHandler = new DBHandler(this);
        matches = dbHandler.getAllMatches();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        updateMatchCardView1.startAnimation(animation);

        spinnerReferee.setOnItemSelectedListener(this);
        spinnerWinner.setOnItemSelectedListener(this);

        dateEditText.setText("" + match.get_date());
        placeEditText.setText("" + match.get_place());
        hourEditText.setText("" + match.get_hour());
        scoreEditText.setText("" + match.get_score());
        Teams firstTeam = dbHandler.getTeam(match.get_firstTeamId());
        Teams secondTeam = dbHandler.getTeam(match.get_secondTeamId());
        List<String> teams = new ArrayList<>();
        teams.add(firstTeam.get_clubHeadquarters());
        teams.add(secondTeam.get_clubHeadquarters());

        List<Referees> refereesList = dbHandler.getAllReferees();
        List<String> referees = new ArrayList<>();

        Referees teamReferee = dbHandler.getRefereeById(match.get_refereeId());
        String fName = teamReferee.get_refereeFName();
        String lName = teamReferee.get_refereeLName();

        if (fName != null & lName != null) {
            referees.add(fName + " " + lName);
        }

        for (Referees referee : refereesList) {
            if (referee.get_refereeState().equals("free")) {
                referees.add(referee.get_refereeFName() + " " + referee.get_refereeLName());
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, referees);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerReferee.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerWinner.setAdapter(dataAdapter2);


        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(UpdateMatchActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        hourEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(UpdateMatchActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hourEditText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = dateEditText.getText().toString();
                String place = placeEditText.getText().toString();
                String hour = hourEditText.getText().toString();
                String score = scoreEditText.getText().toString();
                String refereeName = String.valueOf(spinnerReferee.getSelectedItem());
                String[] cutRefereeName = refereeName.split(" ");
                String refFirstName = cutRefereeName[0];
                Referees referee = dbHandler.getRefereeByName(refFirstName);
                int refereeId = referee.get_refereeId();

                String teamName = String.valueOf(spinnerWinner.getSelectedItem());
                Teams team = dbHandler.getTeamByName(teamName);
                int winnerId = team.get_teamId();

                dbHandler.changeRefereeState("busy", refereeId);
                dbHandler.updateMatch(date, place, hour, score, winnerId, refereeId, matchId);

                UpdateMatchActivity.this.toast();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //It is use to finish current activity
                startActivity(intent);

            }
        });
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void toast() {
        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
    }

    public void onMatchesResultsClick(View view) {
        Intent intent = new Intent(this, MatchesResultsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}