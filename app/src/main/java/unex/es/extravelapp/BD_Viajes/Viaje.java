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

    public Viaje(String idViaje, String tipoTransporte, String horaSalida,
                 String horaLlegada, String precio, String fecha) {

        this.idViaje = idViaje;
        this.tipoTransporte = tipoTransporte;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getIdViaje() { return idViaje; }
    public String getTipoTransporte() { return tipoTransporte; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public String getPrecio() { return precio; }
    public String getFecha() { return fecha; }

    public void setIdVIaje(String idViaje) { this.idViaje = idViaje; }
    public void setTipoTransporte(String tipoTransporte) { this.tipoTransporte = tipoTransporte; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public void setPrecio(String precio) { this.precio = precio; }
    public void setFecha(String fecha) { this.fecha = fecha; }


}