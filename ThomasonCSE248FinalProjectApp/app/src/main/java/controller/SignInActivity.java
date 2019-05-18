package controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.conorthomason.garageapp.Employee;
import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.SingletonService;

public class SignInActivity extends AppCompatActivity {

    private EmployeeManagement employees = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((SingletonService)getApplication()).loadGarage();
        ((SingletonService)getApplication()).loadEmployees();
        employees = ((SingletonService)getApplicationContext()).getEmployeeManagementSingleton();
        System.out.println("Employee Management Created");
    }

    public void signInButton(View v){
        EditText usernameField = (EditText) findViewById(R.id.usernameSignIn);
        EditText passwordField = (EditText) findViewById(R.id.passwordSignIn);

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!employees.findEmployee(username)) {
            builder.setTitle("Login error");
            builder.setMessage("User not found. Please try again.");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            Employee foundEmployee = employees.getEmployee(username);
            if (!foundEmployee.getPassword().equals(password)){
                builder.setTitle("Login error");
                builder.setMessage("Password is incorrect. Please try again.");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
            else {
                employees.setActiveEmployee(foundEmployee);
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }

    }

    public void createGarageButton(View v){
        Intent intent = new Intent(this, CreateGarageActivity.class);
        startActivity(intent);
    }

}
