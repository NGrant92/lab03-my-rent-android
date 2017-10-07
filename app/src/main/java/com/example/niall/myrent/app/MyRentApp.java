package com.example.niall.myrent.app;

import android.app.Application;
import com.example.niall.myrent.models.Portfolio;
import com.example.niall.myrent.models.PortfolioSerializer;

import static com.example.niall.myrent.android.helpers.LogHelpers.info;

public class MyRentApp extends Application
{
  public Portfolio portfolio;
  private static final String FILENAME = "portfolio.json";

  @Override
  public void onCreate()
  {
    super.onCreate();
    PortfolioSerializer serializer = new PortfolioSerializer(this, FILENAME);
    portfolio = new Portfolio(serializer);

    info(this, "MyRent app launched");
  }
}
