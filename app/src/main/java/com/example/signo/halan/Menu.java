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
    private ThreadImportaLivelli thread;
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

        Log.w("Livello Raggiunto",String.valueOf(livelloRaggiunto));


        // SOLO SE è IL PRIMO AVVIO CARICO IL DATABASE CON I LIVELLI

        if(primoAvvio.equals("1")){




            //CARICO IL DATABASE#####################################################
            LivelliDbHelper mDbHelper = new LivelliDbHelper(this);
            mDbHelper.inserisciLivello("Il Lupino","GELSO","S TOLTA GELO SI À","STOLTA GELOSIA");


            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("primoAvvio", "0");
            editor.putInt("livelloRaggiunto",1);
            editor.commit();



        }

        //ricavo il numero totale di livelli
        gestore = new GestoreLivello(this);
        numeroTotLivelli = gestore.getNumeroLivelli();
        //faccio partire il thread che aggiunge i nuovi livelli ricavati dal server
        View v = getWindow().getDecorView().getRootView();

        thread = new ThreadImportaLivelli(this,v);
        thread.execute(Integer.toString(numeroTotLivelli));


        //#########################################################################
        //Setto il nome del primo pulsante in nuova partita o continua
        TextView nuovaPartita = (TextView)findViewById(R.id.nuova_partita);
        if(livelloRaggiunto != 1)
        {
            nuovaPartita.setEnabled(true);
            nuovaPartita.setText("CONTINUA");
        }
        if(livelloRaggiunto > numeroTotLivelli)
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
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Esci")
                .setMessage("Sei sicuro di voler uscire?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }

}
