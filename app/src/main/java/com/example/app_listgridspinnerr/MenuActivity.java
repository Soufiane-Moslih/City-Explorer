package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(navigationView));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.nav_liste) {
            openScreen(ListActivity.class);
        } else if (itemId == R.id.nav_spinner) {
            openScreen(SpinnerActivity.class);
        } else if (itemId == R.id.nav_grid) {
            openScreen(GridActivity.class);
        } else if (itemId == R.id.nav_imageliste) {
            openScreen(Imagelist_Act.class);
        } else if (itemId == R.id.nav_map) {
            openScreen(MapsActivity.class);
        } else if (itemId == R.id.nav_photo_gallery) {
            openScreen(GalleryActivity.class);
        } else if (itemId == R.id.nav_statistics) {
            showStatistics();
        } else if (itemId == R.id.nav_contact_us) {
            showContactUs();
        } else if (itemId == R.id.nav_quit) {
            quitApplication();
        }
        drawerLayout.closeDrawer(findViewById(R.id.nav_view));
        return true;
    }

    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    private void showStatistics() {
        String message = getString(R.string.statistics_description) + "\n\n" + getString(R.string.total_cities) + "\n" + getString(R.string.total_population);
        new androidx.appcompat.app.AlertDialog.Builder(this).setTitle(getString(R.string.statistics)).setMessage(message).setPositiveButton(getString(R.string.ok), null).show();
    }

    private void showContactUs() {
        new androidx.appcompat.app.AlertDialog.Builder(this).setTitle(getString(R.string.contact_us)).setMessage("Email: contact@villesmaroc.ma\nTéléphone: +212 XXX XXX XXX\nAdresse: Maroc").setPositiveButton("OK", null).show();
    }

    private void quitApplication() {
        new androidx.appcompat.app.AlertDialog.Builder(this).setTitle(getString(R.string.quit)).setMessage("Êtes-vous sûr de vouloir quitter l'application ?").setPositiveButton("Oui", (dialog, which) -> {
            finishAffinity();
            System.exit(0);
        }).setNegativeButton("Non", null).show();
    }
}
