package com.example.footballmatch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.footballmatch.R;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.footballmatch.adapters.ChampionshipRVAdapter.championshipTeams;

public class SixteenTeamsTournamentActivity extends AppCompatActivity {

    List<ChampionshipTeams> thisChampionshipTeams = championshipTeams;
    TextView team1LeftText;
    TextView team2LeftText;
    TextView team3LeftText;
    TextView team4LeftText;
    TextView team5LeftText;
    TextView team6LeftText;
    TextView team7LeftText;
    TextView team8LeftText;
    View view1Left;
    View view2Left;
    View view3Left;
    View view4Left;
    View view5Left;
    View view6Left;
    View view7Left;
    View view8Left;
    TextView secondColumnTeam1;
    TextView secondColumnTeam2;
    TextView secondColumnTeam3;
    TextView secondColumnTeam4;
    View secondView1;
    View secondView2;
    View secondView3;
    View secondView4;
    TextView thirdTextTeam1;
    TextView thirdTextTeam2;
    View thirdView1;
    View thirdView2;
    TextView fourthText1;

    TextView rightTeam1Text;
    TextView rightTeam2Text;
    TextView rightTeam3Text;
    TextView rightTeam4Text;
    TextView rightTeam5Text;
    TextView rightTeam6Text;
    TextView rightTeam7Text;
    TextView rightTeam8Text;
    View rightView1;
    View rightView2;
    View rightView3;
    View rightView4;
    View rightView5;
    View rightView6;
    View rightView7;
    View rightView8;
    TextView rightSecondText1;
    TextView rightSecondText2;
    TextView rightSecondText3;
    TextView rightSecondText4;
    View rightSecondView1;
    View rightSecondView2;
    View rightSecondView3;
    View rightSecondView4;
    TextView rightThirdText;
    TextView rightThirdText1;
    View rightThirdView1;
    View rightThirdView2;
    TextView rightFourthText;

    View centerViewRight;
    View centerViewLeft;
    TextView centerText;

