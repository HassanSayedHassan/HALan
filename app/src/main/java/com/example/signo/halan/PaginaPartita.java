package com.example.signo.halan;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by signo on 14/05/2016.
 * Classe che stampa la pagina della partita corrente
 */
public class PaginaPartita {

    private TableLayout layoutBottoni, layoutSoluzione;
    private AppCompatActivity a;
    private DescrittoreLivello livello;
    private String esposto;
    private String soluzione;
    private String soluzione2;
    private int lunghezzaSoluzione;
    private int displayX,displayY;
    private int marginiX;
    private int marginiY;
    private int layoutX,layoutY;
    private int dimensioneBottoni;
    private int testoBottoni;
    private int click = 0;
    private TextView[] l;
    private Button[] b;
    private Stringa enigma;
    private String enigmaSenzaSpazi;
    private int lunghezzaSenzaSpazi;



    private int spazioLateraleBottoni;
    int spazioTopBottoni;
    final int bottoniPerRiga = 7;

    PaginaPartita(AppCompatActivity a,int numLivello){
        a.setContentView(R.layout.activity_partita);
        //assegno le classi private
        this.a = a;

        this.layoutSoluzione = (TableLayout)a.findViewById(R.id.tabellaSoluzione);
        this.layoutBottoni = (TableLayout)a.findViewById(R.id.tabellaBottoni);


        GestoreLivello gestore = new GestoreLivello(a);
        livello = gestore.leggiLivello(numLivello);
        this.esposto = livello.getEsposto();
        this.soluzione = livello.getSoluzione();
        this.soluzione2 = livello.getSoluzione2();
        Display display = a.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.displayX = size.x;
        this.displayY = size.y;

        //ricavo le grandezze per creare i bottoni
        this.testoBottoni = (int) a.getResources().getDimension(R.dimen.testoBottoni);
        this.spazioLateraleBottoni = (int) a.getResources().getDimension(R.dimen.spazioLateraleBottoni);
        this.spazioTopBottoni = (int) a.getResources().getDimension(R.dimen.spazioTopBottoni);
        this.marginiX = (int) a.getResources().getDimension(R.dimen.activity_horizontal_margin);
        this.marginiY = (int) a.getResources().getDimension(R.dimen.activity_vertical_margin);
        this.layoutX = this.displayX-(2*marginiX);
        this.dimensioneBottoni =(this.layoutX-(2*spazioLateraleBottoni*bottoniPerRiga))/bottoniPerRiga;
        this.lunghezzaSoluzione = soluzione.length();
        this.l = new TextView[this.lunghezzaSoluzione];

        this.enigma = new Stringa(this.soluzione);
        this.enigmaSenzaSpazi = enigma.rimuoviSpazi();
        this.lunghezzaSenzaSpazi = enigma.getLunghezza();

        this.b = new Button[this.lunghezzaSenzaSpazi];
    }


    public void disegnaPagina(){
        //Rimuovo la barra degli strumenti
        a.getSupportActionBar().hide();

        //imposto il nome dell'esposto
        TextView textEsposto = (TextView) a.findViewById(R.id.esposto);
        textEsposto.setText(esposto);

        //ricavo il numero di caratteri della soluzione e la splitto in un array di char
        int lunghezzaSoluzione = soluzione.length();
        char[] soluzioneSplitted = soluzione.toCharArray();


        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams lp2 = new TableRow.LayoutParams();
        lp2.setMargins(spazioLateraleBottoni,spazioTopBottoni,spazioLateraleBottoni,spazioTopBottoni);

        //ricavo il numero di righe, lo trasformo in int e lo passo ad un array di tableraw
        double r = Math.ceil((double)lunghezzaSoluzione/(double)bottoniPerRiga);
        int righe = (int)r;

        TableRow t[] = new TableRow[righe];


        //genero il numero di righe
        for(int i=0;i<righe;i++){

            t[i] = new TableRow(a);
            layoutSoluzione.addView(t[i],lp);
            
        }

       Stringa random = new Stringa(soluzione);


        //il ciclo genera i text view e li ordina nella tabella
        int count = 0;
        int j = 0;
        for(int i = 0; i < lunghezzaSoluzione; i++)
        {

            if(count == bottoniPerRiga)
            {
                j++;
                count=0;
            }

            if(!String.valueOf(soluzioneSplitted[i]).equals(" ")) {
                l[i] = new TextView(a);
                l[i].setText("");
                l[i].setWidth(dimensioneBottoni);
                l[i].setHeight(dimensioneBottoni);
                l[i].setBackgroundResource(R.color.bottoni);
                l[i].setTextSize(testoBottoni);
                l[i].setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t[j].addView(l[i], lp2);
            }
            else if(String.valueOf(soluzioneSplitted[i]).equals(" "))
            {
                l[i] = new TextView(a);
                l[i].setText(" ");
                l[i].setWidth(dimensioneBottoni);
                l[i].setHeight(dimensioneBottoni);
                l[i].setBackgroundResource(R.color.sfondo);
                l[i].setTextSize(testoBottoni);
                l[i].setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t[j].addView(l[i], lp2);
            }
            count++;
        }

        //questo ciclo genera bottoni per inserire il testo nella text view


        TableRow.LayoutParams lpB = new TableRow.LayoutParams(dimensioneBottoni,dimensioneBottoni);
        lpB.setMargins(spazioLateraleBottoni,spazioTopBottoni,spazioLateraleBottoni,spazioTopBottoni);


        char[] enigmaSplitted = enigma.mescola();
        double righeEnigma = Math.ceil((double)lunghezzaSenzaSpazi/(double)bottoniPerRiga);

        TableRow[] tr = new TableRow[(int)righeEnigma];


        for(int i=0;i<(int)righeEnigma;i++){

            tr[i] = new TableRow(a);
            layoutBottoni.addView(tr[i],lp);

        }

        j=0;
        count=0;
        for(int i=0;i<lunghezzaSenzaSpazi;i++)
        {

            if (count == bottoniPerRiga)
            {
                j++;
                count=0;
            }
            b[i] = new Button(a);
            b[i].setText(String.valueOf(enigmaSplitted[i]));
            b[i].setBackgroundResource(R.color.testo_bottoni);

            final int finalI = i;
            b[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(l[click].getText().equals(" ")){
                        click++;
                        l[click].setText(b[finalI].getText());
                        b[finalI].setEnabled(false);
                        click++;


                    }
                        else{
                        l[click].setText(b[finalI].getText());
                        b[finalI].setEnabled(false);
                        click++;
                }
                }
            });
            tr[j].addView(b[i],lpB);

            count++;

        }
    }
    public String getSoluzione(){

        return this.soluzione;

    }
    public TextView[] getSoluzioneInserita(){

        return this.l;

    }
    public void resetLivello(){
        //resetto click
        this.click = 0;
        //cancello tutto quello scritto
        for(int i = 0; i < lunghezzaSoluzione; i++) {


            if(!String.valueOf(l[i].getText()).equals(" ")) {
                l[i].setText("");
            }


        }
        //rendo nuovamente i bottoni cliccabili
        for(int i = 0; i < lunghezzaSenzaSpazi; i++) {

            b[i].setEnabled(true);


        }

    }


}


