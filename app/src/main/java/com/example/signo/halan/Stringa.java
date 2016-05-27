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

    public DescrittoreLivelloServer splitRisposta(String stringa){

        int numerolivelli;
        int count=0, j=0, k=0;
        char[] temp;
        String chr, str;

        risposta = new DescrittoreLivelloServer();
        this.stringa = stringa;

        char[] stringaSplitted = stringa.toCharArray();

        numerolivelli = (int)(stringaSplitted[0]);
        risposta.setLivelli(numerolivelli);

        for (count = 0; count< numerolivelli; count++) {

            int k = 0;

            for (int i = 1; i < stringa.length(); i++) {

                chr = String.valueOf(stringaSplitted[i]);

                if (!chr.equals("tappo")) {

                    temp[j] = stringaSplitted[i];
                    j++;
                }
                    else {

                    int j = 0;

                    str = temp.toString();

                    switch (k) {
                        case 1:
                            risposta.setAutore(str, count);
                            break;
                        case 2:
                            risposta.setEsposto(str, count);
                            break;
                        case 3:
                            risposta.setSoluzione(str, count);
                            break;
                        case 4:
                            risposta.setSoluzione2(str, count);
                    }
                    k++;
                }


            }
            count++;
        }


        return risposta;
    }
}

