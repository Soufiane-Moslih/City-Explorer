package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity {
    private final String[] villes = {"agadir", "asfi", "asila", "casablanca", "chefchaouen", "elhajeb", "eljadida", "essaouira", "fes", "laaraych", "laayoune", "meknes", "merrakech", "ouarzazate", "oujda", "rabat", "sidibennour", "tanger", "taroudant", "tetouan"};
    private final int[] images = {R.drawable.agadir, R.drawable.asfi, R.drawable.asila, R.drawable.casa, R.drawable.chefchaouen, R.drawable.elhajeb, R.drawable.eljadida, R.drawable.essaouira, R.drawable.fes, R.drawable.laaraychjpg, R.drawable.laayoune, R.drawable.meknes, R.drawable.merrakech, R.drawable.ouarzazate, R.drawable.oujda, R.drawable.rabat, R.drawable.sidibennour, R.drawable.tanger, R.drawable.taroudant, R.drawable.tetouan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        GridView gridView = findViewById(R.id.galleryGrid);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < villes.length; i++) {
            Map<String, Object> row = new HashMap<>();
            row.put("image", images[i]);
            row.put("city", capitalize(villes[i]));
            data.add(row);
        }
        String[] from = {"image", "city"};
        int[] to = {R.id.galleryImage, R.id.galleryCityName};
        android.widget.SimpleAdapter adapter = new android.widget.SimpleAdapter(this, data, R.layout.item_gallery, from, to);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> openCityDetail(position));
    }

    private void openCityDetail(int index) {
        Intent intent = new Intent(this, CityDetailActivity.class);
        intent.putExtra(CityDetailActivity.EXTRA_CITY_NAME, capitalize(villes[index]));
        intent.putExtra(CityDetailActivity.EXTRA_IMAGE_RES_ID, images[index]);
        intent.putExtra(CityDetailActivity.EXTRA_CITY_DESCRIPTION, getDescriptionFor(villes[index]));
        intent.putExtra(CityDetailActivity.EXTRA_SHOW_DETAILED_INFO, true);
        startActivity(intent);
    }

    private String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String lower = value.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private String getDescriptionFor(String city) {
        if (city == null) {
            return getString(R.string.default_city_description);
        }
        String normalized = city.toLowerCase().replace(" ", "");
        switch (normalized) {
            case "agadir":
                return getString(R.string.agadir_description);
            case "asfi":
                return getString(R.string.asfi_description);
            case "asila":
                return getString(R.string.asila_description);
            case "casa":
            case "casablanca":
                return getString(R.string.casa_description);
            case "chefchaouen":
                return getString(R.string.chefchaouen_description);
            case "elhajeb":
                return getString(R.string.elhajeb_description);
            case "eljadida":
                return getString(R.string.eljadida_description);
            case "essaouira":
                return getString(R.string.essaouira_description);
            case "fes":
                return getString(R.string.fes_description);
            case "laaraych":
                return getString(R.string.laaraych_description);
            case "laayoune":
                return getString(R.string.laayoune_description);
            case "meknes":
                return getString(R.string.meknes_description);
            case "merrakech":
            case "marrakech":
                return getString(R.string.merrakech_description);
            case "ouarzazate":
                return getString(R.string.ouarzazate_description);
            case "oujda":
                return getString(R.string.oujda_description);
            case "rabat":
                return getString(R.string.rabat_description);
            case "sidibennour":
            case "sidibenour":
                return getString(R.string.sidibennour_description);
            case "tanger":
                return getString(R.string.tanger_description);
            case "taroudant":
                return getString(R.string.taroudant_description);
            case "tetouan":
                return getString(R.string.tetouan_description);
            default:
                return getString(R.string.default_city_description);
        }
    }
}
