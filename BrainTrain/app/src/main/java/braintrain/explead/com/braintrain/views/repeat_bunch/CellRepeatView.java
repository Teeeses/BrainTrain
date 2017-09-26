package braintrain.explead.com.braintrain.views.repeat_bunch;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.logic.repeat_bunch.CellRepeat;

/**
 * Created by develop on 25.09.2017.
 */

public class CellRepeatView extends RelativeLayout implements CellRepeat.OnClickedCell {


    private Context context;
    private CellRepeat mCellRepeat;
    private int size;
    private float globalX;
    private float globalY;

    private View view;

    private int normalColor;
    private int trueColor = Color.WHITE;
    private int errorColor = Color.RED;

    public CellRepeatView(Context context, int size) {
        super(context);
        this.size = size;
        init(context);
    }

    public CellRepeatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CellRepeatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        normalColor = context.getResources().getColor(R.color.colorPrimary);
    }

    public void create(CellRepeat cellRepeat) {
        this.mCellRepeat = cellRepeat;
        this.mCellRepeat.setListener(this);


        LayoutParams params = new LayoutParams(size, size);
        this.setPadding(2, 2, 2, 2);
        this.setLayoutParams(params);
        view = new View(context);
        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setBackgroundColor(normalColor);

        globalX = size * cellRepeat.getX();
        globalY = size * cellRepeat.getY();

        this.setX(globalX);
        this.setY(globalY);

        this.addView(view);
    }


    @Override
    public void onClickTrue() {

    }

    @Override
    public void onClickError() {

    }
}