package main.salonero1.Tabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.salonero1.R;

/**
 * Created by Edwin on 15/02/2015.
 */
public class FragmentCargando extends Fragment {

    public FragmentCargando(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentcargando,container,false);


        return v;
    }
}