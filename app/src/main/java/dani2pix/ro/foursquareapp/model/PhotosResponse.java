package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Domnica on 24.08.2016.
 */
public class PhotosResponse {
    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
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
