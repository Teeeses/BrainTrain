package braintrain.explead.com.braintrain.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;

public class ChoiceForCountingCells extends LinearLayout {

    private Context context;

    private ArrayList<Integer> choiceArray;
    private int trueValue;

    public interface OnChoiceListener {
        void ok();
        void error();
    }

    public ChoiceForCountingCells(Context context) {
        super(context);
        init(context);
    }

    public ChoiceForCountingCells(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChoiceForCountingCells(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setOrientation(HORIZONTAL);
    }

    public void create(final ArrayList<Integer> choiceArray, final int trueValue, final OnChoiceListener listener) {
        this.removeAllViews();
        this.choiceArray = choiceArray;
        this.trueValue = trueValue;

        int size = this.getMeasuredWidth()/choiceArray.size();
        for(int i = 0; i < choiceArray.size(); i++) {
            final int index = i;
            RelativeLayout layout = new RelativeLayout(context);
            LinearLayout.LayoutParams paramsLayout = new LayoutParams(size, size);
            layout.setLayoutParams(paramsLayout);
            layout.setPadding(4, 4, 4, 4);

            final TextView textView = new TextView(context);
            RelativeLayout.LayoutParams paramsTextView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            paramsTextView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            textView.setLayoutParams(paramsTextView);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_round_black_btn));
            textView.setAlpha(0.7f);

            textView.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.standard_text));
            textView.setTextColor(Color.WHITE);
            textView.setText(String.format(Locale.ROOT, "%d", choiceArray.get(i)));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(trueValue == choiceArray.get(index)) {
                        textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_round_green_btn));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_round_black_btn));
                            }
                        }, 100);
                        listener.ok();
                    } else {

                        textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_round_red_btn));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_round_black_btn));
                            }
                        }, 100);
                        listener.error();
                    }
                }
            });

            layout.addView(textView);
            this.addView(layout);
        }
    }
}
