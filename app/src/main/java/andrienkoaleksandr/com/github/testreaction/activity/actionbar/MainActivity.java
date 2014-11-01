package andrienkoaleksandr.com.github.testreaction.activity.actionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.GameThread;
import andrienkoaleksandr.com.github.testreaction.fragment.ControlFragment;
import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.fragment.Settings;
import andrienkoaleksandr.com.github.testreaction.view.SmartButton;

/**
 * Created by Andrienko Alexander on 26.10.2014.
 *
 */
public class MainActivity extends ActionBarActivity {

    private static TableRow controlRow;

    private static ControlFragment controlFragment;

    private FragmentManager fm = getFragmentManager();

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private static int AmountRow = 4;
    private static int amountElementsOfRow = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.drawer_activity);

        controlRow = (TableRow) findViewById(R.id.control_row);

//        addControlFragment();

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
//            selectItem(0);
        }
    }


    public void addControlFragment() {
        if(findViewById(R.id.content_row) != null) {
            controlFragment = ControlFragment.newInstance();
            controlFragment.onDetach();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(controlRow.getId(), controlFragment);
            ft.commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        addControlFragment();
    }

    @Override
    protected void onPause() {
        super.onPause();
        controlRow.getChildCount();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(controlFragment);
        ft.commit();
    }

    // Add ActionBar Menu with dropdown of game speed
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // set speed of game
        switch (item.getItemId()) {
            case R.id.item0:
                GameThread.setSpeed(Constant.SLOW);
                return true;
            case R.id.item1:
                GameThread.setSpeed(Constant.NORMAL);
                return true;
            case R.id.item2:
                GameThread.setSpeed(Constant.FAST);
                return true;
            case R.id.item3:
                GameThread.setSpeed(Constant.NIGHTMARE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public static int getAmountRow() {
        return AmountRow;
    }

    public static int getAmountElementsOfRow() {
        return amountElementsOfRow;
    }
    //Show content
    void selectItem(int id){
        FragmentTransaction ft = null;
        switch (id){
            case 0:
                Intent intent = new Intent(this, andrienkoaleksandr.com.github.testreaction.activity.actionbar.SecondActivity.class);
                startActivity(intent);
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Test1", Toast.LENGTH_SHORT);
                break;
            case 2:
                //delete all content
                clearActivity();
                ft = fm.beginTransaction();
                Fragment fm = Settings.newInstance("test", "test");
                ft.replace(R.id.content_row, fm);
                ft.commit();
                Toast.makeText(getApplicationContext(), "Test2", Toast.LENGTH_SHORT);
                break;
//                Intent intent = new Intent(this, ua.org.horishniy.geekhub.geekhubandroidhome01.appInterface.Activities.AnimationActivity.class);
//                startActivity(intent);
            default:
//                this.id = id;
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentContent = ua.org.horishniy.geekhub.geekhubandroidhome01.appInterface.Fragments.Content.setIndex(id);
//                fragmentTransaction.replace(R.id.frame_content, fragmentContent);
//                fragmentTransaction.commit();
                break;
        }
    }

    private void clearActivity() {
        ((TableRow) findViewById(R.id.content_row)).removeAllViews();
        ((TableRow) findViewById(R.id.title_row)).removeAllViews();
        ((TableRow) findViewById(R.id.control_row)).removeAllViews();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}


