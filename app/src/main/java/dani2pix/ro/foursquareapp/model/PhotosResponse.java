package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Domnica on 24.08.2016.
 */
public class PhotosResponse {
    @SerializedName("response")
    public Photos response;

    public class Photos {
        @SerializedName("photos")
        VenuePhotos photos;

        public VenuePhotos getPhotos() {
            return photos;
        }

        public void setPhotos(VenuePhotos photos) {
            this.photos = photos;
        }
    }
}
