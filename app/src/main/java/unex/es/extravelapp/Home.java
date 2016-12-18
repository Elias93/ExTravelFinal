package unex.es.extravelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import unex.es.extravelapp.BD_Viajes.DataBaseHelper;

public class Home extends AppCompatActivity {

    private Button butActivity_mainIniciarSesion;
    private Button butActivity_mainCrearCuenta;
    private Button butActivity_mainContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        inicializarComponentes();
    }

    public void inicializarComponentes(){

        butActivity_mainIniciarSesion = (Button)findViewById(R.id.buttonActivity_mainIniciarSesion);
        butActivity_mainIniciarSesion.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent IntentLogin = new Intent(getApplicationContext(), MenuLateral.class);
                IntentLogin.putExtra("id", "Login");
                startActivity(IntentLogin);
            }
        });
        butActivity_mainCrearCuenta = (Button)findViewById(R.id.buttonActivity_mainCrearCuenta);
        butActivity_mainCrearCuenta.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent IntentRegistro = new Intent(getApplicationContext(), MenuLateral.class);
                IntentRegistro.putExtra("id", "Crear");
                startActivity(IntentRegistro);
            }
        });
        butActivity_mainContinuar = (Button)findViewById(R.id.buttonActivity_mainContinuar);
        butActivity_mainContinuar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), MenuLateral.class);
                    intent.putExtra("id", "Buscar");
                    startActivity(intent);
            }
        });
    }

}
