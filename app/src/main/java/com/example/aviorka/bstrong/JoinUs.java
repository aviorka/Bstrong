package com.example.aviorka.bstrong;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aviorka.bstrong.persistence.Storage;

import java.util.regex.Pattern;

/**
 * JoinUs
 * This class for registration
 */
public class JoinUs extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    //The database db.

    private EditText textInputEmail;
    private EditText textInputFullName;
    private EditText textInputWeight;
    private EditText textInputHeight;
    private EditText textInputAge;
    private EditText textInputPassword;
    private EditText textInputConfirmPassword;
    private Storage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);
        db = Storage.geInstance(getApplicationContext());

        //Save to Login.
        textInputFullName = findViewById(R.id.etFullname);
        textInputEmail = findViewById(R.id.etEmail);
        textInputPassword = findViewById(R.id.etPassword);
        textInputConfirmPassword = findViewById(R.id.etConfirmPassword);
        textInputAge = findViewById(R.id.etAge);
        textInputWeight = findViewById(R.id.etWeight);
        textInputHeight = findViewById(R.id.etHeight);

        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);

        Bundle extras = getIntent().getExtras();
        ContentValues trainee = (ContentValues)extras.get("TRAINEE");
        if(trainee != null){
            populateTrainee(trainee);
        }

        //onClick on text view join us for register activity.
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinUs.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void populateTrainee(ContentValues trainee){

    }

    public void confirmInput(View v) {

        try {
            //If one of the validate return false the validation failed .
            if (!validateEmail() || !validateFullname() || !validatePassword() || !validateAge() || !validateHeight() || !validateWeight() ) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ContentValues trainee = insertTrainee();

        //Start MY PLANE activity
       Intent intent = new Intent(JoinUs.this, MyPlan.class);
       intent.putExtra("TRAINEE", trainee);
       startActivity(intent);
       finish();
    }

    //Inserting Trainee
    private ContentValues insertTrainee() {
        ContentValues cv = new ContentValues();
        cv.put("fullName", textInputFullName.getText().toString().trim());
        cv.put("email", textInputEmail.getText().toString().trim());
        cv.put("password", textInputPassword.getText().toString().trim());
        cv.put("age", textInputAge.getText().toString().trim());
        cv.put("height", textInputHeight.getText().toString().trim());
        cv.put("weight", textInputWeight.getText().toString().trim());


        long traineeId = db.insert("trainee", cv);

        cv.put("traineeId", traineeId);
        return cv;
    }

    //Validate Email
    private boolean validateEmail() {
        String email = textInputEmail.getText().toString().trim();
        if (email.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else if (checkIfEmailExists(email)) {  //Check if email already exist
            textInputEmail.setError("Email already exist");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    //Check if email exist
    private boolean checkIfEmailExists(String email){
        ContentValues cv = db.getSingle("Select email from trainee where email = ?", new String[]{email});
        if(cv.size() > 0) return true;
        return false;
    }


    //Age validation
    private boolean validateAge(){
        if(textInputAge.getText().toString().length() == 0 ){
            textInputAge.setError("Please fill your age ");
            return false;
            } else if(Integer.parseInt(textInputAge.getText().toString()) < 16){
            textInputAge.setError("Application support age 16 and above");
            return false;
        }
        return true;
    }


    //Weight validation
    private boolean validateWeight(){
        if(textInputWeight.getText().toString().length() > 0 && Integer.parseInt(textInputWeight.getText().toString()) < 40  ){
            textInputWeight.setError("Application support weight 40 and above");
            return false;
        }
        return true;
    }

    //Height validation
    private boolean validateHeight(){
        if(textInputHeight.getText().toString().length() > 0 && Integer.parseInt(textInputHeight.getText().toString()) < 140  ){
            textInputHeight.setError("Application support Height 140cm and above");
            return false;
        }
        return true;
    }

    private boolean validateFullname(){
        if(textInputEmail.getText().toString().length() < 2) {
            textInputEmail.setError("Full name must contain at least 3 characters");
            return false;
        }
        return true;
    }

    //Validate Password
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();
        String confirmPasswordInput = textInputConfirmPassword.getText().toString().trim();

        if (passwordInput.equals(confirmPasswordInput)) {

            if (passwordInput.length() < 4) {
                textInputPassword.setError("Password must contain at least 4 characters");
                return false;
            } else if (passwordInput.contains(" ")) {
                textInputPassword.setError("No Spaces Allowed");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                textInputPassword.setError("Password must contain any letter");
                return false;
            } else if (confirmPasswordInput.isEmpty()) {
                textInputConfirmPassword.setError("Field can't be empty");
                return false;
            } else {
                textInputConfirmPassword.setError(null);
                textInputPassword.setError(null);
                return true;
            }
        } else {
            textInputConfirmPassword.setError("Password don't match!");
            return false;
        }

    }

}




