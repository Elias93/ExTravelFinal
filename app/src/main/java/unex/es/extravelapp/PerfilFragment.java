package unex.es.extravelapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PerfilFragment extends Fragment{

    Interfaz comunicacion;
    TextView nombre;
    TextView password;
    String nameString;
    Button modificar;
    Button viajes;
    Button borrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);

        Bundle bundle = this.getArguments();
        String namePaquete = bundle.getString("nombre");
        String passwordPaquete = bundle.getString("password");

        nombre = (TextView) view.findViewById(R.id.textViewPerfilNombre);
        nombre.setText(namePaquete);
        password = (TextView)view.findViewById(R.id.textViewPerfilPassword);
        password.setText(passwordPaquete);
        modificar = (Button) view.findViewById(R.id.buttonModificar);
        viajes = (Button) view.findViewById(R.id.buttonviajes);
        borrar = (Button) view.findViewById(R.id.buttonActualizar);

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
        nameString = nombre.getText().toString();

        modificar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                comunicacion.PerfilToActualizar();
            }
        });

        viajes.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                comunicacion.PerfilToFavoritos();
            }
        });

        borrar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                comunicacion.responderDelete(nameString);
            }
        });

    }
}
