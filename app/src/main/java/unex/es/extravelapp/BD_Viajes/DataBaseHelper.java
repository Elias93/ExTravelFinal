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
                        DummyContent.ITEMS.get(Integer.parseInt(id) - 1).getFecha());
                values.put(ViajesTable.COLUMNA_ID, idViaje);
                values.put(ViajesTable.COLUMNA_TIPO_TRANSPORTE, v.getTipoTransporte());
                values.put(ViajesTable.COLUMNA_HORA_SALIDA, v.getHoraSalida());
                values.put(ViajesTable.COLUMNA_HORA_LLEGADA, v.getHoraLlegada());
                values.put(ViajesTable.COLUMNA_PRECIO, v.getPrecio());
                values.put(ViajesTable.COLUMNA_FECHA, v.getFecha());
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
        return "Viaje no eciste";
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

        this.getReadableDatabase().insert(ViajesTable.TABLA_VIAJES, null, values);
        return "Viaje Insertado";
    }

    /*
    		 public String DeleteSingleContact (String contact){
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor test = this.getWritableDatabase().rawQuery("select "+contact+" from CONTACTS" ,null);
            if (test.getCount()!=0){
				this.getWritableDatabase().rawQuery("delete from CONTACTS where name like '"+contact+"'" ,null);
				return "Contacto Borrado";
			}else return "Contacto Inexistente";
        }

		public String UpdateContact(String name,Contacts contact){
            Cursor test = this.getWritableDatabase().rawQuery("select "+name+" from CONTACTS" ,null);
            if (test.getCount()!=0){
				this.getWritableDatabase().rawQuery("update CONTACTS set name="+contact.getName()+",number="+contact.getNumber()+" where name like '"+name+"'" ,null);
				return "Contacto Modificado";
			}else return "No existe el contacto";

		}
 */
		public void DeleteContactList (){
            SQLiteDatabase sqLiteDatabase = null;
            sqLiteDatabase.execSQL(ViajesTable.DROP_QUERY);
            sqLiteDatabase.execSQL(ViajesTable.CREATE_QUERY);
		}


}
