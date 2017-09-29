package braintrain.explead.com.braintrain.views.total_chaos_views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.logic.total_chaos.CellTotal;

/**
 * Created by develop on 25.09.2017.
 */

public class CellTotalView extends RelativeLayout implements CellTotal.OnClickedCell {


    private Context context;
    private CellTotal mCellTotal;
    private float size;
    private float globalX;
    private float globalY;

    private TextView textView;

    private int normalColor;
    private int trueColor = Color.WHITE;
    private int errorColor = Color.RED;

    public CellTotalView(Context context, float size) {
        super(context);
        this.size = size;
        init(context);
    }

    public CellTotalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CellTotalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        normalColor = context.getResources().getColor(R.color.color_tab);
    }

    public void create(CellTotal cellTotal, float sizeText) {
        this.mCellTotal = cellTotal;
        this.mCellTotal.setListener(this);


        LayoutParams params = new LayoutParams((int)size, (int)size);
        int padding = context.getResources().getDimensionPixelOffset(R.dimen.radius)/2;
        this.setPadding(padding, padding, padding, padding);
        this.setLayoutParams(params);
        textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_total_chaos_cell_view, null, false);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setBackgroundColor(normalColor);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setText(String.format(Locale.ROOT,"%d", cellTotal.getValue()));
        textView.setTextSize(sizeText);
        Log.d("TAG", "Text size: " + sizeText);

        globalX = size * cellTotal.getX();
        globalY = size * cellTotal.getY();

        this.setX(globalX);
        this.setY(globalY);

        this.addView(textView);
    }


    @Override
    public void onClickTrue() {
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickError() {
        textView.setBackgroundColor(errorColor);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setBackgroundColor(normalColor);
            }
        }, 100);
    }
}