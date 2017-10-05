package braintrain.explead.com.braintrain.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import braintrain.explead.com.braintrain.ui.fragments_menu.MenuCountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragments_menu.MenuSearchingElementFragment;
import braintrain.explead.com.braintrain.ui.fragments_menu.MenuTotalChaosFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.CountingCellsFragment;
import braintrain.explead.com.braintrain.ui.fragment_traning.RepeatBunchFragment;

/**
 * Created by develop on 13.12.2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = {
            new MenuTotalChaosFragment(), new RepeatBunchFragment(), new MenuSearchingElementFragment(), new MenuCountingCellsFragment()
    };

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    public String getPageTitle(int position) {
        return Integer.toString(position + 1);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}