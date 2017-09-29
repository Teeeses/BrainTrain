package braintrain.explead.com.braintrain.views.searching_element;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by develop on 29.09.2017.
 */

public class CellSearchingView extends TextView {

    private int value;

    public CellSearchingView(Context context) {
        super(context);
    }

    public CellSearchingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CellSearchingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int value) {
        this.value = value;
        this.setText(String.format(Locale.ROOT, "%d", value));
    }
}
