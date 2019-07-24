package com.example.aviorka.bstrong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * EquipmentE
 * The class get the equipment from trainee
 * Switch case : taking trainee select
 */
public class EquipmentE extends AppCompatActivity implements View.OnClickListener {

    boolean dumbbellB = true, benchB = true, boxB = true, medicineBoxB = true;
    String totalEquipment = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_e);


        ImageView dumbblle = (ImageView) findViewById(R.id.dumbbellIVE);
        ImageView bench = (ImageView) findViewById(R.id.benchIVE);
        ImageView box = (ImageView) findViewById(R.id.boxIVE);
        ImageView medicineBox = (ImageView) findViewById(R.id.medicinboxIVE);
        ImageButton returnImage = (ImageButton) findViewById(R.id.returnIBE);

        dumbblle.setOnClickListener(this);
        bench.setOnClickListener(this);
        box.setOnClickListener(this);
        medicineBox.setOnClickListener(this);

        //Select and send after click the return image
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //Pass data back
                intent.putExtra("returnData",totalEquipment);
                setResult(Activity.RESULT_OK, intent );
                finish();
            }
        });

    }

    //View : Switch case that choose trainee equipment selection
    @Override
    public void onClick(View view) {

        //If the trainee select it send/deleted
        switch (view.getId()) {
            case R.id.dumbbellIVE:
                if (dumbbellB) {
                    dumbbellB = false;
                    toast("You selected dumbbell");
                    if(!(totalEquipment.toLowerCase().contains("dumbbell".toLowerCase()))){
                        totalEquipment += ",dumbbell";
                    }
                } else {
                    dumbbellB = true;
                    toast("No dumbbell");
                    if(totalEquipment.toLowerCase().contains("dumbbell".toLowerCase())){
                         totalEquipment = totalEquipment.replaceAll(",dumbbell", "");
                    }
                }
                break;
            case R.id.benchIVE:
                if (benchB) {
                    benchB = false;
                    toast("You selected bench");
                    if(!(totalEquipment.toLowerCase().contains(",bench".toLowerCase()))){
                         totalEquipment += ",bench";
                    }
                } else {
                    benchB = true;
                    toast("No bench");
                    if(totalEquipment.toLowerCase().contains(",bench".toLowerCase())) {
                        totalEquipment = totalEquipment.replaceAll(",bench", "");
                    }
                }
                break;
            case R.id.boxIVE:
                if (boxB) {
                    boxB = false;
                    toast("You selected box");
                    if(!(totalEquipment.toLowerCase().contains(",box".toLowerCase()))){
                        totalEquipment += ",box";
                    }
                } else {
                    boxB = true;
                    toast("No box");
                    if (totalEquipment.toLowerCase().contains(",box".toLowerCase())) {
                        totalEquipment = totalEquipment.replaceAll(",box", "");
                    }
                }
                break;
            case R.id.medicinboxIVE:
                if (medicineBoxB) {
                    medicineBoxB = false;
                    toast("You selected medicine Box");
                    if(!(totalEquipment.toLowerCase().contains(",medicine Box".toLowerCase()))){
                        totalEquipment += ",medicine Box";
                    }
                } else {
                    medicineBoxB = true;
                    toast("No medicine Box");
                    if (totalEquipment.toLowerCase().contains(",medicine Box".toLowerCase())) {
                        totalEquipment = totalEquipment.replaceAll(",medicine Box", "");
                    }
                }
                break;
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, EquipmentE.class);
    }
    //Toast equipment selection
    private void toast(String massege){
        Toast toast = Toast.makeText(getApplicationContext(), massege, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return;
    }
}
