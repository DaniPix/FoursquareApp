package dani2pix.ro.foursquareapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoursquareActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foursquare);
        ButterKnife.bind(this);
        initializeToolbar();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.findFragmentByTag(VenuesFragment.class.getCanonicalName()) == null) {
                Fragment fragment = new VenuesFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.searchContainer, fragment, VenuesFragment.class.getCanonicalName())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    private void initializeToolbar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setSubtitle(R.string.app_subtitle);
        }
    }
}
