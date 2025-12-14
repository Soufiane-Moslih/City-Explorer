package com.example.app_listgridspinnerr;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class CityDetailActivity extends AppCompatActivity {
    public static final String EXTRA_CITY_NAME = "extra_city_name";
    public static final String EXTRA_IMAGE_RES_ID = "extra_image_res_id";
    public static final String EXTRA_CITY_DESCRIPTION = "extra_city_description";
    public static final String EXTRA_SHOW_DETAILED_INFO = "extra_show_detailed_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        TextView cityNameView = findViewById(R.id.cityName);
        TextView cityDescriptionView = findViewById(R.id.cityDescription);
        ImageView cityImageView = findViewById(R.id.cityImage);
        ViewPager2 imageSlider = findViewById(R.id.imageSlider);
        LinearLayout sliderContainer = findViewById(R.id.sliderContainer);
        LinearLayout dotsContainer = findViewById(R.id.dotsContainer);
        LinearLayout infoContainer = findViewById(R.id.infoContainer);
        MaterialCardView aboutCard = findViewById(R.id.aboutCard);
        MaterialCardView informationCard = findViewById(R.id.informationCard);
        String cityName = getIntent().getStringExtra(EXTRA_CITY_NAME);
        if (cityName == null) {
            cityName = getString(R.string.default_city_name);
        }
        int imageRes = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, R.drawable.tanger);
        String description = getIntent().getStringExtra(EXTRA_CITY_DESCRIPTION);
        if (description == null) {
            description = getString(R.string.default_city_description);
        }
        cityNameView.setText(cityName);
        cityDescriptionView.setText(description);
        String normalizedCityName = cityName.toLowerCase().replace(" ", "");
        if (normalizedCityName.equals("casablanca") || normalizedCityName.equals("casa") ||
            normalizedCityName.equals("marrakech") || normalizedCityName.equals("merrakech") ||
            normalizedCityName.equals("tanger") || normalizedCityName.equals("ifrane") ||
            normalizedCityName.equals("agadir") || normalizedCityName.equals("rabat")) {
            cityImageView.setVisibility(View.GONE);
            sliderContainer.setVisibility(View.VISIBLE);
            setupImageSlider(imageSlider, dotsContainer, normalizedCityName);
        } else {
            cityImageView.setVisibility(View.VISIBLE);
            sliderContainer.setVisibility(View.GONE);
            cityImageView.setImageResource(imageRes);
        }
        boolean showDetailedInfo = getIntent().getBooleanExtra(EXTRA_SHOW_DETAILED_INFO, false);
        if (showDetailedInfo) {
            aboutCard.setVisibility(View.VISIBLE);
            informationCard.setVisibility(View.VISIBLE);
            addCityInfo(infoContainer, cityName);
        } else {
            aboutCard.setVisibility(View.GONE);
            informationCard.setVisibility(View.GONE);
        }
    }

    private void addCityInfo(LinearLayout container, String cityName) {
        if (cityName == null) return;
        String normalized = cityName.toLowerCase().replace(" ", "");
        CityInfo cityInfo = getCityInfo(normalized);
        if (cityInfo != null) {
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Position : " + cityInfo.position);
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Code postal : " + cityInfo.postalCode);
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Population : " + cityInfo.population);
        } else {
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Position : Non disponible");
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Code postal : Non disponible");
            addInfoItem(container, android.R.drawable.ic_menu_mylocation, "Population : Non disponible");
        }
    }

    private CityInfo getCityInfo(String normalized) {
        switch (normalized) {
            case "agadir":
                return new CityInfo("30.4278° N, 9.5981° W", "80000", "1 017 030");
            case "asfi":
            case "safi":
                return new CityInfo("32.2992° N, 9.2372° W", "46000", "346 096");
            case "asila":
                return new CityInfo("35.4667° N, 6.0333° W", "90000", "28 861");
            case "casablanca":
            case "casa":
                return new CityInfo("33.5731° N, 7.5898° W", "20000-20200", "4 012 310");
            case "chefchaouen":
                return new CityInfo("35.1714° N, 5.2694° W", "91000", "36 280");
            case "elhajeb":
                return new CityInfo("33.6833° N, 5.3667° W", "33000", "28 126");
            case "eljadida":
                return new CityInfo("33.2342° N, 8.5228° W", "24000", "147 549");
            case "essaouira":
                return new CityInfo("31.5085° N, 9.7595° W", "44000", "70 634");
            case "fes":
                return new CityInfo("34.0331° N, 5.0003° W", "30000-30100", "1 336 960");
            case "laaraych":
            case "larache":
                return new CityInfo("35.1933° N, 6.1556° W", "92000", "109 294");
            case "laayoune":
                return new CityInfo("27.1536° N, 13.2033° W", "70000", "262 507");
            case "meknes":
                return new CityInfo("33.8950° N, 5.5547° W", "50000", "595 375");
            case "merrakech":
            case "marrakech":
                return new CityInfo("31.6295° N, 7.9811° W", "40000-40100", "839 296");
            case "ouarzazate":
                return new CityInfo("30.9200° N, 6.9100° W", "45000", "57 245");
            case "oujda":
                return new CityInfo("34.6867° N, 1.9114° W", "60000", "405 253");
            case "rabat":
                return new CityInfo("34.0209° N, 6.8417° W", "10000-10100", "2 020 970");
            case "sidibennour":
                return new CityInfo("32.6500° N, 8.4333° W", "23000", "40 044");
            case "tanger":
                return new CityInfo("35.7595° N, 5.8330° W", "90000", "688 356");
            case "taroudant":
                return new CityInfo("30.4700° N, 8.8700° W", "83000", "71 133");
            case "tetouan":
                return new CityInfo("35.5711° N, 5.3722° W", "93000", "461 167");
            default:
                return null;
        }
    }

    private static class CityInfo {
        String position;
        String postalCode;
        String population;
        CityInfo(String position, String postalCode, String population) {
            this.position = position;
            this.postalCode = postalCode;
            this.population = population;
        }
    }

    private void addInfoItem(LinearLayout container, int iconRes, String text) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = (int) (12 * getResources().getDisplayMetrics().density);
        itemLayout.setPadding(0, (int)(4 * getResources().getDisplayMetrics().density), 0, 0);
        itemLayout.setLayoutParams(params);
        ImageView icon = new ImageView(this);
        icon.setImageResource(iconRes);
        int iconSize = (int) (22 * getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        icon.setLayoutParams(iconParams);
        if (iconRes == android.R.drawable.btn_star) {
            icon.setColorFilter(0xFFFFD700);
        } else {
            android.util.TypedValue typedValue = new android.util.TypedValue();
            getTheme().resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
            int textColor = typedValue.data;
            icon.setColorFilter(textColor);
        }
        TextView textView = new TextView(this);
        textView.setText(text);
        android.util.TypedValue typedValue = new android.util.TypedValue();
        getTheme().resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
        textView.setTextColor(typedValue.data);
        textView.setTextSize(15);
        textView.setLetterSpacing(0.01f);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.leftMargin = (int) (12 * getResources().getDisplayMetrics().density);
        textView.setLayoutParams(textParams);
        itemLayout.addView(icon);
        itemLayout.addView(textView);
        container.addView(itemLayout);
    }

    private void setupImageSlider(ViewPager2 viewPager, LinearLayout dotsContainer, String cityName) {
        List<Integer> cityImages = new ArrayList<>();
        if (cityName.equals("casablanca") || cityName.equals("casa")) {
            cityImages.add(R.drawable.casa1);
            cityImages.add(R.drawable.casa2);
            cityImages.add(R.drawable.casa3);
            cityImages.add(R.drawable.casa);
            cityImages.add(R.drawable.mosquehassan2);
        } else if (cityName.equals("marrakech") || cityName.equals("merrakech")) {
            cityImages.add(R.drawable.kech1);
            cityImages.add(R.drawable.kech2);
            cityImages.add(R.drawable.kech3);
            cityImages.add(R.drawable.kech4);
            cityImages.add(R.drawable.kech5);
        } else if (cityName.equals("tanger")) {
            cityImages.add(R.drawable.tanger1);
            cityImages.add(R.drawable.tanger2);
            cityImages.add(R.drawable.tanger3);
            cityImages.add(R.drawable.tanger4);
        } else if (cityName.equals("ifrane")) {
            cityImages.add(R.drawable.ifrane1);
            cityImages.add(R.drawable.ifrane2);
            cityImages.add(R.drawable.ifrane3);
            cityImages.add(R.drawable.ifrane4);
            cityImages.add(R.drawable.ifrane5);
            cityImages.add(R.drawable.ifrane6);
            cityImages.add(R.drawable.lionifrane);
        } else if (cityName.equals("agadir")) {
            cityImages.add(R.drawable.agadir);
            cityImages.add(R.drawable.agadir1);
            cityImages.add(R.drawable.agadir2);
            cityImages.add(R.drawable.agadir3);
            cityImages.add(R.drawable.agadiroufella);
        } else if (cityName.equals("rabat")) {
            cityImages.add(R.drawable.rabat);
            cityImages.add(R.drawable.rabat1);
            cityImages.add(R.drawable.rabat2);
            cityImages.add(R.drawable.rabat3);
            cityImages.add(R.drawable.rabat4);
            cityImages.add(R.drawable.rabat5);
        }
        if (cityImages.isEmpty()) {
            return;
        }
        ImageSliderAdapter adapter = new ImageSliderAdapter(cityImages);
        viewPager.setAdapter(adapter);
        createDotsIndicators(dotsContainer, cityImages.size(), viewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateDotsIndicators(dotsContainer, position);
                super.onPageSelected(position);
            }
        });
    }

    private void createDotsIndicators(LinearLayout container, int count, ViewPager2 viewPager) {
        container.removeAllViews();
        for (int i = 0; i < count; i++) {
            View dot = new View(this);
            int dotSize = (int) (10 * getResources().getDisplayMetrics().density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);
            params.setMargins((int)(6 * getResources().getDisplayMetrics().density), 0, (int)(6 * getResources().getDisplayMetrics().density), 0);
            dot.setLayoutParams(params);
            android.graphics.drawable.GradientDrawable dotDrawable = new android.graphics.drawable.GradientDrawable();
            dotDrawable.setShape(android.graphics.drawable.GradientDrawable.OVAL);
            dotDrawable.setColor(0xFFCCCCCC);
            dot.setBackground(dotDrawable);
            final int position = i;
            dot.setOnClickListener(v -> viewPager.setCurrentItem(position, true));
            container.addView(dot);
        }
        if (count > 0) {
            updateDotsIndicators(container, 0);
        }
    }

    private void updateDotsIndicators(LinearLayout container, int activePosition) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View dot = container.getChildAt(i);
            android.graphics.drawable.GradientDrawable dotDrawable = new android.graphics.drawable.GradientDrawable();
            dotDrawable.setShape(android.graphics.drawable.GradientDrawable.OVAL);
            if (i == activePosition) {
                dotDrawable.setColor(0xFF2196F3);
                int dotSize = (int) (12 * getResources().getDisplayMetrics().density);
                dot.getLayoutParams().width = dotSize;
                dot.getLayoutParams().height = dotSize;
            } else {
                dotDrawable.setColor(0xFFCCCCCC);
                int dotSize = (int) (10 * getResources().getDisplayMetrics().density);
                dot.getLayoutParams().width = dotSize;
                dot.getLayoutParams().height = dotSize;
            }
            dot.setBackground(dotDrawable);
            dot.requestLayout();
        }
    }
}
