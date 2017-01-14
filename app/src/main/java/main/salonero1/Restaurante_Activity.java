package main.salonero1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import  android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import android.app.DialogFragment;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import  android.support.v4.app.FragmentTransaction;

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
import java.util.HashMap;
import java.util.List;

import main.salonero1.Adapters.adapterestitem;
import main.salonero1.Adapters.adaptermenuitem;
import main.salonero1.Tabs.Dialognummesa;
import main.salonero1.Tabs.FragmentCargando;
import main.salonero1.Tabs.Tabdesgloce;
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
    int posicionresta;

    FragmentTransaction ft;
    FragmentCargando fragmentcargando;



    private WIFIcomprobacion WIFIthread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Salonero");

        fragmentcargando= new FragmentCargando();

        cargarDatos();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {




        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.searchrest) {






            //  FragmentManager fm = getFragmentManager();
            //    dialogcuenta dialogFragment = new dialogcuenta ();
            //  dialogFragment.show(fm, "Cuenta");





            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onClick(View view, int position) {

        posicionresta=position;
        WIFIthread = new WIFIcomprobacion();
        WIFIthread.execute((Void) null);




    }



    public void cargarDatos() {



        ft = getSupportFragmentManager().beginTransaction();

// Replace the contents of the container with the new fragment
        ft.replace(R.id.Fragmentlayoutrest,   fragmentcargando);


        ft.commit();


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

                                        getSupportFragmentManager().beginTransaction().remove(fragmentcargando).commit();

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


                    getSupportFragmentManager().beginTransaction().remove(fragmentcargando).commit();





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





    public class WIFIcomprobacion extends AsyncTask<Void, Void, Boolean> {


        boolean wifipresencia=false;
        String bssidrestaurante;



        boolean sucess;









        public boolean Revisarwifi(String bssidrestaurante ) {
            WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);



            List<ScanResult> results = wifiManager.getScanResults();
            List<String> ListaBSSID=new ArrayList<String>() {
            };

            for(int i = 0; i < results.size(); i++){

                ListaBSSID.add(results.get(i).BSSID);




            }


            if (ListaBSSID.contains(bssidrestaurante)) {

                return true;
            }
            else{
                return false;
              }







        }















        @Override
        protected Boolean doInBackground(Void... params) {


           //

            //HashMap<String, String> datoslogueo = new HashMap<>();
            final WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);


            if (wifiManager.isWifiEnabled()){
                wifipresencia=true;

            }
            else
            {
                wifiManager.setWifiEnabled(true);

                try {

                    while (wifiManager.isWifiEnabled()!=true) {
                        //Wait to connect
                        Thread.sleep(1000);
                    }


                } catch (Exception e) {
                }
            }



            bssidrestaurante=Restaurante1.get(posicionresta).getBSSID();

          sucess=  Revisarwifi(bssidrestaurante);


            return sucess;

        }




        @Override
        protected void onPreExecute(){




        }

        @Override
        protected void onPostExecute(final Boolean success) {










            if (success) {




                Intent i = new Intent(Restaurante_Activity.this, MainActivity.class);
                i.putExtra("Nombreresta",Restaurante1.get(posicionresta).getNombre());
                startActivity(i);


            } else {



                AlertDialog alertDialog = new AlertDialog.Builder(Restaurante_Activity.this).create();
                alertDialog.setTitle("Aviso");
                alertDialog.setMessage("Usted no se encuentra en el restaurante");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Salir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ver el menu(no pedidos)",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Restaurante_Activity.this, MainActivity.class);
                                i.putExtra("Nombreresta",Restaurante1.get(posicionresta).getNombre());
                                startActivity(i);

                            }
                        });


                alertDialog.show();



            }
        }

        @Override
        protected void onCancelled() {
            // mAuthTask = null;

        }
    }





}
