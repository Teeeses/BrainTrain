package braintrain.explead.com.braintrain.ui.fragments_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.ui.MainActivity;

/**
 * Created by develop on 05.10.2017.
 */

public class MenuCountingCellsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_counting_cells, container, false);

        Button btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity) getActivity()).openCountingCellsFragment();
            }
        });

        return view;
    }
}
