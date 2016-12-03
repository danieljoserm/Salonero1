package main.salonero1.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import main.salonero1.R;
import main.salonero1.clases.menuitem;


public class adaptermenuitem extends RecyclerView.Adapter<adaptermenuitem.ViewHolder>  {

    List<menuitem> menuitems;

    public adaptermenuitem(List<menuitem> menuitems12) {
        super();

        menuitems=menuitems12;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menuitem_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final menuitem menuitem = menuitems.get(i);


        viewHolder.nombremenuitem12.setText( menuitem.getNombremenuitem());
       viewHolder.precio.setText("Precio:" + Integer.toString(menuitem.getPrecio()));
       viewHolder.cantidad.setText("Cantidad:"+ Integer.toString(menuitem.getCantidad()));
        final int posicionj=i;



        viewHolder.buttonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuitems.get(posicionj).setCantidad(menuitems.get(posicionj).getCantidad()+1);

                notifyDataSetChanged();
            }
        });


        viewHolder.buttonmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(menuitems.get(posicionj).getCantidad()!=0)
                {
                    menuitems.get(posicionj).setCantidad(menuitems.get(posicionj).getCantidad() - 1);

                }

                notifyDataSetChanged();


            }
        });









    }

    @Override
    public int getItemCount() {

        return menuitems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombremenuitem12;
        public TextView cantidad;
        public TextView precio;
        public Button buttonmas;
        public Button buttonmenos;

        public ViewHolder(View itemView) {
            super(itemView);

           nombremenuitem12 = (TextView) itemView.findViewById(R.id.Nombremenuitem);
           cantidad = (TextView) itemView.findViewById(R.id.Cantidadmenuitem);
           precio=(TextView) itemView.findViewById(R.id.Preciomenuitem);
            buttonmas = (Button) itemView.findViewById(R.id.buton_mas_menuitem);
            buttonmenos = (Button) itemView.findViewById(R.id.buton_menos_menuitem);


        }
    }
}