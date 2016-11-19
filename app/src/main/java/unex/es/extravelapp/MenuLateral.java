package unex.es.extravelapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuLateral extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Interfaz {

    //Respuesta al login
    boolean respuestaLogin = false;
    String nombreLogin = "userLogin";
    String passwordLogin = "passwordLogin";


    //IP de la URL
    String IP = "http://extravel.esy.es";

    //Rutas a los Web Services
    String GET = IP + "";
    String GET_BY_ID = IP + "/obtener_usuario_por_id.php";
    String GET_LOGIN = IP + "/obtener_usuario_login.php";
    String INSERT = IP + "/insertar_usuario.php";
    String DELETE = IP + "/borrar_usuario.php";
    String UPDATE = IP + "/actualizar_usuario.php";

    ObtenerWebServices hiloConexion;

    //A esta clase la llamarán los distintos tipos de responder según su id.
    public class ObtenerWebServices extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) { //Obtiene como parámetro de entrada la variable "devuelve" para ponerla en el resultado.
            super.onPostExecute(s);

            if(s.equals("LoginCorrecto")){

                respuestaLogin=true;
                Context context = getApplicationContext();
                CharSequence text = "Usuario correcto";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new BusquedaFragment()).commit();

            }else if(s.equals("LoginIncorrecto")){

                respuestaLogin=false;
                Context context = getApplicationContext();
                CharSequence text = "Usuario incorrecto";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            if(s.equals("ActualizadoCorrecto")){

                //Mensaje en pantalla tras la inserción
                Context context = getApplicationContext();
                CharSequence text = "¡Actualizado correctamente!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Redireccionar al fragmento busqueda.
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new BusquedaFragment()).commit();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            String devuelve = ""; //Variable donde se imprimirán los resultados
            URL url = null; // Url de donde queremos obtener información

            if(params[1]=="1"){ //Lo que se debe hacer si es opción 1 -> Consultar todos los usuarios

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){

                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados
                        String resultJSON = respuestaJSON.getString("estado");   // results es el nombre del campo en el JSON

                        if (resultJSON=="1"){ //Si la variable estado es 1 -> Hay usuarios
                            JSONArray usuariosJSON = respuestaJSON.getJSONArray("usuarios");
                            for (int i=0;i<usuariosJSON.length();i++){
                                devuelve = devuelve + usuariosJSON.getJSONObject(i).getString("idUsuario") + " " +
                                                      usuariosJSON.getJSONObject(i).getString("name") + " " +
                                                      usuariosJSON.getJSONObject(i).getString("password") + "\n";
                            }
                        }else
                            if (resultJSON=="2"){
                                devuelve = "No hay usuarios";
                            }
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return devuelve;

            }else if(params[1]=="2"){ //Lo que se debe hacer si es opción 2 -> Registrar usuario

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();
                    //Creo el Objeto JSON
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("name", params[2]);
                    jsonParam.put("password", params[3]);
                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        //Almacenamos la respuesta en una cadena con BufferedReader
                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            result.append(line);
                            //response+=line;
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {      // hay un alumno que mostrar
                            devuelve = "Usuario insertado correctamente";

                        } else if (resultJSON == "2") {
                            devuelve = "El usuario no pudo insertarse";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;

            }else if(params[1]=="3"){ //Lo que se debe hacer si es opción 3 -> Obtener usuario por su nombre y contraseña
                    try {

                        //Abrir la conexión
                        url = new URL(cadena);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                                " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                        int respuesta = connection.getResponseCode();
                        StringBuilder result = new StringBuilder();
                        if (respuesta == HttpURLConnection.HTTP_OK){

                            InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader
                            // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                            // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                            // StringBuilder.
                            String line;
                            while ((line = reader.readLine()) != null) {
                                result.append(line);        // Paso toda la entrada al StringBuilder
                            }

                            //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                            JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                            //Accedemos al vector de resultados
                            String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                            if (resultJSON.equals("1")){      // está ese usuario en la base de datos
                                devuelve = "LoginCorrecto";

                                //Asignamos a estas valores los datos del usuario logeado
                                nombreLogin = params[2];
                                passwordLogin = params[3];

                            }
                            else if (resultJSON.equals("2")){
                                devuelve = "LoginIncorrecto";

                            }
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return devuelve;

            }else if(params[1]=="4"){ //Lo que se debe hacer si es opción 4 -> Actualizar usuario por su nombre

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    //Creo el Objeto JSON
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("nameID", params[2]);
                    jsonParam.put("name", params[3]);
                    jsonParam.put("password", params[4]);

                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();
                    StringBuilder result = new StringBuilder();
                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            result.append(line);
                            //response+=line;
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON.equals("1")) {      // hay un alumno que mostrar
                            devuelve = "ActualizadoCorrecto";

                        } else if (resultJSON == "2") {
                            devuelve = "El usuario no pudo actualizarse";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }else if(params[1]=="5"){ //Lo que se debe hacer si es opción 5 -> Eliminar usuario por su nombre
                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    //Creo el Objeto JSON
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("name", params[2]);

                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();
                    StringBuilder result = new StringBuilder();
                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            result.append(line);
                            //response+=line;
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {      // hay un alumno que mostrar
                            devuelve = "Usuario borrado correctamente";

                        } else if (resultJSON == "2") {
                            devuelve = "No existe ese usuario";
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return devuelve;
            }

            return null;
        }
    }

    //Conectar fragmentos mediante la interfaz --------------------------------------------------------------------------------------

    @Override
    public void responderBusqueda(){
        Intent intent = new Intent(getApplicationContext(), ViajeListActivity.class);
        startActivity(intent);
        //Opcion 1,
        //Usuario user = new Usuario("nombre", "password");
        //Comunicar de fragment a fragment
        //android.app.FragmentManager fragmentManager = getFragmentManager();
        //BusquedaFragment fragmentBusq = (BusquedaFragment) fragmentManager.findFragmentById(R.id."id del fragmeto");
    }

    @Override
    public void responderRegistro(String nombre, String password){

        hiloConexion = new ObtenerWebServices();
        hiloConexion.execute(INSERT, "2", nombre.toString(), password.toString()); //Opcion 2, registrar un usuario.

        //Mensaje en pantalla tras la inserción
        Context context = getApplicationContext();
        CharSequence text = "¡Registrado correctamente!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Redireccionar al fragmento login.
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, new LoginFragment()).commit();

    }

    @Override
    public void responderLogin(String nombre, String password){

        hiloConexion = new ObtenerWebServices();
        String cadenallamada = GET_LOGIN + "?name=" + nombre.toString() + "&password=" + password.toString();
        hiloConexion.execute(cadenallamada, "3", nombre.toString(), password.toString()); //Opcion 3, logear un usuario.

    }

    @Override
    public void responderUpdate(String nombreID, String nombre, String password){

        hiloConexion = new ObtenerWebServices();
        hiloConexion.execute(UPDATE, "4", nombreID.toString(), nombre.toString(), password.toString());

    }

    @Override
    public void responderDelete(String nombre){

        hiloConexion = new ObtenerWebServices();
        hiloConexion.execute(DELETE, "5", nombre.toString());

        //Mensaje en pantalla tras la inserción
        Context context = getApplicationContext();
        CharSequence text = "¡Borrado correctamente!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Eliminamos el logeo de ese usuario
        respuestaLogin = false;

        //Redireccionar al fragmento busqueda.
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, new BusquedaFragment()).commit();

    }

    @Override
    public void PerfilToFavoritos(){

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, new FavoritosFragment()).commit();
    }

    @Override
    public void PerfilToActualizar(){

        FragmentManager fragmentManager=getSupportFragmentManager();
        Bundle paquete = new Bundle();
        paquete.putString("nombre", nombreLogin);
        ActualizarFragment fragmentActualizar = new ActualizarFragment();
        fragmentActualizar.setArguments(paquete);
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentActualizar).commit();

    }

  //----------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();

        String id = (String) getIntent().getExtras().getString("id");
        if (id.equals("Buscar")) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new BusquedaFragment()).commit();
        }else
        if (id.equals("Login")) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new LoginFragment()).commit();
        }else
        if (id.equals("Crear")) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new RegistroFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.Buscar) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new BusquedaFragment()).commit();

        } else if (id == R.id.Registrarse) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new RegistroFragment()).commit();

        } else if ((id == R.id.Login)&&(respuestaLogin==false)) { //Solo podemos entrar a logearnos si no lo hemos hecho ya, Al entrar por primera vez o deslogeandose.
            fragmentManager.beginTransaction().replace(R.id.content_main, new LoginFragment()).commit();

        } else if ((id == R.id.Perfil)&&(respuestaLogin==true)) {
            Bundle paquete = new Bundle();
            paquete.putString("nombre", nombreLogin);
            paquete.putString("password", passwordLogin);
            PerfilFragment fragPerfil = new PerfilFragment();
            fragPerfil.setArguments(paquete);
            fragmentManager.beginTransaction().replace(R.id.content_main, fragPerfil).commit();

        } else if (id == R.id.Favoritos){//&&(respuestaLogin==true)) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new FavoritosFragment()).commit();

        } else if (id == R.id.Ayuda) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new AyudaFragment()).commit();

        }else if (id == R.id.Ajustes) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new AjustesFragment()).commit();

        }else if (respuestaLogin==false){
            Context context = getApplicationContext();
            CharSequence text = "Necesitas logearte";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
