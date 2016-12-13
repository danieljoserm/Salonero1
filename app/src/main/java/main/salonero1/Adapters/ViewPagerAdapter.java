package main.salonero1.Adapters;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.salonero1.Tabs.FragmentObserver;
import main.salonero1.Tabs.Tab1;
import main.salonero1.clases.menuitem;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
   // List<String> palabras;
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    List<menuitem> Menu;
    private static HashMap<Integer, Tab1> mPageReferenceMap=new HashMap<Integer, Tab1>();
    private Observable mObservers = new FragmentObserver();

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb,    List<menuitem> menu) {
        super(fm);
       Menu= new ArrayList<menuitem>();
         for(int i=0;i<menu.size();i++) {
            Menu.add(menu.get(i));
        }


        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;


    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Tab1 getItem( int position ) {

        //mObservers.deleteObservers(); // Clear existing observers.


            Tab1 tab1 = Tab1.newInstance(Titles[position].toString(),Menu);

        if(tab1 instanceof Observer)
            mObservers.addObserver((Observer) tab1);
        mPageReferenceMap.put(position, tab1);
        return tab1;


    }

    public Tab1 getFragment(int position) {
        return mPageReferenceMap.get(position);
    }


    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
        mPageReferenceMap.remove(position);
    }






    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;


    }


    public void updateFragments() {
        mObservers.notifyObservers();
    }
}