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

public class RepeatBunchFragment extends Fragment {

    private FieldRepeatBunchView fieldView;
    private TimeCountingView countingView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repeat_bunch, container, false);

        createField(view);
        createCounting(view);

        return view;
    }

    private void createField(View view) {
        fieldView = (FieldRepeatBunchView) view.findViewById(R.id.fieldView);
        fieldView.setField(6, (int) App.getWidthScreen());
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
            }
        });
        countingView.startCounting();
    }
}
