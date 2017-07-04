package main.salonero1.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import main.salonero1.R;
import main.salonero1.clases.subnombres;

/**
 * Created by danie on 7/3/2017.
 */

class adaptersubmenu_excluyente extends StatelessSection {

    String title;
    List<subnombres> list;

    public  int selectedPosition=-1;
    //private int lastPosition = -1;
    private CheckBox lastChecked = null;
    private  int lastCheckedPos = 0;

    public adaptersubmenu_excluyente(String title, List<subnombres> list) {
        super(R.layout.section, R.layout.subnombre_itemrow);

        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        subnombres name = list.get(position);
        itemHolder.Nombre.setText(name.getTexto());


        //  itemHolder.checkBox.setChecked(selectedPosition == position);



        itemHolder.checkBox.setOnCheckedChangeListener(null);

        //if true, your check box will be selected, else unselected

        itemHolder.checkBox.setTag(position);

        if(position==selectedPosition)
        {
            itemHolder.checkBox.setChecked(true);
        }
        else
        {
            itemHolder.checkBox.setChecked(false);
        }
        itemHolder.checkBox.setOnCheckedChangeListener(new adaptersubmenu_excluyente.CheckListener(itemHolder.checkBox,position));

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
                selectedPosition = position;
               // adaptersubmenu_excluyente;
            } else {
                checkbox.setChecked(false);

            }
            buttonView.setChecked(isChecked);

        }


    }




    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
      HeaderViewHolder headerHolder = (HeaderViewHolder) holder;



        headerHolder.tvTitle.setText(title);
    }
}

class HeaderViewHolder extends RecyclerView.ViewHolder {

     final TextView tvTitle;

    public HeaderViewHolder(View view) {
        super(view);

        tvTitle = (TextView) view.findViewById(R.id.section_text);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {


     final View rootView;
     final TextView Nombre;
     final CheckBox checkBox;


    public ItemViewHolder(View view) {
        super(view);


        rootView = view;

        Nombre = (TextView) itemView.findViewById(R.id.textviewsubnombre);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxsubnombre);
    }
}