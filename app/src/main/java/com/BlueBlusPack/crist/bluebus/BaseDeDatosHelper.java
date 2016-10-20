package com.BlueBlusPack.crist.bluebus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Agu on 11/10/2016.
 */

public class BaseDeDatosHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 2;
    private static String TABLE_NAME = "";
    private static String TABLE_CREATE = "";

    public BaseDeDatosHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory){
        super(contexto,nombre,factory,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antVersion, int newVersion){
        db.execSQL("drop table if exist "+TABLE_NAME);
        db.setVersion(newVersion);
        DATABASE_VERSION = newVersion;
        db.execSQL(TABLE_CREATE);
    }
}

