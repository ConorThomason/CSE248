package controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.conorthomason.garageapp.R;

public class CreateGarageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void confirmButton(View view){
        try {
            EditText carSpacesBox = (EditText) findViewById(R.id.carSpacesEntry);
            EditText motorcycleSpacesBox = (EditText) findViewById(R.id.motorcycleSpacesEntry);
            EditText truckSpacesBox = (EditText) findViewById(R.id.truckSpacesEntry);
            EditText carRateBox = (EditText) findViewById(R.id.carRateEntry);
            EditText motorcycleRateBox = (EditText) findViewById(R.id.motorcycleRateEntry);
            EditText truckRateBox = (EditText) findViewById(R.id.truckRateEntry);
            EditText managerUsernameBox = (EditText) findViewById(R.id.managerUsernameEntry);
            EditText managerPasswordBox = (EditText) findViewById(R.id.passwordEntry);
            EditText managerFirstNameBox = (EditText) findViewById(R.id.firstNameEntry);
            EditText managerLastNameBox = (EditText) findViewById(R.id.lastNameEntry);

            CheckBox carCashBox = (CheckBox) findViewById(R.id.carCashCheckBox);
            CheckBox carCheckBox = (CheckBox) findViewById(R.id.carCheckCheckBox);
            CheckBox carDebitBox = (CheckBox) findViewById(R.id.carDebitCheckBox);
            CheckBox carCreditBox = (CheckBox) findViewById(R.id.carCreditCheckBox);
            CheckBox motorcycleCashBox = (CheckBox) findViewById(R.id.motorcycleCashCheckBox);
            CheckBox motorcycleCheckBox = (CheckBox) findViewById(R.id.motorcycleCheckCheckBox);
            CheckBox motorcycleDebitBox = (CheckBox) findViewById(R.id.motorcycleDebitCheckBox);
            CheckBox motorcycleCreditBox = (CheckBox) findViewById(R.id.motorcycleCreditCheckBox);
            CheckBox truckCashBox = (CheckBox) findViewById(R.id.truckCashCheckBox);
            CheckBox truckCheckBox = (CheckBox) findViewById(R.id.truckCheckCheckBox);
            CheckBox truckDebitBox = (CheckBox) findViewById(R.id.truckDebitCheckBox);
            CheckBox truckCreditBox = (CheckBox) findViewById(R.id.truckCreditCheckBox);

            int carSpaces = (!carSpacesBox.getText().equals("")) ? Integer.parseInt(carSpacesBox.getText().toString()) : 0;
            int motorcycleSpaces = (!motorcycleSpacesBox.getText().equals("")) ? Integer.parseInt(motorcycleSpacesBox.getText().toString()) : 0;
            int truckSpaces = (!truckSpacesBox.getText().equals("")) ? Integer.parseInt(truckSpacesBox.getText().toString()) : 0;

            double carRate = (!carRateBox.getText().equals("")) ? Double.parseDouble(carRateBox.getText().toString()) : 0;
            double motorcycleRate = (!motorcycleRateBox.getText().equals("")) ? Double.parseDouble(motorcycleRateBox.getText().toString()) : 0;
            double truckRate = (!truckRateBox.getText().equals("")) ? Double.parseDouble(truckRateBox.getText().toString()) : 0;

            String managerUsername = managerUsernameBox.getText().toString();
            String managerPassword = managerPasswordBox.getText().toString();
            String managerFirstName = managerFirstNameBox.getText().toString();
            String managerLastName = managerLastNameBox.getText().toString();

            boolean[] carTypes = {carCashBox.isChecked(), carCheckBox.isChecked(), carDebitBox.isChecked(), carCreditBox.isChecked()};
            boolean[] motorcycleTypes = {motorcycleCashBox.isChecked(), motorcycleCheckBox.isChecked(), motorcycleDebitBox.isChecked(), motorcycleCreditBox.isChecked()};
            boolean[] truckTypes = {truckCashBox.isChecked(), truckCheckBox.isChecked(), truckDebitBox.isChecked(), truckCreditBox.isChecked()};

            if (carSpaces == 0 || motorcycleSpaces == 0 || truckSpaces == 0 || carRate == 0 || motorcycleRate == 0 || truckRate == 0
                    || managerUsername.equals("") || managerPassword.equals("") || managerFirstName.equals("") || managerLastName.equals("")){
                NullPointerException e = new NullPointerException();
                throw e;
            }

        } catch (NullPointerException e){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("User creation error");
            dialog.setMessage("One or more entries are missing or incorrect; please try again");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } catch (NumberFormatException f){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("User creation error");
            dialog.setMessage("One or more entries are missing or incorrect; please try again");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }
    }

}
