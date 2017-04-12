package main.salonero1.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import main.salonero1.ItemClickListener;
import main.salonero1.R;
import main.salonero1.clases.Restau;
import main.salonero1.webservice.VolleySingleton;

/**
 * Created by danie on 4/3/2017.
 */

public class adaptersubnombre extends RecyclerView.Adapter<adaptersubnombre.ViewHolder>  {

    List<Restau> Restaurante1;
    private ItemClickListener clickListener;

    private int lastPosition = -1;
    private ImageLoader imageLoader;
    Context context;

    public adaptersubnombre(List<Restau> Restaurante,Context context1) {
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
        imageLoader.get(Restaurante2.getImagen(), ImageLoader.getImageListener(viewHolder.imagenPost,
                R.drawable.loading, android.R.drawable.ic_dialog_alert));


        viewHolder.imagenPost.setImageUrl(Restaurante2.getImagen(), imageLoader);

        setAnimation(viewHolder.container, i);


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
        FrameLayout container;


        public ViewHolder(View itemView) {
            super(itemView);

            imagenPost = (NetworkImageView) itemView.findViewById(R.id.imagenrest);
            container= (FrameLayout) itemView.findViewById(R.id.item_rest_container);
            Nombre = (TextView) itemView.findViewById(R.id.nombrerest);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }





}
