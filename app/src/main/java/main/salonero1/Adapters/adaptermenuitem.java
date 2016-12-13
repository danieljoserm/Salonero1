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
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.Serializable;
import java.util.List;

import main.salonero1.R;
import main.salonero1.clases.menuitem;
import main.salonero1.webservice.VolleySingleton;


public class adaptermenuitem extends RecyclerView.Adapter<adaptermenuitem.ViewHolder>  {

    List<menuitem> menuitems;
    List<menuitem> menucompleto;
    Context context;

    private ImageLoader imageLoader;


    public adaptermenuitem(List<menuitem> menuitemscategoria,Context context1,List<menuitem> menucompleto1) {
        super();

        menuitems=menuitemscategoria;
        context=context1;
        menucompleto=menucompleto1;
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
        imageLoader = VolleySingleton.getInstance(context).getImageLoader();
        // imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        imageLoader.get(menuitem.getImagen(), ImageLoader.getImageListener(viewHolder.imagenPost,
                R.drawable.loading, android.R.drawable.ic_dialog_alert));

        viewHolder.imagenPost.setImageUrl(menuitem.getImagen(), imageLoader);
        final int posicionj=i;








        //http://epmghispanic.media.clients.ellingtoncms.com/img/photos/2014/09/24/pa_patacones_t670x470.jpg?23a6cf1936a4889561e6226c97c290c4239edcb5

        viewHolder.buttonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                menuitems.get(posicionj).setCantidad(menuitems.get(posicionj).getCantidad()+1);


                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intent.putExtra("cantidad",Integer.toString(menuitems.get(posicionj).getCantidad()));
                intent.putExtra("posicion",Integer.toString(menuitems.get(posicionj).getindex()) );
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

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


                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intent.putExtra("cantidad",Integer.toString(menuitems.get(posicionj).getCantidad()));
                intent.putExtra("posicion",Integer.toString(menuitems.get(posicionj).getindex()) );
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


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


        public TextView nombremenuitem12;
        public TextView cantidad;
        public TextView precio;
        public Button buttonmas;
        public Button buttonmenos;
        public NetworkImageView imagenPost;



        public ViewHolder(View itemView) {
            super(itemView);

           nombremenuitem12 = (TextView) itemView.findViewById(R.id.Nombremenuitem);
           cantidad = (TextView) itemView.findViewById(R.id.Cantidadmenuitem);
           precio=(TextView) itemView.findViewById(R.id.Preciomenuitem);
            buttonmas = (Button) itemView.findViewById(R.id.buton_mas_menuitem);
            buttonmenos = (Button) itemView.findViewById(R.id.buton_menos_menuitem);
            imagenPost = (NetworkImageView) itemView.findViewById(R.id.imagenmenuitem);


        }
    }
}