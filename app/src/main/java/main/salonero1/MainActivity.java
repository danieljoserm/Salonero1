package main.salonero1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import main.salonero1.clases.categorias;
import main.salonero1.slidingconfig.SlidingTabLayout;
import main.salonero1.Adapters.ViewPagerAdapter;
import main.salonero1.webservice.Constantes;
import main.salonero1.webservice.VolleySingleton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //
    //web service
    private Gson gson = new Gson();

    categorias[] categorias;


    List<categorias> categoriaslista;

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Entradas","Bebidas","Postres","Platos Fuertes","Cocteles" };
    int Numboftabs =5;
    //fdf

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




      cargarDatos();





















    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cuenta) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void cargarDatos() {
        // Petición GET
        VolleySingleton.getInstance(this).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GETcategorias,
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


                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
                    JSONArray mensaje = response.getJSONArray("categorias");
                    // Parsear con Gson
                    categorias = gson.fromJson(mensaje.toString(), categorias[].class);

                    categoriaslista= new ArrayList<categorias>(Arrays.asList(categorias));

                    List<CharSequence> conversion=new ArrayList<CharSequence>() ;


                    for(int i=0;i<categoriaslista.size();i++) {
                                conversion.add(categoriaslista.get(i).getNombre());

                    }

           final CharSequence[] Titles1=conversion.toArray(new CharSequence[conversion.size()]);

                    adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles1,categoriaslista.size());

                    // Assigning ViewPager View and setting the adapter
                    pager = (ViewPager) findViewById(R.id.pager);
                    pager.setAdapter(adapter);

                    // Assiging the Sliding Tab Layout View
                    tabs = (SlidingTabLayout) findViewById(R.id.tabs);
                    tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
                    // tabs.setSelectedIndicatorColors(R.color.fondos);
                    // Setting Custom Color for the Scroll bar indicator of the Tab View

                    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                        @Override
                        public int getIndicatorColor(int position) {
                            return getResources().getColor(R.color.colorAccent);
                        }
                    });

                    // Setting the ViewPager For the SlidingTabsLayout
                    tabs.setViewPager(pager);


                    //System.out.println(Arrays.toString(Titles1));

                   // cupones = new ArrayList<Cupon1>(Arrays.asList(cupones1));
                   // mAdapter = new adaptercupones(cupones);
                   // mRecyclerView.setAdapter(mAdapter);


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
