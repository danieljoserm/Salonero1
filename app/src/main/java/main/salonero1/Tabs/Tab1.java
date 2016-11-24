package main.salonero1.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.R;
import main.salonero1.clases.menuitem;


/**
 * Created by Dani on 05/08/2015.
 */
public class Tab1 extends Fragment {
String texto;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Adapter;
    List<menuitem> menuitems;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);

        readBundle(getArguments());

      //  TextView prueba = (TextView) rootView.findViewById(R.id.Textviewfragmento1);
       /// prueba.setText(texto);



        menuitems= new ArrayList<>();
        menuitem hola= new menuitem();
        hola.setnombremenuitem("Pollo con garbanzos");
        hola.setCantidad(1);
        hola.setprecio(2);


        menuitem hola2= new menuitem();
        hola2.setnombremenuitem("Casuela de queso");
        hola2.setCantidad(2);
        hola2.setprecio(234);

        menuitem hola3= new menuitem();
        hola3.setnombremenuitem("Pizza");
        hola3.setCantidad(424);
        hola3.setprecio(235);

        menuitem hola4= new menuitem();
        hola4.setnombremenuitem("Higado en salsa");
        hola4.setCantidad(256);
        hola4.setprecio(2343);


        menuitem hola5= new menuitem();
        hola5.setnombremenuitem("Paella");
        hola5.setCantidad(256);
        hola5.setprecio(2343);

        menuitem hola6= new menuitem();
        hola6.setnombremenuitem("Sopa Azteca");
        hola6.setCantidad(256);
        hola6.setprecio(2343);
        menuitem hola7= new menuitem();
        hola7.setnombremenuitem("Pastel de papa");
        hola7.setCantidad(256);
        hola7.setprecio(2343);


        menuitems.add(hola);
        menuitems.add(hola2);
        menuitems.add(hola3);
        menuitems.add(hola4);
        menuitems.add(hola5);
        menuitems.add(hola6);
        menuitems.add(hola7);



        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview_menuitem);
        mRecyclerView.setHasFixedSize(true);

     //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
       // mRecyclerView.setLayoutManager(mLayoutManager);

          mLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
         mRecyclerView.setLayoutManager(mLayoutManager);

        Adapter = new adaptermenuitem(menuitems);
        //
        mRecyclerView.setAdapter(Adapter);






        return rootView;
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