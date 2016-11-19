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

public class LoginFragment extends Fragment {

    Interfaz comunicacion;
    Button loginBoton;
    EditText nombre;
    EditText password;
    String nameString;
    String passwordString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        nombre = (EditText) view.findViewById(R.id.Nombre_ID);
        password = (EditText)view.findViewById(R.id.Password_ID);
        loginBoton = (Button) view.findViewById(R.id.buttonLoginAcceder);

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

        loginBoton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                nameString = nombre.getText().toString();
                passwordString = password.getText().toString();
                comunicacion.responderLogin(nameString, passwordString);
            }
        });
    }
}