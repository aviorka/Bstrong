package com.example.aviorka.bstrong;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Calendar;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviorka.bstrong.DB_Managment.DataObjects.Equipment;
import com.example.aviorka.bstrong.persistence.Storage;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * MyPlan
 * This class collect the information from user
 *
 */
public class MyPlan extends AppCompatActivity implements Serializable {
    public static final int REQUEST_CODE_GETMESSAGE = 1014;
    String[] DAYS = {"1", "2", "3"};

    private Button selectEqu, selectTimePerWeek, btnContinue;
    private int timePerWeek;
    private String currentDate = null;

    private CheckBox cbNoEquipment;

    boolean hasEquipment;
    boolean hasOpenedEquipActivity;

    private List<Equipment> equipment ;
    private Storage db;
    private ContentValues trainee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        equipment= new ArrayList<>();
        db = Storage.geInstance(super.getBaseContext());

        selectEqu = findViewById(R.id.selectEquBtn);

        cbNoEquipment = findViewById(R.id.cbNoEquipment);

        selectTimePerWeek = findViewById(R.id.selectTimeBtn);
        btnContinue = findViewById(R.id.continuenBtn);

        // get ContentValues object containing trainee data from JoinUs
        Bundle extras = getIntent().getExtras();
        trainee = (ContentValues)extras.get("TRAINEE");

        /**
         * Display current date
         */
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView dateTv = findViewById(R.id.dateTv);
        dateTv.setText(currentDate);

        /**
         * Dialog to select days for workout
         */
        selectTimePerWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPlan.this);

                builder.setTitle("Select number of days to exercise ");
                builder.setItems(DAYS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timePerWeek = i+1;
                        setSelectedRecurrence(timePerWeek);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // If activity was activated from ExercisePlan, check populate GUI with selected recurrence and equipment
        ContentValues cv = getRecurrence();
        setSelectedRecurrence(cv.getAsInteger("recurrenceId") + 1);

        //
        List<ContentValues> cvList = getEquipment();

        for(ContentValues eq : cvList){
            if(eq.getAsInteger("equipmentId") == 5) {   // if this is No Equipment
                cbNoEquipment.setChecked(true);
                break;
            }else{
                for(Equipment e : EquipmentE.EquipmentMap.values()){
                    if(e.getDbId() == eq.getAsInteger("equipmentId"))
                        equipment.add(e);
                }

            }
        }


        final Context ctx = getApplicationContext();
        /**
         * CheckBox - Check if user marked the no equipment checkbox
         */
        cbNoEquipment.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(hasEquipment == true && isChecked == true) {
                    equipment.clear();
                    Toast.makeText(getApplicationContext(), "Equipment removed ", Toast.LENGTH_LONG).show();
                }else
                    hasEquipment = !isChecked;
            }
        });


        /**
         * select Equipment_
         * hasOpenedEquipActivity - Check if user open the equipment selection
         */
        selectEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasOpenedEquipActivity = true;
                //Launch the equipmentE activity
                Intent intent = EquipmentE.makeIntent(MyPlan.this);
                intent.putStringArrayListExtra("SELECTED_EQUIPMENT",(ArrayList)equipment);
                startActivityForResult(intent, REQUEST_CODE_GETMESSAGE);
            }
        });


        /**
         * Lets start button : check the user input
         */
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!hasEquipment && !cbNoEquipment.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Did you forget to mark an equipment? ", Toast.LENGTH_LONG).show();
                        return;
                    }else if (checkDaysPerWeek()) {

                        int pos = 0;
                        String equipPlaceHolders = "";
                        String[] params = new String[equipment.size()+1];

                        if(hasEquipment){
                            for(Equipment equip : equipment){
                                if(equipPlaceHolders.length() > 0){
                                    equipPlaceHolders += ", ";
                                }
                                equipPlaceHolders += "?";
                                params[pos] = String.valueOf(equip.getDbId()) ;
                                pos++;
                            }
                        }else{
                            equipPlaceHolders = "5";    //depicts no equipment
                        }

                        String sql = "select * from plan where equipmentID in (" + equipPlaceHolders + ") and recurrenceID = ? ";
                        params[pos] = String.valueOf(timePerWeek) ;
                        List<ContentValues> cvList = db.getMultiple(sql, params);

                        // delete existing records from exercise for current user
                        db.delete("exercise", "traineeId = ?", new String[]{trainee.getAsString("traineeId")});

                        // for each plan record insert its planId and recurrence into exercise table
                        ContentValues insertParams = new ContentValues();

                        for(ContentValues cv : cvList){
                            insertParams.put("planId", cv.getAsInteger("planId"));
                            insertParams.put("traineeId", trainee.getAsInteger("traineeId"));
                            insertParams.put("recurrenceID", timePerWeek);
                            insertParams.put("startDate", currentDate);

                            db.insert("exercise" , insertParams);
                        }

                        Intent intent = ExercisePlan.makeIntent(MyPlan.this);
                        intent.putExtra("TRAINEE", trainee);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ContentValues getRecurrence(){
        ContentValues cv = db.getSingle("select top 1 recurrenceId from exercise where traineeId = ?", new String[]{trainee.getAsString("traineeId")});
        return cv;
    }

    private List<ContentValues> getEquipment(){
        List<ContentValues> cvList = db.getMultiple("select distinct equipmentId from [plan] where planId in (select planId from exercise where traineeId = ?)", new String[]{trainee.getAsString("traineeId")});
        return cvList;
    }

    private void setSelectedRecurrence(int timePerWeek){
        TextView tvDaysPerWeek = findViewById(R.id.tvDaysPerWeek);
        switch (timePerWeek){
            case 1:
                tvDaysPerWeek.setText("1 day is for motivation");
                break;
            case 2:
                tvDaysPerWeek.setText("2 days is a good beginning");
                break;
            case 3:
                tvDaysPerWeek.setText("3 days is awesome");
                break;
        }
    }
    //Building plan ============================================================ MOST IMPORTANT !!
    private void buildingPlan(List<String> equipment) {

        int index = 0;
        //Looping Array List to add the selected equipment to the plan
        while (equipment.size() > index) {

            switch (equipment.get(index)){
                case "box":
                    //Here i want to add the equipment to table
                    break;
                case "dumbbell":
                    break;
                case "medicine Box":
                    break;
                case "bench":
                    break;
            }
            index++;
        }
    }

    /**
     * Check that user selected the weekly recurrence
     * @return
     */
    private boolean checkDaysPerWeek() {
        if (timePerWeek == 0) {
            Toast.makeText(getApplicationContext(), "Please select how many day to exercise ", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    /**
     * Get the equipment result from EqipmentE activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            String[] equipArr;
            Bundle extras = data.getExtras();
            if(extras == null) {
                equipArr= null;
            } else {
                equipment= (ArrayList<Equipment>)extras.getSerializable("SELECTED_EQUIPMENT");
            }
            if(equipment.size() == 0)
                hasEquipment = false;
            else{
                hasEquipment =true;
                if(cbNoEquipment.isChecked()){
                    cbNoEquipment.setChecked(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
