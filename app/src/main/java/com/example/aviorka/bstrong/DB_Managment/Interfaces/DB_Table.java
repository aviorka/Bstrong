package com.example.aviorka.bstrong.DB_Managment.Interfaces;

import android.database.Cursor;

import java.util.ArrayList;

public interface DB_Table {

    public void insert(DB_Data data);

    public void update(DB_Data data);

    public void delete(int id);

    public int count();

    public DB_Data getById(int id);

    public ArrayList<DB_Data> getList();

    public DB_Data cursorToObject(Cursor cursor);
}