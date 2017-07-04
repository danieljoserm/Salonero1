package main.salonero1.Adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import main.salonero1.ItemClickListener;
import main.salonero1.R;
import main.salonero1.clases.Restau;
import main.salonero1.clases.subnombres;
import main.salonero1.webservice.VolleySingleton;

/**
 * Created by danie on 4/3/2017.
 */

public class adaptersubnombre extends RecyclerView.Adapter<adaptersubnombre.ViewHolder> {

    List<subnombres> subnombres;
    //private ItemClickListener clickListener;
    public  int selectedPosition;
    //private int lastPosition = -1;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    private static int lastCheckedPosition=0;


    public adaptersubnombre(List<subnombres> subnombres) {


       this.subnombres = subnombres;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subnombre_itemrow, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        selectedPosition=0;

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        subnombres subnombre = subnombres.get(i);

        viewHolder.Nombre.setText(subnombre.getTexto());

        viewHolder.checkBox.setOnCheckedChangeListener(null);

        //if true, your check box will be selected, else unselected

        viewHolder.checkBox.setTag(i);

        if(i==selectedPosition)
        {
            viewHolder.checkBox.setChecked(true);
        }
        else
        {
            viewHolder.checkBox.setChecked(false);
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CheckListener(viewHolder.checkBox,i));

    }




    @Override
    public int getItemCount() {

        return subnombres.size();
    }


    class CheckListener implements CompoundButton.OnCheckedChangeListener {

        private final int position;
        private CheckBox checkbox;

        public CheckListener(CheckBox checkbox,int position) {

            this.checkbox = checkbox;
            this.position=position;

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {

            if (isChecked) {
                checkbox.setChecked(true);
                //notifyItemChanged(selectedPosition);
                selectedPosition = position;
              // notifyDataSetChanged();
                //notifyItemChanged(position);
                notifyItemRangeChanged(0, (subnombres.size()+1)*2);

            } else {
                checkbox.setChecked(false);

            }
            buttonView.setChecked(isChecked);

        }


    }


    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView Nombre;
        public CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);


            Nombre = (TextView) itemView.findViewById(R.id.textviewsubnombre);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxsubnombre);






        }






    }


}
