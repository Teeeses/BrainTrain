package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.views.TimeCountingView;
import braintrain.explead.com.braintrain.views.repeat_bunch.FieldRepeatBunchView;


public class RepeatBunchFragment extends GameBaseFragment implements FieldRepeatBunchView.OnRepeatBunchListener {

    private FieldRepeatBunchView fieldView;
    private TimeCountingView countingView;

    private int count = 4;

    private View view;
    private Button btnStart;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repeat_bunch, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(startGameClick);

        fieldView = (FieldRepeatBunchView) view.findViewById(R.id.fieldView);
        countingView = (TimeCountingView) view.findViewById(R.id.countingView);

        openMenuLayout();

        return view;
    }

    @Override
    public void openGameLayout() {
        super.openGameLayout();
        createField();
        createCounting(false);
    }

    @Override
    public void openMenuLayout() {
        super.openMenuLayout();
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

    private View.OnClickListener startGameClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGameLayout();
        }
    };
}
