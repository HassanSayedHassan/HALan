package com.example.signo.halan;

/**
 * Created by carmine on 27/05/2016.
 */
public class DescrittoreLivelloServer {

    private int numerolivelli;
    private String[] esposto = new String[200];
    private String[] soluzione = new String[200];
    private String[] autore = new String[200];
    private String[] soluzione2 = new String[200];
    private int i=0;

    DescrittoreLivelloServer(){

    }

    public void setLivelli(int numerolivelli){

        this.numerolivelli = numerolivelli;

    }
    public void setEsposto(String esposto, int i){

        this.esposto[i] = esposto;

    }
    public void setSoluzione(String soluzione, int i){

        this.soluzione[i] = soluzione;

    }
    public void setAutore(String autore, int i){

        this.autore[i] = autore;

    }
    public void setSoluzione2(String soluzione2, int i){

        this.soluzione2[i] = soluzione2;

    }

    public int getLivelli(){

        return (numerolivelli);
    }
    public String[] getEsposto(){

        return(esposto);

    }
    public String[] getSoluzione(){

        return(soluzione);
    }
    public String[] getAutore(){

        return(autore);
    }
    public String[] getSoluzione2(){

        return (soluzione2);

    }

}


