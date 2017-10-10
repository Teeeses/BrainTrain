package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.beans.ButtonLevel;
import braintrain.explead.com.braintrain.ui.BaseActivity;
import braintrain.explead.com.braintrain.ui.MainActivity;
import braintrain.explead.com.braintrain.utils.Utils;
import braintrain.explead.com.braintrain.views.TimeCountingView;
import braintrain.explead.com.braintrain.views.total_chaos_views.FieldTotalChaosView;


public class TotalChaosFragment extends GameBaseFragment implements FieldTotalChaosView.OnTotalChaosListener{

    //game
    private FieldTotalChaosView fieldView;
    private TimeCountingView countingView;
    private Chronometer mChronometer;

    //menu
    private ArrayList<ButtonLevel> levels;
    private TextView tvClosed;
    private TextView tvBestTime;
    private ImageView imageClosed;
    private Button btnStart;
    private Drawable colorActive;
    private Drawable colorPassive;
    private View view;

    private int size = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_total_chaos, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);

        mChronometer = (Chronometer) view.findViewById(R.id.chronometer);
        fieldView = (FieldTotalChaosView) view.findViewById(R.id.fieldView);
        countingView = (TimeCountingView) view.findViewById(R.id.countingView);


        colorActive = getResources().getDrawable(R.drawable.bg_round_green_btn);
        colorPassive = getResources().getDrawable(R.drawable.bg_round_white_btn);

        imageClosed = (ImageView) view.findViewById(R.id.imageClosed);
        tvBestTime = (TextView) view.findViewById(R.id.tvBestTime);
        tvClosed = (TextView) view.findViewById(R.id.textClosed);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameLayout();
            }
        });

        openMenuLayout();

        return view;
    }

    @Override
    public void openGameLayout() {
        super.openGameLayout();
        createField();
        createCounting();
    }

    @Override
    public void openMenuLayout() {
        super.openMenuLayout();
        create();
    }

    private void createField() {
        fieldView.setField(size, (int) App.getWidthScreen());
        fieldView.setListener(this);
    }

    private void createCounting() {
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
        mChronometer.stop();
        long time = SystemClock.elapsedRealtime() - mChronometer.getBase();
        ((BaseActivity)getActivity()).setTotalChaosResult(time, size);
        getActivity().onBackPressed();
    }

    public void resetChronometer() {
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.start();
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void create() {
        int maxLevel = ((BaseActivity)getActivity()).getLevelTotalChaos();
        levels = new ArrayList<>();

        levels.add(new ButtonLevel((maxLevel >= 6), "", 6, (Button) view.findViewById(R.id.btn66), getResources().getDrawable(R.drawable.min6), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 7), "00:30", 7, (Button) view.findViewById(R.id.btn77), getResources().getDrawable(R.drawable.min7), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 8), "00:58", 8, (Button) view.findViewById(R.id.btn88), getResources().getDrawable(R.drawable.min8), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 9), "01:26", 9, (Button) view.findViewById(R.id.btn99), getResources().getDrawable(R.drawable.min9), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 10), "02:04", 10, (Button) view.findViewById(R.id.btn1010), getResources().getDrawable(R.drawable.min10), btnStart));

        for(final ButtonLevel button: levels) {
            button.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectButton(button);
                }
            });
        }

        ButtonLevel currentButton = levels.get(maxLevel-6);
        selectButton(currentButton);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.increase_decrease_animation);
        currentButton.getBtn().startAnimation(anim);
    }

    private void selectButton(ButtonLevel button) {
        size = button.getSize();
        for(int i = 0; i < levels.size(); i++) {
            levels.get(i).setColor(colorPassive);
        }
        button.setColor(colorActive);

        setTextBestTime();
        update(button.isOpen(), button.getImage());
        setTvClosed(button.getTime());
        visibilityButtonStart(button.isOpen());
    }

    private void setTvClosed(String time) {
        tvClosed.setText(String.format(Locale.ROOT, getContext().getResources().getString(R.string.total_inscription), time));
    }

    public void update(boolean value, Drawable image) {
        if(value) {
            imageClosed.setImageDrawable(image);
            tvClosed.setVisibility(View.GONE);
        } else {
            imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
            tvClosed.setVisibility(View.VISIBLE);
        }
    }

    private void setTextBestTime() {
        long time = ((BaseActivity)getActivity()).getTimeTotalChaos(size);
        if(time == 0) {
            tvBestTime.setVisibility(View.INVISIBLE);
        } else {
            tvBestTime.setVisibility(View.VISIBLE);
            tvBestTime.setText(String.format(Locale.ROOT, getContext().getResources().getString(R.string.best_time), Utils.millsToString(time)));
        }
    }

    public void visibilityButtonStart(boolean value) {
        if(value) {
            btnStart.setVisibility(View.VISIBLE);
        } else {
            btnStart.setVisibility(View.INVISIBLE);
        }
    }
}
