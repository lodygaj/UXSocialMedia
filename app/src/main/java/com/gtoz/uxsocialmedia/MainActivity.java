package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private FrameLayout contentView;
    private LinearLayout categoryView;
    private NavigationView navigationView;
    private ImageButton categoryShowBtn;
    boolean categoryShown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize objects from layouts
        categoryView = (LinearLayout) findViewById(R.id.categoryView);
        contentView = (FrameLayout) findViewById(R.id.flContent);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        categoryShowBtn = (ImageButton) findViewById(R.id.imageTest2);

        // Set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thrifty");

        // Set grid fragment in main content layout
        GridFragment gridFrag = new GridFragment();
        setFragment(gridFrag);

        // Set up navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Handle image button to open and close category view
        categoryShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoryShown) {
                    // Start animation and shift view down
                    categoryView.animate().translationY(categoryView.getHeight() - categoryShowBtn.getHeight());
                    categoryShown = false;
                }
                else {
                    // Start animation and shift view back up
                    categoryView.animate().translationY(0);
                    categoryShown = true;
                }
            }
        });

        // Set up category horizontal list view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.category_grid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoryListAdapter adapter = new CategoryListAdapter(this, fm);
        recyclerView.setAdapter(adapter);
        ListSpacing dec = new ListSpacing(5, 1);
        recyclerView.addItemDecoration(dec);
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
            GridFragment gridFrag = new GridFragment();
            setFragment(gridFrag);
        } else if (id == R.id.nav_my_locations) {
            MyLocationsFragment myLocationsFrag = new MyLocationsFragment();
            setFragment(myLocationsFrag);
        } else if (id == R.id.nav_my_account) {
            MyAccountFragment myAccountFrag = new MyAccountFragment();
            setFragment(myAccountFrag);
        }
        else if (id == R.id.nav_settings) {
            SettingsFragment settingsFrag = new SettingsFragment();
            setFragment(settingsFrag);
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
}