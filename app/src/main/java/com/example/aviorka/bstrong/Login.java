package com.example.aviorka.bstrong;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviorka.bstrong.persistence.TraineeTable;

/**
 * Login
 * This class for login
 * Check if the input is admin and send to another activity
 */
public class Login extends AppCompatActivity {

    TraineeTable helper = TraineeTable.geInstance(this);
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_join);

        textInputUsername = findViewById(R.id.etUserName);
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
        String username = textInputUsername.getEditText().getText().toString().trim();
        String pass = textInputPassword.getEditText().getText().toString().trim();

        try {
            if ((username.equals("admin")) && (pass.equals("admin"))) {
               // Intent intent = new Intent(Login.this, AdminConnection.class);
               // startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO Check if user name & password really in database
        //Check match between user name and pass
        if ((helper.checkMatcForUser(username, pass))) {
            //Start MY PLANE activity
            Intent intent = new Intent(Login.this, MyPlane.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Login Failed. The email or password you entered is incorrect", Toast.LENGTH_LONG).show();
            textInputUsername.setError("User or Password don't match!");
            textInputPassword.setError("User or Password don't match!");
            return;
        }
    }


}
