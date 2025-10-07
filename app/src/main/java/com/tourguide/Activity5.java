package com.tourguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity5 extends Activity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        tvResult = findViewById(R.id.tvResult);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("user_name");
            String city = extras.getString("user_city");
            tvResult.setText("Bonjour " + name + " 👋\nVous habitez à " + city + ".");
        }
    }
}
