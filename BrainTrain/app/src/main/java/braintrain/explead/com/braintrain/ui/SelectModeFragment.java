package braintrain.explead.com.braintrain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import braintrain.explead.com.braintrain.R;

/**
 * Created by develop on 25.09.2017.
 */

public class SelectModeFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_mode, container, false);

        ImageView oneMode = (ImageView) view.findViewById(R.id.oneMode);
        oneMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openTotalChaosFragment();
            }
        });

        ImageView twoMode = (ImageView) view.findViewById(R.id.twoMode);
        twoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openRepeatBunchFragment();
            }
        });

        return view;
    }

}