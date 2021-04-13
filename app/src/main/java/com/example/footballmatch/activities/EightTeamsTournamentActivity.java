package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.adapters.ChampionshipRVAdapter.championshipTeams;

public class EightTeamsTournamentActivity extends AppCompatActivity {

    TextView leftTeamText1;
    TextView leftTeamText2;
    TextView leftTeamText3;
    TextView leftTeamText4;
    TextView rightTeamText1;
    TextView rightTeamText2;
    TextView rightTeamText3;
    TextView rightTeamText4;
    View leftView1;
    View leftView2;
    View right8View1;
    View right8View2;
    View leftSecond8View1;
    View leftSecond8View2;
    TextView leftTeamText;
    TextView leftTeamText5;
    View rightSecond8View;
    View rightSecond8View3;
    TextView rightTeamText;
    TextView rightTeamText5;
    View thirdView8Left1;
    View rightThirdView;
    View rightSecondView6;
    View thirdView3;
    TextView fourthText;
    TextView rightFourthText2;
    View thirdView4;
    View centerViewRight2;
    TextView centerText2;
    TextView winnerText8;
    ConstraintLayout layoutEight;
    DBHandler dbHandler;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_teams_tournament);

        leftTeamText1 = findViewById(R.id.leftTeamText1);
        leftTeamText2 = findViewById(R.id.leftTeamText2);
        leftTeamText3 = findViewById(R.id.leftTeamText3);
        leftTeamText4 = findViewById(R.id.leftTeamText4);
        rightTeamText1 = findViewById(R.id.rightTeamText1);
        rightTeamText2 = findViewById(R.id.rightTeamText2);
        rightTeamText3 = findViewById(R.id.rightTeamText3);
        rightTeamText4 = findViewById(R.id.rightTeamText4);
        leftView1 = findViewById(R.id.leftView1);
        leftView2 = findViewById(R.id.leftView2);
        right8View1 = findViewById(R.id.right8View1);
        right8View2 = findViewById(R.id.right8View2);
        leftSecond8View1 = findViewById(R.id.leftSecond8View1);
        leftSecond8View2 = findViewById(R.id.leftSecond8View2);
        leftTeamText = findViewById(R.id.leftTeamText);
        leftTeamText5 = findViewById(R.id.leftTeamText5);
        rightSecond8View = findViewById(R.id.rightSecond8View);
        rightSecond8View3 = findViewById(R.id.rightSecond8View3);
        rightTeamText = findViewById(R.id.rightTeamText);
        rightTeamText5 = findViewById(R.id.rightTeamText5);
        thirdView8Left1 = findViewById(R.id.thirdView8Left1);
        rightThirdView = findViewById(R.id.rightThirdView);
        rightSecondView6 = findViewById(R.id.rightSecondView6);
        thirdView3 = findViewById(R.id.thirdView3);
        fourthText = findViewById(R.id.fourthText);
        rightFourthText2 = findViewById(R.id.rightFourthText2);
        thirdView4 = findViewById(R.id.thirdView4);
        centerViewRight2 = findViewById(R.id.centerViewRight2);
        centerText2 = findViewById(R.id.centerText2);
        winnerText8 = findViewById(R.id.winnerText8);
        layoutEight = findViewById(R.id.layoutEight);
        dbHandler = new DBHandler(this);

        List<TextView> firstTeamsText = new ArrayList<TextView>();
        List<TextView> secondTeamsText = new ArrayList<TextView>();
        List<TextView> thirdTeamsText = new ArrayList<TextView>();
        firstTeamsText.add(leftTeamText1);
        firstTeamsText.add(leftTeamText2);
        firstTeamsText.add(leftTeamText3);
        firstTeamsText.add(leftTeamText4);
        firstTeamsText.add(rightTeamText1);
        firstTeamsText.add(rightTeamText3);
        firstTeamsText.add(rightTeamText2);
        firstTeamsText.add(rightTeamText4);
        secondTeamsText.add(leftTeamText5);
        secondTeamsText.add(leftTeamText);
        secondTeamsText.add(rightTeamText);
        secondTeamsText.add(rightTeamText5);
        thirdTeamsText.add(fourthText);
        thirdTeamsText.add(rightFourthText2);

        List<ChampionshipTeams> thisChampionshipTeams = championshipTeams;
        int championshipId = thisChampionshipTeams.get(0).get_championshipId();
        List<Teams> teamsF = new ArrayList<Teams>();

        if (thisChampionshipTeams.size() == 8) {
            for (int i = 0; i < thisChampionshipTeams.size(); ) {
                for (int j = 0; j < firstTeamsText.size(); j++) {
                    ChampionshipTeams champTeam = thisChampionshipTeams.get(i);
                    int teamId = champTeam.get_teamId();
                    Teams team = dbHandler.getTeam(teamId);
                    firstTeamsText.get(j).setText(team.get_clubHeadquarters());
                    teamsF.add(team);
                    i++;
                }
            }
        }

        List<Matches> matchesList = dbHandler.getMatchesByChampId(championshipId);
        List<Teams> winnerTeamsList = new ArrayList<>();

        for (int j = 0; j < secondTeamsText.size(); ) {
            for (int i = 0; i < matchesList.size(); i++) {
                Matches match = matchesList.get(i);
                if (match.get_winnerId() > 0) {
                    int winnerId = match.get_winnerId();
                    Teams firstTeam = dbHandler.getTeam(winnerId);
                    secondTeamsText.get(j).setText(firstTeam.get_clubHeadquarters());
                    winnerTeamsList.add(firstTeam);
                    j++;
                }
            }
            break;
        }

        List<Matches> winners = new ArrayList<>();
        List<Matches> existingMatches = dbHandler.getMatchesByChampId(championshipId);

        if (winnerTeamsList.size() % 2 == 0 & winnerTeamsList.size() > 0) {
            Matches match = new Matches();
            int count = 0;
            for (int j = 0; j < winnerTeamsList.size(); j += 2) {
                Teams firstTeam = winnerTeamsList.get(j);
                Teams secondTeam = winnerTeamsList.get(j + 1);

                for (Matches thisMatch : existingMatches) {
                    if (!(thisMatch.get_firstTeamId() == firstTeam.get_teamId()) & !(thisMatch.get_secondTeamId() == secondTeam.get_teamId())) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count > 0) {
                    match.set_firstTeamId(firstTeam.get_teamId());
                    match.set_secondTeamId(secondTeam.get_teamId());
                    match.set_championshipId(championshipId);
                    match.set_date("");
                    match.set_hour("");
                    match.set_score("");
                    match.set_place("");
                    dbHandler.addMatch(match);
                    winners.add(match);
                }
            }
        }

        List<Teams> finalTeams = new ArrayList<>();

        for (int j = 0; j < thirdTeamsText.size(); ) {
            for (int i = 0; i < winners.size(); i++) {
                Matches match1 = winners.get(i);
                if (match1.get_winnerId() > 0) {
                    int winnerId = match1.get_winnerId();
                    Teams firstTeam = dbHandler.getTeam(winnerId);
                    thirdTeamsText.get(j).setText(firstTeam.get_clubHeadquarters());
                    finalTeams.add(firstTeam);
                    j++;
                }
            }
            break;
        }

        List<Matches> existingFinalMatches = dbHandler.getMatchesByChampId(championshipId);
        Matches finalMatch = new Matches();

        if (finalTeams.size() % 2 == 0 & finalTeams.size() > 0) {
            int finalCount = 0;
            for (int j = 0; j < finalTeams.size(); j++) {
                Teams firstTeam = finalTeams.get(j);
                Teams secondTeam = finalTeams.get(j + 1);

                for (Matches thisMatch : existingFinalMatches) {
                    if (!(thisMatch.get_firstTeamId() == firstTeam.get_teamId()) & !(thisMatch.get_secondTeamId() == secondTeam.get_teamId())) {
                        finalCount++;
                    } else {
                        finalCount = 0;
                        break;
                    }
                }
                if (finalCount > 0) {
                    finalMatch.set_firstTeamId(firstTeam.get_teamId());
                    finalMatch.set_secondTeamId(secondTeam.get_teamId());
                    finalMatch.set_championshipId(championshipId);
                    finalMatch.set_date("");
                    finalMatch.set_hour("");
                    finalMatch.set_score("");
                    finalMatch.set_place("");
                    dbHandler.addMatch(finalMatch);
                }
            }
        }

        if (finalMatch.get_winnerId() > 0) {
            Teams winnerTeam = dbHandler.getTeam(finalMatch.get_winnerId());
            centerText2.setText(winnerTeam.get_clubHeadquarters());
        }

    }
}

