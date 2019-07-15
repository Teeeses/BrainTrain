package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
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

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.beans.ButtonLevel;
import braintrain.explead.com.braintrain.dialogs.DialogCompleted;
import braintrain.explead.com.braintrain.logic.total_chaos.CellTotal;
import braintrain.explead.com.braintrain.logic.total_chaos.FieldTotalChaos;
import braintrain.explead.com.braintrain.ui.BaseActivity;
import braintrain.explead.com.braintrain.ui.MainActivity;
import braintrain.explead.com.braintrain.utils.Utils;
import braintrain.explead.com.braintrain.views.TimeCountingView;
import braintrain.explead.com.braintrain.views.total_chaos_views.FieldTotalChaosView;


public class TotalChaosFragment extends GameBaseFragment {

    //game
    private FieldTotalChaosView fieldView;
    private TimeCountingView countingView;
    private Chronometer mChronometer;

    //menu
    private ArrayList<ButtonLevel> levels;
    private TextView tvClosed;
    private TextView tvBestTime;
    private TextView tvCurrentValue;
    private ImageView imageClosed;
    private Button btnStart;
    private RelativeLayout btnWatchVideo;
    private Drawable colorActive;
    private Drawable colorPassive;
    private View view;


    private SoundPool soundPool;

    private RelativeLayout winLayout;
    private TextView tvWinTIme;

    private int size = 6;
    private FieldTotalChaos field;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_total_chaos, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);
        winLayout = (RelativeLayout) view.findViewById(R.id.winLayout);
        winLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winLayout.setVisibility(View.GONE);
                getActivity().onBackPressed();
            }
        });


        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPool.load(getActivity(), R.raw.one, 1);
        soundPool.load(getActivity(), R.raw.sound_win, 2);

        mChronometer = (Chronometer) view.findViewById(R.id.chronometer);
        fieldView = (FieldTotalChaosView) view.findViewById(R.id.fieldView);
        countingView = (TimeCountingView) view.findViewById(R.id.countingView);


        colorActive = getResources().getDrawable(R.drawable.bg_round_green_btn);
        colorPassive = getResources().getDrawable(R.drawable.bg_round_white_btn);

        imageClosed = (ImageView) view.findViewById(R.id.imageClosed);
        tvBestTime = (TextView) view.findViewById(R.id.tvBestTime);
        tvClosed = (TextView) view.findViewById(R.id.textClosed);
        tvCurrentValue = (TextView) view.findViewById(R.id.tvCurrentValue);
        tvWinTIme = (TextView) view.findViewById(R.id.tvWinTIme);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameLayout();
            }
        });

        btnWatchVideo = (RelativeLayout) view.findViewById(R.id.btnWatchVideo);
        btnWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appodeal.show(getActivity(), Appodeal.REWARDED_VIDEO);
            }
        });
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean isPrecache) {
                Log.d("Appodeal", "onRewardedVideoLoaded");
            }
            @Override
            public void onRewardedVideoFailedToLoad() {
                Log.d("Appodeal", "onRewardedVideoFailedToLoad");
            }
            @Override
            public void onRewardedVideoShown() {
                Log.d("Appodeal", "onRewardedVideoShown");
            }
            @Override
            public void onRewardedVideoFinished(double amount, String name) {
                Log.d("Appodeal", "onRewardedVideoFinished");
                watchVideo();
            }
            @Override
            public void onRewardedVideoClosed(boolean finished) {
                Log.d("Appodeal", "onRewardedVideoClosed");

                btnWatchVideo.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onRewardedVideoExpired() {
                Log.d("Appodeal", "onRewardedVideoExpired");
                btnWatchVideo.setVisibility(View.INVISIBLE);
            }
        });

        openMenuLayout();

        return view;
    }

    private void watchVideo() {
        btnWatchVideo.setVisibility(View.INVISIBLE);
        ((BaseActivity)getActivity()).addLevelTotalChaos(size);
        openMenuLayout();
        selectButton(levels.get(size-6));
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

    private void win() {
        soundPool.play(2, 0.5f, 0.5f, 1, 0, 1f);
        mChronometer.stop();
        long time = SystemClock.elapsedRealtime() - mChronometer.getBase();
        ((BaseActivity)getActivity()).setTotalChaosResult(time, size);

        String text = String.format(Locale.ROOT, getResources().getString(R.string.result), Utils.millsToString(time));
        new DialogCompleted(getActivity(), text, new DialogCompleted.OnDialogCompletedListener() {
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

    private void createField() {
        field = new FieldTotalChaos(size, new FieldTotalChaos.OnFieldListener() {
            @Override
            public void onWin() {
                win();
            }

            @Override
            public void onError(CellTotal cellTotal) {
                fieldView.onError(cellTotal);
            }

            @Override
            public void onTrue(CellTotal cellTotal) {
                fieldView.onTrue(cellTotal);
            }

            @Override
            public void onCurrentValue(int value) {
                soundPool.play(1, 0.5f, 0.5f, 1, 0, 1f);
                tvCurrentValue.setText(String.format(Locale.ROOT, getResources().getString(R.string.current_value), value));
            }
        });
        fieldView.setField(field, (int) App.getWidthScreen());
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
            btnWatchVideo.setVisibility(View.INVISIBLE);
        } else {
            btnStart.setVisibility(View.INVISIBLE);

            int maxLevel = ((BaseActivity)getActivity()).getLevelTotalChaos();
            if(Appodeal.isLoaded(Appodeal.REWARDED_VIDEO) && size - 1 == maxLevel) {
                btnWatchVideo.setVisibility(View.VISIBLE);
            } else {
                btnWatchVideo.setVisibility(View.INVISIBLE);
            }
        }
    }
}
