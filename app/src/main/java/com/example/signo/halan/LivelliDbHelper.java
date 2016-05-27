package com.example.signo.halan;

import android.content.ContentValues;
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
                    LivelliContratto.Livelli.COLONNA_SOLUZIONE2 + TEXT_TYPE + VIRGOLA +
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

    public void inserisciLivello(String autore, String esposto, String soluzione1, String soluzione2){


        //CARICO IL DATABASE#####################################################

        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(LivelliContratto.Livelli.COLONNA_AUTORE, autore);
        values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, esposto);
        values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, soluzione1);
        values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE2, soluzione2);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                LivelliContratto.Livelli.TABELLA_NOME,
                null,
                values);



    }

}
