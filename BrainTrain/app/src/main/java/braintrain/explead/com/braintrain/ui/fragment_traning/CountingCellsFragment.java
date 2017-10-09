package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.logic.counting_cells.FieldCountingCells;
import braintrain.explead.com.braintrain.views.ChoiceForCountingCells;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;


public class CountingCellsFragment extends GameBaseFragment {

    private FieldCountingCellsView fieldView;
    private FieldCountingCells field;

    private ChoiceForCountingCells choiceForCountingCells;
    private View view;
    private int size;

    private Button btnStart;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_counting_cells, container, false);

        menuLayout = (RelativeLayout) view.findViewById(R.id.menuLayout);
        gameLayout = (RelativeLayout) view.findViewById(R.id.gameLayout);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(startGameClick);

        choiceForCountingCells = (ChoiceForCountingCells) view.findViewById(R.id.choiceForCountingCells);
        fieldView = (FieldCountingCellsView) view.findViewById(R.id.fieldView);

        openMenuLayout();

        return view;
    }

    @Override
    public void openGameLayout() {
        super.openGameLayout();
        createField();
        createViews();
    }

    @Override
    public void openMenuLayout() {
        super.openMenuLayout();
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

    private View.OnClickListener startGameClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGameLayout();
        }
    };

}
