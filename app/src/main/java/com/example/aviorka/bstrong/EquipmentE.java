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


    //TODO add view id as a field
    public class EquipmentState implements Serializable {

        private boolean isSelected;
        private String name;
        private int dbId;

        public EquipmentState(boolean isSelected, String name, int dbId){
            setSelected(isSelected);
            setName(name);

        }
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDbId(){return this.dbId;}
        public void setDbId(int dbId){this.dbId = dbId;}
    }

    private Map<Integer, EquipmentState> equipmentStateMap = new HashMap<>();
    List<EquipmentState> equipmentList = new ArrayList<>();


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
        equipmentList = (ArrayList<EquipmentState>)extras.getSerializable("SELECTED_EQUIPMENT");

//        String sql = "select * from equipment";
//        String params[] = new String[]{};
//        List<ContentValues> cvList = db.getMultiple(sql, params);

        //TODO add view id to the constructor
        equipmentStateMap.put(dumbbell.getId(), new EquipmentState(false, "dumbbell", 1));
        equipmentStateMap.put(bench.getId(), new EquipmentState(false, "bench", 2));
        equipmentStateMap.put(box.getId(), new EquipmentState(false, "box", 3));
        equipmentStateMap.put(medicineBox.getId(), new EquipmentState(false, "medicineBall", 4));

        // TODO Use EquipmentState view id
        for(EquipmentState equip : equipmentList) {
            equipmentStateMap.get(equip.getDbId()).setSelected(true);
            //TODO fix findViewById(equip).setBackground(getResources().getDrawable(R.drawable.background_selected));
        }

        //Select and send after click the return image
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //Pass data back
                //TODO fix intent.putIntegerArrayListExtra("SELECTED_EQUIPMENT",(ArrayList<Integer>) equipmentList);
                setResult(Activity.RESULT_OK, intent );
                finish();
            }
        });

    }

    //View : Switch case that choose trainee equipment selection
    @Override
    public void onClick(View view) {

        EquipmentState es = equipmentStateMap.get(view.getId());

        if(!es.isSelected()){   //If equipment was not selected select it
            es.setSelected(true);
            toast( es.getName() +" was selected");
            view.setBackground(getResources().getDrawable(R.drawable.background_selected));
            // TODO fix equipmentList.add(view.getId());
        }else {
            es.setSelected(false);
            toast( es.getName() +" was removed");
            view.setBackground(getResources().getDrawable(R.drawable.background_unselected));
            equipmentList.remove(Integer.valueOf(view.getId()));
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, EquipmentE.class);
    }
    //Toast equipment selection
    private void toast(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return;
    }
}
