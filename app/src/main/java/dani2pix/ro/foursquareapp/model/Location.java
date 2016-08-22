package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Domnica on 22.08.2016.
 */
public class Location {
    @SerializedName("address")
    private String address;
    @SerializedName("distance")
    private String distance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
