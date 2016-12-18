package unex.es.extravelapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import unex.es.extravelapp.BD_Viajes.DataBaseHelper;
import unex.es.extravelapp.BD_Viajes.Viaje;

public class FavoritosFragment extends Fragment {

    TableLayout tabla;
    Button borrar;
    DataBaseHelper dbhelper;
    List<Viaje> listaViajes = new ArrayList<Viaje>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favoritos_fragment, container, false);
        tabla=(TableLayout) view.findViewById(R.id.lista);
        borrar=(Button) view.findViewById(R.id.borrar);
        dbhelper = new DataBaseHelper(getActivity().getApplication());
        borrarTodosViajes();
        mostrarInformacion();
        return view;
    }

    private void borrarTodosViajes(){
        borrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbhelper.onRestar();
                tabla.removeAllViews();
                mostrarInformacion();
            }
        });
    }

    private void mostrarInformacion(){
        int cont=1;
        try (Cursor cur = dbhelper.getCursorViaje()){
            while(cur.moveToNext()){
                Viaje v = new Viaje(cur.getString(0),cur.getString(1),cur.getString(2)
                        ,cur.getString(3),cur.getString(4),cur.getString(5),cur.getString(6),cur.getString(7)
                        ,cur.getString(8),cur.getString(9),cur.getString(10),cur.getString(11)
                        );
                listaViajes.add(v);
                cont++;
            }
        }
        dbhelper.close();

        if(listaViajes.isEmpty()){
            TextView textView = new TextView(getContext());
            TableRow row=new TableRow(getContext());
            textView.setText("Sin viajes guardados");
            row.addView(textView);
            tabla.addView(row);
        }else{
            int i=0;
            while (i<listaViajes.size()) {
                TextView textViewTipoTransporte = new TextView(getContext());
                TextView textViewHoraSalidaLlegada = new TextView(getContext());
                TextView textViewPrecio = new TextView(getContext());
                TextView textViewFecha = new TextView(getContext());
                TextView textViewOrigenDestino = new TextView(getContext());
                TextView textViewEspacioSeparacion = new TextView(getContext());

                TableRow rowTipoTransporte=new TableRow(getContext());
                TableRow rowHoraSalidaLlegada=new TableRow(getContext());
                TableRow rowPrecio=new TableRow(getContext());
                TableRow rowFecha=new TableRow(getContext());
                TableRow rowOrigenDestino=new TableRow(getContext());
                TableRow rowEspacioSeparacion=new TableRow(getContext());

                textViewTipoTransporte.setText("Tipo de transporte: "+listaViajes.get(i).getTipoTransporte());
                textViewHoraSalidaLlegada.setText("Hora salida-llegada: "+listaViajes.get(i).getHoraSalida()+"-"+listaViajes.get(i).getHoraLlegada());
                textViewPrecio.setText("Precio: "+listaViajes.get(i).getPrecio());
                textViewFecha.setText("Fecha: "+listaViajes.get(i).getFecha());
                textViewOrigenDestino.setText("Origen-Destino: "+listaViajes.get(i).getOrigen()+"-"+listaViajes.get(i).getDestino());

                //agregamos el textview al TableRow
                rowTipoTransporte.addView(textViewTipoTransporte);
                rowHoraSalidaLlegada.addView(textViewHoraSalidaLlegada);
                rowPrecio.addView(textViewPrecio);
                rowFecha.addView(textViewFecha);
                rowOrigenDestino.addView(textViewOrigenDestino);
                rowEspacioSeparacion.addView(textViewEspacioSeparacion);

                //Finalmente agregamos el TableRow al TableLayout
                tabla.addView(rowTipoTransporte);
                tabla.addView(rowHoraSalidaLlegada);
                tabla.addView(rowPrecio);
                tabla.addView(rowFecha);
                tabla.addView(rowOrigenDestino);
                tabla.addView(rowEspacioSeparacion);
                i++;
            }
        }
        listaViajes.clear();
    }

    private void borrarViaje(){
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.onRestar();
                tabla.removeAllViews();
                mostrarInformacion();
            }
        });
    }
}
