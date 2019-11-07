package com.example.aviorka.bstrong.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aviorka.bstrong.EquipmentE;
import com.example.aviorka.bstrong.R;
import com.example.aviorka.bstrong.persistence.Storage;

import java.util.List;

public class ExercisePlan extends AppCompatActivity {

    int [][] idMapping = {{R.id.tvLegs0, R.id.tvLegs1, R.id.tvLegs2, R.id.tvLegs3, R.id.tvLegs4, R.id.tvLegs5, R.id.tvLegs6},
            {R.id.tvBack0, R.id.tvBack1, R.id.tvBack2, R.id.tvBack3, R.id.tvBack4, R.id.tvBack5, R.id.tvBack6},
            {R.id.tvChest0, R.id.tvChest1, R.id.tvChest2, R.id.tvChest3, R.id.tvChest4, R.id.tvChest5, R.id.tvChest6},
            {R.id.tvBiceps0, R.id.tvBiceps1, R.id.tvBiceps2, R.id.tvBiceps3, R.id.tvBiceps4, R.id.tvBiceps5, R.id.tvBiceps6},
            {R.id.tvTriceps0, R.id.tvTriceps1, R.id.tvTriceps2, R.id.tvTriceps3, R.id.tvTriceps4, R.id.tvTriceps5, R.id.tvTriceps6},
            {R.id.tvShoulders0, R.id.tvShoulders1, R.id.tvShoulders2, R.id.tvShoulders3, R.id.tvShoulders4, R.id.tvShoulders5, R.id.tvShoulders6}};

    private static final int RECU_1 =1;
    private static final int RECU_2 =2;
    private static final int RECU_3 =3;

    private Storage db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);
    }

    /**
     * Retrieve data depicting a single exercise for a week
     * according to the user selected frequency.
     */
    private void showData(){

        List<ContentValues> resultSet = db.getMultiple("select * from exercise", new String[]{});
        for(ContentValues muscle : resultSet){
            setExercise(muscle.getAsInteger("muscleId"), muscle.getAsInteger("recurrenceId"));
        }
    }
    /**
     * set exercises to schedule plan
     * @param muscle id from DB
     * @param recurrence id from DB
     */
    private void setExercise(int muscle, int recurrence){
        TextView tv = null;

        switch(recurrence){
            case RECU_1:
                tv = findViewById(idMapping[muscle-1][1]);
                tv.setText("X");
                break;
            case RECU_2:
                tv = findViewById(idMapping[muscle-1][1]);
                tv.setText("X");
                tv = findViewById(idMapping[muscle-1][3]);
                tv.setText("X");
                break;
            case RECU_3:
                tv = findViewById(idMapping[muscle-1][0]);
                tv.setText("X");
                tv = findViewById(idMapping[muscle-1][2]);
                tv.setText("X");
                tv = findViewById(idMapping[muscle-1][4]);
                tv.setText("X");
                break;
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, ExercisePlan.class);
    }
    
}


