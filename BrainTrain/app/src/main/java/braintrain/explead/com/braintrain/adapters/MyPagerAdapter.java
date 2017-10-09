package braintrain.explead.com.braintrain.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public MyPagerAdapter(FragmentManager fragmentManager, Fragment[] fragments) {
        super(fragmentManager);
        this.fragments = fragments;
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