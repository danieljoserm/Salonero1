package main.salonero1.Tabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.R;
import main.salonero1.clases.menuitem;


/**
 * Created by Dani on 05/08/2015.
 */
public class Tab1 extends Fragment implements Observer {
String texto;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
   static adaptermenuitem Adapter;

    List<menuitem> menuitems1;

    List<menuitem> menucategoria;

    Button button;

    @Override
    public void update(Observable observable, Object data) {
        View root = getView();
     //   Adapter.notifyDataSetChanged();
        // Update your views here.
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);

        readBundle(getArguments());


        menucategoria= new ArrayList<menuitem>();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("actualizar"));

        for(int i=0;i<menuitems1.size();i++){

            if((menuitems1.get(i).getCategoria()).equals(texto)){
                menucategoria.add(menuitems1.get(i));

            }

       }




        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview_menuitem);
        mRecyclerView.setHasFixedSize(true);

        //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
        // mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Adapter = new adaptermenuitem(menucategoria,getActivity(),menuitems1);
        //
        mRecyclerView.setAdapter(Adapter);










        return rootView;
    }


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            Adapter.notifyDataSetChanged();

        }
    };



            @Override
    public void onResume() {
        super.onResume();





}



    static public void refresh(){
     Adapter.notifyDataSetChanged();
    }

    static public void destroy(){
      Adapter.clearData();
    }







    public static Tab1 newInstance( String message, List<menuitem> menu)
    {
        Tab1 fragment = new Tab1();
        Bundle bundle = new Bundle(2);
        bundle.putString("mensaje", message);
        bundle.putSerializable("menuitems", (Serializable) menu);
        fragment.setArguments(bundle);
        return fragment ;
    }

    public void readBundle(Bundle bundle) {
        if (bundle != null) {
            texto = bundle.getString("mensaje");
            menuitems1= (List<menuitem>)bundle.getSerializable("menuitems");

        }
    }







}




