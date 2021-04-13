package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import static com.example.footballmatch.MainActivity.user;

public class FriendlyMatchesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CardView frMatchCardView;
    DatePickerDialog picker;
    Spinner firstSpinner;
    Spinner secondSpinner;
    TextView organizeMatchText;
    TextView firstTeamNameText;
    TextView secondTeamNameText;
    TextView refereeTextView3;
    EditText dateView;
    EditText placeView;
    EditText hourView;
    Button createMatchBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendly_matches);

        frMatchCardView = findViewById(R.id.frMatchCardView);
        firstSpinner = findViewById(R.id.firstSpinner);
        secondSpinner = findViewById(R.id.secondSpinner);
        Spinner refereeSpinner3 = findViewById(R.id.refereeSpinner3);
        organizeMatchText = findViewById(R.id.organizeMatchText);
        firstTeamNameText = findViewById(R.id.firstTeamNameText);
        secondTeamNameText = findViewById(R.id.secondTeamNameText);
        refereeTextView3 = findViewById(R.id.refereeTextView3);
        dateView = findViewById(R.id.dateView);
        placeView = findViewById(R.id.placeView);
        hourView = findViewById(R.id.hourView);
        createMatchBtn = findViewById(R.id.createMatchBtn);
        dbHandler = new DBHandler(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        frMatchCardView.startAnimation(animation);

        firstSpinner.setOnItemSelectedListener(this);
        secondSpinner.setOnItemSelectedListener(this);
        refereeSpinner3.setOnItemSelectedListener(this);

        List<Referees> refereesList = dbHandler.getAllReferees();
        List<String> refereesName = new ArrayList<>();

        for (Referees referee : refereesList) {
            if (referee.get_refereeState().equals("free")) {
                refereesName.add(referee.get_refereeFName() + " " + referee.get_refereeLName());
            }
        }

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, refereesName);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        refereeSpinner3.setAdapter(dataAdapter1);

        int ownerId = user.get_id();

        dateView.setInputType(InputType.TYPE_NULL);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(FriendlyMatchesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        hourView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FriendlyMatchesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hourView.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        List<Teams> firstTeams = dbHandler.getAllTeamsByOwnerId(ownerId);
        List<Teams> secondTeams = dbHandler.getAllTeams();
        List<String> teamNameP = new ArrayList<>();

        for (Teams team : firstTeams) {
            teamNameP.add(team.get_clubHeadquarters());
        }

        List<String> teamNames = new ArrayList<>();

        for (Teams team : secondTeams) {
            teamNames.add(team.get_clubHeadquarters());
        }

        if (user.is_isOrganizer()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNames);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            firstSpinner.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNames);

            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            secondSpinner.setAdapter(dataAdapter2);

        } else if (!user.is_isOrganizer()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNameP);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            firstSpinner.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamNames);

            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            secondSpinner.setAdapter(dataAdapter2);
        }

        createMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateDate() & validateHour() & validatePlace() & firstTeamExists() & secondTeamExists() & twoDifferentTeams()) {
                    Matches match = new Matches();
                    String refereeName = String.valueOf(refereeSpinner3.getSelectedItem());
                    String[] cutRefereeName = refereeName.split(" ");
                    String refFirstName = cutRefereeName[0];
                    Referees referee = dbHandler.getRefereeByName(refFirstName);
                    String firstTeam = String.valueOf(firstSpinner.getSelectedItem());
                    String secondTeam = String.valueOf(secondSpinner.getSelectedItem());
                    String date = dateView.getText().toString();
                    String hour = hourView.getText().toString();
                    String place = placeView.getText().toString();
                    match.set_firstTeamId(dbHandler.getTeamByName(firstTeam).get_teamId());
                    match.set_secondTeamId(dbHandler.getTeamByName(secondTeam).get_teamId());
                    match.set_date(date);
                    match.set_place(place);
                    match.set_hour(hour);
                    match.set_score("");
                    match.set_refereeId(referee.get_refereeId());
                    dbHandler.addMatch(match);
                    dbHandler.changeRefereeState("busy", referee.get_refereeId());

                    FriendlyMatchesActivity.this.toast();
                    Intent intent = new Intent(FriendlyMatchesActivity.this.getApplicationContext(), MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //It is use to finish current activity
                    startActivity(intent);
                }
            }
        });
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //It is use to finish current activity
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private boolean validateDate() {
        String date = dateView.getText().toString().trim();
        if (date.isEmpty()) {
            dateView.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateHour() {
        String hour = hourView.getText().toString().trim();
        if (hour.isEmpty()) {
            hourView.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePlace() {
        String place = placeView.getText().toString();
        if (place.isEmpty()) {
            placeView.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean firstTeamExists() {
        String firstTeam = String.valueOf(firstSpinner.getSelectedItem());
        List<Teams> teams = dbHandler.getAllTeams();
        for (Teams team : teams) {
            if (team.get_clubHeadquarters().equals(firstTeam)) {
                return true;
            }
        }
        firstTeamNameText.setError("The team does not exists");
        return false;
    }

    private boolean secondTeamExists() {
        String secondTeam = String.valueOf(secondSpinner.getSelectedItem());
        List<Teams> teams = dbHandler.getAllTeams();
        for (Teams team : teams) {
            if (team.get_clubHeadquarters().equals(secondTeam)) {
                return true;
            }
        }
        secondTeamNameText.setError("The team does not exists");
        return false;
    }


    private boolean twoDifferentTeams() {
        String firstTeam = String.valueOf(firstSpinner.getSelectedItem());
        String secondTeam = String.valueOf(secondSpinner.getSelectedItem());
        if (firstTeam.equals(secondTeam)) {
            firstTeamNameText.setError("Teams must be different.");
            secondTeamNameText.setError("Teams must be different.");
            return false;
        } else if (!firstTeam.equals(secondTeam)) {
            return true;
        }
        return true;
    }

    private void toast() {
        Toast.makeText(this, "Created", Toast.LENGTH_LONG).show();
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