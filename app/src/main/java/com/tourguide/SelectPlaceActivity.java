package com.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectPlaceActivity extends Activity {

    private ListView listPlaces, listActivities;
    private Button btnPlanBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        TextView tvHeader = findViewById(R.id.tvHeader);
        listPlaces = findViewById(R.id.listPlaces);
        listActivities = findViewById(R.id.listActivities);
        btnPlanBudget = findViewById(R.id.btnPlanBudget);

        String country = getIntent().getStringExtra("country");
        tvHeader.setText("Découvre " + country);

        // --- Lieux à visiter ---
        String[] places;
        // --- Activités à faire ---
        String[] activities;

        switch (country) {
            case "France":
                places = new String[]{"Paris", "Nice", "Lyon"};
                activities = new String[]{"Déguster des croissants 🥐", "Visiter le Louvre 🖼️", "Croisière sur la Seine 🚤"};
                break;
            case "Maroc":
                places = new String[]{"Marrakech", "Casablanca", "Fès"};
                activities = new String[]{"Balade dans la médina 🕌", "Goûter un tajine 🍲", "Promenade à dos de chameau 🐪"};
                break;
            case "Japon":
                places = new String[]{"Tokyo", "Kyoto", "Osaka"};
                activities = new String[]{"Découvrir les temples ⛩️", "Assister à un match de sumo 🥋", "Goûter les sushis 🍣"};
                break;
            case "États-Unis":
                places = new String[]{"New York", "Los Angeles", "Miami"};
                activities = new String[]{"Grimper à l’Empire State 🏙️", "Faire du surf 🌊", "Visiter les studios Hollywood 🎬"};
                break;
            default:
                places = new String[]{"Destination principale"};
                activities = new String[]{"Activité populaire"};
        }

        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, places);
        listPlaces.setAdapter(placeAdapter);

        ArrayAdapter<String> activityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, activities);
        listActivities.setAdapter(activityAdapter);

        listPlaces.setOnItemClickListener((parent, view, position, id) -> {
            String selectedPlace = places[position];
            Toast.makeText(this, "Lieu choisi : " + selectedPlace, Toast.LENGTH_SHORT).show();
        });

        listActivities.setOnItemClickListener((parent, view, position, id) -> {
            String selectedActivity = activities[position];
            Toast.makeText(this, "Activité : " + selectedActivity, Toast.LENGTH_SHORT).show();
        });

        // 🔹 Aller vers Activity4 pour calculer le budget
        btnPlanBudget.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity4.class);
            startActivity(intent);
        });
    }
}
