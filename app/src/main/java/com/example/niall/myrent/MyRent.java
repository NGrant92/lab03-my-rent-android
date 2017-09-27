package com.example.niall.myrent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.text.TextWatcher;

public class MyRent extends Activity implements TextWatcher {

  private EditText geolocation;
  private Residence residence;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_rent);
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
