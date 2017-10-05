package braintrain.explead.com.braintrain.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.ui.BaseActivity;
import braintrain.explead.com.braintrain.ui.fragment_traning.CountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.SearchingElementFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.TotalChaosFragment;
import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 27.09.2017.
 */

public class GameActivity extends BaseActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sPref = getSharedPreferences(Utils.APP_PREFERENCES, MODE_PRIVATE);

        int mode = getIntent().getExtras().getInt("mode");
        int level = getIntent().getExtras().getInt("level");

        if(mode == 1) {
            openTotalChaosFragment(level);
        } else if(mode == 2) {
            openRepeatBunchFragment();
        } else if(mode == 3) {
            openSearchingElementFragment();
        } else if(mode == 4) {
            openCountingCellsFragment();
        }
    }


    public void openTotalChaosFragment(int size) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new TotalChaosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("size", size);
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void openRepeatBunchFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new RepeatBunchFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void openSearchingElementFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new SearchingElementFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void openCountingCellsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new CountingCellsFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
