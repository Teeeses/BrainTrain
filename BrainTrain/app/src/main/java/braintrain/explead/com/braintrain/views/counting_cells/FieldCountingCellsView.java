package braintrain.explead.com.braintrain.views.counting_cells;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.logic.counting_cells.CellCountingCells;
import braintrain.explead.com.braintrain.logic.counting_cells.FieldCountingCells;

/**
 * Created by develop on 02.10.2017.
 */

public class FieldCountingCellsView extends RelativeLayout {

    private Context context;
    private FieldCountingCells field;
    private int sizeField;
    private float sizeCell;

    private CellCountingCellsView[][] fieldView;

    public FieldCountingCellsView(Context context) {
        super(context);
        init(context);
    }

    public FieldCountingCellsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FieldCountingCellsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void setField(int size, int sizeField) {
        field = new FieldCountingCells(size);
        this.sizeField = sizeField;
        sizeCell = sizeField/(size+1);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);

        createFieldView();
        setClickable(false);
    }

    public void createFieldView() {
        fieldView = new CellCountingCellsView[field.getSize()][field.getSize()];
        LinearLayout verticallLayout = new LinearLayout(context);
        verticallLayout.setOrientation(LinearLayout.VERTICAL);
        verticallLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        for(int i = 0; i < field.getSize(); i++) {
            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int)sizeCell));
            for(int j = 0; j < field.getSize(); j++) {
                final CellCountingCells cell = field.getCell(i, j);
                CellCountingCellsView cellView = new CellCountingCellsView(context, sizeCell);
                cellView.create(cell);
                fieldView[i][j] = cellView;

                horizontalLayout.addView(cellView);
            }
            verticallLayout.addView(horizontalLayout);
        }
        this.addView(verticallLayout);
    }

    public void setClickable(boolean value) {
        for(int i = 0; i < field.getSize(); i++) {
            for(int j = 0; j < field.getSize(); j++) {
                fieldView[i][j].setClickable(value);
            }
        }
    }
}
