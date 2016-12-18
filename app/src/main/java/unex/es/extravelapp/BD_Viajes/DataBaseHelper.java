package unex.es.extravelapp.BD_Viajes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import unex.es.extravelapp.dummy.DummyContent;

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(Context context) {
        super(context, "Retail", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ViajesTable.CREATE_QUERY);
    }

    public void onRestar() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL(ViajesTable.DROP_QUERY);
        sqLiteDatabase.execSQL(ViajesTable.CREATE_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int prevVersion, int newVersion) {
        sqLiteDatabase.execSQL(ViajesTable.DROP_QUERY);
        sqLiteDatabase.execSQL(ViajesTable.CREATE_QUERY);
    }

    public void importarViaje(String id, String idViaje){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getReadableDatabase();
        try ( Cursor test = this.getReadableDatabase().rawQuery("select * from VIAJES where id like '"+idViaje+"'",null)) {

            if (test.getCount() == 0) {

                Viaje v = new Viaje(idViaje,

                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getTipoTransporte(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getHoraSalida(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getHoraLlegada(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getPrecio(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getFecha(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getOrigen(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getDestino(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getLatitudOrigen(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getLongitudOrigen(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getLatitudDestino(),
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getLongitudDestino()
                        );

                values.put(ViajesTable.COLUMNA_ID, idViaje);
                values.put(ViajesTable.COLUMNA_TIPO_TRANSPORTE, v.getTipoTransporte());
                values.put(ViajesTable.COLUMNA_HORA_SALIDA, v.getHoraSalida());
                values.put(ViajesTable.COLUMNA_HORA_LLEGADA, v.getHoraLlegada());
                values.put(ViajesTable.COLUMNA_PRECIO, v.getPrecio());
                values.put(ViajesTable.COLUMNA_FECHA, v.getFecha());
                values.put(ViajesTable.COLUMNA_ORIGEN, v.getOrigen());
                values.put(ViajesTable.COLUMNA_DESTINO, v.getDestino());
                values.put(ViajesTable.COLUMNA_LATITUDORIGEN, v.getLatitudOrigen());
                values.put(ViajesTable.COLUMNA_LONGITUDORIGEN, v.getLongitudOrigen());
                values.put(ViajesTable.COLUMNA_LATITUDDESTINO, v.getLatitudDestino());
                values.put(ViajesTable.COLUMNA_LONGITUDDESTINO, v.getLongitudDestino());

                //insert rows
                this.getWritableDatabase().insert(ViajesTable.TABLA_VIAJES, null, values);
            }
        }
    }

    public Cursor getCursorViaje() {
        return this.getWritableDatabase().rawQuery(ViajesTable.SElECT_ALL_QUERY, null);
    }

    public Cursor getViaje(Integer idViaje) {
        try ( Cursor test = this.getReadableDatabase().rawQuery("select * from VIAJES where COLUMNA_ID_VIAJE like '"+idViaje+"'",null)) {
            if (test.getCount() != 0)
                return test;
        }
        return null;
    }

    public String setViajeFavorito(Integer idViaje) {
        try ( Cursor test = this.getWritableDatabase().rawQuery(
                "update VIAJES set COLUMNA_FAV="+true+" where name COLUMNA_ID_VIAJE like'"+idViaje+"'",null)) {
            if (test.getCount() != 0)
                return "Viaje añadido a favorito";
        }
        return "Viaje no existe";
    }

    public String addViaje (Viaje v){
        try ( Cursor test = this.getWritableDatabase().rawQuery("select * from VIAJES where NAME like '"+v.getIdViaje()+"'"  ,null)) {
            if (test.getCount()!=0)
                return "Ya existe el viaje";
        }
        ContentValues values = new ContentValues();
        values.put(ViajesTable.COLUMNA_ID, v.getIdViaje());
        values.put(ViajesTable.COLUMNA_TIPO_TRANSPORTE, v.getTipoTransporte());
        values.put(ViajesTable.COLUMNA_HORA_SALIDA, v.getHoraSalida());
        values.put(ViajesTable.COLUMNA_HORA_LLEGADA, v.getHoraLlegada());
        values.put(ViajesTable.COLUMNA_PRECIO, v.getPrecio());
        values.put(ViajesTable.COLUMNA_FECHA, v.getFecha());
        values.put(ViajesTable.COLUMNA_ORIGEN, v.getOrigen());
        values.put(ViajesTable.COLUMNA_DESTINO, v.getDestino());
        values.put(ViajesTable.COLUMNA_LATITUDORIGEN, v.getLatitudOrigen());
        values.put(ViajesTable.COLUMNA_LONGITUDORIGEN, v.getLongitudOrigen());
        values.put(ViajesTable.COLUMNA_LATITUDDESTINO, v.getLatitudDestino());
        values.put(ViajesTable.COLUMNA_LONGITUDDESTINO, v.getLongitudDestino());

        this.getReadableDatabase().insert(ViajesTable.TABLA_VIAJES, null, values);
        return "Viaje Insertado";
    }

	public void DeleteContactList (){
            SQLiteDatabase sqLiteDatabase = null;
            sqLiteDatabase.execSQL(ViajesTable.DROP_QUERY);
            sqLiteDatabase.execSQL(ViajesTable.CREATE_QUERY);
		}


}
