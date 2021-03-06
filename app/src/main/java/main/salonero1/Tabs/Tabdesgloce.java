package main.salonero1.Tabs;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.salonero1.Adapters.adaptercuenta;
import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.LoginActivity;
import main.salonero1.MainActivity;
import main.salonero1.R;
import main.salonero1.clases.menuitem;


public class Tabdesgloce extends Fragment {

    List<menuitem> menuitems1;
    List<menuitem> pedido;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Adapter;
    TextView total;
    int sumatotal;


    private Gson gson = new Gson();

    public Tabdesgloce( )
    {

    }







    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_desgloce,container,false);

        total=(TextView) v.findViewById(R.id.totalapagarcuenta);
        readBundle(getArguments());

        sumatotal=0;
       pedido= new ArrayList<menuitem>();
        for(int i=0;i<menuitems1.size();i++){

            if((menuitems1.get(i).getCantidad()!=0)){
             pedido.add(menuitems1.get(i));
             sumatotal= (menuitems1.get(i).getPrecio()*menuitems1.get(i).getCantidad())+sumatotal;





            }


            final Button button = (Button) v.findViewById(R.id.cuentapedir);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



                    Toast.makeText(getContext(),"pidiendo",Toast.LENGTH_LONG).show();

                    String json = new Gson().toJson(pedido);


                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONObject obj = new JSONObject();

                    try {



                    obj.put("pedido",jsonArray);


                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
                }


                    System.out.println(json);

                    // Now convert the JSON string back to your java object
                  //  Type type = new TypeToken<List<DataObject>>(){}.getType();
                   // List<DataObject> inpList = new Gson().fromJson(json, type);
                    //for (int i=0;i<inpList.size();i++) {
                      //  DataObject x = inpList.get(i);
                       // System.out.println(x);
                   // }

                }
            });

        }

        total.setText("Total a pagar:"+ Integer.toString(sumatotal));

        if(pedido!=null) {

            mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_cuenta);
            mRecyclerView.setHasFixedSize(true);

            //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
            // mRecyclerView.setLayoutManager(mLayoutManager);

            mLayoutManager = new GridLayoutManager(v.getContext(), 1);
            mRecyclerView.setLayoutManager(mLayoutManager);

           // Adapter = new adaptercuenta(pedido,);
            //
            mRecyclerView.setAdapter(Adapter);

        }
        else

        {



            Toast.makeText(getActivity(),"Su lista de pedidos se encuentra vacia" ,Toast.LENGTH_SHORT).show();
        }



        return v;
    }





    @Override
    public void onViewCreated( final View view, final Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );

        view.findViewById( R.id.cuentacancelar ).setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick( final View v )
                    {

                       Intent intent = new Intent("botonrevisar");

                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);


                        getActivity().getSupportFragmentManager().beginTransaction().remove(Tabdesgloce.this).commit();



                    }
                }
        );
    }






    public static Tabdesgloce newInstance( List<menuitem> menu)
    {
        Tabdesgloce fragment = new Tabdesgloce();
        Bundle bundle = new Bundle(2);

        bundle.putSerializable("menuitems", (Serializable) menu);
        fragment.setArguments(bundle);
        return fragment ;
    }

    public void readBundle(Bundle bundle) {
        if (bundle != null) {

            menuitems1= (List<menuitem>)bundle.getSerializable("menuitems");

        }
    }











}