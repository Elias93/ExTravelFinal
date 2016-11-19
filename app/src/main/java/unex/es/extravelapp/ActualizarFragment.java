package unex.es.extravelapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ActualizarFragment extends Fragment {

    Interfaz comunicacion;
    EditText nombre;
    EditText password;
    Button modificar;
    String nombreID;
    String nameString;
    String passwordString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actualizar_fragment, container, false);

        Bundle bundle = this.getArguments();
        nombreID = bundle.getString("nombre");

        nombre = (EditText) view.findViewById(R.id.newName);
        password = (EditText)view.findViewById(R.id.newPassword);
        modificar = (Button) view.findViewById(R.id.buttonActualizar);

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

        modificar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                nameString = nombre.getText().toString();
                passwordString = password.getText().toString();
                //Le pasamos el nombre por que buscará la tupla, y los nuevos valores
                comunicacion.responderUpdate(nombreID, nameString, passwordString);

            }
        });


    }
}
