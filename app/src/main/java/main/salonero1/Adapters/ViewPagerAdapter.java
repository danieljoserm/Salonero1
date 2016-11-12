package main.salonero1.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import main.salonero1.Tabs.Tab1;
import main.salonero1.Tabs.Tab2;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<String> palabras;
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, List<String> palabras1 ) {
        super(fm);

        palabras = new ArrayList<String>();
        palabras.add("hola");
        palabras.add("diferente");
        palabras.add("porfa");
        palabras.add("resf");
        palabras.add("resf");
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;


    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {


            Tab1 tab1 = Tab1.newInstance(palabras.get(position));
            return tab1;


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}