package dani2pix.ro.foursquareapp.listeners;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;

import java.lang.ref.WeakReference;

import dani2pix.ro.foursquareapp.view.VenueView;

/**
 * Created by Domnica on 23.08.2016.
 */
public class LocationListener implements android.location.LocationListener {



    private static final long LOCATION_REFRESH_DISTANCE = 1000; // meters
    private static final long LOCATION_REFRESH_TIME = 10000; // milliseconds


    private WeakReference<VenueView> mView;

    public LocationListener(Activity activity, VenueView view) {
        this.mView = new WeakReference<>(view);
        setUpLocation(activity);
    }

    private void setUpLocation(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkAvailable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkAvailable) {
            if (mView != null && mView.get() != null) {
                mView.get().onLocationUnavailable();
            }
        } else {
            onLocationChanged(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && mView != null && mView.get() != null) {
            mView.get().onLocationUpdated(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // Do nothing
    }

    @Override
    public void onProviderEnabled(String s) {
        // Do nothing
    }

    @Override
    public void onProviderDisabled(String s) {
        // Do nothing
    }


}
