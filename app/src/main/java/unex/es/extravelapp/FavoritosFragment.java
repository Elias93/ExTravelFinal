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
        mostarInformacion();
        return view;
    }

    private void borrarTodosViajes(){
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.onRestar();
                tabla.removeAllViews();
                mostarInformacion();
            }
        });
    }

    private void mostarInformacion(){
        int cont=1;
        try (Cursor cur = dbhelper.getCursorViaje()){
            while(cur.moveToNext()){
                Viaje v = new Viaje(cur.getString(0),cur.getString(1),cur.getString(2)
                        ,cur.getString(3),cur.getString(4),cur.getString(5));
                listaViajes.add(v);
                cont++;
            }
        }
        dbhelper.close();

        if(listaViajes.isEmpty()){
            TextView textView = new TextView(getContext());
            TableRow row=new TableRow(getContext());
            textView.setText("Tabla vacia");
            //agregamos el textview al TableRow
            row.addView(textView);
            tabla.addView(row);
        }else{
            int i=0;
            while (i<listaViajes.size()) {
                //Button butBorrar = new Button(getContext());
                //butBorrar.setPadding(30, 80, 15, 15);
                TextView textView = new TextView(getContext());
                TextView textView2 = new TextView(getContext());
                TextView textView3 = new TextView(getContext());
                TextView textView4 = new TextView(getContext());
                TextView textView5 = new TextView(getContext());
                TextView textView7 = new TextView(getContext());
                TableRow row=new TableRow(getContext());
                TableRow row2=new TableRow(getContext());
                TableRow row3=new TableRow(getContext());
                TableRow row4=new TableRow(getContext());
                TableRow row5=new TableRow(getContext());
                TableRow row6=new TableRow(getContext());
                TableRow row7=new TableRow(getContext());
                //le damos dimenciones al textview
                //textView.setPadding(5, 5, 5, 5);
                //agregamos los datos al textview
                /*textView.setText("Tipo de transporte: "+listaViajes.get(i).getTipoTransporte()
                        +" - Hora de salida: "+listaViajes.get(i).getHoraSalida()
                        +" - Hora de llegada: "+listaViajes.get(i).getHoraLlegada()
                        +" - Precio: "+listaViajes.get(i).getPrecio()
                        +" - Fecha: "+listaViajes.get(i).getFecha());*/
                textView.setText("Tipo de transporte: "+listaViajes.get(i).getTipoTransporte());
                textView2.setText("Hora de salida: "+listaViajes.get(i).getHoraSalida());
                textView3.setText("Hora de llegada: "+listaViajes.get(i).getHoraLlegada());
                textView4.setText("Precio: "+listaViajes.get(i).getPrecio());
                textView5.setText("Fecha: "+listaViajes.get(i).getFecha());
                //int x=i+1;
                //butBorrar.setText("Borrar viaje "+x);

                //agregamos el textview al TableRow
                row.addView(textView);
                row2.addView(textView2);
                row3.addView(textView3);
                row4.addView(textView4);
                row5.addView(textView5);
                //row6.addView(butBorrar);
                row7.addView(textView7);


                //Finalmente agregamos el TableRow al TableLayout
                tabla.addView(row);
                tabla.addView(row2);
                tabla.addView(row3);
                tabla.addView(row4);
                tabla.addView(row5);
                //tabla.addView(row6);
                tabla.addView(row7);
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
                mostarInformacion();
            }
        });
    }
}
