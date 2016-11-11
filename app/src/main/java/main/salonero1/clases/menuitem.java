package main.salonero1.clases;

public class menuitem {

    private String nombremenuitem;
    private int cantidad;
    private int precio;



    public menuitem(String nombremenuitem1, int cantidad1, int precio1){
        this.cantidad=cantidad1;
        this.nombremenuitem=nombremenuitem1;
        this.precio=precio1;
    }

    public menuitem(){


    }

        public void setnombremenuitem(String nombremenuitem) {
            this.nombremenuitem = nombremenuitem ;
        }


    public void setCantidad(int cantidad   ) {
        this.cantidad=cantidad ;
    }
    public void setprecio(int precio) {
        this.precio=precio ;
    }







    public String getNombremenuitem(){


        return nombremenuitem;
    }


    public int getCantidad(){


        return cantidad;
    }


    public int getPrecio(){


        return precio;
    }


}
