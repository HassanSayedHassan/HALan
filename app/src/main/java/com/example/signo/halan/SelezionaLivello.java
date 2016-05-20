package com.example.signo.halan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SelezionaLivello extends AppCompatActivity {

    private int livelli;
    private int marginiX;
    private int marginiY;
    private int displayX,displayY;
    private int layoutX,layoutY;
    private int livelliPerRiga = 5;
    private int dimensioneBottoni;
    private int righe;
    private int spazioLateraleBottoni;
    private TableLayout tab;
    private TableRow[] r;
    private Button[] b;
    TableRow.LayoutParams lp,lpB;
    Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_livello);
        //elimino la barra superiore
        getSupportActionBar().hide();
        c = this;
        //ricavo lo spazio laterale per i pulsanti
        spazioLateraleBottoni = (int) getResources().getDimension(R.dimen.spazioLateraleBottoni);
        //ricavo la larghezza pagina
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayX = size.x;
        displayY = size.y;
        //ricavo i margini pagina
        marginiX = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        marginiY = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        //ricavo lo spazio del layout
        layoutX = this.displayX-(2*marginiX);
        //dimensione dei bottoni tenendo conto dei margini del layout e dello spazio tra i pulsanti
        Log.w("DimX",String.valueOf(layoutX));
        Log.w("marginiX",String.valueOf(marginiX));
        dimensioneBottoni =(this.layoutX-(2*spazioLateraleBottoni*livelliPerRiga))/livelliPerRiga;
        //creo i parametri da passare alle righe
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        //parametri per i bottoni
        lpB = new TableRow.LayoutParams(dimensioneBottoni,dimensioneBottoni);
        lpB.setMargins(spazioLateraleBottoni,spazioLateraleBottoni,spazioLateraleBottoni,spazioLateraleBottoni);
        //ricavo la tabella dal layout
        tab = new TableLayout(this);
        tab = (TableLayout)findViewById(R.id.tabellaLivelli);


        //ricavo il numero di livelli presenti nel database
        GestoreLivello g = new GestoreLivello(this);
        livelli = g.getNumeroLivelli();

        //calcolo il numero di righe da stampare
        righe = (int) Math.ceil((double)livelli/(double)livelliPerRiga);

        //istanzio i pulsanti
        b = new Button[livelli];

        //istanzio gli oggetti tableRaw
        r = new TableRow[righe];

        int count = 0;
        int j=0;
        //creo la prima riga
        r[j]=new TableRow(this);
        tab.addView(r[j],lp);
        for(int i=0;i<livelli;i++){

            if(count == livelliPerRiga)
            {
                j++;
                //creo la riga e la aggiungo al TableLayout
                r[j]=new TableRow(this);
                tab.addView(r[j],lp);
                count = 0;
            }



            //la riempio con i bottoni
            b[i]= new Button(this);
            b[i].setText(String.valueOf(i+1));
            b[i].setTextColor(Color.parseColor("white"));
            b[i].setBackgroundResource(R.color.bottoni);
            r[j].addView(b[i],lpB);
            final int liv = i+1;
            Log.w("Livello",String.valueOf(liv));
            //listener per i bottoni
            b[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(c, Partita.class);
                    intent.putExtra("livello",liv);
                    startActivity(intent);

                }
            });







            count++;


        }


        Log.w("Righe",String.valueOf(righe));


    }
}
