package net.pushplan.pushplanandroid.pushplan;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class DemoHuddle extends FragmentActivity implements ActionBar.TabListener, android.app.ActionBar.TabListener {

    private static Context context;

    public static Context getAppContext() {
        return DemoHuddle.context;
    }
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_huddle);

        DemoHuddle.context = getApplicationContext();
        //set name of demo activity
        String huddleName;
        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras == null) {
                huddleName= "demoHuddle";
            } else {
                huddleName= extras.getString("name");
            }
        } else {
            huddleName= (String) savedInstanceState.getSerializable("name");
        }
        setTitle(huddleName);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final android.app.ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should be enabled, to hierarchical
        // parent.
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if ((getCount()-1) == i){
                return new AddPollFragment();
            }
            if ((getCount()-2) == i){
                return new TimeFragment();
            }
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new ChatFragment();

                default:
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if ((getCount()-1) == position){
                return "Add+";
            }
            switch (position) {
                case 0:
                    return "Group Chat";
                case 1:
                    return "Ideas";
                case 2:
                    return "Times";
                default:
                    return "...";
            }

        }

        /**
         * A fragment that launches other parts of the demo application.
         */
        public static class ChatFragment extends Fragment {
            public ChatFragment(){}

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
                /*
                // Demonstration of a collection-browsing activity.
                rootView.findViewById(R.id.demo_collection_button)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                                //startActivity(intent);
                            }
                        });

                // Demonstration of navigating to external activities.
                rootView.findViewById(R.id.demo_external_activity)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Create an intent that asks the user to pick a photo, but using
                                // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
                                // the application from the device home screen does not return
                                // to the external activity.
                                Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                                externalActivityIntent.setType("image/*");
                                externalActivityIntent.addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                startActivity(externalActivityIntent);
                            }
                        });
                */
                return rootView;
            }
        }
        /**
         * A fragment that launches other parts of the demo application.
         */
        public static class AddPollFragment extends Fragment {
            public AddPollFragment(){}

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_add_poll, container, false);

                return rootView;
            }

            @Override
            public void onActivityCreated(Bundle savedInstanceState){
                super.onActivityCreated(savedInstanceState);
                Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getAppContext(),
                        R.array.poll_array, R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
            }
        }
        /**
         * A fragment that launches other parts of the demo application.
         */
        public static class TimeFragment extends Fragment {
            public TimeFragment(){}

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_time, container, false);


                return rootView;
            }

            @Override
            public void onActivityCreated(Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);

                TableLayout time_table;
                time_table=(TableLayout)getActivity().findViewById(R.id.maintable);
                TableRow row;
                TextView t1, t2, t3, t4, t5;
                //Converting to dip unit
                int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        (float) 1, getResources().getDisplayMetrics());

                for (int current = 0; current < TimeList.times.length; current++) {
                    row = new TableRow(getAppContext());

                    t1 = new TextView(getAppContext());
                    t1.setTextColor(getResources().getColor(R.color.black));
                    t2 = new TextView(getAppContext());
                    t2.setTextColor(getResources().getColor(R.color.black));
                    t2 = new TextView(getAppContext());
                    t2.setTextColor(getResources().getColor(R.color.black));
                    t3 = new TextView(getAppContext());
                    t3.setTextColor(getResources().getColor(R.color.black));
                    t4 = new TextView(getAppContext());
                    t4.setTextColor(getResources().getColor(R.color.black));
                    t5 = new TextView(getAppContext());
                    t5.setTextColor(getResources().getColor(R.color.black));

                    t1.setText(TimeList.times[current]);
                    t2.setText(TimeList.times[current]);
                    t3.setText(TimeList.times[current]);
                    t4.setText(TimeList.times[current]);
                    t5.setText(TimeList.times[current]);
                    //t2.setText(TimeList.countries[current]);

                    t1.setTypeface(null, 1);
                    t2.setTypeface(null, 1);
                    t3.setTypeface(null, 1);
                    t4.setTypeface(null, 1);
                    t5.setTypeface(null, 1);

                    t1.setTextSize(15);
                    t2.setTextSize(15);
                    t3.setTextSize(15);
                    t4.setTextSize(15);
                    t5.setTextSize(15);

                    t1.setWidth(70 * dip);
                    t2.setWidth(70 * dip);
                    t3.setWidth(70 * dip);
                    t4.setWidth(70 * dip);
                    t5.setWidth(70 * dip);
                    t1.setHeight(50 * dip);
                    t2.setHeight(50 * dip);
                    t3.setHeight(50 * dip);
                    t4.setHeight(50 * dip);
                    t5.setHeight(50 * dip);
                    t1.setPadding(10*dip, 0, 0, 0);
                    t2.setPadding(15*dip, 0, 0, 0);
                    t3.setPadding(25*dip, 0, 0, 0);
                    t4.setPadding(25*dip, 0, 0, 0);
                    t5.setPadding(25*dip, 0, 0, 0);

                    row.addView(t1);
                    row.addView(t2);
                    row.addView(t3);
                    row.addView(t4);
                    row.addView(t5);

                    time_table.addView(row, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                }

            }
        }
        /**
         * A dummy fragment representing a section of the app, but that simply displays dummy text.
         */
        public static class DummySectionFragment extends Fragment {
            public DummySectionFragment(){}

            public static final String ARG_SECTION_NUMBER = "section_number";

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_idea, container, false);
                //Bundle args = getArguments();
                //((TextView) rootView.findViewById(android.R.id.text1)).setText(
                //        getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));

                return rootView;
            }

            @Override
            public void onActivityCreated(Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);

                final ListView listview = (ListView) getActivity().findViewById(android.R.id.list);

                Idea idea;

                ArrayList<Idea> ideas = new ArrayList<Idea>();

                idea = new Idea();
                idea.setName("Presto");
                idea.setDate("1/1/2013");
                ideas.add(idea);

                idea = new Idea();
                idea.setName("Pizza Bogo");
                idea.setDate("1/7/1985");
                ideas.add(idea);

                idea = new Idea();
                idea.setName("Cyclone Pita");
                idea.setDate("5/7/2013");
                ideas.add(idea);

                idea = new Idea();
                idea.setName("Panera");
                idea.setDate("5/2/2014");
                ideas.add(idea);

                idea = new Idea();
                idea.setName("Brown Bag Burgers");
                idea.setDate("9/6/2013");
                ideas.add(idea);
                listview.setAdapter(new IdeaAdapter(getAppContext(), ideas));

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo_huddle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home){ //necessary for API 14 only
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_demo_huddle, container, false);
            return rootView;
        }
    }
    public void openSettings() {
        // Do something in response to button
        Intent intent = new Intent(this, PushSettings.class);
        startActivity(intent);

    }

}
