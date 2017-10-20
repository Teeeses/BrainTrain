package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.logic.counting_cells.FieldCountingCells;
import braintrain.explead.com.braintrain.views.ChoiceForCountingCells;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;


public class CountingCellsFragment extends GameBaseFragment {

    private FieldCountingCellsView fieldView;
    private FieldCountingCells field;

    private ChoiceForCountingCells choiceForCountingCells;
    private View view;
    private int size;

    private TextView btnStart;
    private TextView tvTime;

    private int score = 0;
    private int trueAnswers = 0;
    private int mistakAnswers = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_counting_cells, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);

        btnStart = (TextView) view.findViewById(R.id.btnStart);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        btnStart.setOnClickListener(startGameClick);

        choiceForCountingCells = (ChoiceForCountingCells) view.findViewById(R.id.choiceForCountingCells);
        fieldView = (FieldCountingCellsView) view.findViewById(R.id.fieldView);

        choiceForCountingCells.post(new Runnable() {
            @Override
            public void run() {
                openMenuLayout();
            }
        });

        return view;
    }

    @Override
    public void openGameLayout() {
        createField();
        createViews();
        super.openGameLayout();
    }

    @Override
    public void openMenuLayout() {
        super.openMenuLayout();
    }

    private void createField() {
        size = 5;
        field = new FieldCountingCells(size);
    }

    private void createCountingDownTimer() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTime.setText();
            }

            public void onFinish() {
                tvTime.setText("done!");
            }
        }.start();
    }

    private void createViews() {
        fieldView.setField(field, (int) App.getWidthScreen());
        choiceForCountingCells.create(field.getChoices(), field.getCount(), new ChoiceForCountingCells.OnChoiceListener() {
            @Override
            public void ok() {
                createField();
                createViews();

                trueAnswers++;
            }

            @Override
            public void error() {
                mistakAnswers++;
            }
        });

        View findCell = view.findViewById(R.id.findCell);
        GradientDrawable background = (GradientDrawable ) findCell.getBackground();
        background.setColor(FieldCountingCellsView.colors[field.getIndex()]);
    }

    private View.OnClickListener startGameClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGameLayout();
        }
    };

}
