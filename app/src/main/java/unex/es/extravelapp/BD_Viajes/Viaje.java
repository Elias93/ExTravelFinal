package unex.es.extravelapp.BD_Viajes;

import java.util.ArrayList;
import java.util.List;

public class Viaje {
    private String idViaje;
    private String tipoTransporte;
    private String horaSalida;
    private String horaLlegada;
    private String precio;
    private String fecha;
    private String origen;
    private String destino;
    private String latitudOrigen;
    private String longitudOrigen;
    private String latitudDestino;
    private String longitudDestino;

    public Viaje(String idViaje, String tipoTransporte, String horaSalida,
                String horaLlegada, String precio, String fecha, String origen, String destino,
                String latitudOrigen, String longitudOrigen, String latitudDestino, String longitudDestino) {

        this.idViaje = idViaje;
        this.tipoTransporte = tipoTransporte;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.latitudOrigen = latitudOrigen;
        this.longitudOrigen = longitudOrigen;
        this.latitudDestino = latitudDestino;
        this.longitudDestino = longitudDestino;
    }

    public String getIdViaje() { return idViaje; }
    public String getTipoTransporte() { return tipoTransporte; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public String getPrecio() { return precio; }
    public String getFecha() { return fecha; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public String getLatitudOrigen() { return latitudOrigen; }
    public String getLongitudOrigen() { return longitudOrigen; }
    public String getLatitudDestino() { return latitudDestino; }
    public String getLongitudDestino() { return longitudDestino; }

    public void setIdVIaje(String idViaje) { this.idViaje = idViaje; }
    public void setTipoTransporte(String tipoTransporte) { this.tipoTransporte = tipoTransporte; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public void setPrecio(String precio) { this.precio = precio; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setLatitudOrigen(String latitudOrigen) { this.latitudOrigen = latitudOrigen; }
    public void setLongitudOrigen(String longitudOrigen) { this.longitudOrigen = longitudOrigen; }
    public void setLatitudDestino(String latitudDestino) { this.latitudDestino = latitudDestino; }
    public void setLongitudDestino(String longitudDestino) { this.longitudDestino = longitudDestino; }
}