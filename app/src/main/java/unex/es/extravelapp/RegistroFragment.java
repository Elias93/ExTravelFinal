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

public class RegistroFragment extends Fragment {

    Button registro;
    EditText nombre;
    EditText password;
    EditText passwordConfirmar;
    Interfaz comunicacion;

    String nameString;
    String passwordString;
    String passwordConfirmarString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registro_fragment, container, false);
        nombre = (EditText) view.findViewById(R.id.putNombre);
        password = (EditText) view.findViewById(R.id.putPassword);
        passwordConfirmar = (EditText) view.findViewById(R.id.putPasswordConfirmar);
        registro = (Button) view.findViewById(R.id.buttonRegistroRegistrarse);
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

        registro.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                nameString = nombre.getText().toString();
                passwordString = password.getText().toString();
                passwordConfirmarString = passwordConfirmar.getText().toString();

                //Si los campos no son nulos:
                if((!nameString.equals("")) && (!passwordString.equals(""))){
                    //Contraseña verificada:
                    if(passwordString.equals(passwordConfirmarString)){
                        comunicacion.responderRegistro(nameString, passwordString);
                    }else{
                        Context context = getContext();
                        CharSequence text = "Verifica correctamente la contraseña";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }else{
                    Context context = getContext();
                    CharSequence text = "Hay algún campo nulo";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
