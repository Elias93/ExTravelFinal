package unex.es.extravelapp.BD_Viajes;

import android.content.Context;
import android.provider.BaseColumns;

/**
 * Created by socor on 14/11/2016.
 */

public class ViajesTable implements BaseColumns {

    //tabla
    public static final String TABLA_VIAJES = "VIAJES";
    //atributos
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_TIPO_TRANSPORTE = "tipoTransporte";
    public static final String COLUMNA_HORA_SALIDA = "horaSalida";
    public static final String COLUMNA_HORA_LLEGADA = "horaLlegada";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_ORIGEN = "origen";
    public static final String COLUMNA_DESTINO = "destino";


    public static final String CREATE_QUERY = "create table " + TABLA_VIAJES + " (" +
            COLUMNA_ID + " INTEGER, " +
            COLUMNA_TIPO_TRANSPORTE + " TEXT, " +
            COLUMNA_HORA_SALIDA + " TEXT, " +
            COLUMNA_HORA_LLEGADA + " TEXT, " +
            COLUMNA_PRECIO + " TEXT, " +
            COLUMNA_FECHA + " TEXT, " +
            COLUMNA_ORIGEN + " TEXT, " +
            COLUMNA_DESTINO + " TEXT)";

    public static final String DROP_QUERY = "drop table " + TABLA_VIAJES;
    public static final String SElECT_ALL_QUERY = "select * from " + TABLA_VIAJES;

}
