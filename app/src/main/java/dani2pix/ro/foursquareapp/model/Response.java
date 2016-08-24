package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Domnica on 22.08.2016.
 */
public class Response {
    @SerializedName("response")
    public SearchResponse response;



    public class SearchResponse {

        @SerializedName("venues")
        List<Venue> venues;


        public List<Venue> getVenues() {
            return venues;
        }

        public void setVenues(List<Venue> venues) {
            this.venues = venues;
        }
    }
}
