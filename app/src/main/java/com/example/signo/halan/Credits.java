package com.example.signo.halan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Credits extends AppCompatActivity {

    private DescrittoreLivelloServer risposta;
    private int risultato;
    private String[] autore;
    private String[] esposto;
    private String[] soluzione1;
    private String[] soluzione2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //elimino la barra superiore
        getSupportActionBar().hide();


        Stringa str = new Stringa("3@autore1@esposto1@soluzione1@soluzione2@autore2@esposto2@soluzione1@soluzione2@pluto@espostopluto@ciaoatutti@atutticiao@");

        //risposta = str.splitRisposta("3autore1@esposto1@soluzione1@soluzione2@autore2@esposto2@soluzione1@soluzione2@pluto@espostopluto@ciaoatutti@atutticiao@");

        risposta = str.splitRisposta();

        autore = risposta.getAutore();
        Log.w("Autore",autore[0]);





    }











}
