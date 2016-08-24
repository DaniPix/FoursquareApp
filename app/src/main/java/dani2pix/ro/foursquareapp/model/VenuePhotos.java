package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Domnica on 24.08.2016.
 */
public class VenuePhotos {
    @SerializedName("items")
    List<PhotoItem> photoItems;

    public List<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
    }
}
