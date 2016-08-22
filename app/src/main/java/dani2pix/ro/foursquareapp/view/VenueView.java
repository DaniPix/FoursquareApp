package dani2pix.ro.foursquareapp.view;

import java.util.List;

import dani2pix.ro.foursquareapp.model.Venue;

/**
 * Created by Domnica on 22.08.2016.
 */
public interface VenueView extends View {
    void showVenues(List<Venue> venues);
}
