package dani2pix.ro.foursquareapp.model;

import java.util.List;
import java.util.Map;

import dani2pix.ro.foursquareapp.constants.RestConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by Domnica on 22.08.2016.
 */
public interface FoursquareService {
    @GET(RestConstants.SEARCH_VENUES)
    Observable<Response> fetchVenues(@QueryMap Map<String, String> queryParams);


    class Factory {
        public static FoursquareService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestConstants.BASE_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FoursquareService.class);
        }
    }
}
