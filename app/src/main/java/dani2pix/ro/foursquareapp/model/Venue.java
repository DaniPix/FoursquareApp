package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Domnica on 22.08.2016.
 */
public class Venue {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private Location location;

    private String photo;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
