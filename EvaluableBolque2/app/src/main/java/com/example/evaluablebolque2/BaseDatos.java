
package com.example.evaluablebolque2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "ranking.db";
    private static final int VERSION_BD = 1;

    public BaseDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE partidas (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "puntuacion INTEGER NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        // Aquí se gestionarán futuras actualizaciones
        // de la estructura de la base de datos.
    }

    // Guardar una partida
    public long guardarPartida(String nombre, int puntuacion) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("puntuacion", puntuacion);

        return db.insert("partidas", null, valores);
    }


    public ArrayList<String> obtenerRanking() {

        ArrayList<String> ranking = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, puntuacion FROM partidas " +"ORDER BY puntuacion DESC, id ASC",null);

        try {
            int posicion = 1;

            while (cursor.moveToNext()) {

                String nombre = cursor.getString(0);
                int puntuacion = cursor.getInt(1);

                String resultado = posicion + ".  " + nombre + "     " + puntuacion + " / 10";

                ranking.add(resultado);
                posicion++;
            }

        } finally {cursor.close();}

        return ranking;
    }

}
