package dani2pix.ro.foursquareapp;

import android.app.Application;
import android.content.Context;

import dani2pix.ro.foursquareapp.model.FoursquareService;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Domnica on 22.08.2016.
 */
public class FoursquareApplication {

    private FoursquareService mFoursquareService;
    private Scheduler mSubscribeScheduler;

    public FoursquareService getFourSquareService() {
        if (mFoursquareService == null) {
            return FoursquareService.Factory.create();
        }
        return mFoursquareService;
    }

    public Scheduler getSubscribeScheduler() {
        if (mSubscribeScheduler == null) {
            mSubscribeScheduler = Schedulers.io();
        }
        return mSubscribeScheduler;
    }
}
