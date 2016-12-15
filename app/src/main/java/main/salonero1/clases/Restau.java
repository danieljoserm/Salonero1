package main.salonero1.clases;

import android.provider.ContactsContract;

/**
 * Created by User on 11/30/2016.
 */
public class Restau {

    private String Nombre;
    private String BSSID;
    private String Imagen;




    public Restau(String nombre, String BSSID,String Imagen){
      this.Nombre=nombre;
        this.BSSID=BSSID;
        this.Imagen= Imagen;
    }

    public void setImagen(String imagen   ) {
        this.Imagen=imagen ;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public void setnombre(String Nombre) {
        this.Nombre = Nombre;
    }




    public String getImagen(){


        return Imagen;
    }

    public String getNombre(){


        return Nombre;
    }

    public String getBSSID(){


        return BSSID;
    }


}
