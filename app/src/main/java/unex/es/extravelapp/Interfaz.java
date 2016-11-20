package unex.es.extravelapp;

/**
 * Created by Elias on 13/11/2016.
 */

public interface Interfaz {

    //Comunicar BusquedaFragment con ViajeListActivity 
    public void responderBusqueda(String origen);

    //Añadir un usuario a la BD
    public void responderRegistro(String nombre, String password);

    //Obtener usuario por su id
    public void responderLogin(String nombre, String password);

    //Actualizar un usuario
    public void responderUpdate(String nombreID, String nombre, String password);

    //Borrar un usuario
    public void responderDelete(String nombre);

    //Comunicar fragmtent (perfil) con fragmtent (lista de viajes favoritos)
    public void PerfilToFavoritos();

    //Comunicar fragmtent (perfil) con fragmtent (Actualizar)
    public void PerfilToActualizar();

}
