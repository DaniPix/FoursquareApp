package dani2pix.ro.foursquareapp;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dani2pix.ro.foursquareapp.adapter.VenuesAdapter;
import dani2pix.ro.foursquareapp.listeners.LocationListener;
import dani2pix.ro.foursquareapp.listeners.VenuesClickListener;
import dani2pix.ro.foursquareapp.model.Venue;
import dani2pix.ro.foursquareapp.presenter.VenuesPresenter;
import dani2pix.ro.foursquareapp.view.VenuesView;


/**
 * Created by Domnica on 22.08.2016.
 */
public class VenuesFragment extends Fragment implements VenuesView {

    private VenuesPresenter venuesPresenter;

    @BindView(R.id.venuesList)
    RecyclerView mVenuesList;

    List<Venue> mVenues;

    private String mCurrentLocation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venuesPresenter = new VenuesPresenter(getActivity());
        venuesPresenter.attachView(this);
        setHasOptionsMenu(true);
        new LocationListener(getActivity(), this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        venuesPresenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venues_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        if (mVenues != null) {
            mVenuesList.setAdapter(new VenuesAdapter(getContext(), mVenues, R.layout.venues_item_layout));
            mVenuesList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        mVenuesList.addOnItemTouchListener(new VenuesClickListener(getContext(), mVenuesList, new VenuesClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentManager manager = getFragmentManager();
                Fragment fragment = new VenueFragment();
                Bundle bundle = new Bundle();
                Venue venue = mVenues.get(position);
                bundle.putString("name", venue.getName());
                bundle.putSerializable("location", venue.getLocation());
                bundle.putString("photo", venue.getPhoto());
                fragment.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.searchContainer, fragment, VenueFragment.class.getCanonicalName())
                        .commit();
            }
        }));
        return view;
    }

    @Override
    public void showVenues(List<Venue> venues) {
        if (venues != null && !venues.isEmpty()) {
            mVenues = venues;
            mVenuesList.setAdapter(new VenuesAdapter(getContext(), venues, R.layout.venues_item_layout));
            mVenuesList.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            Toast.makeText(getContext(), "Nothing around you...where the fuck are you?", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationUpdated(Location location) {
        mCurrentLocation = location.getLatitude() + ", " + location.getLongitude();
    }

    @Override
    public void onLocationUnavailable() {
        Toast.makeText(getContext(), "We have no clue where you are...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                venuesPresenter.loadVenues(query, mCurrentLocation);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}
