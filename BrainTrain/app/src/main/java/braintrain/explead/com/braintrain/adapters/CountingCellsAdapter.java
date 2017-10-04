package braintrain.explead.com.braintrain.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import braintrain.explead.com.braintrain.R;
import braintrain.explead.com.braintrain.utils.Utils;

/**
 * Created by develop on 02.10.2017.
 */

public class CountingCellsAdapter extends BaseAdapter {


    private Context context;

    private LayoutInflater lInflater;
    private ViewHolder viewHolder;

    private ArrayList<Integer> cells;
    private int[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.GREEN, Color.BLUE, Color.GRAY};

    public CountingCellsAdapter(Context context, ArrayList<Integer> cells){
        this.cells = cells;
        this.context = context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.item_searching_element_view, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.view = (TextView) convertView.findViewById(R.id.cell);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int color = colors[cells.get(position)];
        viewHolder.view.setBackgroundColor(color);


        return convertView;
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public Object getItem(int position) {
        return cells.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView view;
    }
}