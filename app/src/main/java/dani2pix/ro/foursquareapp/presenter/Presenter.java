package dani2pix.ro.foursquareapp.presenter;

/**
 * Created by Domnica on 22.08.2016.
 */
public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
