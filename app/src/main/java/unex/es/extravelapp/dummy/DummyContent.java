package unex.es.extravelapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    static{
        addItem(new DummyItem("1", "1", "Iberia", "19:00", "21:00", "19,00", "05/11/16", "Badajoz", "Madrid", "38.894277", "-6.818892", "40.392834", "-3.702014"));
        addItem(new DummyItem("2", "2","Ryanair", "20:00", "22:00", "20,14", "06/11/16", "Badajoz", "Barcelona", "38.894277", "-6.818892", "41.422384", "2.175672"));
        addItem(new DummyItem("3", "3","Vueling", "21:00", "23:00", "21,14", "07/11/16", "Badajoz", "Madrid", "38.894277", "-6.818892", "40.392834", "-3.702014"));
        addItem(new DummyItem("4", "4","Iberia", "22:00", "00:00", "322,14", "08/11/16", "Badajoz", "Roma", "38.894277", "-6.818892", "41.882079", "12.568738"));
        addItem(new DummyItem("5", "5","Ryanair", "23:00", "01:00", "23,14", "09/11/16", "Badajoz", "Madrid", "38.894277", "-6.818892", "40.392834", "-3.702014"));

        addItem(new DummyItem("6", "6","Iberia", "00:00", "02:00", "124,14", "10/11/16", "Madrid", "Roma", "40.392834", "-3.702014", "41.882079", "12.568738"));
        addItem(new DummyItem("7", "7","Ryanair", "01:00", "03:00", "25,14", "11/11/16", "Madrid", "Barcelona", "40.392834", "-3.702014", "41.422384", "2.175672"));
        addItem(new DummyItem("8", "8","Vueling", "02:00", "04:00", "26,14", "12/11/16", "Madrid", "Badajoz", "40.392834", "-3.702014", "38.894277", "-6.818892"));
        addItem(new DummyItem("9", "9","Ryanair", "03:00", "05:00", "127,14", "13/11/16", "Madrid", "Roma", "40.392834", "-3.702014", "41.882079", "12.568738"));

        addItem(new DummyItem("10", "10","Iberia", "04:00", "06:00", "128,14", "14/11/16", "Barcelona", "Roma", "41.422384", "2.175672", "41.882079", "12.568738"));
        addItem(new DummyItem("11", "11","Ryanair", "05:00", "07:00", "29,14", "15/11/16", "Barcelona", "Madrid", "41.422384", "2.175672", "40.392834", "-3.702014"));
        addItem(new DummyItem("12", "12","Iberia", "06:00", "08:00", "30,14", "16/11/16", "Barcelona", "Madrid", "41.422384", "2.175672", "40.392834", "-3.702014"));
        addItem(new DummyItem("13", "13","Vueling", "02:00", "08:00", "32,14", "16/11/16", "Barcelona", "Badajoz", "41.422384", "2.175672", "38.894277", "-6.818892"));

        addItem(new DummyItem("14", "14","Vueling", "03:00", "08:00", "132,14", "17/11/16", "Roma", "Madrid", "41.882079", "12.568738", "40.392834", "-3.702014"));
        addItem(new DummyItem("15", "15","Vueling", "02:00", "18:00", "312,14", "18/11/16", "Roma", "Badajoz", "41.882079", "12.568738", "38.894277", "-6.818892"));

    }

    public static class DummyItem {
        public String id;
        public String idViaje;
        public String tipoTransporte;
        public String horaSalida;
        public String horaLlegada;
        public String precio;
        public String fecha;
        public String origen;
        public String destino;
        public String latitudOrigen;
        public String longitudOrigen;
        public String latitudDestino;
        public String longitudDestino;

        public DummyItem(String id,String idViaje, String tipoTransporte, String horaSalida,
                         String horaLlegada, String precio, String fecha, String origen, String destino,
                         String latitudOrigen, String longitudOrigen, String latitudDestino, String longitudDestino) {

            this.id = id;
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

        public String getId() { return id; }
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

        public void setId(String id) { this.id = id; }
        public void setIdViaje(String idViaje) { this.idViaje = idViaje; }
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

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
    }
}
