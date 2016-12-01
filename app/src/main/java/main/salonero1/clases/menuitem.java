package main.salonero1.clases;

public class menuitem {

    private String Nombre;
    private int cantidad;
    private int Precio;



    public menuitem(String Nombre, int cantidad1, int precio1){
        this.cantidad=cantidad1;
        this.Nombre=Nombre;
        this.Precio=precio1;
    }

    public menuitem(){


    }

        public void setnombremenuitem(String nombremenuitem) {
            this.Nombre = Nombre ;
        }


    public void setCantidad(int cantidad   ) {
        this.cantidad=cantidad ;
    }
    public void setprecio(int precio) {
        this.Precio=precio ;
    }







    public String getNombremenuitem(){


        return Nombre;
    }


    public int getCantidad(){


        return cantidad;
    }


    public int getPrecio(){


        return Precio;
    }


}
