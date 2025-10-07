package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            startActivity(new Intent(this, SelectCountryActivity.class));
        });


        Button btnForm = findViewById(R.id.btnForm);
        btnForm.setOnClickListener(v -> {
            startActivity(new Intent(this, Activity4.class));
        });
    }
}
