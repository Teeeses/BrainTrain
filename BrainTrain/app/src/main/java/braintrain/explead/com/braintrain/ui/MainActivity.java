package braintrain.explead.com.braintrain.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import braintrain.explead.com.braintrain.GameActivity;
import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.adapters.MyPagerAdapter;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.TotalChaosFragment;
import braintrain.explead.com.braintrain.utils.Utils;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class MainActivity extends BaseActivity {

    private static Activity activity;
    private static Fragment fragment;

    private ScrollerViewPager viewPager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences(Utils.APP_PREFERENCES, MODE_PRIVATE);
        activity = this;

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        App.setWidthScreen(displaymetrics.widthPixels);
        App.setHeightScreen(displaymetrics.heightPixels);

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

    }

    public void openGameActivity(int mode, int level) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("mode", mode);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
