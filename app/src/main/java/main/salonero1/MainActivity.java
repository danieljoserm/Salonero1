package main.salonero1;


import android.app.FragmentManager;
import android.os.Build;
import  android.support.v4.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
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

import main.salonero1.Adapters.adaptercuenta;
import main.salonero1.Tabs.Dialognummesa;
import main.salonero1.Tabs.FragmentCargando;
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
List<menuitem> pedido;

    String Restnombre;
    DrawerLayout drawer;
    int cantidadtitulos;



    List<categorias> categoriaslista;
    categorias[] categorias;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
   // CharSequence Titles[]={"Entradas","Bebidas","Postres","Platos Fuertes","Cocteles" };
    //int Numboftabs =5;
    //fdf
    //botones

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Adapter;



    FragmentTransaction ft;
    FragmentCargando fragmentcargando;



    private void showdialogmesa() {
        FragmentManager fm = getFragmentManager();
        Dialognummesa dialognummesa = new Dialognummesa();
        dialognummesa.show(fm,"ingrese el numero de mesa");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.cuenta));

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);




                if(pedido!=null) {

                    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_cuenta);
                    mRecyclerView.setHasFixedSize(true);

                    //   mLayoutManager = new LinearLayoutManager(rootView.getContext());
                    // mRecyclerView.setLayoutManager(mLayoutManager);

                    mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    Adapter = new adaptercuenta(pedido);
                    //
                    mRecyclerView.setAdapter(Adapter);

                }
                else

                {



                    Toast.makeText(getApplicationContext(),"Su lista de pedidos se encuentra vacia" ,Toast.LENGTH_SHORT).show();
                }




            }
        }

                ;



        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        NavigationView rightNavigationView = (NavigationView) findViewById(R.id.nav_right_view);
        rightNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle Right navigation view item clicks here.
                int id = item.getItemId();


                drawer.closeDrawer(GravityCompat.END); /*Important Line*/
                return true;
            }
        });


        fragmentcargando= new FragmentCargando();


        Intent i= getIntent();
        Bundle b = i.getExtras();
        //showdialogmesa();

        menu=new ArrayList<menuitem>();
        pedido=new ArrayList<menuitem>();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver1,
              new IntentFilter("botonrevisar"));




        if(b!=null)
        {
            Restnombre =(String) b.get("Nombreresta");

        }
    //    Toast.makeText(this.getBaseContext(),Restnombre ,
      //          Toast.LENGTH_SHORT).show();


        getSupportActionBar().setTitle(Restnombre);






// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above



      cargarDatos();



















    }
    private void updateFragments() {
        adapter.updateFragments();
    }




    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            int index = Integer.parseInt(intent.getStringExtra("posicion"));
            int  cantidad = Integer.parseInt(intent.getStringExtra("cantidad"));

            menu.get(index).setCantidad(cantidad);


if(contained(pedido,index)){


}
else{
pedido.add(menu.get(index));
}



           // pedido.contains(menu.
            //Toast.makeText(MainActivity.this,"probando"+"cantidad:"+cantidad+"posicion:"+index ,Toast.LENGTH_SHORT).show();
            //global = (List<Cupon>) i.getSerializableExtra("LIST");



        }
    };


    public BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            pager.getAdapter().notifyDataSetChanged();



        }
    };


public boolean contained (List<menuitem> pedido, int index){

 boolean esta=false;
    for(int i = 0; i < pedido.size(); i++){

     if(pedido.get(i).getNombremenuitem().equals(menu.get(index).getNombremenuitem())){

         esta=true;
     }

    }
if(esta){

    return true;
}
else{return false;
}

}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        this.finish();
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



            return true;
            default:


            break;



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

            drawer.openDrawer(GravityCompat.END);
         //   ft = getSupportFragmentManager().beginTransaction();

// Replace the contents of the container with the new fragment
           // ft.replace(R.id.Fragmentlayout, Tabdesgloce.newInstance(menu));


            //ft.commit();




          //  FragmentManager fm = getFragmentManager();
        //    dialogcuenta dialogFragment = new dialogcuenta ();
          //  dialogFragment.show(fm, "Cuenta");





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

        } else if (id == R.id.sign_out) {

            SaveSharedPreference.clearUserName(this);

            Intent intent = new Intent(this,LoginActivity.class);
              startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void cargarDatos() {

        ft = getSupportFragmentManager().beginTransaction();

// Replace the contents of the container with the new fragment
        ft.replace(R.id.Fragmentlayout, fragmentcargando);


        ft.commit();


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

                                        getSupportFragmentManager().beginTransaction().remove(fragmentcargando).commit();

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

                    cantidadtitulos=categoria.size();
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
                            return getResources().getColor(R.color.cardview_dark_background);
                        }
                    });



                  //  tabs.setSelectedIndicatorColors(R.color.colorgris);

                    // Setting the ViewPager For the SlidingTabsLayout
                    tabs.setViewPager(pager);

                    getSupportFragmentManager().beginTransaction().remove(fragmentcargando).commit();
                    // probando con el page listener
                   // showdialogmesa();




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
