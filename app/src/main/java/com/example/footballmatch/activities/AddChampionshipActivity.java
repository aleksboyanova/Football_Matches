package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.database.DBHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddChampionshipActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CardView addChampCardView;
    DatePickerDialog picker;
    TextView addChampText;
    EditText nameChampEditText;
    EditText dateChampEditText;
    TextView refereeTextView2;
    RadioGroup radioGroupChamp;
    RadioButton eightChampTeamsRadioBtn;
    RadioButton sixteenChampTeamsRadioBtn;
    List<Referees> refereesList = new ArrayList<>();
    Button addBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_championship);

        addChampCardView = findViewById(R.id.addChampCardView);
        Spinner refereeSpinner = findViewById(R.id.refereeSpinner);
        addChampText = findViewById(R.id.updateMatchText);
        nameChampEditText = findViewById(R.id.nameChampEditText);
        dateChampEditText = findViewById(R.id.dateChampEditText);
        radioGroupChamp = findViewById(R.id.radioGroupChamp);
        eightChampTeamsRadioBtn = findViewById(R.id.eightChampTeamsRadioBtn);
        sixteenChampTeamsRadioBtn = findViewById(R.id.sixteenChampTeamsRadioBtn);
        refereeTextView2 = findViewById(R.id.refereeTextView2);
        addBtn = findViewById(R.id.addBtn);
        dbHandler = new DBHandler(this);
        refereesList = dbHandler.getAllReferees();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
        addChampCardView.startAnimation(animation);

        refereeSpinner.setOnItemSelectedListener(this);

        List<Referees> refereesList = dbHandler.getAllReferees();
        List<String> refereesName = new ArrayList<>();

        for (Referees referee : refereesList) {
            if (referee.get_refereeState().equals("free")) {
                refereesName.add(referee.get_refereeFName() + " " + referee.get_refereeLName());
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, refereesName);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        refereeSpinner.setAdapter(dataAdapter);

        dateChampEditText.setInputType(InputType.TYPE_NULL);
        dateChampEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddChampionshipActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateChampEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateName()) {
                    String champName = nameChampEditText.getText().toString();
                    String dateChamp = dateChampEditText.getText().toString();
                    String refereeName = String.valueOf(refereeSpinner.getSelectedItem());
                    String[] cutRefereeName = refereeName.split(" ");
                    String refFirstName = cutRefereeName[0];
                    Referees referee = dbHandler.getRefereeByName(refFirstName);

                    if (eightChampTeamsRadioBtn.isChecked()) {
                        Championship championship = new Championship();
                        championship.set_name(champName);
                        championship.set_size(8);
                        championship.set_date(dateChamp);
                        championship.set_refereeId(referee.get_refereeId());
                        dbHandler.addChampionship(championship);
                        dbHandler.changeRefereeState("busy", referee.get_refereeId());
                    }

                    if (sixteenChampTeamsRadioBtn.isChecked()) {
                        Championship championship = new Championship();
                        championship.set_name(champName);
                        championship.set_size(16);
                        championship.set_date(dateChamp);
                        championship.set_refereeId(referee.get_refereeId());
                        dbHandler.addChampionship(championship);
                        dbHandler.changeRefereeState("busy", referee.get_refereeId());
                        dataAdapter.remove((String) refereeSpinner.getSelectedItem());
                        dataAdapter.notifyDataSetChanged();
                    }

                    AddChampionshipActivity.this.toast();
                    Intent intent = new Intent(AddChampionshipActivity.this.getApplicationContext(), ChampionshipActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateName() {
        String championshipName = nameChampEditText.getText().toString();
        if (championshipName.isEmpty()) {
            nameChampEditText.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

    private void toast() {
        Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onChampionshipClick(View view) {
        Intent intent = new Intent(this, ChampionshipActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}