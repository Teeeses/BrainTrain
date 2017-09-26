package braintrain.explead.com.braintrain.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import braintrain.explead.com.braintrain.R;

/**
 * Created by develop on 26.09.2017.
 */

public class TimeCountingView extends RelativeLayout {

    public interface OnCountingListener {
        void startCounting();
        void endCounting();
    }

    private OnCountingListener listener;

    private Context context;
    private int value;

    private TextView textView;

    private int width;


    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            if(value == 0) {
                handler.removeCallbacks(run);
                endCounting();
            }
            setText(value);
            value--;
            handler.postDelayed(run, 1000);
        }
    };

    public TimeCountingView(Context context) {
        super(context);
        init(context);
    }

    public TimeCountingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeCountingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void create(int width, OnCountingListener listener) {
        this.width = width;
        this.listener = listener;

        this.setVisibility(View.GONE);
        createBlackout();

        RelativeLayout.LayoutParams paramsLayout = new RelativeLayout.LayoutParams(width, width);
        paramsLayout.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(paramsLayout);

        textView = (TextView) LayoutInflater.from(context).inflate(R.layout.time_counting_view, null, false);
        LayoutParams paramsText = new LayoutParams(width/4, width/4);
        paramsText.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        textView.setLayoutParams(paramsText);

        this.addView(textView);
    }

    public void createBlackout() {
        RelativeLayout blackout = new RelativeLayout(context);
        blackout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        blackout.setBackgroundColor(Color.BLACK);
        blackout.setAlpha(0.7f);
        this.addView(blackout);
    }

    public void setText(int value) {
        textView.setText(String.format(Locale.ROOT, "%d", value));
    }

    public void startCounting() {
        listener.startCounting();
        value = 3;
        setText(value);
        this.setVisibility(View.VISIBLE);
        handler.postDelayed(run, 0);
    }

    public void endCounting() {
        listener.endCounting();
        this.setVisibility(View.GONE);
    }
}
