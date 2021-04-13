package com.example.footballmatch.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.footballmatch.classes.Championship;
import com.example.footballmatch.classes.ChampionshipTeams;
import com.example.footballmatch.classes.Matches;
import com.example.footballmatch.classes.Players;
import com.example.footballmatch.classes.Referees;
import com.example.footballmatch.classes.Teams;
import com.example.footballmatch.classes.Users;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 42;
    private static final String DATABASE_NAME = "football_match_Db";

    //Users
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_IS_ORGANIZER = "isOrganizer";

    //Team
    public static final String TABLE_TEAM = "team";
    public static final String COLUMN_TEAM_ID = "teamId";
    public static final String COLUMN_CLUB_HEADQUARTERS = "clubHeadquarters";
    public static final String COLUMN_STADIUM = "stadium";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_OWNER_ID = "ownerId";
    public static final String COLUMN_TEAM_REFEREE_ID = "refereeId";

    //Player
    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_PLAYER_ID = "playerId";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_TEAMID = "teamId";
    public static final String COLUMN_USER_ID = "userId";

    //Championship
    public static final String TABLE_CHAMPIONSHIP = "championship";
    public static final String COLUMN_CHAMPIONSHIP_ID = "championshipId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_CHAMP_DATE = "date";
    public static final String COLUMN_CHAMP_REFEREE_ID = "refereeId";

    //Referee
    public static final String TABLE_REFEREE = "referee";
    public static final String COLUMN_REFEREE_ID = "refereeId";
    public static final String COLUMN_REFEREE_FNAME = "refereeFName";
    public static final String COLUMN_REFEREE_LNAME = "refreeeLName";
    public static final String COLUMN_STATE_ = "state";
    public static final String COLUMN_USERID = "userId";

    //Match
    public static final String TABLE_MATCH = "matches";
    public static final String COLUMN_MATCH_ID = "matchId";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_FIRST_TEAM_ID = "firstTeamId";
    public static final String COLUMN_SECOND_TEAM_ID = "secondTeamId";
    public static final String COLUMN_REFEREEID = "refereeId";
    public static final String COLUMN_WINNERID = "winnerId";
    public static final String COLUMN_CHAMPIONSHIPID = "championshipId";

    //ChampionshipTeams
    public static final String TABLE_CHAMPIONSHIP_TEAMS = "championshipTeams";
    public static final String COLUMN_CHAMPIONSHIP_TEAMS_ID = "championshipTeamsId";
    public static final String COLUMN_CHAMPIONSHIPS_ID = "championshipId";
    public static final String COLUMN_TEAMSID = "teamId";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Table Team
        String team = "CREATE TABLE " + TABLE_TEAM + "(" +
                COLUMN_TEAM_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_CLUB_HEADQUARTERS + " NVARCHAR  ," +
                COLUMN_STADIUM + " NVARCHAR  ," +
                COLUMN_STATE + " NVARCHAR  ," +
                COLUMN_OWNER_ID + " INTEGER ," +
                COLUMN_TEAM_REFEREE_ID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_OWNER_ID + ")" + " REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_TEAM_REFEREE_ID + ")" + " REFERENCES " + TABLE_REFEREE + "(" + COLUMN_REFEREE_ID + ")" +
                ")";
        db.execSQL(team);

        //Table Users
        String users = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_USERNAME + " NVARCHAR NOT NULL," +
                COLUMN_PASSWORD + " NVARCHAR NOT NULL ," +
                COLUMN_IS_ORGANIZER + " BIT NOT NULL " +
                ")";
        db.execSQL(users);

        //Table Player
        String player = "CREATE TABLE " + TABLE_PLAYER + "(" +
                COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_FIRST_NAME + " NVARCHAR ," +
                COLUMN_LAST_NAME + " NVARCHAR  ," +
                COLUMN_TEAMID + " INTEGER ," +
                COLUMN_USER_ID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_TEAMID + ")" + " REFERENCES " + TABLE_TEAM + "(" + COLUMN_TEAM_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_USER_ID + ")" + " REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")" +
                ")";
        db.execSQL(player);

        //Table Championship
        String championship = "CREATE TABLE " + TABLE_CHAMPIONSHIP + "(" +
                COLUMN_CHAMPIONSHIP_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_NAME + " NVARCHAR ," +
                COLUMN_SIZE + " NVARCHAR ," +
                COLUMN_CHAMP_DATE + " NVARCHAR ," +
                COLUMN_CHAMP_REFEREE_ID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_CHAMP_REFEREE_ID + ")" + " REFERENCES " + TABLE_REFEREE + "(" + COLUMN_REFEREE_ID + ")" +
                ")";
        db.execSQL(championship);

        //Table Referee
        String referee = "CREATE TABLE " + TABLE_REFEREE + "(" +
                COLUMN_REFEREE_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_STATE_ + " NVARCHAR ," +
                COLUMN_REFEREE_FNAME + " NVARCHAR ," +
                COLUMN_REFEREE_LNAME + " NVARCHAR ," +
                COLUMN_USERID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_USERID + ")" + " REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")" +
                ")";
        db.execSQL(referee);

        //Table Match
        String match = "CREATE TABLE " + TABLE_MATCH + "(" +
                COLUMN_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_DATE + " NVARCHAR ," +
                COLUMN_PLACE + " NVARCHAR ," +
                COLUMN_HOUR + " NVARCHAR ," +
                COLUMN_SCORE + " NVARCHAR ," +
                COLUMN_FIRST_TEAM_ID + " INTEGER ," +
                COLUMN_SECOND_TEAM_ID + " INTEGER ," +
                COLUMN_REFEREEID + " INTEGER ," +
                COLUMN_WINNERID + " INTEGER ," +
                COLUMN_CHAMPIONSHIPID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_FIRST_TEAM_ID + ")" + " REFERENCES " + TABLE_TEAM + "(" + COLUMN_TEAM_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_SECOND_TEAM_ID + ")" + " REFERENCES " + TABLE_TEAM + "(" + COLUMN_TEAM_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_REFEREEID + ")" + " REFERENCES " + TABLE_REFEREE + "(" + COLUMN_REFEREE_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_WINNERID + ")" + " REFERENCES " + TABLE_TEAM + "(" + COLUMN_TEAM_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_CHAMPIONSHIPID + ")" + " REFERENCES " + TABLE_CHAMPIONSHIP + "(" + COLUMN_CHAMPIONSHIP_ID + ")" +
                ")";
        db.execSQL(match);

        //Table ChampionshipTeams
        String championshipTeams = "CREATE TABLE " + TABLE_CHAMPIONSHIP_TEAMS + "(" +
                COLUMN_CHAMPIONSHIP_TEAMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_CHAMPIONSHIPS_ID + " INTEGER ," +
                COLUMN_TEAMSID + " INTEGER ," +
                " FOREIGN KEY " + "(" + COLUMN_CHAMPIONSHIPS_ID + ")" + " REFERENCES " + TABLE_CHAMPIONSHIP + "(" + COLUMN_CHAMPIONSHIP_ID + ")," +
                " FOREIGN KEY " + "(" + COLUMN_TEAMSID + ")" + " REFERENCES " + TABLE_TEAM + "(" + COLUMN_TEAM_ID + ")" +
                ")";
        db.execSQL(championshipTeams);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_REFEREE);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MATCH);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CHAMPIONSHIP_TEAMS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CHAMPIONSHIP);
        onCreate(db);
    }

    public void addUser(Users user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_username());
        values.put(COLUMN_PASSWORD, user.get_password());
        if (user.is_isOrganizer()) {
            values.put(COLUMN_IS_ORGANIZER, 1);
        } else {
            values.put(COLUMN_IS_ORGANIZER, 0);
        }

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addPlayer(Players player) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, player.get_firstName());
        values.put(COLUMN_LAST_NAME, player.get_lastName());
        values.put(COLUMN_TEAMID, player.get_teamId());
        values.put(COLUMN_USER_ID, player.get_userId());

        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    public void addTeam(Teams team) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUB_HEADQUARTERS, team.get_clubHeadquarters());
        values.put(COLUMN_STADIUM, team.get_stadium());
        values.put(COLUMN_STATE, team.get_state());
        values.put(COLUMN_OWNER_ID, team.get_ownerId());
        values.put(COLUMN_TEAM_REFEREE_ID, team.get_refereeId());

        db.insert(TABLE_TEAM, null, values);
        db.close();
    }

    public void addChampionship(Championship championship) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, championship.get_name());
        values.put(COLUMN_SIZE, championship.get_size());
        values.put(COLUMN_CHAMP_DATE, championship.get_date());
        values.put(COLUMN_CHAMP_REFEREE_ID, championship.get_refereeId());

        db.insert(TABLE_CHAMPIONSHIP, null, values);
        db.close();
    }

    public void addReferee(Referees referee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE_, referee.get_refereeState());
        values.put(COLUMN_REFEREE_FNAME, referee.get_refereeFName());
        values.put(COLUMN_REFEREE_LNAME, referee.get_refereeLName());
        values.put(COLUMN_USERID, referee.get_userId());

        db.insert(TABLE_REFEREE, null, values);
        db.close();
    }

    public void addMatch(Matches match) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, match.get_date());
        values.put(COLUMN_PLACE, match.get_place());
        values.put(COLUMN_HOUR, match.get_hour());
        values.put(COLUMN_SCORE, match.get_score());
        values.put(COLUMN_FIRST_TEAM_ID, match.get_firstTeamId());
        values.put(COLUMN_SECOND_TEAM_ID, match.get_secondTeamId());
        values.put(COLUMN_REFEREEID, match.get_refereeId());
        values.put(COLUMN_WINNERID, match.get_winnerId());
        values.put(COLUMN_CHAMPIONSHIPID, match.get_championshipId());

        db.insert(TABLE_MATCH, null, values);
        db.close();
    }

    public void addChampionshipTeam(ChampionshipTeams championshipTeam) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAMSID, championshipTeam.get_teamId());
        values.put(COLUMN_CHAMPIONSHIPS_ID, championshipTeam.get_championshipId());

        db.insert(TABLE_CHAMPIONSHIP_TEAMS, null, values);
        db.close();
    }

    public int updateTeam(String clubHeadquarters, String stadium, String state, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUB_HEADQUARTERS, clubHeadquarters);
        values.put(COLUMN_STADIUM, stadium);
        values.put(COLUMN_STATE, state);

        return db.update(TABLE_TEAM, values, COLUMN_OWNER_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public int updateMatch(String date, String place, String hour, String score, int winnerId, int refereeId, int matchId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_PLACE, place);
        values.put(COLUMN_HOUR, hour);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_WINNERID, winnerId);
        values.put(COLUMN_REFEREEID, refereeId);

        return db.update(TABLE_MATCH, values, COLUMN_MATCH_ID + "=?", new String[]{String.valueOf(matchId)});
    }

    public int updateTeamsReferee(int refereeId, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAM_REFEREE_ID, refereeId);

        return db.update(TABLE_TEAM, values, COLUMN_OWNER_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public int updatePlayersTeam(int teamId, int playerId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAMID, teamId);

        return db.update(TABLE_PLAYER, values, COLUMN_PLAYER_ID + "=?", new String[]{String.valueOf(playerId)});
    }

    public int changeRefereeState(String state, int refereeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE_, state);

        return db.update(TABLE_REFEREE, values, COLUMN_REFEREE_ID + "=?", new String[]{String.valueOf(refereeId)});
    }

    public List<Users> getAllUser() {
        String[] columns = {
                COLUMN_ID,
                COLUMN_PASSWORD,
                COLUMN_USERNAME,
                COLUMN_IS_ORGANIZER
        };
        // sorting orders
        String sortOrder =
                COLUMN_USERNAME + " ASC";
        List<Users> userList = new ArrayList<Users>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.set_password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ORGANIZER)) == 1) {
                    user.set_isOrganizer(true);
                } else {
                    user.set_isOrganizer(false);
                }
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public List<Players> getPlayersByTeam(int teamId) {
        String[] columns = {
                COLUMN_PLAYER_ID,
                COLUMN_FIRST_NAME,
                COLUMN_LAST_NAME,
                COLUMN_TEAMID,
                COLUMN_USER_ID
        };

        String query = "SELECT * FROM " + TABLE_PLAYER + " WHERE " + COLUMN_TEAMID + " = " + "\"" + teamId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        List<Players> playersList = new ArrayList<Players>();
        Players player = new Players();
        if (cursor.moveToFirst()) {
            player.set_playerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_ID))));
            player.set_firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
            player.set_lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
            player.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMID))));
            player.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
            playersList.add(player);
        }
        cursor.close();
        db.close();
        return playersList;
    }

    public List<ChampionshipTeams> getAllChampionshipTeams() {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_TEAMS_ID,
                COLUMN_CHAMPIONSHIPS_ID,
                COLUMN_TEAMSID
        };

        String sortOrder =
                COLUMN_TEAMSID + " ASC";
        List<ChampionshipTeams> championshipTeamsList = new ArrayList<ChampionshipTeams>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHAMPIONSHIP_TEAMS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                ChampionshipTeams championshipTeam = new ChampionshipTeams();
                championshipTeam.set_championshipTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_TEAMS_ID))));
                championshipTeam.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPS_ID))));
                championshipTeam.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMSID))));

                championshipTeamsList.add(championshipTeam);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return championshipTeamsList;
    }

    public List<Championship> getAllChampionship() {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_ID,
                COLUMN_NAME,
                COLUMN_SIZE,
                COLUMN_CHAMP_DATE,
                COLUMN_CHAMP_REFEREE_ID
        };

        List<Championship> championshipsList = new ArrayList<Championship>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHAMPIONSHIP, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Championship championship = new Championship();
                championship.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_ID))));
                championship.set_name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                championship.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_DATE)));
                championship.set_size(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SIZE))));
                championship.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_REFEREE_ID))));

                championshipsList.add(championship);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return championshipsList;
    }

    public List<String> getChampionshipTeams() {
        List<Teams> teams = getAllTeams();
        List<ChampionshipTeams> championshipTeams = getAllChampionshipTeams();
        List<String> teamsName = new ArrayList<>();
        for (Teams team : teams) {
            for (ChampionshipTeams championshipTeam : championshipTeams) {
                if (championshipTeam.get_teamId() == team.get_teamId()) {
                    teamsName.add(team.get_clubHeadquarters());
                }
            }
        }
        return teamsName;
    }

    public List<Players> getAllPlayers() {
        String[] columns = {
                COLUMN_PLAYER_ID,
                COLUMN_FIRST_NAME,
                COLUMN_LAST_NAME,
                COLUMN_TEAMID,
                COLUMN_USER_ID
        };
        // sorting orders
        String sortOrder =
                COLUMN_FIRST_NAME + " ASC";
        List<Players> playersList = new ArrayList<Players>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Players player = new Players();
                player.set_playerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_ID))));
                player.set_firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                player.set_lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
                player.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMID))));
                player.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                playersList.add(player);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return playersList;
    }

    public List<Matches> getAllMatches() {
        String[] columns = {
                COLUMN_MATCH_ID,
                COLUMN_DATE,
                COLUMN_PLACE,
                COLUMN_HOUR,
                COLUMN_SCORE,
                COLUMN_FIRST_TEAM_ID,
                COLUMN_SECOND_TEAM_ID,
                COLUMN_REFEREEID,
                COLUMN_WINNERID,
                COLUMN_CHAMPIONSHIPID
        };
        // sorting orders
        String sortOrder =
                COLUMN_PLACE + " ASC";
        List<Matches> matchesList = new ArrayList<Matches>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MATCH, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Matches match = new Matches();
                match.set_matchId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MATCH_ID))));
                match.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                match.set_place(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                match.set_hour(cursor.getString(cursor.getColumnIndex(COLUMN_HOUR)));
                match.set_score(cursor.getString(cursor.getColumnIndex(COLUMN_SCORE)));
                match.set_firstTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_TEAM_ID))));
                match.set_secondTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_TEAM_ID))));
                match.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREEID))));
                match.set_winnerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WINNERID))));
                match.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPID))));
                matchesList.add(match);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return matchesList;
    }

    public List<Matches> getAllMatchesNotSorted() {
        String[] columns = {
                COLUMN_MATCH_ID,
                COLUMN_DATE,
                COLUMN_PLACE,
                COLUMN_HOUR,
                COLUMN_SCORE,
                COLUMN_FIRST_TEAM_ID,
                COLUMN_SECOND_TEAM_ID,
                COLUMN_REFEREEID,
                COLUMN_WINNERID,
                COLUMN_CHAMPIONSHIPID
        };
        List<Matches> matchesList = new ArrayList<Matches>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MATCH, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Matches match = new Matches();
                match.set_matchId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MATCH_ID))));
                match.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                match.set_place(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                match.set_hour(cursor.getString(cursor.getColumnIndex(COLUMN_HOUR)));
                match.set_score(cursor.getString(cursor.getColumnIndex(COLUMN_SCORE)));
                match.set_firstTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_TEAM_ID))));
                match.set_secondTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_TEAM_ID))));
                match.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREEID))));
                match.set_winnerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WINNERID))));
                match.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPID))));
                matchesList.add(match);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return matchesList;
    }

    public List<Matches> getMatchesByChampId(int champId) {
        String[] columns = {
                COLUMN_MATCH_ID,
                COLUMN_DATE,
                COLUMN_PLACE,
                COLUMN_HOUR,
                COLUMN_SCORE,
                COLUMN_FIRST_TEAM_ID,
                COLUMN_SECOND_TEAM_ID,
                COLUMN_REFEREEID,
                COLUMN_WINNERID,
                COLUMN_CHAMPIONSHIPID
        };

        List<Matches> matchesList = new ArrayList<Matches>();
        String query = "SELECT * FROM " + TABLE_MATCH + " WHERE " + COLUMN_CHAMPIONSHIPID + " = " + "\"" + champId + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Matches match = new Matches();
                match.set_matchId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MATCH_ID))));
                match.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                match.set_place(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                match.set_hour(cursor.getString(cursor.getColumnIndex(COLUMN_HOUR)));
                match.set_score(cursor.getString(cursor.getColumnIndex(COLUMN_SCORE)));
                match.set_firstTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_TEAM_ID))));
                match.set_secondTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECOND_TEAM_ID))));
                match.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREEID))));
                match.set_winnerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WINNERID))));
                match.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPID))));
                matchesList.add(match);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return matchesList;
    }

    public Users getUser(String username) {
        String[] columns = {
                COLUMN_ID,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_IS_ORGANIZER
        };

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = " + "\"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Users user = new Users();
        if (cursor.moveToFirst()) {
            user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
            user.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            user.set_password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ORGANIZER)) == 1) {
                user.set_isOrganizer(true);
            } else {
                user.set_isOrganizer(false);
            }
        }
        cursor.close();
        db.close();
        return user;
    }

    public List<Teams> getAllTeams() {
        String[] columns = {
                COLUMN_TEAM_ID,
                COLUMN_CLUB_HEADQUARTERS,
                COLUMN_STADIUM,
                COLUMN_STATE,
                COLUMN_OWNER_ID,
                COLUMN_TEAM_REFEREE_ID
        };
        String sortOrder =
                COLUMN_CLUB_HEADQUARTERS + " ASC";
        List<Teams> teamList = new ArrayList<Teams>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TEAM, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Teams team = new Teams();
                team.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID))));
                team.set_clubHeadquarters(cursor.getString(cursor.getColumnIndex(COLUMN_CLUB_HEADQUARTERS)));
                team.set_stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUM)));
                team.set_state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
                team.set_ownerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER_ID))));
                team.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_REFEREE_ID))));
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return teamList;
    }

    public List<Referees> getAllReferees() {
        String[] columns = {
                COLUMN_REFEREE_ID,
                COLUMN_REFEREE_FNAME,
                COLUMN_REFEREE_LNAME,
                COLUMN_STATE_,
                COLUMN_USERID
        };
        List<Referees> refereeList = new ArrayList<Referees>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REFEREE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Referees referee = new Referees();
                referee.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_ID))));
                referee.set_refereeFName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_FNAME)));
                referee.set_refereeLName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_LNAME)));
                referee.set_refereeState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE_)));
                referee.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERID))));
                refereeList.add(referee);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return refereeList;
    }

    public Teams getTeam(int teamId) {
        String[] columns = {
                COLUMN_TEAM_ID,
                COLUMN_CLUB_HEADQUARTERS,
                COLUMN_STADIUM,
                COLUMN_STATE,
                COLUMN_OWNER_ID,
                COLUMN_TEAM_REFEREE_ID
        };

        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + COLUMN_TEAM_ID + " = " + "\"" + teamId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Teams team = new Teams();
        if (cursor.moveToFirst()) {
            team.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID))));
            team.set_clubHeadquarters(cursor.getString(cursor.getColumnIndex(COLUMN_CLUB_HEADQUARTERS)));
            team.set_stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUM)));
            team.set_state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
            team.set_ownerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER_ID))));
            team.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_REFEREE_ID))));
        }
        cursor.close();
        db.close();
        return team;
    }

    public Championship getChampionshipByName(String name) {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_ID,
                COLUMN_NAME,
                COLUMN_SIZE,
                COLUMN_CHAMP_DATE,
                COLUMN_CHAMP_REFEREE_ID
        };

        String query = "SELECT * FROM " + TABLE_CHAMPIONSHIP + " WHERE " + COLUMN_NAME + " = " + "\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Championship championship = new Championship();
        if (cursor.moveToFirst()) {
            championship.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_ID))));
            championship.set_name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            championship.set_size(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SIZE))));
            championship.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_DATE)));
            championship.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_REFEREE_ID))));
        }
        cursor.close();
        db.close();
        return championship;
    }

    public Championship getChampionshipById(int champId) {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_ID,
                COLUMN_NAME,
                COLUMN_SIZE,
                COLUMN_CHAMP_DATE,
                COLUMN_CHAMP_REFEREE_ID
        };

        String query = "SELECT * FROM " + TABLE_CHAMPIONSHIP + " WHERE " + COLUMN_CHAMPIONSHIP_ID + " = " + "\"" + champId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Championship championship = new Championship();
        if (cursor.moveToFirst()) {
            championship.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_ID))));
            championship.set_name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            championship.set_size(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SIZE))));
            championship.set_date(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_DATE)));
            championship.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMP_REFEREE_ID))));
        }
        cursor.close();
        db.close();
        return championship;
    }

    public Teams getTeamByName(String name) {
        String[] columns = {
                COLUMN_TEAM_ID,
                COLUMN_CLUB_HEADQUARTERS,
                COLUMN_STADIUM,
                COLUMN_STATE,
                COLUMN_OWNER_ID,
                COLUMN_TEAM_REFEREE_ID
        };

        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + COLUMN_CLUB_HEADQUARTERS + " = " + "\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Teams team = new Teams();
        if (cursor.moveToFirst()) {
            team.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID))));
            team.set_clubHeadquarters(cursor.getString(cursor.getColumnIndex(COLUMN_CLUB_HEADQUARTERS)));
            team.set_stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUM)));
            team.set_state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
            team.set_ownerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER_ID))));
            team.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_REFEREE_ID))));
        }
        cursor.close();
        db.close();
        return team;
    }

    public Referees getRefereeByName(String name) {
        String[] columns = {
                COLUMN_REFEREE_ID,
                COLUMN_REFEREE_FNAME,
                COLUMN_REFEREE_LNAME,
                COLUMN_STATE_,
                COLUMN_USERID
        };

        String query = "SELECT * FROM " + TABLE_REFEREE + " WHERE " + COLUMN_REFEREE_FNAME + " = " + "\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Referees referee = new Referees();
        if (cursor.moveToFirst()) {
            referee.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_ID))));
            referee.set_refereeFName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_FNAME)));
            referee.set_refereeLName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_LNAME)));
            referee.set_refereeState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE_)));
            referee.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERID))));
        }
        cursor.close();
        db.close();
        return referee;
    }

    public Referees getRefereeById(int refereeId) {
        String[] columns = {
                COLUMN_REFEREE_ID,
                COLUMN_REFEREE_FNAME,
                COLUMN_REFEREE_LNAME,
                COLUMN_STATE_,
                COLUMN_USERID
        };

        String query = "SELECT * FROM " + TABLE_REFEREE + " WHERE " + COLUMN_REFEREE_ID + " = " + "\"" + refereeId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Referees referee = new Referees();
        if (cursor.moveToFirst()) {
            referee.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_ID))));
            referee.set_refereeFName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_FNAME)));
            referee.set_refereeLName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_LNAME)));
            referee.set_refereeState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE_)));
            referee.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERID))));
        }
        cursor.close();
        db.close();
        return referee;
    }

    public Referees getRefereeByState(String state) {
        String[] columns = {
                COLUMN_REFEREE_ID,
                COLUMN_REFEREE_FNAME,
                COLUMN_REFEREE_LNAME,
                COLUMN_STATE_,
                COLUMN_USERID
        };

        String query = "SELECT * FROM " + TABLE_REFEREE + " WHERE " + COLUMN_STATE_ + " = " + "\"" + state + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Referees referee = new Referees();
        if (cursor.moveToFirst()) {
            referee.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_ID))));
            referee.set_refereeFName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_FNAME)));
            referee.set_refereeLName(cursor.getString(cursor.getColumnIndex(COLUMN_REFEREE_LNAME)));
            referee.set_refereeState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE_)));
            referee.set_userId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERID))));
        }
        cursor.close();
        db.close();
        return referee;
    }

    public Teams getTeamByOwnerId(int ownerId) {
        String[] columns = {
                COLUMN_TEAM_ID,
                COLUMN_CLUB_HEADQUARTERS,
                COLUMN_STADIUM,
                COLUMN_STATE,
                COLUMN_OWNER_ID,
                COLUMN_TEAM_REFEREE_ID
        };

        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + COLUMN_OWNER_ID + " = " + "\"" + ownerId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Teams team = new Teams();
        if (cursor.moveToFirst()) {
            team.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID))));
            team.set_clubHeadquarters(cursor.getString(cursor.getColumnIndex(COLUMN_CLUB_HEADQUARTERS)));
            team.set_stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUM)));
            team.set_state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
            team.set_ownerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER_ID))));
            team.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_REFEREE_ID))));
        }
        cursor.close();
        db.close();
        return team;
    }

    public List<Teams> getAllTeamsByOwnerId(int ownerId) {
        String[] columns = {
                COLUMN_TEAM_ID,
                COLUMN_CLUB_HEADQUARTERS,
                COLUMN_STADIUM,
                COLUMN_STATE,
                COLUMN_OWNER_ID,
                COLUMN_TEAM_REFEREE_ID
        };

        List<Teams> teamsList = new ArrayList<Teams>();
        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + COLUMN_OWNER_ID + " = " + "\"" + ownerId + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Teams team = new Teams();
                team.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID))));
                team.set_clubHeadquarters(cursor.getString(cursor.getColumnIndex(COLUMN_CLUB_HEADQUARTERS)));
                team.set_stadium(cursor.getString(cursor.getColumnIndex(COLUMN_STADIUM)));
                team.set_state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
                team.set_ownerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER_ID))));
                team.set_refereeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_REFEREE_ID))));
                teamsList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return teamsList;
    }

    public List<ChampionshipTeams> getChampionshipTeams(int champId) {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_TEAMS_ID,
                COLUMN_CHAMPIONSHIPS_ID,
                COLUMN_TEAMSID
        };

        List<ChampionshipTeams> championshipTeamsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CHAMPIONSHIP_TEAMS + " WHERE " + COLUMN_CHAMPIONSHIPS_ID + " = " + "\"" + champId + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ChampionshipTeams championshipTeam = new ChampionshipTeams();
                championshipTeam.set_championshipTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_TEAMS_ID))));
                championshipTeam.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPS_ID))));
                championshipTeam.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMSID))));
                championshipTeamsList.add(championshipTeam);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return championshipTeamsList;
    }

    public List<ChampionshipTeams> getAllChampionshipTeamsByTeamId(int teamId) {
        String[] columns = {
                COLUMN_CHAMPIONSHIP_TEAMS_ID,
                COLUMN_CHAMPIONSHIPS_ID,
                COLUMN_TEAMSID
        };

        List<ChampionshipTeams> championshipTeamsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CHAMPIONSHIP_TEAMS + " WHERE " + COLUMN_TEAMSID + " = " + "\"" + teamId + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ChampionshipTeams championshipTeam = new ChampionshipTeams();
                championshipTeam.set_championshipTeamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIP_TEAMS_ID))));
                championshipTeam.set_championshipId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CHAMPIONSHIPS_ID))));
                championshipTeam.set_teamId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMSID))));
                championshipTeamsList.add(championshipTeam);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return championshipTeamsList;
    }

    public List<Users> checkAdmin() {
        String[] columns = {
                COLUMN_ID,
                COLUMN_PASSWORD,
                COLUMN_USERNAME,
                COLUMN_IS_ORGANIZER
        };

        List<Users> userList = new ArrayList<Users>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_USERS + " WHERE " + COLUMN_IS_ORGANIZER + " = 1 ", null);
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.set_password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.set_isOrganizer(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_IS_ORGANIZER))));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteUser(Users user) {
        SQLiteDatabase db = getReadableDatabase();
        int id = user.get_id();
        return db.delete(TABLE_USERS, COLUMN_ID + "=" + id, null) > 0;
    }
}

