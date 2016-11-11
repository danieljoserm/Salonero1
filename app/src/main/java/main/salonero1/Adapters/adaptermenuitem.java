package main.salonero1.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        menuitem menuitem = menuitems.get(i);


        viewHolder.nombremenuitem12.setText(menuitem.getNombremenuitem());
       viewHolder.cantidad.setText( Integer.toString(menuitem.getCantidad()));
       viewHolder.precio.setText( Integer.toString(menuitem.getPrecio()));

    }

    @Override
    public int getItemCount() {

        return menuitems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nombremenuitem12;
        public TextView cantidad;
        public TextView precio;

        public ViewHolder(View itemView) {
            super(itemView);

           nombremenuitem12 = (TextView) itemView.findViewById(R.id.Nombremenuitem);
           cantidad = (TextView) itemView.findViewById(R.id.Cantidadmenuitem);
           precio=(TextView) itemView.findViewById(R.id.Preciomenuitem);


        }
    }
}