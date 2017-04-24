package main.salonero1.clases;

/**
 * Created by danie on 4/12/2017.
 */

public class subnombres {

    private int index;
    private String texto;


    public subnombres(int index, String texto){

        this.index=index;
        this.texto=texto;



    }



    public void setindex(int index){

        this.index=index;

    }

    public int getIndex(){

        return index;
    }

    public void setTexto(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }


}



