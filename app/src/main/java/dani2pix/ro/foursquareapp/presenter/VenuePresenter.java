package dani2pix.ro.foursquareapp.presenter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dani2pix.ro.foursquareapp.FoursquareApplication;
import dani2pix.ro.foursquareapp.constants.RestConstants;
import dani2pix.ro.foursquareapp.model.FoursquareService;
import dani2pix.ro.foursquareapp.model.PhotoItem;
import dani2pix.ro.foursquareapp.model.PhotosResponse;
import dani2pix.ro.foursquareapp.model.Response;
import dani2pix.ro.foursquareapp.model.Venue;
import dani2pix.ro.foursquareapp.view.VenueView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Domnica on 22.08.2016.
 */
public class VenuePresenter implements Presenter<VenueView> {

    private VenueView venueView;
    private Subscription subscription;
    private List<Venue> venues;
    private List<Venue> updatedVenues;

    @Override
    public void attachView(VenueView view) {
        this.venueView = view;

    }

    @Override
    public void detachView() {
        this.venueView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public void loadVenues(String query, String location) {
        if (query == null || location == null || query.isEmpty() || location.isEmpty()) {
            return;
        }


        if (subscription != null) {
            subscription.unsubscribe();
        }


        Map<String, String> params = new HashMap<>();
        params.put("client_id", RestConstants.CLIENT_ID);
        params.put("client_secret", RestConstants.CLIENT_SECRET);
        params.put("v", "20130815");
        params.put("ll", "40.7,-74");
        params.put("query", query);

        FoursquareApplication application = new FoursquareApplication();
        final FoursquareService service = application.getFourSquareService();
        subscription = service.fetchVenues(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.getSubscribeScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        fetchVenuePhoto();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response response) {
                        VenuePresenter.this.venues = response.response.getVenues();
                    }
                });
    }


    private void fetchVenuePhoto() {
        final Map<String, String> params = new HashMap<>();
        params.put("client_id", RestConstants.CLIENT_ID);
        params.put("client_secret", RestConstants.CLIENT_SECRET);
        params.put("v", "20130815");

        FoursquareApplication application = new FoursquareApplication();
        final FoursquareService service = application.getFourSquareService();
        updatedVenues = new ArrayList<>();
        for (final Venue venue : venues) {
            subscription = service.fetchVenuePhotos(venue.getId(), params)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.getSubscribeScheduler())
                    .subscribe(new Subscriber<PhotosResponse>() {
                        @Override
                        public void onCompleted() {
                            updatedVenues.add(venue);
                            if (updatedVenues.size() == venues.size()) {
                                venueView.showVenues(updatedVenues);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PhotosResponse photosResponse) {
                            if (!photosResponse.response.getPhotos().getPhotoItems().isEmpty()) {
                                PhotoItem item = photosResponse.response.getPhotos().getPhotoItems().get(0);
                                venue.setPhoto(item.getPrefix() + item.getWidth() + "x" + item.getHeight() + item.getSuffix());
                            }
                        }
                    });
        }
    }

}
