package com.example.signo.halan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by signo on 07/05/2016.
 */
public class LivelliDbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "halan.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String VIRGOLA = ",";
    private static final String SQL_CREA_DATABASE =
            "CREATE TABLE " + LivelliContratto.Livelli.TABELLA_NOME + " (" +
                    LivelliContratto.Livelli._ID + " INTEGER PRIMARY KEY," +
                    LivelliContratto.Livelli.COLONNA_ESPOSTO + TEXT_TYPE + VIRGOLA +
                    LivelliContratto.Livelli.COLONNA_SOLUZIONE + TEXT_TYPE + VIRGOLA +
                    LivelliContratto.Livelli.COLONNA_AUTORE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LivelliContratto.Livelli.TABELLA_NOME;

    public LivelliDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREA_DATABASE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
