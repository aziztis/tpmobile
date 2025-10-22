package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity4 extends Activity {

    private EditText etAmount, etDays;
    private Spinner spCountry;
    private Button btnCalculate;
    private ProgressBar progressBar;
    private TextView tvSummary;

    private static final int REQUEST_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        etAmount = findViewById(R.id.etAmount);
        etDays = findViewById(R.id.etDays);
        spCountry = findViewById(R.id.spCountry);
        btnCalculate = findViewById(R.id.btnCalculate);
        progressBar = findViewById(R.id.progressBar);
        tvSummary = findViewById(R.id.tvSummary);

        String[] countries = {"France (EUR)", "Maroc (MAD)", "Japon (JPY)", "États-Unis (USD)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> {
            String amountStr = etAmount.getText().toString();
            String daysStr = etDays.getText().toString();

            if (amountStr.isEmpty() || daysStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            int days = Integer.parseInt(daysStr);
            String selectedCountry = spCountry.getSelectedItem().toString();

            progressBar.setVisibility(View.VISIBLE);

            new Handler().postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(Activity4.this, Activity5.class);
                intent.putExtra("amount", amount);
                intent.putExtra("days", days);
                intent.putExtra("country", selectedCountry);
                startActivityForResult(intent, REQUEST_RESULT);
            }, 2000);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_RESULT && resultCode == RESULT_OK && data != null) {
            String summary = data.getStringExtra("return_summary");
            tvSummary.setText(summary);
            tvSummary.setVisibility(View.VISIBLE);
        }
    }
}
