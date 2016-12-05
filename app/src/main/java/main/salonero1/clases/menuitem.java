package main.salonero1.clases;

public class menuitem {

    private String Nombre;
     private int Precio;
    private int Cantidad;
    private String Categoria;
    private String Imagen;






    public menuitem(String Nombre, int cantidad1, int precio1,String categoria, String imagen){
        this.Cantidad=cantidad1;
        this.Nombre=Nombre;
        this.Precio=precio1;
        this.Categoria=categoria;
        this.Imagen=imagen;
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

    public void setCategoria(String categoria   ) {
        this.Categoria=categoria ;
    }

    public void setImagen(String imagen   ) {
        this.Imagen=imagen ;
    }





    public String getCategoria(){


        return Categoria;
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






}
