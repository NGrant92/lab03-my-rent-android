package com.example.niall.myrent.app;

import android.app.Application;
import com.example.niall.myrent.models.Portfolio;
import static com.example.niall.myrent.android.helpers.LogHelpers.info;

public class MyRentApp extends Application
{
  public Portfolio portfolio;

  @Override
  public void onCreate()
  {
    super.onCreate();
    portfolio = new Portfolio();

    info(this, "MyRent app launched");
  }
}
