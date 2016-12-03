package main.salonero1.clases;

public class menuitem {

    private String Nombre;
     private int Precio;
    private int Cantidad;



    public menuitem(String Nombre, int cantidad1, int precio1){
        this.Cantidad=cantidad1;
        this.Nombre=Nombre;
        this.Precio=precio1;
    }

    public menuitem(){


    }

        public void setnombremenuitem(String nombremenuitem) {
            this.Nombre = Nombre ;
        }


    public void setCantidad(int cantidad   ) {
        this.Cantidad=cantidad ;
    }
    public void setprecio(int precio) {
        this.Precio=precio ;
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


}
