package main.salonero1.webservice;


public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";
    //private static final String PUERTO_HOST = ":80";
    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "52.38.158.11";
    /**
     * URLs del Web Service
     */
    public static final String GETrestaurante = "http://" + IP + PUERTO_HOST + "/i_wishdata/obtener_restaurante.php";
    public static final String GETcategorias= "http://" + IP + PUERTO_HOST + "/i_wishdata/obtener_categorias.php";
    public static final String GETplatosfuertes = "http://" + IP + PUERTO_HOST + "/i_wishdata/obtener_tiendas.php";
    public static final String GET = "http://" + IP + PUERTO_HOST + "/i_wishdata/obtener_metas.php";
    public static final String GET_BY_ID = "http://" + IP + PUERTO_HOST + "/i_wishdata/obtener_meta_por_id.php";
    public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/i_wishdata/actualizar_meta.php";
    public static final String DELETE = "http://" + IP + PUERTO_HOST + "/i_wishdata/borrar_meta.php";
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/i_wishdata/insertar_meta.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";

}