package com.example.footballmatch.classes;

public class Referees {
    private int _refereeId;
    private String _refereeState;
    private String _refereeFName;
    private String _refereeLName;
    private int _userId;

    public Referees(int refereeId, String refereeState, String refereeFName, String refereeLName, int userId) {
        this._refereeId = refereeId;
        this._refereeState = refereeState;
        this._refereeFName = refereeFName;
        this._refereeLName = refereeLName;
        this._userId = userId;
    }

    public Referees() {
    }

    public String get_refereeFName() {
        return _refereeFName;
    }

    public void set_refereeFName(String _refereeFName) {
        this._refereeFName = _refereeFName;
    }

    public String get_refereeLName() {
        return _refereeLName;
    }

    public void set_refereeLName(String _refereeLName) {
        this._refereeLName = _refereeLName;
    }

    public int get_refereeId() {
        return _refereeId;
    }

    public void set_refereeId(int _refereeId) {
        this._refereeId = _refereeId;
    }

    public String get_refereeState() {
        return _refereeState;
    }

    public void set_refereeState(String _refereeState) {
        this._refereeState = _refereeState;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }
}
