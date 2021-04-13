package com.example.footballmatch.classes;

public class Championship {
    private int _championshipId;
    private String _name;
    private int _size;
    private String _date;
    private int _refereeId;

    public Championship(int championshipId, String name, int size, String date, int refereeId) {
        this._championshipId = championshipId;
        this._name = name;
        this._size = size;
        this._date = date;
        this._refereeId = refereeId;
    }

    public int get_refereeId() {
        return _refereeId;
    }

    public void set_refereeId(int _refereeId) {
        this._refereeId = _refereeId;
    }

    public Championship(){};

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public int get_championshipId() {
        return _championshipId;
    }

    public void set_championshipId(int _championshipId) {
        this._championshipId = _championshipId;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_size() {
        return _size;
    }

    public void set_size(int _size) {
        this._size = _size;
    }
}
