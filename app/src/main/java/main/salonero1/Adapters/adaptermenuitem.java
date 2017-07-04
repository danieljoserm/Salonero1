package main.salonero1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import main.salonero1.ExpandableLayout;
import main.salonero1.R;
import main.salonero1.clases.menuitem;
import main.salonero1.clases.subnombres;
import main.salonero1.webservice.VolleySingleton;
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter;


public class adaptermenuitem extends RecyclerView.Adapter<adaptermenuitem.ViewHolder>  {

    List<menuitem> menuitems;
    List<menuitem> menucompleto;
    Context context;
    private HashSet<Integer> expandedPositionSet;
    private int lastPosition = -1;
    private Bitmap imagenitem;
    private ImageLoader imageLoader;
    VolleySingleton volley;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter adapter2;

    SectionedRecyclerViewAdapter sectionAdapter;




    public adaptermenuitem(List<menuitem> menuitemscategoria,Context context1,List<menuitem> menucompleto1) {
        super();
        expandedPositionSet = new HashSet<>();
        menuitems=menuitemscategoria;
        context=context1;
        menucompleto=menucompleto1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menuitem_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
   volley= new VolleySingleton(context);

        sectionAdapter = new SectionedRecyclerViewAdapter();



        List<subnombres> hola= new ArrayList<subnombres>();
        subnombres probando= new subnombres(1,"res");

        hola.add(probando);
        subnombres probando1= new subnombres(2,"pollo");
        hola.add(probando1);
        subnombres probando2= new subnombres(3,"pescado");
        hola.add(probando2);
        subnombres probando3= new subnombres(4,"cerdo");
        hola.add(probando3);
        subnombres probando4= new subnombres(5,"camaron");
        hola.add(probando4);

        List<subnombres> hola2= new ArrayList<subnombres>();
        subnombres probando5= new subnombres(1,"chimichurri");
        hola2.add(probando5);
        subnombres probando6= new subnombres(2,"frijoles");
        hola2.add(probando6);
        subnombres probando7= new subnombres(3,"queso");
        hola2.add(probando7);

        adapter= new adaptersubnombre(hola);
        GridLayoutManager glm = new GridLayoutManager(context, 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });

        viewHolder.recyclerViewbnombres.setLayoutManager(glm);
        sectionAdapter.addSection(new ContactsSection("carne",hola));
        //sectionAdapter.addSection(new ContactsSection1("acompanamientos",hola2));
        RecyclerView.Adapter subAdapter1 = new adaptersubnombre(hola);
        RecyclerView.Adapter subAdapter2 = new adaptersubnombre_incluyente(hola2);




        RecyclerViewMergeAdapter mergeAdapter = new RecyclerViewMergeAdapter();

        mergeAdapter.addAdapter(subAdapter1);
        mergeAdapter.addAdapter(subAdapter2);

        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
        sections.add(new SectionedGridRecyclerViewAdapter.Section(0,"Carne"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(5,"acompanamientos"));

        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(context,R.layout.section,R.id.section_text,viewHolder.recyclerViewbnombres,mergeAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));



        viewHolder.recyclerViewbnombres.setAdapter(mSectionedAdapter);
        ((SimpleItemAnimator) viewHolder.recyclerViewbnombres.getItemAnimator()).setSupportsChangeAnimations(false);


        return viewHolder;

    }




    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final menuitem menuitem = menuitems.get(i);
        viewHolder.updateItem(i);

//coasas del segundo recycler




        viewHolder.titulodescripcion.setText(menuitem.getNombremenuitem());
        viewHolder.nombremenuitem12.setText( menuitem.getNombremenuitem());
       viewHolder.precio.setText("Precio:" + Integer.toString(menuitem.getPrecio()));



       // viewHolder.recyclerViewbnombres.setLayoutManager(new GridLayoutManager(context,2));















/*
        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
        //Sections
        sections.add(new SectionedGridRecyclerViewAdapter.Section(0,"Carne"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(5,"acompanamientos"));
        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(context,R.layout.section,R.id.section_text,viewHolder.recyclerViewbnombres,adapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));




        viewHolder.recyclerViewbnombres.setAdapter(mSectionedAdapter);*/





        //viewHolder.recyclerViewbnombres.setAdapter(adapter);

        //  viewHolder.cantidad.setText("Mas informacion");
//        imageLoader = volley.getImageLoader();
        if(menuitem.getCantidad()==0){

            viewHolder.buttonmas.setText("+");

        }
        else
        {
            viewHolder.buttonmas.setText(Integer.toString(menuitem.getCantidad()));

        }


        imageLoader = volley.getInstance(context).getImageLoader();
        // imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        imageLoader.get(menuitem.getImagen(), ImageLoader.getImageListener(viewHolder.imagenPost,
                R.drawable.loading, android.R.drawable.ic_dialog_alert));



        viewHolder.imagenPost.setImageUrl(menuitem.getImagen(), imageLoader);




   BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;





    //    Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.getResources(), R.drawable.camaronesfondo,options);

      //  Bitmap blurredBitmap = blurBitmap(imagenitem,context);

