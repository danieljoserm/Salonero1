package main.salonero1.clases;

import java.io.Serializable;

public class menuitem implements Serializable {
    private int index;
    private  String restaurante;
    private String Nombre;
     private int Precio;
    private int Cantidad;
    private String categoria;
    private String Imagen;






    public menuitem(int index, String categoria1,String Nombre, int cantidad1, int precio1, String imagen,String restaurante){
        this.Cantidad=cantidad1;
        this.Nombre=Nombre;
        this.restaurante=restaurante;
        this.Precio=precio1;
        this.categoria=categoria1;
        this.Imagen=imagen;
        this.index=index;
    }

    public menuitem(){


    }

        public void setnombremenuitem(String nombremenuitem) {
            this.Nombre = Nombre ;
        }

    public void setnombreindex(int index) {
        this.index=index ;
    }



    public void setCantidad(int cantidad   ) {
        this.Cantidad=cantidad ;
    }
    public void setprecio(int precio) {
        this.Precio=precio ;
    }

    public void setCategoria(String categoria   ) {
        this.categoria=categoria ;
    }

    public void setImagen(String imagen   ) {
        this.Imagen=imagen ;
    }

    public void setRestaurante (String restaurante){

        this.restaurante=restaurante;
    }



    public String getCategoria(){


        return categoria;
    }

    public String getImagen(){


        return Imagen;
    }

    public String getNombremenuitem(){


        return Nombre;
    }

    public int getCantidad(){


        return Cantidad;
    }


    public int getPrecio(){


        return Precio;
    }

    public int getindex(){


        return index;
    }

public String getRestaurante(){
    return restaurante;
}




}
