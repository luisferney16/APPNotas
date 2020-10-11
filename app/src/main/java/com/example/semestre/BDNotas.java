package com.example.semestre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDNotas extends SQLiteOpenHelper {
    public static String  TAsignaturas = "Asignaturas";
    public static String  TSemestre = "cortes";
    public static String  COLAID = "ID";
    public static String  NOMID = "Nombre";
    public static String TNotas = "Notas";
    public BDNotas(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
