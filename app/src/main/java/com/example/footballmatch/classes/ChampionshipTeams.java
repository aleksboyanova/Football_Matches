package com.example.footballmatch.classes;

public class ChampionshipTeams {
    private int _championshipTeamId;
    private int _championshipId;
    private int _teamId;

    public ChampionshipTeams(int championshipTeamId, int championshipId, int teamId) {
        this._championshipTeamId = championshipTeamId;
        this._championshipId = championshipId;
        this._teamId = teamId;
    }

    public ChampionshipTeams() {
    }

    public int get_championshipTeamId() {
        return _championshipTeamId;
    }

    public void set_championshipTeamId(int _championshipTeamId) {
        this._championshipTeamId = _championshipTeamId;
    }

    public int get_championshipId() {
        return _championshipId;
    }

    public void set_championshipId(int _championshipId) {
        this._championshipId = _championshipId;
    }

    public int get_teamId() {
        return _teamId;
    }

    public void set_teamId(int _teamId) {
        this._teamId = _teamId;
    }
}
