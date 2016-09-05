package dani2pix.ro.foursquareapp.presenter;

import android.content.Context;

import dani2pix.ro.foursquareapp.model.Location;
import dani2pix.ro.foursquareapp.model.Venue;
import dani2pix.ro.foursquareapp.view.VenueView;

/**
 * Created by Domnica on 05.09.2016.
 */
public class VenuePresenter implements Presenter<VenueView> {

    private static final String TAG = "VenuePresenter.class";
    private VenueView mVenueView;
    private Context mContext;

    public VenuePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(VenueView view) {
        this.mVenueView = view;
    }

    @Override
    public void detachView() {
        this.mVenueView = null;
    }

    public void buildVenue(String name, Location location, String photo) {
        Venue venue = new Venue();
        venue.setName(name);
        venue.setLocation(location);
        venue.setPhoto(photo);
        mVenueView.showVenue(venue);
    }

}

