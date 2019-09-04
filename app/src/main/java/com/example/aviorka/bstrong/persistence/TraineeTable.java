package com.example.aviorka.bstrong.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aviorka.bstrong.persistence.DataObjects.Trainee;

import java.util.ArrayList;

/**
 * Trainee Table
 * Equipment Table
 * Recurrence Table
 * plan Table
 * Exercise Table
 * Database
 */
public class TraineeTable extends SQLiteOpenHelper  {

    private static TraineeTable instance;
    // Database Name
    public static final String DATABASE_NAME = "BStrong.db";
    // Contacts table name
    public static final String TRAINEE_TABLE = "trainee";// User table name
    public static final String EQUIPMENT_TABLE = "equipment";// Equipment table name
    public static final String RECURRENCE_TABLE = "recurrence";// Recurrence table name
    public static final String PLAN_TABLE = "plan";// Plan table name
    public static final String EXERCISE_TABLE = "exercise";// Exercise table name


    // Trainee Table Columns names
    public static final String COL_USER_ID = "ID";
    public static final String COL_USERNAME = "FULL_NAME";
    public static final String COL_PASS = "PASSWORD";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_AGE = "AGE";
    public static final String COL_WEIGHT = "WEIGHT";

    // Equipment Table Columns names
    public static final String COL_EQUIPMENT_ID = "ID";
    public static final String COL_EQUIPMENT_NAME = "NAME";

    // Muscle Table Columns names
    public static final String COL_MUSCLE_ID = "ID";
    public static final String COL_MUSCLE_NAME = "NAME";

    // Recurrence Table Columns names
    public static final String COL_RECURRENCE_ID = "ID";
    public static final String COL_RECURRENCE_NAME = "FULL_NAME";
    public static final String COL_WEEKLYDAYS = "WEEKLYDAYS";

    // Plan Table Columns names
    public static final String COL_PLAN_ID = "ID";
    public static final String COL_EQUIPMENTID = "EQUIPMENT_ID";
    public static final String COL_RECURRENCEID = "RECURRENCE_ID";

    // Exercise Table Columns names
    public static final String COL_EXERCISE_ID = "ID";
    //TODO
    //Add COLUMNS :
    //equipmentID , muscleId , recurrenceID
    public static final String COL_SESSIONS_ID = "SESSIONS";
    public static final String COL_REPETITIONS_ID = "REPETITIONS";


    public static final int DATABASE_VERSION = 1;

    SQLiteDatabase db;

 public static TraineeTable geInstance(Context ctx){
     if(instance == null){
         instance = new TraineeTable(ctx);
     }
     return instance;
 }
    //DataBase Helper
    private TraineeTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreat
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTraineeTable = "create table trainee (" + COL_USER_ID + " integer primary key AUTOINCREMENT  ,"
                + COL_USERNAME + " text not null, "
                + COL_NAME + " text not null, "
                + COL_PASS + " text not null, "
                + COL_EMAIL + " text not null );";

        //Execut the queay
        db.execSQL(createTraineeTable);
        this.db = db;
    }

    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TRAINEE_TABLE);
        // Creating tables again
        this.onCreate(db);
    }


    //Inserting data to Trainee table
    public void insert(DB_Data data) {
        Trainee trainee = (Trainee)data;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, trainee.getUsername());
        values.put(COL_NAME, trainee.getName());
        values.put(COL_PASS, trainee.getPassword());
        values.put(COL_EMAIL, trainee.getEmail());


        // Inserting Row
        db.insert(TRAINEE_TABLE, null, values);
        // Closing database connection
        db.close();
    }

    //Update Name for trainee
    public void update(DB_Data data) {
        Trainee trainee = (Trainee)data;

        SQLiteDatabase db = this.getWritableDatabase();
        String quary = null;

        quary = "UPDATE " + TRAINEE_TABLE + " SET " + COL_NAME +
                " = '" + trainee.getName() + "' , " + COL_USERNAME + " = '"+trainee.getUsername()+"'" +
                " WHERE " + COL_USER_ID + " = '" + trainee.getId() + "'";

        db.execSQL(quary);
    }

    //Delete by id
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String quary = "DELETE FROM " + TRAINEE_TABLE + " WHERE "
                + COL_USER_ID + " = '" + id + "'" ;

        db.execSQL(quary);
    }

    //Count trainees
    public int count() {
        String countQuery = "SELECT  * FROM " + TRAINEE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    //Get trainee by id
    public DB_Data getById(int id) {
        String countQuery = "SELECT  * FROM " + TRAINEE_TABLE +  " WHERE " + COL_USER_ID + " = '" + id + "'";;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return  cursorToObject(cursor);
    }

    public ArrayList<DB_Data> getList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String quary = "SELECT * FROM " + TRAINEE_TABLE;
        Cursor mCursor = db.rawQuery(quary, null);
        ArrayList<DB_Data> trainees = new ArrayList<DB_Data>();
        mCursor.moveToFirst();

        while (!mCursor.isAfterLast()) {
            Trainee trainee = (Trainee)cursorToObject(mCursor);
            trainees.add(trainee);
            mCursor.moveToNext();
        }
        mCursor.close();
        return trainees;
    }

    public DB_Data cursorToObject(Cursor cursor) {
        Trainee trainee = new Trainee();
        trainee.setId(cursor.getLong(0));
        trainee.setUsername(cursor.getString(1));
        trainee.setName(cursor.getString(2));
        trainee.setPassword(cursor.getString(3));
        trainee.setEmail(cursor.getString(4));
        trainee.setScore(cursor.getString(5));
        return trainee;
    }


    //Check if userEmail already exist
    public boolean checkIfEmailExists(String userEmail) {
        db = this.getReadableDatabase();

        String query = "select " + COL_EMAIL + " from " + TRAINEE_TABLE + " where " + COL_EMAIL + " = '" + userEmail + "';";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //Check if userName already exist
    public boolean checkIfUserExists(String username) {
        db = this.getReadableDatabase();

        String query = "select " + COL_PASS + " from " + TRAINEE_TABLE + " where " + COL_PASS + " = '" + username + "';";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //Check the match beetwen user input and existUser
    public boolean searchUsername(String username) {

        //Read data from dataBase
        db = this.getReadableDatabase();

        String query = "select " + COL_USERNAME + " from " + TRAINEE_TABLE + " where " + COL_USERNAME + " = '" + username + "';";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //Check the match beetwen password input and existpass
    public boolean searchPassword(String pass) {

        //Read data from dataBase
        db = this.getReadableDatabase();

        String query = "select " + COL_PASS + " from " + TRAINEE_TABLE + " where " + COL_PASS + " = '" + pass + "';";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }


    //Check if user name & password match in database
    public boolean checkMatcForUser(String username, String pass) {
        //Read data from dataBase
        db = this.getReadableDatabase();

        String query = "select " + COL_PASS + "," + COL_USERNAME + " from " + TRAINEE_TABLE + " where " + COL_PASS + " = '" + pass + "' and + " + COL_USERNAME + " = '" + username + "';";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //Returns only the ID that matches the name passed in
    public Cursor getTraineeID(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String quary = "SELECT " + COL_USER_ID + " FROM " + TRAINEE_TABLE +
                " WHERE " + COL_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(quary, null);
        return data;
    }



}