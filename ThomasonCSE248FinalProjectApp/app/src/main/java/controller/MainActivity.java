package controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.conorthomason.garageapp.Employee;
import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.Garage;
import com.conorthomason.garageapp.Manager;
import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.SingletonService;
import com.conorthomason.garageapp.Vehicle;
import com.conorthomason.garageapp.VehicleType;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EmployeeManagement employees = null;
    private Garage garage = null;

    public void signOutButtonAction(Menu navMenu){

        ((SingletonService) getApplication()).saveData();
        Intent intent = new Intent(this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void signUpButtonAction(Menu navMenu){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void fabAction(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        alert.setView(dialogView
        );
        alert.setTitle("Create Vehicle");
        alert.setView(dialogView);
        final RadioGroup radioGroup = (RadioGroup)dialogView.findViewById(R.id.vehicleRadioGroup);
        radioGroup.check(R.id.carRadioButton);
        alert.setPositiveButton("Create Vehicle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final EditText plateInput = (EditText) dialogView.findViewById(R.id.vehicleCreationPlateInput);

                    int id = radioGroup.getCheckedRadioButtonId();
                    final VehicleType selectedType;
                    try{
                        switch(id){
                            case R.id.carRadioButton:
                                selectedType = VehicleType.CAR;
                                break;
                            case R.id.motorcycleRadioButton:
                                selectedType = VehicleType.MOTORCYCLE;
                                break;
                            case R.id.truckRadioButton:
                                selectedType = VehicleType.TRUCK;
                                break;
                            default:
                                throw new NullPointerException();
                        }

                    Employee parkingEmployee = employees.getActiveEmployee();
                    garage.parkVehicle(new Vehicle(selectedType, parkingEmployee.getFullName(), plateInput.getText().toString()));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        AlertDialog.Builder error = new AlertDialog.Builder(MainActivity.this);
                        error.setTitle("Creation error");
                        error.setMessage("One or more required fields were not filled. Please try again");
                        error.setPositiveButton("OK", null);
                        error.show();
                    }

                }
            });
        alert.show();

    }

    protected void onDestroy(Bundle savedInstanceState){
        ((SingletonService)getApplication()).saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        employees = ((SingletonService)getApplication()).getEmployeeManagementSingleton();
        garage = ((SingletonService)getApplication()).getGarageSingleton();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAction(view);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu navMenu = navigationView.getMenu();
        employees = ((SingletonService)getApplication()).getEmployeeManagementSingleton();
        if (employees.getActiveEmployee() instanceof Manager){
            navMenu.findItem(R.id.sign_up_button).setVisible(true);
            navMenu.findItem(R.id.employee_management).setVisible(true);
        }
        else{
            navMenu.findItem(R.id.sign_up_button).setVisible(false);
            navMenu.findItem(R.id.employee_management).setVisible(false);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu navMenu = navigationView.getMenu();
        if (id == R.id.sign_out_button){
            signOutButtonAction(navMenu);
        }
        else if (id == R.id.sign_up_button){
            signUpButtonAction(navMenu);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
