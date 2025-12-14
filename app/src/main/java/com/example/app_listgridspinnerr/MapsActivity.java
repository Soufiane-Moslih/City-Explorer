package com.example.app_listgridspinnerr;

import androidx.fragment.app.FragmentActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private MaterialButton btnMyLocation;
    private MaterialButton btnMapType;
    private Map<Marker, Integer> markerImageMap = new HashMap<>();
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private int currentMapType = GoogleMap.MAP_TYPE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingsHelper.applyTheme(this);
        SettingsHelper.applyLanguage(this, SettingsHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnMyLocation = findViewById(R.id.btnMyLocation);
        btnMapType = findViewById(R.id.btnMapType);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng ifrane = new LatLng(33.5333, -5.1167);
        LatLng rabat = new LatLng(34.0209, -6.8416);
        LatLng casablanca = new LatLng(33.5731, -7.5898);
        LatLng tanger = new LatLng(35.7595, -5.8330);
        LatLng agadir = new LatLng(30.4278, -9.5981);
        LatLng marrakech = new LatLng(31.6295, -7.9811);
        Marker ifraneMarker = mMap.addMarker(new MarkerOptions().position(ifrane).title("Ifrane").icon(createCustomMarker(R.drawable.lionifrane)));
        markerImageMap.put(ifraneMarker, R.drawable.lionifrane);
        Marker rabatMarker = mMap.addMarker(new MarkerOptions().position(rabat).title("Rabat").icon(createCustomMarker(R.drawable.rabathassan)));
        markerImageMap.put(rabatMarker, R.drawable.rabathassan);
        Marker casablancaMarker = mMap.addMarker(new MarkerOptions().position(casablanca).title("Casablanca").icon(createCustomMarker(R.drawable.mosquehassan2)));
        markerImageMap.put(casablancaMarker, R.drawable.mosquehassan2);
        Marker tangerMarker = mMap.addMarker(new MarkerOptions().position(tanger).title("Tanger").icon(createCustomMarker(R.drawable.kasbahtanger)));
        markerImageMap.put(tangerMarker, R.drawable.kasbahtanger);
        Marker agadirMarker = mMap.addMarker(new MarkerOptions().position(agadir).title("Agadir").icon(createCustomMarker(R.drawable.agadiroufella)));
        markerImageMap.put(agadirMarker, R.drawable.agadiroufella);
        Marker marrakechMarker = mMap.addMarker(new MarkerOptions().position(marrakech).title("Marrakech").icon(createCustomMarker(R.drawable.kechjamaelfnaa)));
        markerImageMap.put(marrakechMarker, R.drawable.kechjamaelfnaa);
        mMap.setOnMarkerClickListener(this);
        LatLng centerMorocco = new LatLng(33.5731, -6.8416);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerMorocco, 6.5f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        setupMyLocationButton();
        setupMapTypeButton();
        requestLocationPermission();
    }

    private void setupMyLocationButton() {
        btnMyLocation.setOnClickListener(v -> {
            if (checkLocationPermission()) {
                getCurrentLocation();
            } else {
                requestLocationPermission();
            }
        });
    }

    private void setupMapTypeButton() {
        btnMapType.setOnClickListener(v -> {
            if (mMap != null) {
                switch (currentMapType) {
                    case GoogleMap.MAP_TYPE_NORMAL:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        currentMapType = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case GoogleMap.MAP_TYPE_SATELLITE:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        currentMapType = GoogleMap.MAP_TYPE_TERRAIN;
                        break;
                    case GoogleMap.MAP_TYPE_TERRAIN:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        currentMapType = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                    case GoogleMap.MAP_TYPE_HYBRID:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        currentMapType = GoogleMap.MAP_TYPE_NORMAL;
                        break;
                    default:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        currentMapType = GoogleMap.MAP_TYPE_NORMAL;
                        break;
                }
            }
        });
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
               ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        if (!checkLocationPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            enableMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            } else {
                Toast.makeText(this, getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void enableMyLocation() {
        if (mMap != null && checkLocationPermission()) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    private void getCurrentLocation() {
        if (!checkLocationPermission()) {
            requestLocationPermission();
            return;
        }
        Task<Location> locationTask = fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null && mMap != null) {
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
                    Toast.makeText(MapsActivity.this, getString(R.string.location_found), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MapsActivity.this, getString(R.string.location_not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private BitmapDescriptor createCustomMarker(int resourceId) {
        int markerWidth = 120;
        int markerHeight = 120;
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        if (originalBitmap == null) {
            return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        }
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, markerWidth, markerHeight, false);
        Bitmap circularBitmap = getCircularBitmap(resizedBitmap);
        return BitmapDescriptorFactory.fromBitmap(circularBitmap);
    }

    private Bitmap getCircularBitmap(Bitmap bitmap) {
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, size, size);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xFF000000);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFFFFFFFF);
        paint.setStrokeWidth(4);
        canvas.drawOval(rectF, paint);
        return output;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer imageResId = markerImageMap.get(marker);
        if (imageResId != null) {
            showImageDialog(marker.getTitle(), imageResId);
            return true;
        }
        return false;
    }

    private void showImageDialog(String cityName, int imageResId) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_city_image, null);
        ImageView imageView = dialogView.findViewById(R.id.dialogImage);
        ViewPager2 imageSlider = dialogView.findViewById(R.id.dialogImageSlider);
        LinearLayout sliderContainer = dialogView.findViewById(R.id.sliderContainer);
        LinearLayout dotsContainer = dialogView.findViewById(R.id.dialogDotsContainer);
        TextView cityNameView = dialogView.findViewById(R.id.dialogCityName);
        cityNameView.setText(cityName);
        Dialog dialog = new MaterialAlertDialogBuilder(this).setView(dialogView).create();
        String normalizedCityName = cityName.toLowerCase().replace(" ", "");
        if (normalizedCityName.equals("casablanca") || normalizedCityName.equals("casa") ||
            normalizedCityName.equals("marrakech") || normalizedCityName.equals("merrakech") ||
            normalizedCityName.equals("tanger") || normalizedCityName.equals("ifrane") ||
            normalizedCityName.equals("agadir") || normalizedCityName.equals("rabat")) {
            imageView.setVisibility(View.GONE);
            sliderContainer.setVisibility(View.VISIBLE);
            setupImageSlider(imageSlider, dotsContainer, dialog, normalizedCityName);
        } else {
            imageView.setVisibility(View.VISIBLE);
            sliderContainer.setVisibility(View.GONE);
            imageView.setImageResource(imageResId);
            imageView.setOnClickListener(v -> dialog.dismiss());
        }
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
    }

    private void setupImageSlider(ViewPager2 viewPager, LinearLayout dotsContainer, Dialog dialog, String cityName) {
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
        viewPager.setOnClickListener(v -> dialog.dismiss());
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
