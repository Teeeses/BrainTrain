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

import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.dialogs.DialogCompleted;
import braintrain.explead.com.braintrain.logic.counting_cells.FieldCountingCells;
import braintrain.explead.com.braintrain.ui.MainActivity;
import braintrain.explead.com.braintrain.utils.Utils;
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
    private TextView tvScore;
    private TextView tvBestScore;

    private int score = 0;
    private int trueAnswers = 0;
    private int mistakAnswers = 0;

    private CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_counting_cells, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);
        tvBestScore = (TextView) view.findViewById(R.id.tvBestScore);

        btnStart = (TextView) view.findViewById(R.id.btnStart);
        tvTime = (TextView) view.findViewById(R.id.tvTime);

        tvScore = (TextView) view.findViewById(R.id.tvScore);

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
        score = 0;
        trueAnswers = 0;
        mistakAnswers = 0;
        createField();
        createViews();
        createCountingDownTimer();
        super.openGameLayout();
    }

    @Override
    public void openMenuLayout() {
        super.openMenuLayout();
        try {
            timer.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int result = ((MainActivity)getActivity()).getCountingCellsResult();
        System.out.println("bbbbb " + result);
        tvBestScore.setText(String.format(Locale.ROOT,
                getResources().getString(R.string.best_result), result));
    }

    private void createField() {
        size = 5;
        field = new FieldCountingCells(size);
    }

    private void createCountingDownTimer() {
        timer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTime.setText(Utils.millsToString(millisUntilFinished));
            }

            public void onFinish() {
                ((MainActivity)getActivity()).setCountingCellsResult(score);

                tvTime.setText(Utils.millsToString(0));

                String text = String.format(Locale.ROOT.ROOT, getResources().getString(R.string.score), Integer.toString(score));
                new DialogCompleted(getContext(), text, new DialogCompleted.OnDialogCompletedListener() {
                    @Override
                    public void onMenu() {
                        getActivity().onBackPressed();
                    }

                    @Override
                    public void onAgain() {
                        openGameLayout();
                    }
                }).show();
            }
        }.start();

    }

    private void createViews() {
        tvScore.setText(String.format(Locale.ROOT, getResources().getString(R.string.score), Integer.toString(score)));
        fieldView.setField(field, (int) App.getWidthScreen());
        choiceForCountingCells.create(field.getChoices(), field.getCount(), new ChoiceForCountingCells.OnChoiceListener() {
            @Override
            public void ok() {
                ((MainActivity)getContext()).getSoundPool().play(1, 0.5f, 0.5f, 1, 0, 1f);
                createField();
                createViews();
                trueAnswers++;
                score = score + 100;
                tvScore.setText(String.format(Locale.ROOT, getResources().getString(R.string.score), Integer.toString(score)));
            }

            @Override
            public void error() {
                ((MainActivity)getContext()).getSoundPool().play(1, 0.5f, 0.5f, 1, 0, 1f);
                createField();
                createViews();
                mistakAnswers++;
                score = score - 100;
                if(score < 0) {
                    score = 0;
                }
                tvScore.setText(String.format(Locale.ROOT, getResources().getString(R.string.score), Integer.toString(score)));
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
