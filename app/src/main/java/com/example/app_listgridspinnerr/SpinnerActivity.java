package com.example.app_listgridspinnerr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String[] villes = {"tanger", "asila", "laaraych", "chefchaouen", "fes", "marakech", "asfi", "sidi benour", "el jadida", "casablanca", "el hajeb", "agadir", "essaouira", "rabat", "meknes", "oujda", "nador", "tetouan", "kenitra", "mohammedia", "ouarzazate", "beni mellal", "taroudant", "laayoune"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Spinner spinner = findViewById(R.id.spinnerCities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, villes[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
