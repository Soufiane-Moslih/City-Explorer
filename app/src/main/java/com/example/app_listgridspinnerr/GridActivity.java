package com.example.app_listgridspinnerr;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final String[] villes = {"tanger", "asila", "laaraych", "chefchaouen", "fes", "marakech", "asfi", "sidi benour", "el jadida", "casablanca", "el hajeb", "agadir", "essaouira", "rabat", "meknes", "oujda", "nador", "tetouan", "kenitra", "mohammedia", "ouarzazate", "beni mellal", "taroudant", "laayoune"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        GridView gridView = findViewById(R.id.gridCities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, villes);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
        String city = (String) parent.getItemAtPosition(position);
        Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
    }
}
