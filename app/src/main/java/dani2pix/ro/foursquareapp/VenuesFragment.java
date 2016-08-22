package dani2pix.ro.foursquareapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import dani2pix.ro.foursquareapp.model.Venue;
import dani2pix.ro.foursquareapp.presenter.VenuePresenter;
import dani2pix.ro.foursquareapp.view.VenueView;



/**
 * Created by Domnica on 22.08.2016.
 */
public class VenuesFragment extends Fragment implements VenueView{

    private VenuePresenter venuePresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venuePresenter = new VenuePresenter();
        venuePresenter.attachView(this);

        venuePresenter.loadVenues("");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        venuePresenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void showVenues(List<Venue> venues) {
       if(venues != null && !venues.isEmpty()){
           Toast.makeText(getActivity().getApplicationContext(), "AYYLMAO", Toast.LENGTH_SHORT).show();
       }
    }


}
