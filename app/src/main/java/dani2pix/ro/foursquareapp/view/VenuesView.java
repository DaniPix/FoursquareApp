package dani2pix.ro.foursquareapp.view;

import android.location.Location;

import java.util.List;

import dani2pix.ro.foursquareapp.model.Venue;

/**
 * Created by Domnica on 22.08.2016.
 */
public interface VenuesView extends View {
    void showVenues(List<Venue> venues);

    void onLocationUpdated(Location location);

    void onLocationUnavailable();
}
