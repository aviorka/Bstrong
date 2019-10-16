package com.example.aviorka.bstrong;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviorka.bstrong.adapter.ScheduleAdapter;
import com.example.aviorka.bstrong.persistence.Storage;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/*
* MyPlane
* This class collect the information from user
*
*/
public class MyPlane extends AppCompatActivity implements Serializable {
    public static final int REQUEST_CODE_GETMESSAGE = 1014;
    String[] DAYS = {"1", "2", "3"};
    String totalEquipment = "";
    String heightStr ;
    String weightStr;

    private Button selectEqu, noEqu, selectTimePerWeek, continuen;
    private EditText height = null, weight = null;
    String timePerWeek = null, currentDate = null;
    boolean EquipmentVali = false;
    ArrayList<String> equipment ;
    private Storage db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plane);
        equipment= new ArrayList<>();
        db = Storage.geInstance(super.getBaseContext());

        //TODO get scheduled exercise for user from db into a linked list
        ScheduleAdapter schedAdapter = new ScheduleAdapter();
        GridView gv = (GridView) findViewById(R.id.gvSchedule);
        gv.setAdapter(schedAdapter);

        selectEqu = (Button) findViewById(R.id.selectEquBtn);
        noEqu = (Button) findViewById(R.id.noEquBtn);

        selectTimePerWeek = (Button) findViewById(R.id.selectTimeBtn);
        height = (EditText) findViewById(R.id.heightEt);
        weight = (EditText) findViewById(R.id.weightEt);
        continuen = (Button) findViewById(R.id.continuenBtn);

        //Date viewer
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView dateTv = findViewById(R.id.dateTv);
        dateTv.setText(currentDate);


        //Dialog select time per week
        selectTimePerWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPlane.this);
                builder.setTitle("Select how many days");
                builder.setItems(DAYS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timePerWeek = DAYS[i];
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });




        //If the user dont have equipmentCheck
        noEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EquipmentVali = false;
                equipment.add("null");
            }
        });

        //select Equipment_
        selectEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EquipmentVali = true;
                //Launch the equipmentE activity
               Intent intent = EquipmentE.makeIntent(MyPlane.this);
               startActivityForResult(intent, REQUEST_CODE_GETMESSAGE);
            }
        });




        //continue button : check the user input
        continuen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightStr = height.getText().toString();
                weightStr = weight.getText().toString();

                try {
                    if (EquipmentVali) {
                        Toast.makeText(getApplicationContext(), "Select equipment: ", Toast.LENGTH_LONG).show();
                        return;
                    }else if (checkInput(heightStr, weightStr, currentDate, timePerWeek)) {
                        Toast.makeText(getApplicationContext(), "selection all OK ", Toast.LENGTH_SHORT).show();
                        //Start activity that execute plan for user
                            buildingPlan(timePerWeek,equipment);
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        db.getSingle("select  from muscle where muscle = name ", new String[]{});
    }


    //Building plan ============================================================ MOST IMPORTANT !!
    private void buildingPlan(String timePerWeek, ArrayList<String> equipment) {

        int index = 0;
        //Looping Array List to add the selected equipment to the plan
        while (equipment.size() > index) {

            switch (equipment.get(index)){
                case "box":
                    //Here i want to add the equipment to table
                    break;
                case "dumbblle":
                    break;
                case "medicine Box":
                    break;
                case "bench":
                    break;
            }
            index++;
        }
    }

    //Validation User Info
    private boolean checkInput(String heightStr, String weightStr, String currentDate, String timePerWeek) {
        if (!(currentDate != null)) {
            return false;
        } else if (!(timePerWeek != null)) {
            Toast.makeText(getApplicationContext(), "You forgot to select how many days per weeek ", Toast.LENGTH_LONG).show();
            return false;
        } else if (!(heightStr != null && !"".equals(heightStr))) {
            height.setError("Please enter your height");
            return false;
        } else if (!(weightStr != null && !"".equals(weightStr))) {
            weight.setError("Please enter your weight");
            return false;
        } else {
            return true;
        }
    }


    //Get the equipment result from EqipmentE activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            switch (requestCode){
                case REQUEST_CODE_GETMESSAGE:
                    if(resultCode == Activity.RESULT_OK){
                        totalEquipment = data.getStringExtra("returnData");
                        equipment = new  ArrayList<String>(Arrays.asList(totalEquipment.split(",")));
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
