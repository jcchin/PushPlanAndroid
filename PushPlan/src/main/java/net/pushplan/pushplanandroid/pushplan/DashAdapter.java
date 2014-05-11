package net.pushplan.pushplanandroid.pushplan;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dash_list, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(huddles.get(position).getName());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                startHuddle(v,huddles.get(position).getName() );
                //huddles.remove(position); //or some other task
                //notifyDataSetChanged();
            }
        });

        final View finalView = view;
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    finalView.animate().setDuration(500).alpha(0).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            huddles.remove(position);
                            notifyDataSetChanged();
                            finalView.setAlpha(1);
                        }
                    });
                } else {
                    finalView.animate().setDuration(500).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            huddles.remove(position);
                            notifyDataSetChanged();
                            finalView.setAlpha(1);
                        }
                    });
                }
                //huddles.remove(position); //or some other task
                //notifyDataSetChanged();
            }
        });

        return view;
    }
    public void startHuddle(View view, String name) {
        // Do something in response to button
        Intent intent = new Intent(context, DemoHuddle.class);
        intent.putExtra("name",name);
        context.startActivity(intent);

    }
}
