package controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.widget.TextView;

import com.conorthomason.garageapp.Employee;
import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.Garage;
import com.conorthomason.garageapp.Manager;
import com.conorthomason.garageapp.PaymentScheme;
import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.SingletonService;
import com.conorthomason.garageapp.Ticket;
import com.conorthomason.garageapp.TimeControl;
import com.conorthomason.garageapp.Vehicle;
import com.conorthomason.garageapp.VehicleType;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EmployeeManagement employees = null;
    private Garage garage = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Vehicle> vehicles;

    public void signOutButtonAction(Menu navMenu){

        ((SingletonService) getApplication()).saveGarage();
        ((SingletonService) getApplication()).saveEmployees();
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
        garage = garage;
        garage = ((SingletonService)getApplication()).getGarageSingleton();
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        alert.setView(dialogView);
        alert.setTitle("Create Vehicle");
        alert.setView(dialogView);
        final RadioGroup radioGroup = (RadioGroup)dialogView.findViewById(R.id.vehicleRadioGroup);
        final RadioGroup paymentRadioGroup = (RadioGroup)dialogView.findViewById(R.id.paymentRadioGroup);
        radioGroup.check(R.id.carRadioButton);
        paymentRadioGroup.check(R.id.cashRadioButton);

        alert.setPositiveButton("Create Vehicle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final EditText plateInput = (EditText) dialogView.findViewById(R.id.vehicleCreationPlateInput);

                    int id = radioGroup.getCheckedRadioButtonId();
                    int paymentId = paymentRadioGroup.getCheckedRadioButtonId();
                    final VehicleType selectedType;
                    final PaymentScheme selectedPayment;
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
                        switch(paymentId){
                            case R.id.cashRadioButton:
                                selectedPayment = PaymentScheme.CASH;
                                break;
                            case R.id.checkRadioButton:
                                selectedPayment = PaymentScheme.CHECK;
                                break;
                            case R.id.debitRadioButton:
                                selectedPayment = PaymentScheme.DEBIT;
                                break;
                            case R.id.creditRadioButton:
                                selectedPayment = PaymentScheme.CREDIT;
                                break;
                            default:
                                throw new NullPointerException();
                        }

                    Employee parkingEmployee = employees.getActiveEmployee();
                        Vehicle newVehicle = new Vehicle(selectedType, parkingEmployee.getFullName(), plateInput.getText().toString());
                        boolean success = garage.parkVehicle(newVehicle, selectedPayment);
                    if (!success){
                        throw new NullPointerException();
                    }
                    else{
                        Ticket ticket = new Ticket(newVehicle, newVehicle.getParkedBy(), selectedPayment);
                        AlertDialog.Builder ticketAlert = new AlertDialog.Builder(MainActivity.this);
                        ticketAlert.setTitle("Ticket");
                        ticketAlert.setMessage(ticket.toString());
                        ticketAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                        ticketAlert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                        ticketAlert.show();
                    }
                    ((SingletonService)getApplication()).saveGarage();
                    printMap(garage.getVehicles());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        AlertDialog.Builder error = new AlertDialog.Builder(MainActivity.this);
                        error.setTitle("Creation error");
                        error.setMessage("Entered values are either duplicates or incomplete. Please try again.");
                        error.setPositiveButton("OK", null);
                        error.show();
                    }


                }
            });
        alert.show();

    }

    protected void onDestroy(Bundle savedInstanceState){
        ((SingletonService)getApplication()).saveGarage();
        ((SingletonService)getApplication()).saveEmployees();
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        employees = ((SingletonService)getApplication()).getEmployeeManagementSingleton();
        garage = ((SingletonService)getApplication()).getGarageSingleton();
        garage=garage;
        TimeControl.startTime(1);
        try {
            garage.printVehiclesKeySet();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final View rvView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
        RecyclerView rvVehicles = (RecyclerView) findViewById(R.id.rvVehicles);
        vehicles = new ArrayList<>();
        try {
            Iterator it = garage.getVehicles().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                vehicles.add((Vehicle) pair.getValue());
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        VehiclesAdapter adapter = new VehiclesAdapter(vehicles);
        rvVehicles.setAdapter(adapter);
        rvVehicles.setLayoutManager(new LinearLayoutManager(this));
        rvVehicles.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, VehicleDetailsActivity.class);
                intent.putExtra("Vehicle", vehicles.get(position));
                startActivity(intent);
            }
        }));


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
