package com.example.footballmatch.classes;

public class Matches {
    private int _matchId;
    private String _date;
    private String _place;
    private String _hour;
    private String _score;
    private int _firstTeamId;
    private int _secondTeamId;
    private int _refereeId;
    private int _winnerId;
    private int _championshipId;

    public Matches(String date, String place, String hour, String score, int firstTeamId, int secondTeamId, int refereeId, int winnerId, int championshipId) {
        this._date = date;
        this._place = place;
        this._hour = hour;
        this._score = score;
        this._firstTeamId = firstTeamId;
        this._secondTeamId = secondTeamId;
        this._refereeId = refereeId;
        this._winnerId = winnerId;
        this._championshipId = championshipId;
    }

    public Matches(){

    }

    public int get_matchId() {
        return _matchId;
    }

    public void set_matchId(int _matchId) {
        this._matchId = _matchId;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_place() {
        return _place;
    }

    public void set_place(String _place) {
        this._place = _place;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public int get_firstTeamId() {
        return _firstTeamId;
    }

    public void set_firstTeamId(int _firstTeamId) {
        this._firstTeamId = _firstTeamId;
    }

    public int get_secondTeamId() {
        return _secondTeamId;
    }

    public void set_secondTeamId(int _secondTeamId) {
        this._secondTeamId = _secondTeamId;
    }

    public int get_refereeId() {
        return _refereeId;
    }

    public void set_refereeId(int _refereeId) {
        this._refereeId = _refereeId;
    }

    public int get_winnerId() {
        return _winnerId;
    }

    public void set_winnerId(int _winnerId) {
        this._winnerId = _winnerId;
    }

    public int get_championshipId() {
        return _championshipId;
    }

    public void set_championshipId(int _championshipId) {
        this._championshipId = _championshipId;
    }

    public String get_hour() {
        return _hour;
    }

    public void set_hour(String _hour) {
        this._hour = _hour;
    }
}
