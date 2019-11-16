package com.example.aviorka.bstrong.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Trainee Table
 * Equipment Table
 * Recurrence Table
 * plan Table
 * Exercise Table
 * Database
 * Activity
 */
public class Storage extends SQLiteOpenHelper {

    //Singleton
    private static Storage instance;

    // Database Name
    public static final String DATABASE_NAME = "BStrong.db";

    //DataBase version
    public static final int DATABASE_VERSION = 1;

    //DDL - Data Definition Language
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trainee(traineeId integer primary key," +
                "fullName text not null, " +
                "email text not null, " +
                "password text not null, " +
                "age integer not null, " +
                "height integer not null, " +
                "weight integer not null)");

        db.execSQL("create table equipment(equipmentId integer primary key," +
                "name text not null)");

        db.execSQL("create table recurrence(recurrenceId integer primary key autoincrement, " +
                "name text not null, " +
                "weeklyDays integer not null)");


        db.execSQL("create table [plan](planId integer primary key autoincrement, " +
                "equipmentId integer not null, " +
                "muscleID integer not null, " +
                "recurrenceId integer not null, " +
                "sessions integer not null, " +
                "repetitions integer not null, " +
                "imageResourceId text not null, " +
                "foreign key(equipmentID) references equipment(equipmentId)," +
                "foreign key(muscleID) references muscle(muscleID)," +
                "foreign key(recurrenceId) references recurrence(recurrenceId))");

        db.execSQL("create table muscle(muscleId integer primary key autoincrement," +
                "name text not null)");

        db.execSQL("create table exercise(exerciseId integer primary key autoincrement, " +
                "planId integer not null, " +
                "traineeId integer not null, " +
                "recurrenceId integer not null, " +
                "startDate text not null, " +
                "foreign key(planID) references [plan](planID)," +
                "foreign key(traineeId) references [trainee](traineeId)," +
                "foreign key(recurrenceId) references recurrence(recurrenceId))");

        //DML - Data Manipulation language
        //Insert into Equipment table
        db.execSQL("insert into equipment values(null,\"Dumbbell\")");
        db.execSQL("insert into equipment values(null,\"bench\")");
        db.execSQL("insert into equipment values(null,\"box\")");
        db.execSQL("insert into equipment values(null,\"medicine Box\")");

        // metadata
        db.execSQL("insert into recurrence values(null,\"Low intensity\",1)");
        db.execSQL("insert into recurrence values(null,\"Medium intensity\",2)");
        db.execSQL("insert into recurrence values(null,\"High intensity\",3)");

        // metadata
        db.execSQL("insert into muscle values(null,\"Legs\")");
        db.execSQL("insert into muscle values(null,\"Chest\")");
        db.execSQL("insert into muscle values(null,\"Back\")");
        db.execSQL("insert into muscle values(null,\"Biceps\")");
        db.execSQL("insert into muscle values(null,\"Triceps\")");
        db.execSQL("insert into muscle values(null,\"Shoulders\")");
        db.execSQL("insert into muscle values(null,\"Legs\")");
        db.execSQL("insert into muscle values(null,\"ABS\")");

        db.execSQL("insert into [plan] values(null,1, 1, 1, 5, 10, \"resource_image_id\")");
    }

    //Singleton
    public static Storage geInstance(Context ctx) {
        if (instance == null) {
            instance = new Storage(ctx);
        }
        return instance;
    }

    //Constructor
    private Storage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Insert data to chosen table
    public long insert(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long newId = db.insert(table, null, values);
        db.close(); // Closing database connection
        return newId;
    }

    //Delete data
    public void delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, whereClause, whereArgs);
        db.close(); // Closing database connection
    }

    //TODO
    //Add Email validate and password for login activity
    public ContentValues getSingle(String sql, String[] params){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnNames = null;
        if(db == null)
            return null;

        ContentValues rs = new ContentValues();
        Cursor cur = db.rawQuery(sql, params);

        columnNames = cur.getColumnNames();
        while(cur.moveToNext()){
            for(int col = 0; col < columnNames.length; col++){
                switch(cur.getType(col)){
                    case Cursor.FIELD_TYPE_INTEGER:
                        rs.put(columnNames[col], cur.getInt(col));
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        rs.put(columnNames[col], cur.getString(col));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        rs.put(columnNames[col], cur.getFloat(col));
                        break;
                    case 5: //bool, datetime
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        rs.put(columnNames[col], new String(""));
                        break;
                }
            }
        }

        cur.close();
        db.close();
        return rs;
    }

    /**
     * Check if email and password matched in DB
     * @param email
     * @param pass
     * @return
     */
    public boolean checkMatchForUser(String email, String pass, ContentValues trainee){

        String sql = "select * from trainee where email = ? and password = ? ";
        String params[] = {email, pass};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, params);

        while(cur.moveToNext()) {
            trainee.put("traineeId", cur.getInt(1));
            trainee.put("fullName", cur.getInt(1));
            trainee.put("email", cur.getInt(1));
            trainee.put("password", cur.getInt(1));
            trainee.put("age", cur.getInt(1));
            trainee.put("height", cur.getInt(1));
            trainee.put("weight", cur.getInt(1));
            cur.close();
            db.close();
            return true;
        }

        cur.close();
        db.close();
        return false;
}



    public List<ContentValues> getMultiple(String sql, String[] params){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnNames = null;
        if(db == null)
            return null;

        List<ContentValues> resultSet = new LinkedList<>();

        Cursor cur = db.rawQuery(sql, params);

        columnNames = cur.getColumnNames();
        while(cur.moveToNext()){
            ContentValues record = new ContentValues();
            for(int col = 0; col < columnNames.length; col++){
                switch(cur.getType(col)){
                    case 1:
                        record.put(columnNames[col], cur.getInt(col));
                        break;
                    case 2:
                        record.put(columnNames[col], cur.getString(col));
                        break;
                    case 3:
                        record.put(columnNames[col], cur.getString(col));
                        break;
                    case 4:
                        record.put(columnNames[col], cur.getFloat(col));
                        break;
                    case 5: //bool, datetime
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        record.put(columnNames[col], new String(""));
                        break;
                }
            }
            resultSet.add(record);
        }

        cur.close();
        db.close();
        return resultSet;
    }


}