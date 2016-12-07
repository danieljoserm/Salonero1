package main.salonero1;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import main.salonero1.Tabs.Tabdesgloce;
import main.salonero1.clases.categorias;
import main.salonero1.clases.menuitem;
import main.salonero1.slidingconfig.SlidingTabLayout;
import main.salonero1.Adapters.ViewPagerAdapter;
import main.salonero1.webservice.Constantes;
import main.salonero1.webservice.VolleySingleton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //
    //web service
    private Gson gson = new Gson();

    private Menu menuchange;
    List<menuitem> menu;
    menuitem[] menulista;

    String Restnombre;
    DrawerLayout Drawer;

    List<categorias> categoriaslista;
    categorias[] categorias;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
   // CharSequence Titles[]={"Entradas","Bebidas","Postres","Platos Fuertes","Cocteles" };
    //int Numboftabs =5;
    //fdf
    //botones








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.cuenta));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        Intent i= getIntent();
        Bundle b = i.getExtras();


        menu=new ArrayList<menuitem>();
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));



        if(b!=null)
        {
            Restnombre =(String) b.get("Nombreresta");

        }
        Toast.makeText(this.getBaseContext(),Restnombre ,
                Toast.LENGTH_SHORT).show();


        getSupportActionBar().setTitle(Restnombre);



      cargarDatos();





















    }



    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            int index = Integer.parseInt(intent.getStringExtra("posicion"));
            int  cantidad = Integer.parseInt(intent.getStringExtra("cantidad"));

            menu.get(index).setCantidad(cantidad);


            Toast.makeText(MainActivity.this,"probando"+"cantidad:"+cantidad+"posicion:"+index ,Toast.LENGTH_SHORT).show();
            //global = (List<Cupon>) i.getSerializableExtra("LIST");



        }
    };

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
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(MainActivity.this,"hola",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSupportFragmentManager().beginTransaction().setTransition(
                                FragmentTransaction.TRANSIT_NONE
                        ).add(R.id.DrawerLayout, new Tabdesgloce()).addToBackStack("transition").commit();
                    }
                }, 100);


                return true;
        }

        return super.onKeyDown(keycode, e);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.carritocompra) {

            Toast.makeText(MainActivity.this,"hola" ,Toast.LENGTH_SHORT).show();



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
                                Constantes.GETmenu,
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
                    JSONArray mensaje = response.getJSONArray("menu");
                    // Parsear con Gson

                    menulista= gson.fromJson(mensaje.toString(), menuitem[].class);

                    menu=new ArrayList<menuitem>(Arrays.asList(menulista));


                    List<CharSequence> categoria=new ArrayList<CharSequence>();

                    for(int i=0;i<menu.size();i++)

                    {
                        if(categoria.contains(menu.get(i).getCategoria())){



                        }

                        else

                        {
                            categoria.add(menu.get(i).getCategoria());

                        }

                    }


                    final CharSequence[] Titles1=categoria.toArray(new CharSequence[categoria.size()]);

                   // List<CharSequence> conversion=new ArrayList<CharSequence>() ;








                    adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles1,categoria.size(),menu);

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
