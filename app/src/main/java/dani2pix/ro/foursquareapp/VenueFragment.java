package dani2pix.ro.foursquareapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import dani2pix.ro.foursquareapp.model.Location;
import dani2pix.ro.foursquareapp.model.Venue;
import dani2pix.ro.foursquareapp.presenter.VenuePresenter;
import dani2pix.ro.foursquareapp.view.VenueView;

/**
 * Created by Domnica on 05.09.2016.
 */
public class VenueFragment extends Fragment implements VenueView {

    private VenuePresenter mVenuePresenter;
    private Venue mVenue;

    @BindView(R.id.venueName)
    TextView mVenueName;

    @BindView(R.id.venueLocation)
    TextView mVenueLocation;

    @BindView(R.id.venuePhoto)
    ImageView mVenuePhoto;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVenuePresenter = new VenuePresenter(getActivity());
        mVenuePresenter.attachView(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mVenuePresenter.buildVenue(bundle.getString("name"), (Location) bundle.getSerializable("location"), bundle.getString("photo"));
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVenuePresenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venue_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        mVenueName.setText(mVenue.getName());
        mVenueLocation.setText(mVenue.getLocation().getAddress());
        Picasso.with(getActivity())
                .load(mVenue.getPhoto())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(mVenuePhoto);
        return view;
    }

    @Override
    public void showVenue(Venue venue) {
        mVenue = venue;
    }
}
