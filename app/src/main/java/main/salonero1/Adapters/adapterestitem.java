package main.salonero1.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import main.salonero1.ItemClickListener;
import main.salonero1.R;
import main.salonero1.clases.Restau;
import main.salonero1.clases.menuitem;
import main.salonero1.webservice.VolleySingleton;

/**
 * Created by User on 11/30/2016.
 */
public class adapterestitem extends RecyclerView.Adapter<adapterestitem.ViewHolder>  {

    List<Restau> Restaurante1;
    private ItemClickListener clickListener;


    private ImageLoader imageLoader;
    Context context;

    public adapterestitem(List<Restau> Restaurante,Context context1) {
        super();

        Restaurante1=Restaurante;
        context=context1;
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

        imageLoader = VolleySingleton.getInstance(context).getImageLoader();
       // imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        imageLoader.get("http://www.fancyicons.com/free-icons/108/point-of-interest/png/256/restaurant_blue_256.png", ImageLoader.getImageListener(viewHolder.imagenPost,
                R.drawable.ic_hamburger, android.R.drawable.ic_dialog_alert));


                viewHolder.imagenPost.setImageUrl("http://www.fancyicons.com/free-icons/108/point-of-interest/png/256/restaurant_blue_256.png", imageLoader);



    }

    @Override
    public int getItemCount() {

        return Restaurante1.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{


        public TextView Nombre;
        public NetworkImageView imagenPost;


        public ViewHolder(View itemView) {
            super(itemView);

            imagenPost = (NetworkImageView) itemView.findViewById(R.id.imagenrest);

            Nombre = (TextView) itemView.findViewById(R.id.nombrerest);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }





}
