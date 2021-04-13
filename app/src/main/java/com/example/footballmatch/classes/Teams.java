package com.example.footballmatch.classes;

public class Teams {
    private int _teamId;
    private String _clubHeadquarters;
    private String _stadium;
    private String _state;
    private int _ownerId;
    private int _refereeId;

    public Teams(String clubHeadquarters, String stadium, String state, int ownerId, int refereeId){
        this._clubHeadquarters = clubHeadquarters;
        this._stadium = stadium;
        this._state = state;
        this._ownerId = ownerId;
        this._refereeId = refereeId;
    }

    public Teams(){};

    public int get_refereeId() {
        return _refereeId;
    }

    public void set_refereeId(int _refereeId) {
        this._refereeId = _refereeId;
    }

    public int get_ownerId() {
        return _ownerId;
    }

    public void set_ownerId(Integer _ownerId) {
        this._ownerId = _ownerId;
    }

    public int get_teamId() {
        return _teamId;
    }

    public void set_teamId(int _teamId) {
        this._teamId = _teamId;
    }

    public String get_clubHeadquarters() {
        return _clubHeadquarters;
    }

    public void set_clubHeadquarters(String _clubHeadquarters) {
        this._clubHeadquarters = _clubHeadquarters;
    }

    public String get_stadium() {
        return _stadium;
    }

    public void set_stadium(String _stadium) {
        this._stadium = _stadium;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }
}
