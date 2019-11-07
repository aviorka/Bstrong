package com.example.aviorka.bstrong;

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
 * Check if the input is admin and send to another activity
 */
public class Login extends AppCompatActivity {

    Storage helper = Storage.geInstance(this);
    private EditText textInputPassword;
    private EditText textInputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_join);

        textInputEmail = findViewById(R.id.etEmail);
        textInputPassword = findViewById(R.id.etPassword);

        TextView tvjuinUs = (TextView) findViewById(R.id.tvJuinUs);

        //Check if user already exist pass move to login
        tvjuinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, JoinUs.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //Check if user name & password exist in database
    public void CheckDetails(View v) {
        String email = textInputEmail.getText().toString().trim();
        String pass = textInputPassword.getText().toString().trim();

        try {
            if ((email.equals("admin")) && (pass.equals("admin"))) {
               // Intent intent = new Intent(Login.this, AdminConnection.class);
               // startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO Check if user name & password really in database
        //Check match between user name and pass
        if ((helper.checkMatcForUser(email, pass))) {
            //Start MY PLANE activity
            Intent intent = new Intent(Login.this, MyPlan.class);
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
