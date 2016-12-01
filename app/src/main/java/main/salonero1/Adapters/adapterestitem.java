package main.salonero1.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import main.salonero1.R;
import main.salonero1.clases.Restau;
import main.salonero1.clases.menuitem;

/**
 * Created by User on 11/30/2016.
 */
public class adapterestitem extends RecyclerView.Adapter<adapterestitem.ViewHolder>  {

    List<Restau> Restaurante1;


    public adapterestitem(List<Restau> Restaurante) {
        super();

        Restaurante1=Restaurante;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rest_item, viewGroup, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Restau Restaurante2 = Restaurante1.get(i);

        viewHolder.Nombre.setText(Restaurante2.getNombre());



    }

    @Override
    public int getItemCount() {

        return Restaurante1.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView Nombre;


        public ViewHolder(View itemView) {
            super(itemView);

            Nombre = (TextView) itemView.findViewById(R.id.nombrerest);


        }
    }





}
