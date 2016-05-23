package com.example.signo.halan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Partita extends AppCompatActivity {

    private PaginaPartita partita;
    private int livelloRaggiunto;
    private FragmentManager fm;
    DialogLivelloSuperato livelloSuperato;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        //Dialog del livello superato
        fm = getSupportFragmentManager();
        livelloSuperato = new DialogLivelloSuperato();

        Intent intent = getIntent();
        this.livelloRaggiunto = intent.getIntExtra("livello",1);

        partita = new PaginaPartita(this,livelloRaggiunto);
        partita.disegnaPagina();


    }
    public void inviaSoluzione(View view){

        TextView[] l = partita.getSoluzioneInserita();
        Stringa stringa = new Stringa(partita.getSoluzione());
        //Dialog d=new Dialog(this);


        if(stringa.controlloRisultato(l) == 1) {
            //d.setTitle("Soluzione corretta");
            livelloSuperato.show(fm,"");

            //this.livelloRaggiunto++;

            //Intent intent = new Intent(this, Partita.class);
            //intent.putExtra("livello",livelloRaggiunto);
            //startActivity(intent);



        }
        else {
            //d.setTitle("Ritenta");

        }
        //d.setCancelable(true);
        //d.show();

    }
    public void reset(View view){

        partita.resetLivello();

    }
}
