package controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.conorthomason.garageapp.EmployeeManagement;
import com.conorthomason.garageapp.Garage;
import com.conorthomason.garageapp.Manager;
import com.conorthomason.garageapp.PaymentScheme;
import com.conorthomason.garageapp.R;

import java.util.ArrayList;

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
            EditText managerPasswordBox = (EditText) findViewById(R.id.managerPasswordEntry);
            EditText managerFirstNameBox = (EditText) findViewById(R.id.managerFirstNameEntry);
            EditText managerLastNameBox = (EditText) findViewById(R.id.managerLastNameEntry);

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

            if (managerUsername.equals("") || managerPassword.equals("") || managerFirstName.equals("") || managerLastName.equals("")){
                NullPointerException e = new NullPointerException();
                throw e;
            }
            Garage.createGarage(assertPaymentSchemes(carTypes), assertPaymentSchemes(motorcycleTypes), assertPaymentSchemes(truckTypes),
                    carSpaces, motorcycleSpaces, truckSpaces);
            EmployeeManagement.addEmployee(new Manager(managerUsername, managerPassword, managerFirstName, managerLastName));

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            System.out.println(Garage.garageDetails());
            finish();

        } catch (NullPointerException e){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("User creation error");
            dialog.setMessage("One or more entries are missing or incorrect; please try again");
            dialog.setPositiveButton("OK", null);
            dialog.show();
            e.printStackTrace();
        } catch (NumberFormatException f) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("User creation error");
            dialog.setMessage("One or more entries are missing or incorrect; please try again");
            dialog.setPositiveButton("OK", null);
            dialog.show();
            f.printStackTrace();
        }
    }
    public ArrayList<PaymentScheme> assertPaymentSchemes(boolean[] values){
        ArrayList<PaymentScheme> paymentSchemes = new ArrayList<PaymentScheme>();
        if (values[0]){
            paymentSchemes.add(PaymentScheme.CASH);
        }
        if (values[1]){
            paymentSchemes.add(PaymentScheme.CHECK);
        }
        if (values[2]){
            paymentSchemes.add(PaymentScheme.DEBIT);
        }
        if (values[3]){
            paymentSchemes.add(PaymentScheme.CREDIT);
        }
      return paymentSchemes;
    }

}
