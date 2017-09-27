package braintrain.explead.com.braintrain.views.repeat_bunch;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import braintrain.explead.com.braintrain.logic.repeat_bunch.CellRepeat;
import braintrain.explead.com.braintrain.logic.repeat_bunch.FieldRepeatBunch;
import braintrain.explead.com.braintrain.views.total_chaos_views.FieldTotalChaosView;

/**
 * Created by develop on 25.09.2017.
 */

public class FieldRepeatBunchView extends RelativeLayout implements FieldRepeatBunch.OnFieldListener {

    public interface OnRepeatBunchListener {
        void onWin();
        void onError();
        void onLight();
        void onExtinguish();
    }

    private OnRepeatBunchListener listener;

    private Context context;
    private FieldRepeatBunch field;
    private int sizeField;

    private int sizeCell;

    private CellRepeatView[][] fieldView;

    public FieldRepeatBunchView(Context context) {
        super(context);
        init(context);
    }

    public FieldRepeatBunchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FieldRepeatBunchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void setField(int count, int sizeField) {
        field = new FieldRepeatBunch(count);
        field.setListener(this);
        this.sizeField = sizeField;
        sizeCell = sizeField/field.getSize();

        LayoutParams params = new LayoutParams(sizeField, sizeField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);

        createFieldView();
    }

    public void createFieldView() {
        this.removeAllViews();
        fieldView = new CellRepeatView[field.getSize()][field.getSize()];
        for(int i = 0; i < field.getSize(); i++) {
            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, sizeCell));
            for(int j = 0; j < field.getSize(); j++) {
                final CellRepeat cellRepeat = field.getCell(i, j);
                CellRepeatView cellRepeatView = new CellRepeatView(context, sizeCell);
                cellRepeatView.create(cellRepeat);
                fieldView[i][j] = cellRepeatView;

                cellRepeatView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        field.cellClick(cellRepeat);
                    }
                });

                this.addView(cellRepeatView);
            }
        }
    }

    public void startGame() {
        field.lightCells();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                field.extinguishCells();
            }
        }, 3000);
    }

    public void setClickable(boolean value) {
        for(int i = 0; i < field.getSize(); i++) {
            for(int j = 0; j < field.getSize(); j++) {
                fieldView[i][j].setClickable(value);
            }
        }
    }

    @Override
    public void onWin() {
        listener.onWin();
    }

    @Override
    public void onError(final CellRepeat cellRepeat) {
        Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        listener.onError();
    }

    public void setListener(OnRepeatBunchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onLight() {
        listener.onLight();
    }

    @Override
    public void onExtinguish() {
        listener.onExtinguish();
    }
}
