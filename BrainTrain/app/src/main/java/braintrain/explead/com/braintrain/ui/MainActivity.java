package braintrain.explead.com.braintrain.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.bubbletab.BubbleTab;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.adapters.MyPagerAdapter;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.ui.fragment_traning.CountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.GameBaseFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.TotalChaosFragment;
import braintrain.explead.com.braintrain.utils.Utils;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class MainActivity extends BaseActivity {

    private ScrollerViewPager viewPager;
    private MyPagerAdapter adapter;
    private BubbleTab bubbleTab;

    private GameBaseFragment[] fragments = {
            new TotalChaosFragment(), new RepeatBunchFragment(), new CountingCellsFragment()
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

        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        bubbleTab.setupWithViewPager(viewPager);
    }

    public void hideViewPager() {
        bubbleTab.setVisibility(View.GONE);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
    }

    public void showViewPager() {
        bubbleTab.setVisibility(View.VISIBLE);
        viewPager.setOnTouchListener(null);
    }

    @Override
    public void onBackPressed() {
        int currentPosition = viewPager.getCurrentItem();
        GameBaseFragment fragment = fragments[currentPosition];
        if(fragment.getStatus() == GameBaseFragment.GAME) {
            fragment.openMenuLayout();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.do_you_want_go_out));
            builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
