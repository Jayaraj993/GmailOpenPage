package com.emdsys.android.gmailopenpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
        static int a=0;
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



    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(position==0) {
            a=0;
            fragmentManager.beginTransaction()
                    .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                    .commit();
        }else if (position==1){
            a=1;
            fragmentManager.beginTransaction()
                    .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                    .commit();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ArrayList<ListData> myList = new ArrayList<ListData>();
        ArrayList<ListData> myList1 = new ArrayList<ListData>();
        ListView listView1;
        TextView profile_name;

        String[] title = new String[]{
                "Jayaraj", "Ramesh", "Venketesh", "Vijay",
                "Sathish", "Kalaivanan", "Robert Rajkumar", "Arun Kumar","Sathish", "Kalaivanan", "Robert Rajkumar", "Arun Kumar"
        };
        String[] desc = new String[]{
                "I am Busy", "Have fun guys", "Do u love me", "I hate girls",
                "Have u excercise today", "sleeping", "At home", "Where there is a will there is a way",
                "Happy Moments", "I am Lazy", "I will do", "singing"
        };
        int[] img = new int[]{
                R.drawable.star1, R.drawable.star2, R.drawable.star3, R.drawable.star4,
                R.drawable.star5, R.drawable.star6, R.drawable.star7, R.drawable.star8,
                R.drawable.star5, R.drawable.star6, R.drawable.star7, R.drawable.star8
        };

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
            listView1= (ListView) rootView.findViewById(R.id.gmail_list);

            if (a==0){
                getDataInList();
                listView1.setAdapter(new ListviewAdapter(getActivity(),myList));
            }else if (a==1){
                getDataInListReverse();
                listView1.setAdapter(new ListviewAdapter(getActivity(),myList1));
            }

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    profile_name= (TextView) view.findViewById(R.id.profile_name);
                    ImageView profile_image= (ImageView) view.findViewById(R.id.profile_image);

                    String name = profile_name.getText().toString();

                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    intent.putExtra("name", name);
                    profile_image.buildDrawingCache();
                    Bitmap image= profile_image.getDrawingCache();

                    Bundle extras = new Bundle();
                    extras.putParcelable("imagebitmap", image);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
        private void getDataInList() {
            for (int i = 0; i < title.length; i++) {
                // Create a new object for each list item
                ListData ld = new ListData();
                ld.setName(title[i]);
                ld.setStatus(desc[i]);
                ld.setImgId(img[i]);
                // Add this object into the ArrayList myList
                myList.add(ld);
            }
        }
        private void getDataInListReverse() {
            for (int i = title.length-1; i >=0; i--) {
                // Create a new object for each list item
                ListData ld = new ListData();
                ld.setName(title[i]);
                ld.setStatus(desc[i]);
                ld.setImgId(img[i]);
                // Add this object into the ArrayList myList
                myList1.add(ld);
            }
        }


    }
}
