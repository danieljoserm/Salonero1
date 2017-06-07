package main.salonero1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import main.salonero1.R;
import main.salonero1.clases.Restau;
import main.salonero1.clases.menuitem;

/**
 * Created by User on 12/7/2016.
 */

//sdfd
public class adaptercuenta extends RecyclerView.Adapter<adaptercuenta.ViewHolder> {


    List<menuitem> menuitems;
    Context context;


    public adaptercuenta(List<menuitem> menuitems1,Context context1) {
        super();
context=context1;
      menuitems=menuitems1;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cuenta_item_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final menuitem menuitem = menuitems.get(i);

       viewHolder.nombrecuenta.setText(menuitems.get(i).getNombremenuitem());
        viewHolder.cantidad.setText(Integer.toString(menuitems.get(i).getCantidad()));
viewHolder.precio.setText(Integer.toString(menuitems.get(i).getPrecio()));
        final int posicionj=i;

        viewHolder.buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(menuitems.get(posicionj).getCantidad()!=0) {

                    Intent intent = new Intent("restar");
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    //  intent.putExtra("cantidad",Integer.toString(menuitems.get(posicionj).getCantidad()));
                    intent.putExtra("precio", Integer.toString(menuitems.get(posicionj).getPrecio()));


                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }

                if(menuitems.get(posicionj).getCantidad()!=0)
                {
                    menuitems.get(posicionj).setCantidad(menuitems.get(posicionj).getCantidad() - 1);

                }

                    notifyDataSetChanged();



            }
            });

        viewHolder.buttonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    menuitems.get(posicionj).setCantidad(menuitems.get(posicionj).getCantidad() + 1);




                Intent intent = new Intent("sumar");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
               //intent.putExtra("cantidad",Integer.toString(menuitems.get(posicionj).getCantidad()));
                intent.putExtra("precio",Integer.toString(menuitems.get(posicionj).getPrecio()) );


                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                notifyDataSetChanged();



            }
        });

    }

    @Override
    public int getItemCount() {

        return menuitems.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombrecuenta;
        public TextView cantidad;
        public Button buttonx;
        public TextView precio;
        public Button buttonmas;

        public ViewHolder(View itemView) {
            super(itemView);

           nombrecuenta = (TextView) itemView.findViewById(R.id.textcuentaNombre);
            cantidad = (TextView) itemView.findViewById(R.id.Textcuentacantidad);
            buttonx= (Button) itemView.findViewById(R.id.botoneliminarcuenta);
            precio = (TextView) itemView.findViewById(R.id.preciocuenta);
            buttonmas = (Button) itemView.findViewById(R.id.buttonmascuenta);

        }
    }













}