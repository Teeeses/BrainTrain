package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.GameActivity;
import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.ui.BaseActivity;
import braintrain.explead.com.braintrain.ui.MainActivity;
import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 27.09.2017.
 */

public class MenuTotalChaosFragment extends Fragment {

    private ArrayList<ButtonLevel> levels = new ArrayList<>();

    private TextView textClosed;
    private TextView tvBestTime;
    private ImageView imageClosed;

    private Drawable colorActive;
    private Drawable colorPassive;

    private int size = 6;
    private int maxLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_total_chaos, container, false);

        maxLevel = ((BaseActivity)getActivity()).getLevelTotalChaos();
        colorActive = getResources().getDrawable(R.drawable.bg_round_green_btn);
        colorPassive = getResources().getDrawable(R.drawable.bg_round_white_btn);

        levels.add(new ButtonLevel((maxLevel >= 6), "00:30", 6, (Button) view.findViewById(R.id.btn66), getResources().getDrawable(R.drawable.circle_closed)));
        levels.add(new ButtonLevel((maxLevel >= 7), "00:54", 7, (Button) view.findViewById(R.id.btn77), getResources().getDrawable(R.drawable.circle_closed)));
        levels.add(new ButtonLevel((maxLevel >= 8), "01:30", 8, (Button) view.findViewById(R.id.btn88), getResources().getDrawable(R.drawable.circle_closed)));
        levels.add(new ButtonLevel((maxLevel >= 9), "02:20", 9, (Button) view.findViewById(R.id.btn99), getResources().getDrawable(R.drawable.circle_closed)));
        levels.add(new ButtonLevel((maxLevel >= 10), "03:00", 10, (Button) view.findViewById(R.id.btn1010), getResources().getDrawable(R.drawable.circle_closed)));

        imageClosed = (ImageView) view.findViewById(R.id.imageClosed);
        tvBestTime = (TextView) view.findViewById(R.id.tvBestTime);
        textClosed = (TextView) view.findViewById(R.id.textClosed);
        setTextClosed(levels.get(0).getTime());
        setImageClosed(levels.get(0).isOpen(), levels.get(0).getImage());

        for(final ButtonLevel button: levels) {
            button.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    size = button.getSize();
                    setTextClosed(button.getTime());
                    for(int i = 0; i < levels.size(); i++) {
                        levels.get(i).setColor(colorPassive);
                    }

                    button.setColor(colorActive);
                    setTextBestTime();
                    setImageClosed(button.isOpen(), button.getImage());
                }
            });
        }

        Button btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openGameActivity(1, size);
            }
        });

        return view;
    }

    private void setTextClosed(String time) {
        textClosed.setText(String.format(Locale.ROOT, getContext().getResources().getString(R.string.total_inscription), time));
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

    private void setImageClosed(boolean value, Drawable image) {
        if(size == 6) {
            if(value) {
                imageClosed.setImageDrawable(image);
                textClosed.setVisibility(View.INVISIBLE);
            } else {
                imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
                textClosed.setVisibility(View.VISIBLE);
            }
        } else if(size == 7) {
            if(value) {
                imageClosed.setImageDrawable(image);
                textClosed.setVisibility(View.INVISIBLE);
            } else {
                imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
                textClosed.setVisibility(View.VISIBLE);
            }
        } else if(size == 8) {
            if(value) {
                imageClosed.setImageDrawable(image);
                textClosed.setVisibility(View.INVISIBLE);
            } else {
                imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
                textClosed.setVisibility(View.VISIBLE);
            }
        } else if(size == 9) {
            if(value) {
                imageClosed.setImageDrawable(image);
                textClosed.setVisibility(View.INVISIBLE);
            } else {
                imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
                textClosed.setVisibility(View.VISIBLE);
            }
        } else if(size == 10) {
            if(value) {
                imageClosed.setImageDrawable(image);
                textClosed.setVisibility(View.INVISIBLE);
            } else {
                imageClosed.setImageDrawable(getResources().getDrawable(R.drawable.closed));
                textClosed.setVisibility(View.VISIBLE);
            }
        }
    }

    private class ButtonLevel {
        private int size;
        private Button btn;
        private String time;
        private Drawable image;

        private boolean open;

        public ButtonLevel(boolean open, String time, int size, Button btn, Drawable image) {
            this.open = open;
            this.time = time;
            this.size = size;
            this.btn = btn;
            this.image = image;
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
        setTextBestTime();
    }
}