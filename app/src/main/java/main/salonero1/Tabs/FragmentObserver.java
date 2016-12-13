package main.salonero1.Tabs;

import java.util.Observable;

/**
 * Created by User on 12/13/2016.
 */

public class FragmentObserver extends Observable {
    @Override
    public void notifyObservers() {
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}
