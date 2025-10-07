package com.tourguide;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SelectPlaceActivity extends Activity {

    private TextView tvHeader;
    private EditText etSearch;
    private ListView lvPlaces;
    private PlaceAdapter adapter;

    private final List<Place> all = new ArrayList<>();
    private final List<Place> current = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        tvHeader = findViewById(R.id.tvHeader);
        etSearch  = findViewById(R.id.etSearchPlace);
        lvPlaces  = findViewById(R.id.lvPlaces);

        String code = getIntent().getStringExtra("country_code");
        String name = getIntent().getStringExtra("country_name");
        tvHeader.setText("Pays : " + name);

        if ("TN".equalsIgnoreCase(code)) {
            all.addAll(Arrays.asList(
                    new Place("Musée National", "Ouvert · 10 DT"),
                    new Place("Médina", "Gratuit"),
                    new Place("Cathédrale", "8h–18h")
            ));
        } else if ("FR".equalsIgnoreCase(code)) {
            all.addAll(Arrays.asList(
                    new Place("Louvre", "Musée · €17"),
                    new Place("Tour Eiffel", "Monument"),
                    new Place("Montmartre", "Quartier")
            ));
        } else {
            all.addAll(Arrays.asList(
                    new Place("Centre-ville", "Promenade"),
                    new Place("Parc Central", "Nature"),
                    new Place("Marché Local", "Artisanat")
            ));
        }
        current.addAll(all);

        adapter = new PlaceAdapter(current);
        lvPlaces.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { filter(s.toString()); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String q){
        current.clear();
        if (q == null || q.trim().isEmpty()) {
            current.addAll(all);
        } else {
            String s = q.toLowerCase(Locale.ROOT);
            for (Place p : all) {
                if (p.title.toLowerCase(Locale.ROOT).contains(s) || p.meta.toLowerCase(Locale.ROOT).contains(s)) {
                    current.add(p);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    static class Place {
        final String title, meta;
        Place(String t, String m){ title=t; meta=m; }
    }

    class PlaceAdapter extends BaseAdapter {
        private final List<Place> items;
        PlaceAdapter(List<Place> items){ this.items = items; }

        @Override public int getCount() { return items.size(); }
        @Override public Place getItem(int position) { return items.get(position); }
        @Override public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
            }
            TextView tvTitle = v.findViewById(R.id.tvPlaceTitle);
            TextView tvMeta  = v.findViewById(R.id.tvPlaceMeta);
            Place p = getItem(position);
            tvTitle.setText(p.title);
            tvMeta.setText(p.meta);
            return v;
        }
    }
}
