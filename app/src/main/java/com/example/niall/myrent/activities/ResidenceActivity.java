package com.example.niall.myrent.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.niall.myrent.R;
import com.example.niall.myrent.app.MyRentApp;
import com.example.niall.myrent.models.Portfolio;
import com.example.niall.myrent.models.Residence;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ResidenceActivity extends Activity implements TextWatcher, OnCheckedChangeListener {

  private EditText geolocation;
  private Residence residence;
  private CheckBox rented;
  private Button   dateButton;
  private Portfolio portfolio;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_residence);

    geolocation = (EditText)findViewById(R.id.geolocation);
    dateButton  = (Button)findViewById(R.id.registration_date);
    rented = (CheckBox)findViewById(R.id.isrented);

    residence   = new Residence();
    geolocation.addTextChangedListener(this);
    dateButton.setEnabled(false);
    rented.setOnCheckedChangeListener(this);

    MyRentApp app = (MyRentApp) getApplication();
    portfolio = app.portfolio;
    Long resId = (Long) getIntent().getExtras().getSerializable("RESIDENCE_ID");
    residence = portfolio.getResidence(resId);
    if (residence != null) {
      updateControls(residence);
    }
  }

  public void updateControls(Residence residence)
  {
    geolocation.setText(residence.geolocation);
    rented.setChecked(residence.rented);
    dateButton.setText(residence.getDateString());
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

  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
    Log.i(this.getClass().getSimpleName(), "rented Checked");
    residence.rented = isChecked;
  }
}
