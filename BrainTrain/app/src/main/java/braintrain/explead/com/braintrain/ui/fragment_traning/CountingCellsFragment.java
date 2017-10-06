package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.logic.counting_cells.FieldCountingCells;
import braintrain.explead.com.braintrain.views.ChoiceForCountingCells;
import braintrain.explead.com.braintrain.views.counting_cells.CellCountingCellsView;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;

/**
 * Created by develop on 02.10.2017.
 */

public class CountingCellsFragment extends Fragment {

    private FieldCountingCellsView fieldView;
    private FieldCountingCells field;

    private ChoiceForCountingCells choiceForCountingCells;
    private View view;
    private int size;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_counting_cells, container, false);

        choiceForCountingCells = (ChoiceForCountingCells) view.findViewById(R.id.choiceForCountingCells);
        fieldView = (FieldCountingCellsView) view.findViewById(R.id.fieldView);

        choiceForCountingCells.post(new Runnable() {
            @Override
            public void run() {
                createField();
                createViews();
            }
        });

        return view;
    }

    private void createField() {
        size = 5;
        field = new FieldCountingCells(size);
    }

    private void createViews() {
        fieldView.setField(field, (int) App.getWidthScreen());
        choiceForCountingCells.create(field.getChoices(), field.getCount(), new ChoiceForCountingCells.OnChoiceListener() {
            @Override
            public void ok() {
                createField();
                createViews();
            }

            @Override
            public void error() {

            }
        });

        View findCell = view.findViewById(R.id.findCell);
        GradientDrawable background = (GradientDrawable ) findCell.getBackground();
        background.setColor(FieldCountingCellsView.colors[field.getIndex()]);
    }

}
