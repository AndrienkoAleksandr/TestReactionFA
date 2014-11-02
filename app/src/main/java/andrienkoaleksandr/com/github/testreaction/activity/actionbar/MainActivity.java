package andrienkoaleksandr.com.github.testreaction.activity.actionbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.GameThread;
import andrienkoaleksandr.com.github.testreaction.MultiActivity;
import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.fragment.ContentFragment;
import andrienkoaleksandr.com.github.testreaction.fragment.Options;

/**
 * Created by Andrienko Alexander on 26.10.2014.
 *
 */
public class MainActivity extends ActionBarActivity {

    private FragmentManager fm = getFragmentManager();

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private static int amountRow = 4;
    private static int amountElementsOfRow = 4;
    private static int amountFlash = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.drawer_activity);

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

        if (savedInstanceState == null) {
            selectItem(0);
        }
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

    //Show content
    void selectItem(int id){
        Intent intent = null;
        Fragment fragment = null;
        ((FrameLayout) findViewById(R.id.container)).removeAllViews();
        FragmentTransaction ft = null;
        switch (id){
            case 0:
                putMainContent();
                break;
            case 1:
                intent = new Intent(this, Animation.class);
                startActivity(intent);

                putMainContent();
                break;
            case 2:
                ft = fm.beginTransaction();
                fragment = Options.newInstance();
                ft.replace(R.id.container, fragment);
                ft.commit();
                break;
            case 3:
                intent = new Intent(this, MultiActivity.class);
                startActivity(intent);

                putMainContent();
                break;
        }
    }

    private void putMainContent() {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = ContentFragment.newInstance();
        ft.replace(R.id.container, fragment);
        ft.commit();
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

    public static int getAmountRow() {
        return amountRow;
    }

    public static int getAmountElementsOfRow() {
        return amountElementsOfRow;
    }

    public static void setAmountRow(int amountRow) {
        MainActivity.amountRow = amountRow;
    }

    public static void setAmountElementsOfRow(int amountElementsOfRow) {
        MainActivity.amountElementsOfRow = amountElementsOfRow;
    }

    public static int getAmountFlash() {
        return amountFlash;
    }

    public static void setAmountFlash(int amountFlash) {
        MainActivity.amountFlash = amountFlash;
    }
}


