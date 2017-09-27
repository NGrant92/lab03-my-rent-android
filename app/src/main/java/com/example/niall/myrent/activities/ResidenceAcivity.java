package com.example.niall.myrent.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.text.TextWatcher;

import com.example.niall.myrent.R;
import com.example.niall.myrent.models.Residence;

public class ResidenceAcivity extends Activity implements TextWatcher {

  private EditText geolocation;
  private Residence residence;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_residence);
    geolocation = (EditText) findViewById(R.id.geolocation);
    residence = new Residence();

    geolocation.addTextChangedListener(this);
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
}
