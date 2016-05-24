package com.example.signo.halan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by signo on 07/05/2016.
 */
public class GestoreLivello {

    /*
        metodo che restituisce un oggetto che descrive il livello
     */

    private DescrittoreLivello descrittore;
    private LivelliDbHelper mDbHelper;
    private Context context;
    private SQLiteDatabase db;

    GestoreLivello(Context context){

        descrittore = new DescrittoreLivello();
        mDbHelper = new LivelliDbHelper(context);
        this.context = context;
        db = mDbHelper.getReadableDatabase();

    }
    public DescrittoreLivello leggiLivello(int livello){

        //LEGGO IL LIVELLO DAL DATABASE
        String[] colonne_res = {
                LivelliContratto.Livelli._ID,
                LivelliContratto.Livelli.COLONNA_ESPOSTO,
                LivelliContratto.Livelli.COLONNA_SOLUZIONE,
                LivelliContratto.Livelli.COLONNA_SOLUZIONE2,
                LivelliContratto.Livelli.COLONNA_AUTORE
        };

        // How you want the results sorted in the resulting Cursor
        String ordina = LivelliContratto.Livelli._ID + " DESC";

        Cursor c = db.query(
                LivelliContratto.Livelli.TABELLA_NOME,    // tabella
                colonne_res,                              // colonne da restituire
                LivelliContratto.Livelli._ID+" = ?",                                     // The columns for the WHERE clause
                new String[]{String.valueOf(livello)},                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                ordina                                     // Stringa ordine
        );


        //Cursor c = db.rawQuery("SELECT * FROM livelli WHERE _id = 1",null);
        c.moveToFirst();
        int id = c.getInt(c.getColumnIndexOrThrow(LivelliContratto.Livelli._ID));
        String esposto = c.getString(c.getColumnIndexOrThrow(LivelliContratto.Livelli.COLONNA_ESPOSTO));
        String soluzione = c.getString(c.getColumnIndexOrThrow(LivelliContratto.Livelli.COLONNA_SOLUZIONE));
        String autore = c.getString(c.getColumnIndexOrThrow(LivelliContratto.Livelli.COLONNA_AUTORE));
        String soluzione2 = c.getString(c.getColumnIndexOrThrow(LivelliContratto.Livelli.COLONNA_SOLUZIONE2));
        //./DATABASE####################################################################################################

        descrittore.setLivello(id);
        descrittore.setAutore(autore);
        descrittore.setEsposto(esposto);
        descrittore.setSoluzione(soluzione);
        descrittore.setSoluzione2(soluzione2);

        return(descrittore);
    }
    //restituisce il numero totale di livelli presenti nel database
    int getNumeroLivelli(){

        Cursor c = db.rawQuery("SELECT "+LivelliContratto.Livelli._ID+" FROM "+LivelliContratto.Livelli.TABELLA_NOME+" ORDER BY "+LivelliContratto.Livelli._ID+" DESC LIMIT 0,1",null);

        c.moveToFirst();
        int livelli = c.getInt(c.getColumnIndexOrThrow(LivelliContratto.Livelli._ID));

        return(livelli);


    }


}
