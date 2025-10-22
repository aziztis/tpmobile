package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        TextView tvResult = findViewById(R.id.tvResult);
        Button btnConfirm = findViewById(R.id.btnConfirm);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Récupération des données reçues
        double amount = getIntent().getDoubleExtra("amount", 0);
        int days = getIntent().getIntExtra("days", 0);
        String country = getIntent().getStringExtra("country");

        double rate = 1.0;
        String currencySymbol = "€";
        switch (country) {
            case "Maroc (MAD)": rate = 10.9; currencySymbol = "MAD"; break;
            case "Japon (JPY)": rate = 161.0; currencySymbol = "¥"; break;
            case "États-Unis (USD)": rate = 1.08; currencySymbol = "$"; break;
        }

        double converted = amount * rate;
        double dailyBudget = converted / days;

        // Texte d’affichage du résultat
        String result = String.format("Pays : %s\nMontant : %.2f € = %.2f %s\nDurée : %d jours\nBudget quotidien : %.2f %s",
                country, amount, converted, currencySymbol, days, dailyBudget, currencySymbol);

        tvResult.setText(result);

        // ✅ Si l’utilisateur confirme
        btnConfirm.setOnClickListener(v -> {
            String message = "✅ Bon séjour !\n\n" + result;
            Intent returnIntent = new Intent();
            returnIntent.putExtra("return_summary", message);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        // ❌ Si l’utilisateur annule
        btnCancel.setOnClickListener(v -> {
            String message = "❌ Choisis un autre plan.\n\n" + result;
            Intent returnIntent = new Intent();
            returnIntent.putExtra("return_summary", message);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
