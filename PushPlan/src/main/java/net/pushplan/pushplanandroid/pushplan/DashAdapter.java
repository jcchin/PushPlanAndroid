package net.pushplan.pushplanandroid.pushplan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

/**
 * Created by jeffchin on 5/4/14.
 */
class DashAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Huddle> huddles;

    public DashAdapter(Context context, ArrayList<Huddle> huddles) {

        this.context = context;
        this.huddles = huddles;
    }
    @Override
    public int getCount() {
        return huddles.size();
    }

    @Override
    public Object getItem(int position) {
        return huddles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(huddles.get(position).getName());
        text2.setText(huddles.get(position).getDate());

        return twoLineListItem;
    }
}
