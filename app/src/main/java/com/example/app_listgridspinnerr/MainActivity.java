package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedSettings();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left, insets.getInsets(WindowInsetsCompat.Type.systemBars()).top, insets.getInsets(WindowInsetsCompat.Type.systemBars()).right, insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HomeButtonManager buttonManager = new HomeButtonManager(this);
        View rootView = findViewById(R.id.main);
        buttonManager.setupAllButtons(rootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            showSettingsDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSettingsDialog() {
        String[] themeOptions = {getString(R.string.theme_light), getString(R.string.theme_dark)};
        String[] languageOptions = {getString(R.string.language_french), getString(R.string.language_english)};
        String currentTheme = SettingsHelper.getTheme(this);
        String currentLanguage = SettingsHelper.getLanguage(this);
        int selectedThemeIndex = currentTheme.equals(SettingsHelper.THEME_LIGHT) ? 0 : 1;
        int selectedLanguageIndex = currentLanguage.equals(SettingsHelper.LANG_FRENCH) ? 0 : 1;
        new AlertDialog.Builder(this).setTitle(getString(R.string.settings_title)).setItems(new String[]{getString(R.string.theme), getString(R.string.language)}, (dialog, which) -> {
            if (which == 0) {
                new AlertDialog.Builder(this).setTitle(getString(R.string.select_theme)).setSingleChoiceItems(themeOptions, selectedThemeIndex, (d, index) -> {
                    String theme = index == 0 ? SettingsHelper.THEME_LIGHT : SettingsHelper.THEME_DARK;
                    SettingsHelper.saveTheme(this, theme);
                    applyTheme(theme);
                    d.dismiss();
                }).show();
            } else if (which == 1) {
                new AlertDialog.Builder(this).setTitle(getString(R.string.select_language)).setSingleChoiceItems(languageOptions, selectedLanguageIndex, (d, index) -> {
                    String lang = index == 0 ? SettingsHelper.LANG_FRENCH : SettingsHelper.LANG_ENGLISH;
                    SettingsHelper.saveLanguage(this, lang);
                    applyLanguage(lang);
                    d.dismiss();
                }).show();
            }
        }).show();
    }

    private void applyTheme(String theme) {
        if (theme.equals(SettingsHelper.THEME_DARK)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    private void applyLanguage(String language) {
        SettingsHelper.applyLanguage(this, language);
        recreate();
    }

    private void applySavedSettings() {
        SettingsHelper.applyTheme(this);
        String language = SettingsHelper.getLanguage(this);
        SettingsHelper.applyLanguage(this, language);
    }
}
