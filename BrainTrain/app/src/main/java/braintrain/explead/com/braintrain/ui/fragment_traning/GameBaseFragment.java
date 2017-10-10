package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.ui.MainActivity;

/**
 * Created by develop on 09.10.2017.
 */

public class GameBaseFragment extends Fragment{

    protected MainActivity activity;
    protected RelativeLayout menuLayout;
    protected RelativeLayout gameLayout;

    public static int MENU = 0, GAME = 1;
    protected int status = MENU;

    public void openMenuLayout() {
        status = MENU;
        menuLayout.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.GONE);
        activity.showViewPager();
    }

    public void openGameLayout() {
        status = GAME;
        menuLayout.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
        activity.hideViewPager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            activity = (MainActivity) context;
        }
    }

    public int getStatus() {
        return status;
    }
}
