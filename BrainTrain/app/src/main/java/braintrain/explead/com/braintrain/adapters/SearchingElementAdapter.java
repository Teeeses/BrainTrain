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
 * Created by develop on 30.01.2017.
 */

public class SearchingElementAdapter extends BaseAdapter {

    public interface OnSearchingElementClick {
        void onClick(int value);
    }

    private OnSearchingElementClick listener;

    private Context context;

    private LayoutInflater lInflater;
    private ViewHolder viewHolder;

    private ArrayList<Integer> cells;
    private int[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.GREEN, Color.BLUE, Color.GRAY};
    private int[] animation = {R.anim.alpha};

    public SearchingElementAdapter(Context context, ArrayList<Integer> cells, OnSearchingElementClick listener){
        this.listener = listener;
        this.cells = cells;
        this.context = context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.item_searching_element_view, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvElement = (TextView) convertView.findViewById(R.id.cell);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final int value = (Integer) getItem(position);
        viewHolder.tvElement.setText(String.format(Locale.ROOT, "%d", value));
        viewHolder.tvElement.setTextColor(Color.WHITE);
        viewHolder.tvElement.setBackgroundColor(colors[Utils.randInt(0, colors.length-1)]);

        Animation anim = AnimationUtils.loadAnimation(context, animation[Utils.randInt(0, animation.length-1)]);
        anim.setRepeatCount(Animation.INFINITE);
        viewHolder.tvElement.startAnimation(anim);

        viewHolder.tvElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(value);
            }
        });

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
        TextView tvElement;
    }
}