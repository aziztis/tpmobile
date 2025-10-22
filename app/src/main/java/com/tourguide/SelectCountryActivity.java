package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectCountryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        ListView listView = findViewById(R.id.listCountries);
        String[] countries = {"France", "Italie", "Maroc", "Japon", "Espagne"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = countries[position];
            Intent intent = new Intent(this, SelectPlaceActivity.class);
            intent.putExtra("country", selectedCountry);
            startActivity(intent);
        });
    }
}
