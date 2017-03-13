package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private NavigationView navigationView;
    private GoogleApiClient client;

    private Toolbar topToolbar;
    public static TabLayout tabLayout;
    private int[] tabIcons = {R.drawable.create_story_unselected,
            R.drawable.heart_unselected, R.drawable.home_unselected,
            R.drawable.poi_unselected, R.drawable.qr_unselected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        // Set up top toolbar
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("Thrifty");

        // Set initial fragment
        setFragment(new DiscoveryFragment());

        // Set up tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // Add icons
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[0]).setText("Create"));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[1]).setText("Favorites"));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[2]).setText("Discovery"));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[3]).setText("Poi"));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[4]).setText("Qr"));
        // Set indicator to initial position
        tabLayout.setScrollPosition(2, 0, false);
        // Set tab text color to thrifty grey
        tabLayout.setTabTextColors(getResources().getColor(R.color.thriftyGrey), getResources().getColor(R.color.thriftyGrey));
        // Set click listener for tabs
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // Load fragment
                if (position == 0)
                    setFragment(new CreateStoryFragment());
                else if (position == 1)
                    setFragment(new MyLocationsFragment());
                else if (position == 2)
                    setFragment(new DiscoveryFragment());
                else if (position == 3) {
                    WebsiteFragment websiteFragment = new WebsiteFragment();
                    setFragment(websiteFragment);
                    websiteFragment.setUrl("http://www.roadtrippers.com");
                }
                else if (position == 4)
                    setFragment(new QrReaderFragment());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // Load fragment
                if (position == 0)
                    setFragment(new CreateStoryFragment());
                else if (position == 1)
                    setFragment(new MyLocationsFragment());
                else if (position == 2)
                    setFragment(new DiscoveryFragment());
                else if (position == 3) {
                    WebsiteFragment websiteFragment = new WebsiteFragment();
                    setFragment(websiteFragment);
                    websiteFragment.setUrl("http://www.roadtrippers.com");
                }
                else if (position == 4)
                    setFragment(new QrReaderFragment());
            }
        });

        // Set up navigation drawer
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, topToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // If this is the last fragment in the stack, finish and back out of the app
            if (fm.getBackStackEntryCount() == 1)
                finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            setFragment(new DiscoveryFragment());
        } else if (id == R.id.nav_my_saved_locations) {
            setFragment(new MyLocationsFragment());

        } else if (id == R.id.nav_link_accounts) {
            Toast.makeText(this, "Link Accounts", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_update_interests) {
            Toast.makeText(this, "Clicked Update Interests", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method called to update fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.fl_content, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}