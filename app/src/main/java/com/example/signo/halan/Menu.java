package com.example.signo.halan;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private int livelloRaggiunto;



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
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.primo_avvio), Context.MODE_PRIVATE);

        int defaultValue = 1;
        int primoAvvio = sharedPref.getInt(getString(R.string.primo_avvio), defaultValue);

        //SOLO SE è IL PRIMO AVVIO CARICO IL DATABASE CON I LIVELLI
        if(primoAvvio == 1){

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
            values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, "PRIMO ESPOSTO");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, "PRIMA SOLUZIONE");

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    LivelliContratto.Livelli.TABELLA_NOME,
                    null,
                    values);

            // Create a new map of values, where column names are the keys

            values.put(LivelliContratto.Livelli.COLONNA_AUTORE, "PIPPO");
            values.put(LivelliContratto.Livelli.COLONNA_ESPOSTO, "SECONDO ESPOSTO");
            values.put(LivelliContratto.Livelli.COLONNA_SOLUZIONE, "SECONDA SOLUZIONE");

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    LivelliContratto.Livelli.TABELLA_NOME,
                    null,
                    values);
            //./CARICO IL DATABASE###################################################

            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putInt(getString(R.string.primo_avvio), 0);
            editor.putInt(getString(R.string.livello_raggiunto), 1);
            editor.commit();

            //tolgo messaggio di caricamento
            dialog.dismiss();
        }









    }
    public void avviaPartita(View view) {

        this.sharedPref = getSharedPreferences(getString(R.string.livello_raggiunto), MODE_PRIVATE);
        int defaultValue = 1;

        this.livelloRaggiunto = sharedPref.getInt(getString(R.string.livello_raggiunto), defaultValue);


        Intent intent = new Intent(this, Partita.class);
        intent.putExtra("livello",livelloRaggiunto);
        startActivity(intent);

    }


}
