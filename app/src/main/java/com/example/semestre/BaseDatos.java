package com.example.semestre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    //public static String NombreBD ="APPNOTAS.bd";
    public static String BD = "APPNOTAS.bd";
    public static String  TAsignaturas = "Asignaturas";
    public static String  TSemestre = "cortes";
    public static String  COLAID = "ID";
    public static String  NOMID = "Nombre";
    public static String TNotas = "Notas";
    String Consulta2 =  "CREATE TABLE "+ TSemestre +"(IDSemestre INTEGER PRIMARY KEY AUTOINCREMENT, IDAsignatura TEXT, corte1 DOUBLE, corte2 DOUBLE, Corte3 DOUBLE)";


    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Consulta2);
        db.execSQL("CREATE TABLE "+ TAsignaturas +"(ID TEXT PRIMARY KEY,Nombre TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TSemestre);
        db.execSQL("DROP TABLE IF EXISTS "+ TAsignaturas);

        db.execSQL(Consulta2);
        onCreate(db);
    }
    public boolean GuardarAsignatura(String id, String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLAID, id);
        contentValues.put(NOMID, nombre);
        long result = db.insert(TAsignaturas,null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }
    public ArrayList llenarlv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor listado = db.rawQuery("SELECT * FROM " + TAsignaturas + " ORDER BY Nombre",null);
        if (listado.moveToFirst()){
            do{
                lista.add(listado.getString(1));
            } while(listado.moveToNext());
        }
        return lista;
    }
    public ArrayList llenarlvNotas(){
        ArrayList<String> lista2 = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor listado2 = db.rawQuery("SELECT * FROM " + TSemestre,null);
        if (listado2.moveToFirst()){
            do{
                lista2.add(listado2.getString(0));
                lista2.add(listado2.getString(1));
                lista2.add(listado2.getString(2));
                lista2.add(listado2.getString(3));
                lista2.add(listado2.getString(4));
            } while(listado2.moveToNext());
        }
        return lista2;
    }
    public boolean ActualizarA(String id, String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLAID, id);
        contentValues.put(NOMID, nombre);
        long result = db.update(TAsignaturas,contentValues,"ID = ?", new String[]{id});
        return true;
    }
    public Integer EliminarA(String  i){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TAsignaturas,"ID = ?", new String[]{i});

    }
    public Cursor ListarAs(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor lista = db.rawQuery("SELECT Nombre FROM " + TAsignaturas,null);
        return  lista;
    }
    public boolean GuardarNotas(String idAsignatura  ,Double corte1, Double corte2, Double corte3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDAsignatura", idAsignatura);
        contentValues.put("corte1", corte1);
        contentValues.put("corte2", corte2);
        contentValues.put("corte3", corte3);
        long result = db.insert(TSemestre,null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

}
