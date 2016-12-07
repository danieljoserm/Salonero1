package main.salonero1.Tabs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.salonero1.R;

/**
 * Created by User on 12/6/2016.
 */
public class dialogcuenta extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_cuenta, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}
