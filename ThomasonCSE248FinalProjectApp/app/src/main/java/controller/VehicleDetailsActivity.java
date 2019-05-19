package controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.conorthomason.garageapp.Employee;
import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.Garage;
import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.Receipt;
import com.conorthomason.garageapp.SingletonService;
import com.conorthomason.garageapp.TimeControl;
import com.conorthomason.garageapp.Utilities;
import com.conorthomason.garageapp.Vehicle;

public class VehicleDetailsActivity extends Activity {
    private Garage garage = null;
    private Vehicle vehicle = null;
    private EmployeeManagement employees = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TimeControl.startTime(1);
        vehicle = (Vehicle) getIntent().getSerializableExtra("Vehicle");
        garage = ((SingletonService)getApplication()).getGarageSingleton();
        employees = ((SingletonService)getApplication()).getEmployeeManagementSingleton();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        TextView vehiclePlateDisplay = (TextView) findViewById(R.id.vehiclePlateDisplay);
        vehiclePlateDisplay.setText(" " + vehicle.getLicensePlate());
        TextView timeParkedDisplay = (TextView) findViewById(R.id.timeParkedDisplay);
        timeParkedDisplay.setText(" " + Utilities.zoneStringFormat(garage.getSpace(vehicle.getParkingSpot()).getTimeDateParked()));
        TextView employeeParkedDisplay = (TextView) findViewById(R.id.employeeParkedDisplay);
        employeeParkedDisplay.setText(" " + vehicle.getParkedBy());
        TextView distanceDisplay = (TextView) findViewById(R.id.distanceDisplay);
        distanceDisplay.setText(" " + Integer.toString(vehicle.getParkingSpot()));

        Button retrieveButton = (Button) findViewById(R.id.retrieveButton);

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder removal = new AlertDialog.Builder(VehicleDetailsActivity.this);
                removal.setTitle("Receipt");
                Receipt receipt = new Receipt(vehicle, vehicle.getParkedBy(), garage.getSpace(vehicle.getParkingSpot()), garage.getSpace(vehicle.getParkingSpot()).getAcceptedPaymentSchemes().get(0), vehicle.getEarlyBird());
                garage.removeVehicle(vehicle.getLicensePlate());
                ((SingletonService)getApplication()).saveGarage();
                removal.setMessage(receipt.toString());
                removal.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(VehicleDetailsActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                removal.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent = new Intent(VehicleDetailsActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                removal.show();

            }
        });
        ((SingletonService)getApplication()).saveGarage();

    }

}
