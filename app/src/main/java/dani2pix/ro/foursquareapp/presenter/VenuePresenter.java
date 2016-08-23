package dani2pix.ro.foursquareapp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dani2pix.ro.foursquareapp.FoursquareApplication;
import dani2pix.ro.foursquareapp.constants.RestConstants;
import dani2pix.ro.foursquareapp.model.FoursquareService;
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
        if (query.isEmpty() || location.isEmpty()) {
            return;
        }


        if (subscription != null) {
            subscription.unsubscribe();
        }


        Map<String, String> params = new HashMap<>();
        params.put("client_id", RestConstants.CLIENT_ID);
        params.put("client_secret", RestConstants.CLIENT_SECRET);
        params.put("v", "20130815");
        params.put("ll", location);
        params.put("query", query);

        FoursquareApplication application = new FoursquareApplication();
        FoursquareService service = application.getFourSquareService();
        subscription = service.fetchVenues(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.getSubscribeScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        venueView.showVenues(venues);
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

}