//        Drawable dr = new BitmapDrawable(blurredBitmap);
//dr.setColorFilter( Color.RED, PorterDuff.Mode.MULTIPLY);
  //      viewHolder.expandable.setBackgroundDrawable(dr);



        final int  posicionj=i;

        setAnimation(viewHolder.container, i);








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


            //    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                notifyDataSetChanged();


            }
        });









    }
    public void clearData() {
        int size = this.menuitems.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.menuitems.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
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
        FrameLayout container;
        private ExpandableLayout expandableLayout;
        private LinearLayout expandable;
        public TextView titulodescripcion;
        RecyclerView recyclerViewbnombres;

        public ViewHolder(View itemView) {
            super(itemView);

            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
           nombremenuitem12 = (TextView) itemView.findViewById(R.id.Nombremenuitem);
           cantidad = (TextView) itemView.findViewById(R.id.Cantidadmenuitem);
           precio=(TextView) itemView.findViewById(R.id.Preciomenuitem);
            buttonmas = (Button) itemView.findViewById(R.id.buton_mas_menuitem);
            buttonmenos = (Button) itemView.findViewById(R.id.buton_menos_menuitem);
            imagenPost = (NetworkImageView) itemView.findViewById(R.id.imagenmenuitem);
            container= (FrameLayout) itemView.findViewById(R.id.item_menu_container);
            expandable= (LinearLayout) itemView.findViewById(R.id.expandablelayout);
            titulodescripcion= (TextView) itemView.findViewById(R.id.titulodescripcion);
            recyclerViewbnombres = (RecyclerView) itemView.findViewById(R.id.recyclerview_subnombre);



            //seteo del segundo recyclerview


            //RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(itemView.getContext());

         //  subnombres.setHasFixedSize(false);
           // subnombres.setLayoutManager(layoutManager);

        }


        private void updateItem(final int position) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position,cantidad);
                }
            });
            expandableLayout.setExpand(expandedPositionSet.contains(position));

        }
    }




    private void registerExpand(int position, TextView textView) {
        if (expandedPositionSet.contains(position)) {
            removeExpand(position);

            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down1, 0);
            textView.setText(R.string.detalle);
           // Toast.makeText(context, "Position: " + position + " collapsed!", Toast.LENGTH_SHORT).show();
        } else {
            addExpand(position);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up1, 0);
            textView.setText(R.string.detalle);
           // textview1.setText(menuitem.getNombremenuitem());
         //   Toast.makeText(context, "Position: " + position + " expanded!", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeExpand(int position) {
        expandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        expandedPositionSet.add(position);
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



    class ContactsSection extends StatelessSection {

        String title;
        List<subnombres> list;
         int lastCheckedPosition=0;


        public ContactsSection(String title, List<subnombres> list) {
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
            class ItemViewHolder extends RecyclerView.ViewHolder {


                private final View rootView;
                private final TextView Nombre;
                private final CheckBox checkBox;


                public ItemViewHolder(View view) {
                    super(view);


                    rootView = view;

                    Nombre = (TextView) itemView.findViewById(R.id.textviewsubnombre);
                    checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxsubnombre);
                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lastCheckedPosition = getAdapterPosition();
                            sectionAdapter.notifyDataSetChanged();
                            //notifyDataSetChanged();

                            //notifyItemRangeChanged(0, list.size());

                        }
                    });

                }
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

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.section_text);
        }
    }





    class ContactsSection1 extends StatelessSection {


        String title;
        List<subnombres> list;

        public ContactsSection1(String title, List<subnombres> list) {
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
            return new ItemViewHolder1(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ItemViewHolder1 itemHolder = (ItemViewHolder1) holder;

            subnombres name = list.get(position);
            itemHolder.Nombre.setText(name.getTexto());








        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder1(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder1 headerHolder = (HeaderViewHolder1) holder;



            headerHolder.tvTitle.setText(title);
        }
    }

    class HeaderViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder1(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.section_text);
        }
    }

    class ItemViewHolder1 extends RecyclerView.ViewHolder {


        private final View rootView;
        private final TextView Nombre;
        private final CheckBox checkBox;


        public ItemViewHolder1(View view) {
            super(view);


            rootView = view;

            Nombre = (TextView) itemView.findViewById(R.id.textviewsubnombre);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxsubnombre);
        }



    }
}


