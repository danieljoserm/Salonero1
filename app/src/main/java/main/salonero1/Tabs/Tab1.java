package main.salonero1.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import main.salonero1.R;


/**
 * Created by Dani on 05/08/2015.
 */
public class Tab1 extends Fragment {
String texto;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);

        readBundle(getArguments());

        TextView prueba = (TextView) v.findViewById(R.id.Textviewfragmento1);
        prueba.setText(texto);


        return v;
    }




    public static Tab1 newInstance( String message)
    {
        Tab1 fragment = new Tab1();
        Bundle bundle = new Bundle(2);
        bundle.putString("mensaje", message);
        fragment.setArguments(bundle);
        return fragment ;
    }

    public void readBundle(Bundle bundle) {
        if (bundle != null) {
            texto = bundle.getString("mensaje");

        }
    }





}