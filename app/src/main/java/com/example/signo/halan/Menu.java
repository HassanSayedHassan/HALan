package com.example.signo.halan;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private int livelloRaggiunto;
    String primoAvvio;
    GestoreLivello gestore;
    int numeroTotLivelli;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setto il layout
        setContentView(R.layout.activity_menu);
        //#########Cancello barra superiore#########
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //##########################################



        Context context = this;


        sharedPref = getSharedPreferences("preferenze",MODE_PRIVATE);
        primoAvvio = sharedPref.getString("primoAvvio","1");
        livelloRaggiunto = sharedPref.getInt("livelloRaggiunto",1);
        //setto il numero totale di livelli presenti
        gestore = new GestoreLivello(this);
        numeroTotLivelli = gestore.getNumeroLivelli();

        Log.w("primo avvio",primoAvvio);

        //SOLO SE è IL PRIMO AVVIO CARICO IL DATABASE CON I LIVELLI
        if(primoAvvio.equals("1")){

            //messaggio di caricamento primo avvio
            ProgressDialog dialog=new ProgressDialog(this);
            dialog.setMessage("Caricamento");
            dialog.setCancelable(false);
            dialog.setInverseBackgroundForced(false);
            dialog.show();
            //./messaggio di caricamento primo avvio


            //CARICO IL DATABASE#####################################################
            LivelliDbHelper mDbHelper = new LivelliDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(LivelliContratto.Livelli.COLONNA_AUTORE, "PIPPO");
            values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, "PISTA");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, "COL PI DITE STA");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE2, "COLPI DI TESTA");

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    LivelliContratto.Livelli.TABELLA_NOME,
                    null,
                    values);

            // Create a new map of values, where column names are the keys

            values.put(LivelliContratto.Livelli.COLONNA_AUTORE, "PIPPO");
            values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, "ROM.");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, "A CREA ROMA");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE2, "ACRE AROMA");

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    LivelliContratto.Livelli.TABELLA_NOME,
                    null,
                    values);
            //./CARICO IL DATABASE###################################################

            // Create a new map of values, where column names are the keys

            values.put(LivelliContratto.Livelli.COLONNA_AUTORE, "PIPPO");
            values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, "BAS BES BIS BOS GAR");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, "GAR E DIRE BUS");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE2, "GARE DI REBUS");

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    LivelliContratto.Livelli.TABELLA_NOME,
                    null,
                    values);
            //./CARICO IL DATABASE###################################################

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("primoAvvio", "0");
            editor.putInt("livelloRaggiunto",1);
            editor.commit();

            //tolgo messaggio di caricamento
            dialog.dismiss();
        }
        TextView nuovaPartita = (TextView)findViewById(R.id.nuova_partita);

        if(livelloRaggiunto != 1)
        {

            nuovaPartita.setText("CONTINUA");
        }
        Log.w("Numero tot livelli",String.valueOf(numeroTotLivelli));
        Log.w("Livello raggiunto",String.valueOf(livelloRaggiunto));
        if(livelloRaggiunto >= numeroTotLivelli)
        {
            nuovaPartita.setEnabled(false);
            nuovaPartita.setBackgroundResource(R.color.livelloNonSelezionato);
        }

    }
    public void avviaPartita(View view) {



        Intent intent = new Intent(this, Partita.class);
        intent.putExtra("livello",livelloRaggiunto);
        startActivity(intent);

    }
    public void avviaSelezionaLivello(View view) {

        Intent intent = new Intent(this, SelezionaLivello.class);
        startActivity(intent);

    }

    public void avviaCrediti(View view) {

        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Vuoi Uscire?")
                .setMessage("Sei sicuro di voler uscire dall'applicazione?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Menu.super.onBackPressed();
                    }
                }).create().show();
    }

}
