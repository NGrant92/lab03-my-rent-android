package com.example.niall.myrent.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.view.View;

import com.example.niall.myrent.R;
import com.example.niall.myrent.app.MyRentApp;
import com.example.niall.myrent.models.Portfolio;
import com.example.niall.myrent.models.Residence;
import static com.example.niall.myrent.android.helpers.IntentHelper.navigateUp;

public class ResidenceActivity extends AppCompatActivity implements TextWatcher, /*OnCheckedChangeListener,*/ View.OnClickListener, DatePickerDialog.OnDateSetListener{

  private EditText geolocation;
  private Residence residence;
  private CheckBox rented;
  private Button   dateButton;
  private Portfolio portfolio;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_residence);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    geolocation = (EditText)findViewById(R.id.geolocation);
    dateButton  = (Button)findViewById(R.id.registration_date);
    rented = (CheckBox)findViewById(R.id.isrented);

    residence = new Residence();
    geolocation.addTextChangedListener(this);
    dateButton.setOnClickListener(this);

    //Listener set using: Lambda Expression
    rented.setOnCheckedChangeListener((buttonView, isChecked) -> residence.rented = isChecked);

    /*
    //Listener set using: Delegate/Interface method
    rented.setOnCheckedChangeListener(this);

    //Listener set using: Inner anonymous class
    rented.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        residence.rented = isChecked;
      }
    });
    */

    MyRentApp app = (MyRentApp) getApplication();
    portfolio = app.portfolio;
    Long resId = (Long) getIntent().getExtras().getSerializable("RESIDENCE_ID");
    residence = portfolio.getResidence(resId);
    if (residence != null) {
      updateControls(residence);
    }
  }

  public void updateControls(Residence residence) {
    geolocation.setText(residence.geolocation);
    rented.setChecked(residence.rented);
    dateButton.setText(residence.getDateString());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch (item.getItemId()){
      case android.R.id.home:
        navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void afterTextChanged(Editable editable) {
    residence.setGeolocation(editable.toString());
  }

  /*
  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
    Log.i(this.getClass().getSimpleName(), "rented Checked");
    residence.rented = isChecked;
  }
  */

  private void geolocation(View v)
  {
    // Respond to user input
    geolocation = (EditText) v.findViewById(R.id.geolocation);
    geolocation.addTextChangedListener(new TextWatcher()
    {
      public void onTextChanged(CharSequence c, int start, int before, int count)
      {
        residence.setGeolocation(c.toString());
      }

      public void beforeTextChanged(CharSequence c, int start, int count, int after)
      {
        // this space intentionally left blank
      }

      public void afterTextChanged(Editable c)
      {
        // this space intentionally left blank
      }
    });
  }

  @Override
  public void onDateSet(DatePicker datePicker, int year, int month, int day) {
    Date date = new GregorianCalendar(year, month, day).getTime();
    residence.date = date.getTime();
    dateButton.setText(residence.getDateString());
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.registration_date:
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dpd.show();
        break;
    }
  }

  @Override
  public void onPause()
  {
    super.onPause();
    portfolio.saveResidences();
  }
}
