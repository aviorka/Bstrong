package com.example.aviorka.bstrong;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aviorka.bstrong.DB_Managment.DataObjects.Equipment;
import com.example.aviorka.bstrong.persistence.Storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EquipmentE
 * The class get the equipment from trainee
 * Switch case : taking trainee select
 */
public class EquipmentE extends AppCompatActivity implements View.OnClickListener {

    private Storage db;
    private Map<Integer, Equipment> EquipmentMap = new HashMap<>();
    List<Equipment> equipmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_e);


        ImageView dumbbell = findViewById(R.id.dumbbellIVE);
        ImageView bench = findViewById(R.id.benchIVE);
        ImageView box = findViewById(R.id.boxIVE);
        ImageView medicineBox = findViewById(R.id.medicinboxIVE);
        ImageButton returnImage = findViewById(R.id.returnIBE);

        dumbbell.setOnClickListener(this);
        bench.setOnClickListener(this);
        box.setOnClickListener(this);
        medicineBox.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        equipmentList = (ArrayList<Equipment>)extras.getSerializable("SELECTED_EQUIPMENT");

        //TODO add view id to the constructor - DONE
        EquipmentMap.put(R.id.dumbbellIVE, new Equipment(false, "dumbbell", 1, R.id.dumbbellIVE));
        EquipmentMap.put(R.id.benchIVE, new Equipment(false, "bench", 2, R.id.benchIVE));
        EquipmentMap.put(R.id.boxIVE, new Equipment(false, "box", 3, R.id.boxIVE));
        EquipmentMap.put(R.id.medicinboxIVE, new Equipment(false, "medicineBall", 4, R.id.medicinboxIVE));
        EquipmentMap.put(-1, new Equipment(false, "No Equipment", 5, -1));

        // TODO Use Equipment view id - DONE
        for(Equipment equip : equipmentList) {
            if(equip.getDbId() == 5)    // skip No Equipment since it is not displayed
                continue;
            EquipmentMap.get(equip.getViewId()).setSelected(true);
            findViewById(equip.getViewId()).setBackground(getResources().getDrawable(R.drawable.background_selected));
        }

        //Select and send after click the return image
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //Pass data back
                //TODO fix change type of array - DONE
                intent.putExtra("SELECTED_EQUIPMENT",(ArrayList<Equipment>) equipmentList);
                setResult(Activity.RESULT_OK, intent );
                finish();
            }
        });

    }

    //View : Switch case that choose trainee equipment selection
    @Override
    public void onClick(View view) {

        for(Equipment es : EquipmentMap.values()){
            if(es.getViewId() != -1 && findViewById(es.getViewId()).equals(view)){
                if(!es.isSelected()){   //If equipment was not selected select it
                    es.setSelected(true);
                    toast( es.getName() +" was selected");
                    view.setBackground(getResources().getDrawable(R.drawable.background_selected));
                    equipmentList.add(es);    // TODO fix  - add EquipmentSate instead if its id - DONE
                }else {
                    es.setSelected(false);
                    toast( es.getName() +" was removed");
                    view.setBackground(getResources().getDrawable(R.drawable.background_unselected));
                    equipmentList.remove(es);
                }
                break;
            }
        }
        //Equipment es = EquipmentMap.get(view.getId());

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, EquipmentE.class);
    }
    //Toast equipment selection
    private void toast(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
