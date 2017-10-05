package braintrain.explead.com.braintrain.views.counting_cells;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.logic.counting_cells.CellCountingCells;

/**
 * Created by develop on 02.10.2017.
 */

public class CellCountingCellsView extends RelativeLayout{


    private Context context;
    private CellCountingCells cell;
    private float size;
    private float globalX;
    private float globalY;

    private View view;

    public CellCountingCellsView(Context context, float size) {
        super(context);
        this.size = size;
        init(context);
    }

    public CellCountingCellsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CellCountingCellsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    public void create(CellCountingCells cell) {
        this.cell = cell;
        LayoutParams params = new LayoutParams((int)size, (int)size);
        this.setPadding(2, 2, 2, 2);
        this.setLayoutParams(params);
        view = new View(context);
        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_counting_cell_view));
        GradientDrawable  background = (GradientDrawable ) view.getBackground();
        background.setColor(FieldCountingCellsView.colors[cell.getId()]);

        globalX = size * cell.getX();
        globalY = size * cell.getY();

        this.addView(view);
    }
}
