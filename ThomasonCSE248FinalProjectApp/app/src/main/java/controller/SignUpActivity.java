package controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.conorthomason.garageapp.Attendant;
import com.conorthomason.garageapp.Employee;
import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.Manager;
import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.SingletonService;

public class SignUpActivity extends AppCompatActivity {

    private EmployeeManagement employees = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        employees = ((SingletonService)getApplication()).getEmployeeManagementSingleton();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void confirmSignUpButton(View v){
        TextView usernameEntry = (TextView) findViewById(R.id.usernameEntry);
        TextView passwordEntry = (TextView) findViewById(R.id.passwordEntry);
        TextView firstNameEntry = (TextView) findViewById(R.id.firstNameEntry);
        TextView lastNameEntry = (TextView) findViewById(R.id.lastNameEntry);

        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String firstName = firstNameEntry.getText().toString();
        String lastName = lastNameEntry.getText().toString();

        if (employees.findEmployee(username)){
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
            CheckBox managerBox = (CheckBox) findViewById(R.id.managerCheckBox);
            Employee employee = (managerBox.isChecked()) ? new Manager(username, password, firstName, lastName) : new Attendant(username, password, firstName, lastName);
            employees.addEmployee(employee);
            if (employees.findEmployee(employee.getUsername())){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nop
                    }
                });
                builder.setMessage(username + " added successfully");
                builder.setCancelable(true);
                builder.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nop
                    }
                });
                builder.setMessage("Employee registration failed");
                builder.setCancelable(true);
                builder.show();
            }
        }

    }

}
