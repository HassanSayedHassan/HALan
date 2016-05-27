package com.example.signo.halan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by signo on 27/05/2016.
 */

public class ThreadImportaLivelli extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    private String[] autore;
    private String[] esposto;
    private String[] soluzione1;
    private String[] soluzione2;
    private DescrittoreLivelloServer descrittore;
    private Stringa stringa;
    private int livelliDaAggiungere;
    private int livelloRaggiunto;
    private SharedPreferences sharedPreferences;
    private GestoreLivello gestore;
    private int numeroTotLivelli;
    private View v;

    ThreadImportaLivelli(Context ctx,View v)
    {
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences("preferenze",ctx.MODE_PRIVATE);
        livelloRaggiunto = sharedPreferences.getInt("livelloRaggiunto",1);
        this.v = v;

    }
    @Override
    protected void onPreExecute() {
        //prima dell'esecuzione
        //visualizzo un popup di caricamento
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Caricamento...");
        alertDialog.show();
    }
    @Override
    //Tutto quello che vviene in background
    protected String doInBackground(String ... params) {

        //url dove risiede la pagina php che esegue le operazioni
        String calcola_url = "http://www.provaweb.it/halan/get_livelli.php";


        String livelli = params[0];

        //mi impone di gestire l'eccezione
        try {
            //creo un oggetto di tipo url, prende in ingresso una stringa col link della pagina php
            URL url = new URL(calcola_url);
            //apro la connessione all'url specificato prima
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //invio i dati tramite post
            httpURLConnection.setRequestMethod("POST");
            //setto la connessione in uscita ed in entrata
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            //stringa composta dai parametri post
            String data = URLEncoder.encode("livelli", "UTF-8") + "=" + URLEncoder.encode(livelli, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

            //stream di in ingresso
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


            String response = bufferedReader.readLine();

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return response;




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String r) {
        //alla fine, setto il testo nella risposta e tolgo il dialog di caricamento
        if(r != null && !r.equals("-1")) {

            stringa = new Stringa(r);
            descrittore = stringa.splitRisposta();
            livelliDaAggiungere = descrittore.getLivelli();
            autore = descrittore.getAutore();
            esposto = descrittore.getEsposto();
            soluzione1 = descrittore.getSoluzione();
            soluzione2 = descrittore.getSoluzione2();

            //inserisco i livelli ricevuti dal server nel database locale
            LivelliDbHelper mDbHelper = new LivelliDbHelper(ctx);
            for (int i = 0; i < livelliDaAggiungere; i++) {

                mDbHelper.inserisciLivello(autore[i], esposto[i], soluzione1[i], soluzione2[i]);

            }
        }

        gestore = new GestoreLivello(ctx);
        numeroTotLivelli = gestore.getNumeroLivelli();
        TextView nuovaPartita = (TextView)v.findViewById(R.id.nuova_partita);

        if(livelloRaggiunto <= numeroTotLivelli)
        {
            nuovaPartita.setEnabled(true);
            nuovaPartita.setBackgroundResource(R.color.bottoni);
        }

        //tolgo il dialog
        alertDialog.hide();
    }
}