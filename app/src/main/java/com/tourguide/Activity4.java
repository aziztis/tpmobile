package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Activity4 extends Activity {

    private EditText etName, etCity;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctivity_4);

        etName = findViewById(R.id.etName);
        etCity = findViewById(R.id.etCity);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String city = etCity.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("user_name", name);
            bundle.putString("user_city", city);

            Intent intent = new Intent(this, Activity5.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
