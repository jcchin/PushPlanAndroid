package net.pushplan.pushplanandroid.pushplan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeffchin on 5/9/14.
 */
public class IdeaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Idea> ideas;

    public IdeaAdapter(Context context, ArrayList<Idea> ideas) {

        this.context = context;
        this.ideas = ideas;
    }
    @Override
    public int getCount() {
        return ideas.size();
    }

    @Override
    public Object getItem(int position) {
        return ideas.get(position);
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
            view = inflater.inflate(R.layout.idea_list, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string2);
        listItemText.setText(ideas.get(position).getName());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                //startHuddle(v,ideas.get(position).getName() );
                //huddles.remove(position); //or some other task
                //notifyDataSetChanged();
            }
        });

        final View finalView = view;
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

                finalView.animate().setDuration(500).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                ideas.remove(position);
                                notifyDataSetChanged();
                                finalView.setAlpha(1);
                            }
                        });
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
