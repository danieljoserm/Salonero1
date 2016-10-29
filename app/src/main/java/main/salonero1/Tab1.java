package main.salonero1;

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

import samples.exoguru.materialtabs.adapters.GridAdapter;
import samples.exoguru.materialtabs.adapters.adaptertiendas;
import samples.exoguru.materialtabs.cupones.Cupon;
import samples.exoguru.materialtabs.cupones.tiendas;
import samples.exoguru.materialtabs.webservice.VolleySingleton;

/**
 * Created by Dani on 05/08/2015.
 */
public class Tab1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);


        return v;
    }

}