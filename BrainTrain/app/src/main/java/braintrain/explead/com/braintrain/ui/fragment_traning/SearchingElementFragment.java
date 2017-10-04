package braintrain.explead.com.braintrain.ui.fragment_traning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.adapters.SearchingElementAdapter;
import braintrain.explead.com.braintrain.logic.searching_element.FieldSearchingElement;

/**
 * Created by develop on 29.09.2017.
 */

public class SearchingElementFragment extends Fragment {

    private FieldSearchingElement field;

    private SearchingElementAdapter adapter;
    private GridView gridView;
    private TextView tvGeneralValue;

    private int countErrors;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searching_element, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        tvGeneralValue = (TextView) view.findViewById(R.id.tvGeneralValue);

        create();

        return view;
    }

    private void create() {
        nullifyErrors();
        field = new FieldSearchingElement(2);
        adapter = new SearchingElementAdapter(getContext(), field.getCells(), new SearchingElementAdapter.OnSearchingElementClick() {
            @Override
            public void onClick(int value) {
                if(field.getValue() == value) {
                    create();
                } else {
                    countErrors++;
                }
            }
        });
        gridView.setAdapter(adapter);
        gridView.setNumColumns(4);

        tvGeneralValue.setText(String.format(Locale.ROOT, "%d", field.getValue()));
    }

    private void nullifyErrors() {
        countErrors = 0;
    }
}
