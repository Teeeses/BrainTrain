package braintrain.explead.com.braintrain.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.github.florent37.bubbletab.BubbleTab;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.adapters.MyPagerAdapter;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.ui.fragment_traning.CountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;
import braintrain.explead.com.braintrain.ui.fragments_menu.MenuCountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragments_menu.MenuTotalChaosFragment;
import braintrain.explead.com.braintrain.utils.Utils;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class MainActivity extends BaseActivity {

    private ScrollerViewPager viewPager;
    private MyPagerAdapter adapter;
    private BubbleTab bubbleTab;

    private Fragment[] fragments = {
            new MenuTotalChaosFragment(), new RepeatBunchFragment(), new CountingCellsFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences(Utils.APP_PREFERENCES, MODE_PRIVATE);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        App.setWidthScreen(displaymetrics.widthPixels);
        App.setHeightScreen(displaymetrics.heightPixels);

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);


        bubbleTab = (BubbleTab) findViewById(R.id.bubbleTab);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragments));
        bubbleTab.setupWithViewPager(viewPager);
    }

    public void hideViewPager() {
        bubbleTab.setVisibility(View.GONE);
        viewPager.setEnabled(false);
    }

    public void showViewPager() {
        bubbleTab.setVisibility(View.VISIBLE);
        viewPager.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        int currentPosition = viewPager.getCurrentItem();
        Fragment fragment = fragments[currentPosition];
        if(fragment instanceof CountingCellsFragment) {
            ((CountingCellsFragment)fragment).openMenuLayout();
        }
    }

    public void openGameActivity(int mode, int level) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("mode", mode);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
