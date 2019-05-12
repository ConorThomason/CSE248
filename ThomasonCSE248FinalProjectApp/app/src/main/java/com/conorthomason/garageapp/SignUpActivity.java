package com.conorthomason.garageapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void confirmSignUpButton(View v){
        TextView usernameEntry = (TextView) findViewById(R.id.usernameEntry);
        TextView passwordEntry = (TextView) findViewById(R.id.usernameEntry);
        TextView firstNameEntry = (TextView) findViewById(R.id.usernameEntry);
        TextView lastNameEntry = (TextView) findViewById(R.id.usernameEntry);

        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String firstName = firstNameEntry.getText().toString();
        String lastName = lastNameEntry.getText().toString();

        if (EmployeeManagement.findEmployee(username)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //nop
                }
            });
            builder.setMessage("Username already exists");
            builder.setCancelable(true);
            builder.show();
        }
        else{
            Employee employee = new Employee(username, password, firstName, lastName);
            if (EmployeeManagement.addEmployee(employee)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nop
                    }
                });
                builder.setMessage("Employee " + username + " added successfully");
                builder.setCancelable(true);
                builder.show();
            }
        }

    }

}
