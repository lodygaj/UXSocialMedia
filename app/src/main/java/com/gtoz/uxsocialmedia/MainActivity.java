package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.gtoz.uxsocialmedia.R.id.discovery;
import static com.gtoz.uxsocialmedia.R.id.favorite;
import static com.gtoz.uxsocialmedia.R.id.poi;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private NavigationView navigationView;
    private ImageView favorite, discovery, settings, qr, poi;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize objects from layouts
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Set up top toolbar
        Toolbar topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setTitle("Thrifty");

        // Set up bottom toolbar
        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.bottomToolbar);
        setSupportActionBar(bottomToolbar);
        favorite = (ImageView) findViewById(R.id.favorite);
        discovery = (ImageView) findViewById(R.id.discovery);
        settings = (ImageView) findViewById(R.id.settings);
        poi = (ImageView) findViewById(R.id.poi);
        qr = (ImageView) findViewById(R.id.qrcode);

        // Set menu fragment in main content layout
        DiscoveryFragment menuFrag = new DiscoveryFragment();
        setFragment(menuFrag);

        // Set up navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, topToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Set up click listeners for bottom toolbar buttons

        // Listener for the heart
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set grid fragment
                GridFragment fragment = new GridFragment();
                setFragment(fragment);
                // Update selected toolbar item
                favorite.setImageResource(R.drawable.heart_selected);
                discovery.setImageResource(R.drawable.home_unselected);
                settings.setImageResource(R.drawable.settings_unselected);
                poi.setImageResource(R.drawable.poi_unselected);
                qr.setImageResource(R.drawable.qr_unselected);
            }
        });

        // Listener for home
        discovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set menu fragment
                DiscoveryFragment fragment = new DiscoveryFragment();
                setFragment(fragment);

                // Update selected toolbar item
                favorite.setImageResource(R.drawable.heart_unselected);
                discovery.setImageResource(R.drawable.home_selected);
                settings.setImageResource(R.drawable.settings_unselected);
                poi.setImageResource(R.drawable.poi_unselected);
                qr.setImageResource(R.drawable.qr_unselected);
            }
        });

        // Listener for settings
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set settings fragment
                SettingsFragment fragment = new SettingsFragment();
                setFragment(fragment);
                // Update selected toolbar item
                favorite.setImageResource(R.drawable.heart_unselected);
                discovery.setImageResource(R.drawable.home_unselected);
                settings.setImageResource(R.drawable.settings_selected);
                poi.setImageResource(R.drawable.poi_unselected);
                qr.setImageResource(R.drawable.qr_unselected);
            }
        });

        //Listener for camera (qr scanner)
        poi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Update selected toolbar item
                favorite.setImageResource(R.drawable.heart_unselected);
                discovery.setImageResource(R.drawable.home_unselected);
                settings.setImageResource(R.drawable.settings_unselected);
                //poi.setImageResource(R.drawable.poi_selected);
               // qr.setImageResource(R.drawable.qr_unselected);
            }
        });

        //Listener for camera (qr scanner)
        qr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Update selected toolbar item
                favorite.setImageResource(R.drawable.heart_unselected);
                discovery.setImageResource(R.drawable.home_unselected);
                settings.setImageResource(R.drawable.settings_unselected);
                //poi.setImageResource(R.drawable.poi_unselected);
                //qr.setImageResource(R.drawable.qr_selected);

                Intent mainIntent = new Intent(MainActivity.this, QrReaderActivity.class);
                startActivity(mainIntent);

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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

        // Add search bar to toolbar
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            // Handle search bar here


            return true;
        }
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
            //GridFragment gridFrag = new GridFragment();
            //setFragment(gridFrag);

            // Update selected toolbar item
            favorite.setImageResource(R.drawable.heart_unselected);
            discovery.setImageResource(R.drawable.home_selected);
            settings.setImageResource(R.drawable.settings_unselected);
        } else if (id == R.id.nav_my_saved_locations) {
            GridFragment gridFrag = new GridFragment();
            setFragment(gridFrag);

            // Update selected toolbar item
            favorite.setImageResource(R.drawable.heart_selected);
            discovery.setImageResource(R.drawable.home_unselected);
            settings.setImageResource(R.drawable.settings_unselected);
        } else if (id == R.id.nav_link_accounts) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);

            // Update selected toolbar item
            favorite.setImageResource(R.drawable.heart_unselected);
            discovery.setImageResource(R.drawable.home_unselected);
            settings.setImageResource(R.drawable.settings_selected);
        } else if (id == R.id.nav_update_interests) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);

            // Update selected toolbar item
            favorite.setImageResource(R.drawable.heart_unselected);
            discovery.setImageResource(R.drawable.home_unselected);
            settings.setImageResource(R.drawable.settings_selected);
        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);

            // Update selected toolbar item
            favorite.setImageResource(R.drawable.heart_unselected);
            discovery.setImageResource(R.drawable.home_unselected);
            settings.setImageResource(R.drawable.settings_selected);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method called to update fragment
    public void setFragment(Fragment fragment) {
        fm = getFragmentManager();
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