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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.R;
import main.salonero1.clases.menuitem;
import main.salonero1.webservice.Constantes;
import main.salonero1.webservice.VolleySingleton;


/**
 * Created by Dani on 05/08/2015.
 */
public class Tab1 extends Fragment {
String texto;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Adapter;
  menuitem[] menuitems;
    List<menuitem> menuitems1;
    private Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);

        readBundle(getArguments());

      //  TextView prueba = (TextView) rootView.findViewById(R.id.Textviewfragmento1);
       /// prueba.setText(texto);




        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview_menuitem);
        mRecyclerView.setHasFixedSize(true);

        //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
        // mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cargarDatos();









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



    public void cargarDatos() {
        // Petición GET
        VolleySingleton.getInstance(getActivity()).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GETitems+texto+".php",
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json



                                        procesarRespuestarecibido(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("", "Error Volley: " + error.toString());
                                    }
                                }

                        )
                );
    }


    private void procesarRespuestarecibido(JSONObject response) {


        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {



                case "1": // EXITO




                    JSONArray mensaje = response.getJSONArray("menu");

                   menuitems = gson.fromJson(mensaje.toString(), menuitem[].class);


                   menuitems1 = new ArrayList<menuitem>(Arrays.asList(menuitems));




                    Adapter = new adaptermenuitem(menuitems1);
                    //
                   mRecyclerView.setAdapter(Adapter);


                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d("", e.getMessage());
        }

    }



}