package unex.es.extravelapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BusquedaFragment extends Fragment{
    EditText origen, destino;
    String origenString, destinoString;
    private Button Buscar;
    Interfaz comunicacion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.busqueda_fragment, container, false);
        origen = (EditText) view.findViewById(R.id.editText);
        destino = (EditText) view.findViewById(R.id.editText3);
        return view;
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        // Nos dá la actividad asociada a este fragmento
        comunicacion=(Interfaz) getActivity();

        Buscar = (Button)getActivity().findViewById(R.id.buttonBuscar);
        Buscar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                origenString = origen.getText().toString();
                destinoString = destino.getText().toString();

                //Si los campos no son nulos:
                if((!origenString.equals("") && !destinoString.equals(""))) {
                    comunicacion.responderBusqueda(origenString, destinoString);
                }else{
                    Context context = getContext();
                    CharSequence text = "Necesita un origen y destino";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
