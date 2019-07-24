package com.example.aviorka.bstrong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//TODO add comments
public class AdminConnect extends AppCompatActivity implements AdminDialog.ExampleDialogListener{
    private TextView textViewUsername;
    private TextView textViewPassword;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_connect);

        textViewUsername = (TextView) findViewById(R.id.textview_username);
        textViewPassword = (TextView) findViewById(R.id.textview_password);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        AdminDialog exampleDialog =  new AdminDialog();
        exampleDialog.show(getSupportFragmentManager(), "admin dialog");
    }

    @Override
    public void applyTexts(String username, String password) {
        textViewUsername.setText(username);
        textViewPassword.setText(password);
    }

    @Override
    public void showLoginScreen() {
        Intent intent = new Intent(AdminConnect.this, Login.class);
        startActivity(intent);
        finish();
    }
}
