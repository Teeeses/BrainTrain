package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.app.App;
import braintrain.explead.com.braintrain.views.counting_cells.FieldCountingCellsView;

/**
 * Created by develop on 02.10.2017.
 */

public class CountingCellsFragment extends Fragment {

    private FieldCountingCellsView fieldView;

    private int size;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counting_cells, container, false);

        fieldView = (FieldCountingCellsView) view.findViewById(R.id.fieldView);

        createField();

        return view;
    }

    private void createField() {
        size = 5;
        fieldView.setField(size, (int) App.getWidthScreen());
    }

}
