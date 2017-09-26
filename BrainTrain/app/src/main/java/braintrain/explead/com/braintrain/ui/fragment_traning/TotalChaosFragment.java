package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.views.TimeCountingView;
import braintrain.explead.com.braintrain.views.total_chaos_views.FieldTotalChaosView;

/**
 * Created by develop on 25.09.2017.
 */

public class TotalChaosFragment extends Fragment implements FieldTotalChaosView.OnTotalChaosListener{

    private FieldTotalChaosView fieldView;
    private TimeCountingView countingView;

    private Chronometer mChronometer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total_chaos, container, false);

        mChronometer = (Chronometer) view.findViewById(R.id.chronometer);

        createField(view);
        createCounting(view);

        return view;
    }

    private void createField(View view) {
        fieldView = (FieldTotalChaosView) view.findViewById(R.id.fieldView);
        fieldView.setField(6, (int) App.getWidthScreen());
        fieldView.setListener(this);
    }

    private void createCounting(View view) {
        countingView = (TimeCountingView) view.findViewById(R.id.countingView);
        countingView.create((int) App.getWidthScreen(), new TimeCountingView.OnCountingListener() {
            @Override
            public void startCounting() {
                fieldView.setClickable(false);
            }

            @Override
            public void endCounting() {
                fieldView.setClickable(true);
                resetChronometer();
            }
        });
        countingView.startCounting();
    }

    @Override
    public void onWin() {
        stopChronometer();
    }

    public void stopChronometer() {
        mChronometer.stop();
    }

    public void resetChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }
}
