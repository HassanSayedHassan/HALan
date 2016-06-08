package com.example.signo.halan;

import android.renderscript.Sampler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by signo on 17/05/2016.
 */
public class Stringa {

    private String stringa;
    private int lunghezza;
    private DescrittoreLivelloServer risposta;

    Stringa(String stringa){

        this.stringa = stringa;

    }

    public char[] mescola(){

        int x;
        int y;
        char temp;
        stringa = rimuoviSpazi();
        int lunghezzaStringa = stringa.length();
        char[] stringaSplitted = stringa.toCharArray();
        Random r = new Random();
        for(int i=0;i<50;i++){

            x= r.nextInt(lunghezzaStringa);
            y = r.nextInt(lunghezzaStringa);

            temp = stringaSplitted[x];
            stringaSplitted[x] = stringaSplitted[y];
            stringaSplitted[y]=temp;
        }

        return(stringaSplitted);

    }
    public String rimuoviSpazi(){

        stringa = stringa.replace(" ", "");
        return stringa;



    }
    public int getLunghezza(){

        lunghezza = stringa.length();
        return lunghezza;
    }

    public int controlloRisultato(TextView[] tView){

        int lunghezza = stringa.length();
        char[] stringaSplitted = stringa.toCharArray();
        String str, chr;

        for (int i=0; i<lunghezza; i++){

            str = tView[i].getText().toString();
            chr = String.valueOf(stringaSplitted[i]);

            if(!chr.equals(str)){
                return 0;
            }

        }

        return 1;
    }

    public DescrittoreLivelloServer splitRisposta(){


        int livelliDaAggiungere;
        //splitto la string con tappo @
        String[] splitted = stringa.split("@");
        //ricavo i livelli da aggiungere

        livelliDaAggiungere = (int)Integer.parseInt(String.valueOf(splitted[0]));
        //oggetto che contiene informazioni sui livelli da aggiungere
        DescrittoreLivelloServer descrittore = new DescrittoreLivelloServer(livelliDaAggiungere);

        int indice = 1;
        for(int i=0;i<livelliDaAggiungere;i++){

            descrittore.setAutore(splitted[indice],i);
            indice++;
            descrittore.setEsposto(splitted[indice],i);
            indice++;
            descrittore.setSoluzione(splitted[indice],i);
            indice++;
            descrittore.setSoluzione2(splitted[indice],i);
            indice++;

        }

        return descrittore;

    }
}

