package com.reindeercrafts.navdrawerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.reindeercrafts.navdrawerdemo.fragments.DrawerFragment;
import com.reindeercrafts.navdrawerdemo.fragments.MainFragment;

public class MainActivity extends Activity
        implements DrawerFragment.OnDrawerFragmentItemClickedListener {


    private DrawerLayout mDrawerLayout;

    // Action bar drawer toggle controls the navigation drawer icon in action bar.
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerFragment mDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();

    }

    /**
     * Initialize DrawerLayout.
     */
    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle =
                new ActionBarDrawerToggle(this,             /* Host activity */
                        mDrawerLayout,                      /* DrawerLayout object */
                        R.drawable.ic_navigation_drawer,    /* Nav drawer icon to replace 'Up'*/
                        R.string.open_drawer,               /* "Open drawer" description */
                        R.string.close_drawer) {            /* "Close drawer" description */


                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        // TODO: Add controls to the action bar icons.
                        // Google suggests developers to hide action bar icons when navigation
                        // drawer is open.
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        // TODO: Add controls to the action bar icons.
                        // Show the action bar icons when the navigation drawer is closed.
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Add these two lines to enable the use of DrawerLayout icon on action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // Setup fragments.
        mDrawerFragment = new DrawerFragment();

        // Setup the listener for passing click action.
        mDrawerFragment.setOnDrawerFragmentItemClickedListener(this);

        MainFragment mainFragment = setupMainFragment(1);

        // Tell the activity to load these two fragments.
        getFragmentManager().beginTransaction()
                .replace(R.id.drawer_content_container, mDrawerFragment)
                .replace(R.id.main_content_container, mainFragment).commit();

    }

    /**
     * Create new MainFragment instance that displays
     * given index.
     *
     * @param index Integer index used to be displayed on the screen
     * @return new MainFragment instance.
     */
    private MainFragment setupMainFragment(int index) {
        MainFragment mainFragment = new MainFragment();

        // Set the initial index to 1.
        Bundle bundle = new Bundle();
        bundle.putInt(MainFragment.VIEW_INDEX_ARG, index);

        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    /**
     * We have to override onPostCreate method and call ActionBarDrawerToggle.syncState()
     * method to display the correct navigation drawer icon on action bar. Otherwise the
     * action bar will only display the "up" caret.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemClicked(int position) {
        // When the items in navigation drawer is clicked, we reload a new MainFragment
        // instance showing the corresponding index.
        MainFragment newMainFragment = setupMainFragment(position + 1);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_content_container, newMainFragment).commit();

        // Find the container view for drawer fragment. We'll pass it to
        // DrawerLayout.closeDrawer() function to close the navigation drawer
        // when the item is clicked.
        View drawerView = findViewById(R.id.drawer_content_container);

        mDrawerLayout.closeDrawer(drawerView);

    }
}
