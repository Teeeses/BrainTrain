package braintrain.explead.com.braintrain.views.total_chaos_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.logic.total_chaos.CellTotal;
import braintrain.explead.com.braintrain.logic.total_chaos.FieldTotalChaos;


public class FieldTotalChaosView extends RelativeLayout implements FieldTotalChaos.OnFieldListener {

    public interface OnTotalChaosListener {
        void onWin();
    }

    private OnTotalChaosListener listener;

    private Context context;
    private FieldTotalChaos field;
    private float sizeField;

    private float sizeCell;

    private CellTotalView[][] fieldView;

    public FieldTotalChaosView(Context context) {
        super(context);
        init(context);
    }

    public FieldTotalChaosView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FieldTotalChaosView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void setListener(OnTotalChaosListener listener) {
        this.listener = listener;
    }

    public void setField(int size, float sizeField) {
        field = new FieldTotalChaos(size);
        field.setListener(this);
        this.sizeField = sizeField;
        sizeCell = sizeField/field.getSize();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)sizeField, (int)sizeField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);

        createFieldView();
    }

    public void createFieldView() {
        this.removeAllViews();
        fieldView = new CellTotalView[field.getSize()][field.getSize()];
        float mini_text = context.getResources().getDimension(R.dimen.mini_text);
        float sizeText = mini_text + (10 - field.getSize())*(mini_text/5);
        for(int i = 0; i < field.getSize(); i++) {
            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)sizeCell));
            for(int j = 0; j < field.getSize(); j++) {
                final CellTotal cellTotal = field.getCell(i, j);
                CellTotalView cellTotalView = new CellTotalView(context, sizeCell);
                cellTotalView.create(cellTotal, sizeText);
                fieldView[i][j] = cellTotalView;

                cellTotalView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        field.cellClick(cellTotal);
                    }
                });

                this.addView(cellTotalView);
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
        listener.onWin();
    }

    @Override
    public void onError(CellTotal cellTotal) {
        cellTotal.error();
    }

    @Override
    public void onTrue(CellTotal cellTotal) {
        cellTotal.setStatusTrue();
    }
}
