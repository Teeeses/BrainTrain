package braintrain.explead.com.braintrain.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.TotalChaosFragment;
import braintrain.explead.com.braintrain.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static Activity activity;
    private static Fragment fragment;

    private static SharedPreferences sPref;
    private static Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        res = this.getResources();
        sPref = getSharedPreferences(Utils.APP_PREFERENCES, MODE_PRIVATE);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        App.setWidthScreen(displaymetrics.widthPixels);
        App.setHeightScreen(displaymetrics.heightPixels);

        openSelectModeFragment();
    }

    public void openSelectModeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new SelectModeFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void openTotalChaosFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new TotalChaosFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openRepeatBunchFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new RepeatBunchFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
