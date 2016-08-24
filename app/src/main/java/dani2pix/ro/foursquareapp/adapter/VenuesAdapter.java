package dani2pix.ro.foursquareapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dani2pix.ro.foursquareapp.R;
import dani2pix.ro.foursquareapp.model.Venue;

/**
 * Created by Domnica on 23.08.2016.
 */
public class VenuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Venue> mVenues;
    private int mLayout;
    private LayoutInflater mInflater;

    public VenuesAdapter(Context context, List<Venue> venues, int layout) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mVenues = venues;
        this.mLayout = layout;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayout, parent, false);
        return new VenuesHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VenuesHolder) {
            VenuesHolder venuesHolder = (VenuesHolder) holder;
            Venue venue = mVenues.get(position);
            venuesHolder.name.setText(venue.getName());
            venuesHolder.location.setText(venue.getLocation().getAddress());
            Picasso.with(mContext)
                    .load(venue.getPhoto())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .into(venuesHolder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return mVenues.size();
    }


    class VenuesHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.venueName)
        TextView name;
        @BindView(R.id.venueLocation)
        TextView location;
        @BindView(R.id.venuePhoto)
        ImageView photo;

        public VenuesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
