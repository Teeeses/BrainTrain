package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.views.TimeCountingView;
import braintrain.explead.com.braintrain.views.repeat_bunch.FieldRepeatBunchView;

/**
 * Created by develop on 26.09.2017.
 */

public class RepeatBunchFragment extends Fragment implements FieldRepeatBunchView.OnRepeatBunchListener {

    private FieldRepeatBunchView fieldView;
    private TimeCountingView countingView;

    private int count = 4;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repeat_bunch, container, false);

        fieldView = (FieldRepeatBunchView) view.findViewById(R.id.fieldView);
        countingView = (TimeCountingView) view.findViewById(R.id.countingView);
        createField();
        createCounting(false);

        return view;
    }

    private void createField() {
        fieldView.setField(count, (int) App.getWidthScreen());
        fieldView.setListener(this);
    }

    private void createCounting(final boolean create) {
        countingView.create((int) App.getWidthScreen(), new TimeCountingView.OnCountingListener() {
            @Override
            public void startCounting() {
                fieldView.setClickable(false);
            }

            @Override
            public void endCounting() {
                fieldView.setClickable(true);
                if(create) {
                    createField();
                }
                fieldView.startGame();
            }
        });
        countingView.startCounting();
    }

    @Override
    public void onWin() {
        count++;
        createCounting(true);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLight() {
        fieldView.setClickable(false);
    }

    @Override
    public void onExtinguish() {
        fieldView.setClickable(true);
    }
}
