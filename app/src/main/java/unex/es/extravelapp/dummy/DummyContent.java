package unex.es.extravelapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    static{
        addItem(new DummyItem("1", "1", "Amovens", "19:00", "21:00", "19,00", "05/11/16"));
        addItem(new DummyItem("2", "2","Blablacar", "20:00", "22:00", "20,14", "06/11/16"));
        addItem(new DummyItem("3", "3","Blablacar", "21:00", "23:00", "21,14", "07/11/16"));
        addItem(new DummyItem("4", "4","Amovens", "22:00", "00:00", "22,14", "08/11/16"));
        addItem(new DummyItem("5", "5","Blablacar", "23:00", "01:00", "23,14", "09/11/16"));
        addItem(new DummyItem("6", "6","Amovens", "00:00", "02:00", "24,14", "10/11/16"));
        addItem(new DummyItem("7", "7","Blablacar", "01:00", "03:00", "25,14", "11/11/16"));
        addItem(new DummyItem("8", "8","Amovens", "02:00", "04:00", "26,14", "12/11/16"));
        addItem(new DummyItem("9", "9","Blablacar", "03:00", "05:00", "27,14", "13/11/16"));
        addItem(new DummyItem("10", "10","Blablacar", "04:00", "06:00", "28,14", "14/11/16"));
        addItem(new DummyItem("11", "11","Blablacar", "05:00", "07:00", "29,14", "15/11/16"));
        addItem(new DummyItem("12", "12","Amovens", "06:00", "08:00", "30,14", "16/11/16"));
        addItem(new DummyItem("13", "13","Blablacar", "02:00", "08:00", "32,14", "16/11/16"));
        addItem(new DummyItem("14", "14","Amovens", "03:00", "08:00", "32,14", "17/11/16"));
    }

    public static class DummyItem {
        public String id;
        public String idViaje;
        public String tipoTransporte;
        public String horaSalida;
        public String horaLlegada;
        public String precio;
        public String fecha;


        public DummyItem(String id,String idViaje, String tipoTransporte, String horaSalida,
                         String horaLlegada, String precio, String fecha) {
            this.id = id;
            this.idViaje = idViaje;
            this.tipoTransporte = tipoTransporte;
            this.horaSalida = horaSalida;
            this.horaLlegada = horaLlegada;
            this.precio = precio;
            this.fecha = fecha;
        }

        public String getId() { return id; }
        public String getIdViaje() { return idViaje; }
        public String getTipoTransporte() { return tipoTransporte; }
        public String getHoraSalida() { return horaSalida; }
        public String getHoraLlegada() { return horaLlegada; }
        public String getPrecio() { return precio; }
        public String getFecha() { return fecha; }

        public void setId(String id) { this.id = id; }
        public void setIdViaje(String idViaje) { this.idViaje = idViaje; }
        public void setTipoTransporte(String tipoTransporte) { this.tipoTransporte = tipoTransporte; }
        public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
        public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
        public void setPrecio(String precio) { this.precio = precio; }
        public void setFecha(String fecha) { this.fecha = fecha; }
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
    }
}