    TextView winnerText;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixteen_teams_tournament);

        team1LeftText = findViewById(R.id.team1LeftText);
        team2LeftText = findViewById(R.id.team2LeftText);
        team3LeftText = findViewById(R.id.team3LeftText);
        team4LeftText = findViewById(R.id.team4LeftText);
        team5LeftText = findViewById(R.id.team5LeftText);
        team6LeftText = findViewById(R.id.team6LeftText);
        team7LeftText = findViewById(R.id.team7LeftText);
        team8LeftText = findViewById(R.id.team8LeftText);
        view1Left = findViewById(R.id.view1Left);
        view2Left = findViewById(R.id.view2Left);
        view3Left = findViewById(R.id.view3Left);
        view4Left = findViewById(R.id.view4Left);
        view5Left = findViewById(R.id.view5Left);
        view6Left = findViewById(R.id.view6Left);
        view7Left = findViewById(R.id.view7Left);
        view8Left = findViewById(R.id.view8Left);
        secondColumnTeam1 = findViewById(R.id.secondColumnTeam1);
        secondColumnTeam2 = findViewById(R.id.secondColumnTeam2);
        secondColumnTeam3 = findViewById(R.id.secondColumnTeam3);
        secondColumnTeam4 = findViewById(R.id.secondColumnTeam4);
        secondView1 = findViewById(R.id.secondView1);
        secondView2 = findViewById(R.id.secondView2);
        secondView3 = findViewById(R.id.secondView3);
        secondView4 = findViewById(R.id.secondView4);
        thirdTextTeam1 = findViewById(R.id.thirdTextTeam1);
        thirdTextTeam2 = findViewById(R.id.thirdTextTeam2);
        thirdView1 = findViewById(R.id.thirdView1);
        thirdView2 = findViewById(R.id.thirdView2);
        fourthText1 = findViewById(R.id.fourthText1);

        rightTeam1Text = findViewById(R.id.rightTeam1Text);
        rightTeam2Text = findViewById(R.id.rightTeam2Text);
        rightTeam3Text = findViewById(R.id.rightTeam3Text);
        rightTeam4Text = findViewById(R.id.rightTeam4Text);
        rightTeam5Text = findViewById(R.id.rightTeam5Text);
        rightTeam6Text = findViewById(R.id.rightTeam6Text);
        rightTeam7Text = findViewById(R.id.rightTeam7Text);
        rightTeam8Text = findViewById(R.id.rightTeam8Text);
        rightView1 = findViewById(R.id.rightView1);
        rightView2 = findViewById(R.id.rightView2);
        rightView3 = findViewById(R.id.rightView3);
        rightView4 = findViewById(R.id.rightView4);
        rightView5 = findViewById(R.id.rightView5);
        rightView6 = findViewById(R.id.rightView6);
        rightView7 = findViewById(R.id.rightView7);
        rightView8 = findViewById(R.id.rightView8);
        rightSecondText1 = findViewById(R.id.rightSecondText1);
        rightSecondText2 = findViewById(R.id.rightSecondText2);
        rightSecondText3 = findViewById(R.id.rightSecondText3);
        rightSecondText4 = findViewById(R.id.rightSecondText4);
        rightSecondView1 = findViewById(R.id.rightSecondView1);
        rightSecondView2 = findViewById(R.id.rightSecondView2);
        rightSecondView3 = findViewById(R.id.rightSecondView3);
        rightSecondView4 = findViewById(R.id.rightSecondView4);
        rightThirdText = findViewById(R.id.rightThirdText);
        rightThirdText1 = findViewById(R.id.rightThirdText1);
        rightThirdView1 = findViewById(R.id.rightThirdView1);
        rightThirdView2 = findViewById(R.id.rightThirdView2);
        rightFourthText = findViewById(R.id.rightFourthText);

        centerViewRight = findViewById(R.id.centerViewRight);
        centerViewLeft = findViewById(R.id.centerViewLeft);
        centerText = findViewById(R.id.centerText);

        winnerText = findViewById(R.id.winnerText);
        dbHandler = new DBHandler(this);

        List<TextView> firstTeamsText = new ArrayList<TextView>();
        List<TextView> secondTeamsText = new ArrayList<TextView>();
        List<TextView> thirdTeamsText = new ArrayList<TextView>();
        List<TextView> fourthTeamsText = new ArrayList<TextView>();

        firstTeamsText.add(team1LeftText);
        firstTeamsText.add(team2LeftText);
        firstTeamsText.add(team3LeftText);
        firstTeamsText.add(team4LeftText);
        firstTeamsText.add(team5LeftText);
        firstTeamsText.add(team6LeftText);
        firstTeamsText.add(team7LeftText);
        firstTeamsText.add(team8LeftText);
        firstTeamsText.add(rightTeam8Text);
        firstTeamsText.add(rightTeam7Text);
        firstTeamsText.add(rightTeam6Text);
        firstTeamsText.add(rightTeam5Text);
        firstTeamsText.add(rightTeam4Text);
        firstTeamsText.add(rightTeam3Text);
        firstTeamsText.add(rightTeam2Text);
        firstTeamsText.add(rightTeam1Text);

        secondTeamsText.add(secondColumnTeam1);
        secondTeamsText.add(secondColumnTeam2);
        secondTeamsText.add(secondColumnTeam3);
        secondTeamsText.add(secondColumnTeam4);
        secondTeamsText.add(rightSecondText1);
        secondTeamsText.add(rightSecondText2);
        secondTeamsText.add(rightSecondText3);
        secondTeamsText.add(rightSecondText4);

        thirdTeamsText.add(thirdTextTeam1);
        thirdTeamsText.add(thirdTextTeam2);
        thirdTeamsText.add(rightThirdText);
        thirdTeamsText.add(rightThirdText1);

        fourthTeamsText.add(fourthText1);
        fourthTeamsText.add(rightFourthText);

        int championshipId = thisChampionshipTeams.get(0).get_championshipId();
        List<Teams> teamsF = new ArrayList<Teams>();

        if (thisChampionshipTeams.size() == 16) {
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

        List<Matches> matches = dbHandler.getMatchesByChampId(championshipId);

        if (teamsF.size() % 2 == 0 & teamsF.size() > 0) {
            if (!(matches.size() > 0)) {
                Matches match = new Matches();
                for (int j = 0; j < championshipTeams.size(); j += 2) {
                    ChampionshipTeams firstTeam = championshipTeams.get(j);
                    ChampionshipTeams secondTeam = championshipTeams.get(j + 1);
                    match.set_firstTeamId(firstTeam.get_teamId());
                    match.set_secondTeamId(secondTeam.get_teamId());
                    match.set_championshipId(championshipId);
                    match.set_date("");
                    match.set_hour("");
                    match.set_score("");
                    match.set_place("");
                    dbHandler.addMatch(match);
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
        List<Matches> twoMatches = new ArrayList<Matches>();

        if (finalTeams.size() % 2 == 0 & finalTeams.size() > 0) {
            Matches finalMatch = new Matches();
            int finalCount = 0;
            for (int j = 0; j < finalTeams.size(); j += 2) {
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
                    twoMatches.add(finalMatch);
                }
            }
        }

        List<Teams> winnerTeams = new ArrayList<>();

        for (int j = 0; j < fourthTeamsText.size(); ) {
            for (int i = 0; i < twoMatches.size(); i++) {
                Matches match1 = twoMatches.get(i);
                if (match1.get_winnerId() > 0) {
                    int winnerId = match1.get_winnerId();
                    Teams firstTeam = dbHandler.getTeam(winnerId);
                    fourthTeamsText.get(j).setText(firstTeam.get_clubHeadquarters());
                    winnerTeams.add(firstTeam);
                    j++;
                }
            }
            break;
        }

        List<Matches> existingFMatches = dbHandler.getMatchesByChampId(championshipId);
        Matches winnerMatch = new Matches();

        if (winnerTeams.size() % 2 == 0 & winnerTeams.size() > 0) {
            int winnerCount = 0;
            for (int j = 0; j < winnerTeams.size(); j++) {
                Teams firstTeam = finalTeams.get(j);
                Teams secondTeam = finalTeams.get(j + 1);

                for (Matches thisMatch : existingFMatches) {
                    if (!(thisMatch.get_firstTeamId() == firstTeam.get_teamId()) & !(thisMatch.get_secondTeamId() == secondTeam.get_teamId())) {
                        winnerCount++;
                    } else {
                        winnerCount = 0;
                        break;
                    }
                }
                if (winnerCount > 0) {
                    winnerMatch.set_firstTeamId(firstTeam.get_teamId());
                    winnerMatch.set_secondTeamId(secondTeam.get_teamId());
                    winnerMatch.set_championshipId(championshipId);
                    winnerMatch.set_date("");
                    winnerMatch.set_hour("");
                    winnerMatch.set_score("");
                    winnerMatch.set_place("");
                    dbHandler.addMatch(winnerMatch);
                }
            }
        }

        if (winnerMatch.get_winnerId() > 0) {
            Teams winnerTeam = dbHandler.getTeam(winnerMatch.get_winnerId());
            centerText.setText(winnerTeam.get_clubHeadquarters());
        }
    }
}


