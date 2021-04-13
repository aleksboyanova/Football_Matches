package com.example.footballmatch.classes;

public class Users {
    private int _id;
    private String _username;
    private String _password;
    private boolean _isOrganizer;

    public Users(String username, String password, boolean isOrganizer){
        this._username = username;
        this._password = password;
        this._isOrganizer = isOrganizer;
    }

    public Users() {
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public boolean is_isOrganizer() {
        return _isOrganizer;
    }

    public void set_isOrganizer(boolean _isOrganizer) {
        this._isOrganizer = _isOrganizer;
    }
}
