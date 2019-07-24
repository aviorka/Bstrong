package com.example.aviorka.bstrong;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviorka.bstrong.DB_Managment.DataObjects.Trainee;

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

    //The database helper.
    TraineeTable myDb = TraineeTable.geInstance(this);
    Trainee trainee = new Trainee();

    private TextInputLayout textInputUsername;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputName;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);

        //Save to Login.
        textInputUsername = findViewById(R.id.etUserName);
        textInputName = findViewById(R.id.etName);
        textInputEmail = findViewById(R.id.etEmail);
        textInputPassword = findViewById(R.id.etPassword);
        textInputConfirmPassword = findViewById(R.id.etConfirmPassword);

        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);

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


    public void confirmInput(View v) {

        try {
            //If one of the validate retuen false the validation faild.
            if (!validateEmail() || !validateUsername() || !validateName() || !validatePassword()) {
                Toast.makeText(getApplicationContext(), " Validation ERROR TRY AGAIN", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        insertTrainee();

        //Start MY PLANE activity
       Intent intent = new Intent(JoinUs.this, MyPlane.class);
         startActivity(intent);
    }

    //Inserting Trainee
    private void insertTrainee() {

        trainee.setUsername(textInputUsername.getEditText().getText().toString().trim());
        trainee.setName(textInputName.getEditText().getText().toString().trim());
        trainee.setPassword(textInputPassword.getEditText().getText().toString().trim());
        trainee.setEmail(textInputEmail.getEditText().getText().toString().trim());
        trainee.setScore("0");
        TraineeTable.geInstance(getApplicationContext()).insert(trainee);
    }

    //Validate Email
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else if (myDb.checkIfEmailExists(emailInput)) {  //Check if email already exist
            textInputEmail.setError("Email already exist");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    //Validate User name
    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        } else if (myDb.checkIfUserExists(usernameInput)) {  //Check if email already exist
            textInputEmail.setError("User Name already exist");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }


    //Validate Name
    private boolean validateName() {
        String nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        } else if (nameInput.length() > 15) {
            textInputName.setError("Username too long");
            return false;
        } else {
            textInputName.setError(null);
            return true;
        }
    }

    //Validate Password
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        //Check if password & confirm password match
        if (passwordInput.equals(confirmPasswordInput)) {

            if (passwordInput.length() < 4) {
                textInputPassword.setError("Password must contain 4 characters");
                return false;
            } else if (passwordInput.contains(" ")) {
                textInputPassword.setError("No Spaces Allowed");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                textInputPassword.setError("Password must contain any letter");
                return false;
            } else if (confirmPasswordInput.length() < 4) {
                textInputConfirmPassword.setError("Password must contain 4 characters");
                return false;
            } else if (confirmPasswordInput.contains(" ")) {
                textInputConfirmPassword.setError("No Spaces Allowed");
                return false;
            } else if (confirmPasswordInput.isEmpty()) {
                textInputConfirmPassword.setError("Field can't be empty");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(confirmPasswordInput).matches()) {
                textInputConfirmPassword.setError("Password must contain any letter");
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




