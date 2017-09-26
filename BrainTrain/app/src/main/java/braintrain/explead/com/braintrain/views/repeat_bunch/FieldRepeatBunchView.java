package braintrain.explead.com.braintrain.views.repeat_bunch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import braintrain.explead.com.braintrain.logic.repeat_bunch.CellRepeat;
import braintrain.explead.com.braintrain.logic.repeat_bunch.FieldRepeatBunch;

/**
 * Created by develop on 25.09.2017.
 */

public class FieldRepeatBunchView extends RelativeLayout implements FieldRepeatBunch.OnFieldListener {

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

    public void setField(int size, int sizeField) {
        field = new FieldRepeatBunch(size);
        field.setListener(this);
        this.sizeField = sizeField;
        sizeCell = sizeField/field.getSize();

        LayoutParams params = new LayoutParams(sizeField, sizeField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);

        createFieldView();
    }

    public void createFieldView() {
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

    public void setClickable(boolean value) {
        for(int i = 0; i < field.getSize(); i++) {
            for(int j = 0; j < field.getSize(); j++) {
                fieldView[i][j].setClickable(value);
            }
        }
    }

    @Override
    public void onWin() {
        Toast.makeText(context, "Победа", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(CellRepeat cellRepeat) {

    }

    @Override
    public void onTrue(CellRepeat cellRepeat) {

    }
}
