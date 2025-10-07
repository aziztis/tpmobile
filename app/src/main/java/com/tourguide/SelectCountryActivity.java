package com.tourguide;

import android.app.Activity;
import android.content.Intent;
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

public class SelectCountryActivity extends Activity {

    private EditText etSearch;
    private ListView lvCountries;
    private CountryAdapter adapter;

    private final List<Country> all = Arrays.asList(
            new Country("TN", "Tunisie"),
            new Country("FR", "France"),
            new Country("IT", "Italie"),
            new Country("ES", "Espagne"),
            new Country("MA", "Maroc")
    );
    private final List<Country> current = new ArrayList<>(all);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);

        etSearch = findViewById(R.id.etSearch);
        lvCountries = findViewById(R.id.lvCountries);

        adapter = new CountryAdapter(current);
        lvCountries.setAdapter(adapter);

        lvCountries.setOnItemClickListener((parent, view, position, id) -> {
            Country c = current.get(position);
            Intent i = new Intent(this, SelectPlaceActivity.class);
            i.putExtra("country_code", c.code);
            i.putExtra("country_name", c.name);
            startActivity(i);
        });

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
            for (Country c : all) {
                if (c.name.toLowerCase(Locale.ROOT).contains(s) || c.code.toLowerCase(Locale.ROOT).contains(s)) {
                    current.add(c);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    static class Country {
        final String code, name;
        Country(String code, String name){ this.code = code; this.name = name; }
    }

    class CountryAdapter extends BaseAdapter {
        private final List<Country> items;
        CountryAdapter(List<Country> items){ this.items = items; }

        @Override public int getCount() { return items.size(); }
        @Override public Country getItem(int position) { return items.get(position); }
        @Override public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
            }
            TextView tvName = v.findViewById(R.id.tvCountryName);
            TextView tvCode = v.findViewById(R.id.tvCountryCode);
            Country c = getItem(position);
            tvName.setText(c.name);
            tvCode.setText(c.code);
            return v;
        }
    }
}
