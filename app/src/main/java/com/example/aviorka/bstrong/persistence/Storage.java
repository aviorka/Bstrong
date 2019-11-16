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
                "description text not null, " +
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
        db.execSQL("insert into equipment values(1,\"Dumbbell\")");
        db.execSQL("insert into equipment values(2,\"Bench\")");
        db.execSQL("insert into equipment values(3,\"Box\")");
        db.execSQL("insert into equipment values(4,\"Medicine Box\")");
        db.execSQL("insert into equipment values(5,\"No equipment\")");

        // metadata
        //Insert into recurrence table
        db.execSQL("insert into recurrence values(null,\"Low intensity\",1)");
        db.execSQL("insert into recurrence values(null,\"Medium intensity\",2)");
        db.execSQL("insert into recurrence values(null,\"High intensity\",3)");

        // metadata
        //Insert into muscle table
        db.execSQL("insert into muscle values(null,\"Legs\")");
        db.execSQL("insert into muscle values(null,\"Chest\")");
        db.execSQL("insert into muscle values(null,\"Back\")");
        db.execSQL("insert into muscle values(null,\"Biceps\")");
        db.execSQL("insert into muscle values(null,\"Triceps\")");
        db.execSQL("insert into muscle values(null,\"Shoulders\")");
        db.execSQL("insert into muscle values(null,\"Legs\")");
        db.execSQL("insert into muscle values(null,\"ABS\")");

        //
        db.execSQL("insert into [plan] values(null,1, 1, 1, 3, 8,\"Stand with your feet slightly wider than shoulders width apart. Hold a dumbbell in front of your chest with both your hands. Perform a squat until your hams touch your calves. Keep your back straight, and head and chest up.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,1, 1, 2, 3, 8,\"Your front knee should not extend farther than your big toe and the quad of the second leg should be parallel to the calf of the front leg while you’re at the bottom of the movement. \", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,1, 1, 3, 3, 10,\"Using dumbbells lets your arms free and you can follow a movement where you have the greatest amount of tension on your quads.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,2, 2, 1, 3, 10,\"Lie back on a flat bench. Using a close grip (around shoulder width), lift the bar from the rack and hold it straight over you with your arms locked. This will be your starting position.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,2, 2, 2, 2, 10,\"Stand facing away from a bench, grab it with both hands at shoulder-width.\n" +
                "Extend your legs out in front of you. Slowly lower your body by flexing at the elbows until your arm at forearm create a 90-degree angle.\n" +
                "Using your triceps lift yourself back to the starting position.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,2, 3, 3, 2, 12,\"lay back on an incline bench with a dumbbell on each hand on top of your thighs. The palms of your hand will be facing each other.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,3, 3, 1, 2, 12,\"Sit with knees bent and feet flat on the floor, holding medicine ball to your chest. Lean back slightly at a 45-degree angle to the floor, engaging your core.\n" +
                "Keep feet flexed with heels lightly touching the floor. Rotate to the right, keeping the ball at your chest, and twist from your low back.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,3, 4, 2, 3, 8,\"Sit with knees bent and feet flat on the floor, holding medicine ball to your chest. Lean back slightly at a 45-degree angle to the floor, engaging your core.\n" +
                "Keep feet flexed with heels lightly touching the floor. Rotate to the right, keeping the ball at your chest, and twist from your low back.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,3, 4, 3, 3, 9,\"Sit with knees bent and feet flat on the floor, holding medicine ball to your chest. Lean back slightly at a 45-degree angle to the floor, engaging your core.\n" +
                "Keep feet flexed with heels lightly touching the floor. Rotate to the right, keeping the ball at your chest, and twist from your low back.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,4, 5, 1, 1, 9,\"Step up on the box and then step down , each time starting with a different leg \", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,4, 5, 2, 2, 7,\"jump over the box forth and back \", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,4, 6, 3, 3, 10,\"Sit down on the box and then stand up \", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,5, 7, 1, 3, 10,\"Stand as tall as you can with your feet spread slightly wider than shoulder-width apart. Hold your arms straight out in front of your body at shoulder level, so that your arms parallel to the floor.\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,5, 8, 2, 3, 10,\"Assume a push-up position, but place your hands placed on a raised surface—such as a box, bench, ottoman or one of the steps of your stairs—instead of the floor. Your body should form a straight line from your ankles to your head\", \"resource_image_id\")");
        db.execSQL("insert into [plan] values(null,5, 8, 3, 3, 10,\"Lie on your back on the floor with your knees bent and your feet flat on the floor. Place your arms out to your sides at a 45-degree angle, your palms facing up. Now try to make your tummy as skinny as possible and hold it that way—this gives you a tight core—while breathing normally. That's the starting position\", \"resource_image_id\")");


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
            trainee.put("traineeId", cur.getInt(0));
            trainee.put("fullName", cur.getString(1));
            trainee.put("email", cur.getString(2));
            trainee.put("password", cur.getString(3));
            trainee.put("age", cur.getInt(4));
            trainee.put("height", cur.getInt(5));
            trainee.put("weight", cur.getInt(6));
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