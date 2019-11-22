package com.example.aviorka.bstrong;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviorka.bstrong.persistence.Storage;

/**
 * Login
 * This class for login
 */
public class Login extends AppCompatActivity {

    Storage db = Storage.geInstance(this);
    private EditText textInputPassword;
    private EditText textInputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_join);

        textInputEmail = findViewById(R.id.etEmail);
        textInputPassword = findViewById(R.id.etPassword);

        TextView tvjuinUs = (TextView) findViewById(R.id.tvJuinUs);

        /**
         * If user did not sign up
         */
        tvjuinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, JoinUs.class);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * Authenticate user
     * @param v
     */
    public void CheckDetails(View v) {
        String email = textInputEmail.getText().toString().trim();
        String pass = textInputPassword.getText().toString().trim();
        ContentValues trainee = new ContentValues();

        /**
         * Check if user has exercise plan :
         * User doesn't have : start my plan activity.
         * User has plan: start exercise .
         */
        if ((db.checkMatchForUser(email, pass, trainee))) {
            String sql = "select count(*) as countExercise from exercise where traineeId = ?";
            String params[] = {trainee.getAsString("traineeId")};
            ContentValues cv = db.getSingle(sql,params);
            Intent intent;

            if(cv.getAsInteger("countExercise") > 0 ) {
                //Start Exercise plan  activity
                intent = new Intent(Login.this, ExercisePlan.class);
            } else {
                //Start MY PLANE activity
                intent = new Intent(Login.this,  MyPlan.class);
            }


            intent.putExtra("TRAINEE", trainee);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "Login Failed. The email or password you entered is incorrect", Toast.LENGTH_LONG).show();
            textInputEmail.setError("Email or Password don't match!");
            textInputPassword.setError("Email or Password don't match!");
            return;
        }
    }


}
