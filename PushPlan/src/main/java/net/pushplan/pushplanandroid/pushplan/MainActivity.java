package net.pushplan.pushplanandroid.pushplan;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        makeList();
        setupNotify(savedInstanceState);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        if (id == R.id.action_sign_out) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void startHuddle(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DemoHuddle.class);
        startActivity(intent);

    }
    public void makeList(){
        final ListView listview = (ListView) findViewById(R.id.listView);

        Huddle huddle;

        ArrayList<Huddle> huddles = new ArrayList<Huddle>();

        huddle = new Huddle();
        huddle.setName("Lunch Friends");
        huddle.setDate("1/1/2013");
        huddles.add(huddle);

        huddle = new Huddle();
        huddle.setName("Work Buddies");
        huddle.setDate("1/7/1985");
        huddles.add(huddle);

        huddle = new Huddle();
        huddle.setName("Band");
        huddle.setDate("5/7/2013");
        huddles.add(huddle);

        huddle = new Huddle();
        huddle.setName("College Peeps");
        huddle.setDate("5/2/2014");
        huddles.add(huddle);

        huddle = new Huddle();
        huddle.setName("HS Reunion");
        huddle.setDate("9/6/2013");
        huddles.add(huddle);
        listview.setAdapter(new DashAdapter(this, huddles));

    }
    protected void setupNotify(Bundle savedInstanceState){
        // Notification ID to allow for future updates
        final int MY_NOTIFICATION_ID = 1;

        // Notification Count
        final int[] mNotificationCount = {0};

        // Notification Text Elements
        final CharSequence tickerText = "This is a Really, Really, Super Long Notification Message!";
        final CharSequence contentTitle = "Notification";
        final CharSequence contentText = "Lunch Friends: ";

        // Notification Action Elements
        Intent mNotificationIntent;
        final PendingIntent mContentIntent;

        final SpannableStringBuilder exampleItem = new SpannableStringBuilder();
        exampleItem.append("Dummy");
        exampleItem.setSpan(new ForegroundColorSpan(Color.WHITE), 0, exampleItem.length(), 0);
        exampleItem.append("   Example content");

        // Notification Sound and Vibration on Arrival
        final Uri soundURI = Uri
                .parse("android.resource://net.pushplan.pushplanandroid.pushplan/"
                        + R.raw.click_on);
        final long[] mVibratePattern = { 0, 200, 200, 300 };

        final RemoteViews mContentView = new RemoteViews(
                "net.pushplan.pushplanandroid.pushplan",
                R.layout.custom_notification);

        mNotificationIntent = new Intent(getApplicationContext(),
                DemoHuddle.class);
        mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        final Button button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                // Define the Notification's expanded message and Intent:

                mContentView.setTextViewText(R.id.text, contentText + " ("
                        + ++mNotificationCount[0] + ")");

                // Build the Notification

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                        getApplicationContext())
                        // Set appropriate defaults for the notification light, sound,
                        // and vibration.
                        .setDefaults(Notification.DEFAULT_ALL)
                        // Use a default priority (recognized on devices running Android
                        // 4.1 or later)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Show a number. This is useful when stacking notifications of
                        // a single type.
                        .setNumber(1)
                        .setContentTitle("test1")
                        .setTicker(tickerText)
                        .setSmallIcon(R.drawable.pushplan_logo48)
                        .setAutoCancel(true)
                        .setContentIntent(mContentIntent)
                        .setSound(soundURI)
                        .setVibrate(mVibratePattern)
                        // Show an expanded list of items on devices running Android 4.1
                        // or later.
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("1")
                                .addLine("2")
                                .addLine(exampleItem)
                                .addLine(exampleItem)
                                .setBigContentTitle("dummy Title")

                                .setSummaryText("Dummy summary text"))

                        .setContent(mContentView);

                // Pass the Notification to the NotificationManager:
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(MY_NOTIFICATION_ID,
                        notificationBuilder.build());

            }

        });


    }
    public void openSettings() {
        // Do something in response to button
        Intent intent = new Intent(this, PushSettings.class);
        startActivity(intent);

    }
}

