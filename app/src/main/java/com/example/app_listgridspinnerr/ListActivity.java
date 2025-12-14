package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {
    private final String[] villes = {"tanger", "asila", "laaraych", "chefchaouen", "fes", "marakech", "asfi", "sidi benour", "el jadida", "casablanca", "el hajeb", "agadir", "essaouira", "rabat", "meknes", "oujda", "nador", "tetouan", "kenitra", "mohammedia", "ouarzazate", "beni mellal", "taroudant", "laayoune"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = findViewById(R.id.list);
        String[] sortedCities = villes.clone();
        Arrays.sort(sortedCities, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < sortedCities.length; i++) {
            sortedCities[i] = capitalize(sortedCities[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sortedCities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this::onCityClicked);
    }

    private void onCityClicked(AdapterView<?> parent, android.view.View view, int position, long id) {
        String city = ((TextView) view).getText().toString();
        Integer imageRes = getCityDrawable(city);
        if (imageRes != null) {
            openCityDetail(city, imageRes);
            return;
        }
        Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
    }

    private void openCityDetail(String cityName, @DrawableRes int imageResId) {
        Intent intent = new Intent(this, CityDetailActivity.class);
        intent.putExtra(CityDetailActivity.EXTRA_CITY_NAME, cityName);
        intent.putExtra(CityDetailActivity.EXTRA_IMAGE_RES_ID, imageResId);
        startActivity(intent);
    }

    private String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String lower = value.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private Integer getCityDrawable(String cityName) {
        if (cityName == null) {
            return null;
        }
        String normalized = cityName.replace(" ", "").toLowerCase();
        switch (normalized) {
            case "tanger":
                return R.drawable.tanger;
            case "agadir":
                return R.drawable.agadir;
            case "casablanca":
            case "casa":
                return R.drawable.casa;
            case "asila":
                return R.drawable.asila;
            case "laaraych":
                return R.drawable.laaraychjpg;
            case "chefchaouen":
                return R.drawable.chefchaouen;
            case "asfi":
                return R.drawable.asfi;
            case "sidibenour":
            case "sidibennour":
                return R.drawable.sidibennour;
            case "eljadida":
                return R.drawable.eljadida;
            case "elhajeb":
                return R.drawable.elhajeb;
            case "essaouira":
                return R.drawable.essaouira;
            case "fes":
                return R.drawable.fes;
            case "meknes":
                return R.drawable.meknes;
            case "marakech":
            case "marrakech":
                return R.drawable.merrakech;
            case "oujda":
                return R.drawable.oujda;
            case "rabat":
                return R.drawable.rabat;
            case "tetouan":
                return R.drawable.tetouan;
            case "taroudant":
                return R.drawable.taroudant;
            case "laayoune":
                return R.drawable.laayoune;
            case "ouarzazate":
                return R.drawable.ouarzazate;
            default:
                return null;
        }
    }
}
