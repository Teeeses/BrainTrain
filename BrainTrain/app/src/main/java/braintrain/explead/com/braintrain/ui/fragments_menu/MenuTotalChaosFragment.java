package braintrain.explead.com.braintrain.ui.fragments_menu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.ui.BaseActivity;
import braintrain.explead.com.braintrain.ui.MainActivity;
import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 27.09.2017.
 */

public class MenuTotalChaosFragment extends Fragment {

    private ArrayList<ButtonLevel> levels;

    private TextView tvClosed;
    private TextView tvBestTime;
    private ImageView imageClosed;
    private Button btnStart;

    private Drawable colorActive;
    private Drawable colorPassive;

    private int size = 6;
    private int maxLevel;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_total_chaos, container, false);

        colorActive = getResources().getDrawable(R.drawable.bg_round_green_btn);
        colorPassive = getResources().getDrawable(R.drawable.bg_round_white_btn);

        imageClosed = (ImageView) view.findViewById(R.id.imageClosed);
        tvBestTime = (TextView) view.findViewById(R.id.tvBestTime);
        tvClosed = (TextView) view.findViewById(R.id.textClosed);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openGameActivity(1, size);
            }
        });

        return view;
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

    public void sisibilityButtonStart(boolean value) {
        if(value) {
            btnStart.setVisibility(View.VISIBLE);
        } else {
            btnStart.setVisibility(View.INVISIBLE);
        }
    }

    private void create() {
        maxLevel = ((BaseActivity)getActivity()).getLevelTotalChaos();
        Log.d("TAG", "MAX - " + maxLevel);
        levels = new ArrayList<>();

        levels.add(new ButtonLevel((maxLevel >= 6), "", 6, (Button) view.findViewById(R.id.btn66), getResources().getDrawable(R.drawable.field), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 7), "00:30", 7, (Button) view.findViewById(R.id.btn77), getResources().getDrawable(R.drawable.field), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 8), "00:58", 8, (Button) view.findViewById(R.id.btn88), getResources().getDrawable(R.drawable.field), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 9), "01:26", 9, (Button) view.findViewById(R.id.btn99), getResources().getDrawable(R.drawable.field), btnStart));
        levels.add(new ButtonLevel((maxLevel >= 10), "02:04", 10, (Button) view.findViewById(R.id.btn1010), getResources().getDrawable(R.drawable.field), btnStart));

        for(final ButtonLevel button: levels) {
            button.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    size = button.getSize();
                    for(int i = 0; i < levels.size(); i++) {
                        levels.get(i).setColor(colorPassive);
                    }
                    button.setColor(colorActive);

                    setTextBestTime();
                    update(button.isOpen(), button.getImage());
                    setTvClosed(button.getTime());
                    sisibilityButtonStart(button.isOpen());
                }
            });
        }

        setTvClosed(levels.get(0).getTime());
        update(levels.get(0).isOpen(), levels.get(0).getImage());
        setTextBestTime();
    }

    private class ButtonLevel {
        private int size;
        private Button btn;
        private Button btnStart;
        private String time;
        private Drawable image;

        private boolean open;

        public ButtonLevel(boolean open, String time, int size, Button btn, Drawable image, Button btnStart) {
            this.open = open;
            this.time = time;
            this.size = size;
            this.btn = btn;
            this.image = image;
            this.btnStart = btnStart;
        }

        public int getSize() {
            return size;
        }

        public Button getBtn() {
            return btn;
        }

        public String getTime() {
            return time;
        }

        public boolean isOpen() {
            return open;
        }

        public Drawable getImage() {
            return image;
        }

        public void setColor(Drawable color) {
            btn.setBackgroundDrawable(color);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        create();
    }
}