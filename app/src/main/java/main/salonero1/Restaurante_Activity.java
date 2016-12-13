package main.salonero1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import main.salonero1.Adapters.adapterestitem;
import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.clases.Restau;
import main.salonero1.clases.categorias;
import main.salonero1.clases.menuitem;
import main.salonero1.webservice.Constantes;
import main.salonero1.webservice.VolleySingleton;

public class Restaurante_Activity extends AppCompatActivity  implements ItemClickListener{

    Restau[] Restaurante;
    List<Restau> Restaurante1;
    private Gson gson = new Gson();

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Adapter;
    adapterestitem mAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Salonero");

        cargarDatos();



    }



    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this.getBaseContext(),"item:"+position+Restaurante1.get(position).getNombre() ,
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("Nombreresta",Restaurante1.get(position).getNombre());
        startActivity(i);

    }



    public void cargarDatos() {
        // Petición GET
        VolleySingleton.getInstance(this).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GETrestaurante,
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


                                        AlertDialog alertDialog = new AlertDialog.Builder(Restaurante_Activity.this).create();
                                        alertDialog.setTitle("Aviso");
                                        alertDialog.setMessage("No posee conexión a internet");
                                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Salir",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        android.os.Process.killProcess(android.os.Process.myPid());
                                                        System.exit(1);
                                                    }
                                                });
                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Conexiones",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                                    }
                                                });


                                        alertDialog.show();
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



                    // Obtener array "metas" Json
                    JSONArray mensaje = response.getJSONArray("restaurante");
                    // Parsear con Gson
                    Restaurante = gson.fromJson(mensaje.toString(), Restau[].class);

                    Restaurante1= new ArrayList<Restau>(Arrays.asList(Restaurante));

                    mRecyclerView = (RecyclerView)this.findViewById(R.id.recyclerview_restaurante);
                    mRecyclerView.setHasFixedSize(true);

                    //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
                    // mRecyclerView.setLayoutManager(mLayoutManager);

                    mLayoutManager = new GridLayoutManager(this, 2);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    mAdapter = new adapterestitem(Restaurante1,getBaseContext());
                    //
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setClickListener(this);







                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            this,
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d("", e.getMessage());
        }

    }







}
