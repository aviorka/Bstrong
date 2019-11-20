package com.example.aviorka.bstrong;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

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

    private ContentValues trainee;
    private Storage db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);

        // get ContentValues object containing trainee data from JoinUs
        Bundle extras = getIntent().getExtras();
        trainee = (ContentValues)extras.get("TRAINEE");

        db = Storage.geInstance(getBaseContext());
        disableEquipment();
        showData();

        TextView tvEditPlan = findViewById(R.id.tvEditPlan);
        tvEditPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start MY PLANE activity
                Intent intent = new Intent(ExercisePlan.this, MyPlan.class);
                intent.putExtra("TRAINEE", trainee);
                startActivity(intent);
                finish();
            }
        });

        TextView tvEditTrainee = findViewById(R.id.tvEditTrainee);
        tvEditTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start MY PLANE activity
                Intent intent = new Intent(ExercisePlan.this, JoinUs.class);
                intent.putExtra("TRAINEE", trainee);
                startActivity(intent);
                finish();
            }
        });
    }



    /**
     * Retrieve data depicting a single exercise for a week
     * according to the user selected frequency.
     */
    private void showData(){
        // display trainee full name
        TextView tvFullName = findViewById(R.id.tvFullName);
        tvFullName.setText(trainee.getAsString("fullName"));

        // populate exercises
        List<ContentValues> resultSet = db.getMultiple("select * from 'plan' where planId in (select planId from exercise where traineeId = ? )", new String[]{String.valueOf(trainee.getAsInteger("traineeId"))});
        for(ContentValues planEntry : resultSet){
            setExercise(planEntry.getAsInteger("planId"), planEntry.getAsInteger("muscleID"), planEntry.getAsInteger("recurrenceId"), planEntry.getAsInteger("equipmentId"));
        }
    }
    /**
     * set exercises to schedule plan
     * @param muscle id from DB
     * @param recurrence id from DB
     */
    private void setExercise(final int planId, int muscle, int recurrence, int equipment){
        TextView tv = null;

        switch(recurrence){
            case RECU_1:
                tv = findViewById(idMapping[muscle-1][1]);
                setupX(tv, equipment, planId);
                break;
            case RECU_2:
                tv = findViewById(idMapping[muscle-1][1]);
                setupX(tv, equipment, planId);
                tv = findViewById(idMapping[muscle-1][3]);
                setupX(tv, equipment, planId);
                break;
            case RECU_3:
                tv = findViewById(idMapping[muscle-1][0]);
                setupX(tv, equipment, planId);
                tv = findViewById(idMapping[muscle-1][2]);
                setupX(tv, equipment, planId);
                tv = findViewById(idMapping[muscle-1][4]);
                setupX(tv, equipment, planId);
                break;
        }

    }

    private void setupX(TextView tv, final int equipment, final int planId){
        tv.setText("X");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableEquipment();
                setUpEquipment(equipment, planId);

                // clear exercise details
                TextView tvExerciseDetails = findViewById(R.id.tvDetailText);
                tvExerciseDetails.setText("");

                TextView tvDetailImage = findViewById(R.id.tvDetailImage);
                tvDetailImage.setBackgroundResource(R.drawable.ic_launcher_background);
            }
        });
    }

    private void disableEquipment(){
        TextView tv;
        tv = findViewById(R.id.tvDumbbell);
        tv.setEnabled(false);
        tv = findViewById(R.id.tvBench);
        tv.setEnabled(false);
        tv = findViewById(R.id.tvbox);
        tv.setEnabled(false);
        tv = findViewById(R.id.tvmedicinbox);
        tv.setEnabled(false);
        tv = findViewById(R.id.tvNoEquipment);
        tv.setEnabled(false);
    }

    private void setUpEquipment(int equipment, final int planId){
        TextView tv;
        switch (equipment){
            case 1:
                tv = findViewById(R.id.tvDumbbell);
                break;
            case 2:
                tv = findViewById(R.id.tvBench);
                break;
            case 3:
                tv = findViewById(R.id.tvbox);
                break;
            case 4:
                tv = findViewById(R.id.tvmedicinbox);
                break;
            case 5:
                tv = findViewById(R.id.tvNoEquipment);
                break;
                default:
                    return;
        }

        setUpExercise(tv, planId);
        tv.setEnabled(true);
    }

    //TODO Add explanation on final planId (Why we use final - Because we have anonymous class)
    private void setUpExercise(TextView tv, final int planId){

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv;
                cv = db.getSingle("Select description , imageResourceId from 'plan' where planId = ? ", new String[] {String.valueOf(planId)});

                TextView tvExerciseDetails = findViewById(R.id.tvDetailText);
                tvExerciseDetails.setText(cv.getAsString("description"));

                TextView tvDetailImage = findViewById(R.id.tvDetailImage);
                String imageResourceId = cv.getAsString("imageResourceId");
                int imageResource = getResources().getIdentifier(imageResourceId, "drawable", getPackageName());
                tvDetailImage.setBackgroundResource(imageResource);
            }
        });
    }
    


    public static Intent makeIntent(Context context){
        return new Intent(context, ExercisePlan.class);
    }

}