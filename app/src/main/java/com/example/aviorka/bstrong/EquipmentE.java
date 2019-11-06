package com.example.aviorka.bstrong;

import android.app.Activity;
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


    public class EquipmentState{

        private boolean isSelected;
        private String name;
        private int id;

        public EquipmentState(boolean isSelected, String name, int id){
            setSelected(isSelected);
            setName(name);
            setId(id);
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

        public int getId(){ return id;}
        public void setId(int id){ this.id = id;}
    }

    private Map<Integer, EquipmentState> equipmentStateMap = new HashMap<>();
    List<Integer> equipmentList = new ArrayList<>();


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
        equipmentList = extras.getIntegerArrayList("SELECTED_EQUIPMENT");

        equipmentStateMap.put(R.id.dumbbellIVE, new EquipmentState(false, "dumbbell", 1));
        equipmentStateMap.put(R.id.benchIVE, new EquipmentState(false, "bench", 2));
        equipmentStateMap.put(R.id.boxIVE, new EquipmentState(false, "box", 3));
        equipmentStateMap.put(R.id.medicinboxIVE, new EquipmentState(false, "medicineBall", 4));

        for(int equip : equipmentList) {
            switch (equip ){
                case 1:
                    box.setBackground(getResources().getDrawable(R.drawable.background_selected));
                    equipmentStateMap.get(box.getId()).setSelected(true);
                    break;
                case 2:
                    dumbbell.setBackground(getResources().getDrawable(R.drawable.background_selected));
                    equipmentStateMap.get(dumbbell.getId()).setSelected(true);
                    break;
                case 3:
                    medicineBox.setBackground(getResources().getDrawable(R.drawable.background_selected));
                    equipmentStateMap.get(medicineBox.getId()).setSelected(true);
                    break;
                case 4:
                    bench.setBackground(getResources().getDrawable(R.drawable.background_selected));
                    equipmentStateMap.get(bench.getId()).setSelected(true);
                    break;
            }
        }

        //Select and send after click the return image
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //Pass data back
                intent.putIntegerArrayListExtra("SELECTED_EQUIPMENT",(ArrayList<Integer>) equipmentList);
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
            equipmentList.add(es.getId());
        }else {
            es.setSelected(false);
            toast( es.getName() +" was removed");
            view.setBackground(getResources().getDrawable(R.drawable.background_unselected));
            equipmentList.remove(es.getName());
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
