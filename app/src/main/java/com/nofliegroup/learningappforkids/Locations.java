package com.nofliegroup.learningappforkids;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Locations extends Fragment {
    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;
    private LatLng latLng;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            locationListener = new LocationListener() {

                @Override
                public void onLocationChanged(@NonNull Location location) {
                    try {
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());
                         mMap.addMarker(new MarkerOptions().position(latLng).title("My position"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                    catch (SecurityException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onLocationChanged(@NonNull List<Location> locations) {
                    LocationListener.super.onLocationChanged(locations);
                }

                @Override
                public void onFlushComplete(int requestCode) {
                    LocationListener.super.onFlushComplete(requestCode);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    LocationListener.super.onStatusChanged(provider, status, extras);
                }

                @Override
                public void onProviderEnabled(@NonNull String provider) {
                    LocationListener.super.onProviderEnabled(provider);
                }

                @Override
                public void onProviderDisabled(@NonNull String provider) {
                    LocationListener.super.onProviderDisabled(provider);
                }
            };
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                locationManager = (LocationManager) getSystemService(getContext().LOCATION_SERVICE);
            }
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } catch (SecurityException e) {
                e.printStackTrace();

            }
        } // end of onMapReady
    }; // end of callback

    private Object getSystemService(String locationService) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getContext().getSystemService(locationService);
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_locations, container, false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);




        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}