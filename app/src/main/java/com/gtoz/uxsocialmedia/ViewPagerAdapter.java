package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GtoZ on 11/22/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
//        switch(position) {
//            case 0: return new SettingsFragment();
//            case 1: return new MyLocationsFragment();
//            case 2: return new RootFragment();
//            case 3: return new PoiFragment();
//            case 4: return new QrReaderFragment();
//            default: return new StaticFragment();
//        }

        if(position == 2) {
            return new RootFragment();
        }
        else {
            return mFragmentList.get(position);
        }
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // return null to display only the icon
        return null;
    }
}