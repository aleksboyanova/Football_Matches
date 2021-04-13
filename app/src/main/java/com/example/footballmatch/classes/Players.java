package com.example.footballmatch.classes;

public class Players {
    private int _playerId;
    private String _firstName;
    private String _lastName;
    private int _teamId;
    private int _userId;

    public Players(String firstName, String lastName, int teamId, int userId){
        this._firstName = firstName;
        this._lastName = lastName;
        this._teamId = teamId;
        this._userId = userId;
    }

    public Players() {
    }

    public int get_teamId() {
        return _teamId;
    }

    public void set_teamId(Integer _teamId) {
        this._teamId = _teamId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public int get_playerId() {
        return _playerId;
    }

    public void set_playerId(int _playerId) {
        this._playerId = _playerId;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }
}
