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
    public DescrittoreLivello leggiLivello(Context context,int livello){

        DescrittoreLivello descrittore = new DescrittoreLivello();
        /*
        #############################DATABASE#######################################################
         */

        LivelliDbHelper mDbHelper = new LivelliDbHelper(context);
        // Gets the data repository in write mode

        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String[] colonne_res = {
                LivelliContratto.Livelli._ID,
                LivelliContratto.Livelli.COLONNA_ESPOSTO,
                LivelliContratto.Livelli.COLONNA_SOLUZIONE,
                LivelliContratto.Livelli.COLONNA_AUTORE
        };

        // How you want the results sorted in the resulting Cursor
        String ordina =
                LivelliContratto.Livelli._ID + " DESC";

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
        //./DATABASE####################################################################################################

        descrittore.setLivello(id);
        descrittore.setAutore(autore);
        descrittore.setEsposto(esposto);
        descrittore.setSoluzione(soluzione);

        return(descrittore);
    }

}
