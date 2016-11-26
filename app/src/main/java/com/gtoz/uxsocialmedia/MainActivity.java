package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private NavigationView navigationView;
    private ImageView favorite, discovery, settings, qr, poi;
    private GoogleApiClient client;
    private FrameLayout flContent;

    private Toolbar topToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {R.drawable.settings_unselected,
            R.drawable.heart_unselected, R.drawable.home_unselected,
            R.drawable.poi_unselected, R.drawable.qr_unselected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up top toolbar
        topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("Thrifty");

        // Set up view pager for sliding navigation
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter vAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vAdapter.addFragment(new SettingsFragment());
        vAdapter.addFragment(new MyLocationsFragment());
        vAdapter.addFragment(new DiscoveryFragment());
        vAdapter.addFragment(new GridFragment());
        vAdapter.addFragment(new QrReaderFragment());
        viewPager.setAdapter(vAdapter);

        // Set up tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // Attach to viewPager
        tabLayout.setupWithViewPager(viewPager);
        // Add icons
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

        // Set discovery fragment on app start
        viewPager.setCurrentItem(2);

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
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            DiscoveryFragment menuFrag = new DiscoveryFragment();
            setFragment(menuFrag);
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_my_saved_locations) {
            GridFragment gridFrag = new GridFragment();
            setFragment(gridFrag);
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_link_accounts) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_update_interests) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);
            viewPager.setCurrentItem(0);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method called to update fragment
    public void setFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
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