package com.example.signo.halan;

/**
 * Created by signo on 07/05/2016.
 */
public class DescrittoreLivello {

    private int livello;
    private String esposto;
    private String soluzione;
    private String autore;

    DescrittoreLivello(){

    }

    public void setLivello(int livello){

        this.livello = livello;

    }
    public void setEsposto(String esposto){

        this.esposto = esposto;

    }
    public void setSoluzione(String soluzione){

        this.soluzione = soluzione;

    }
    public void setAutore(String autore){

        this.autore = autore;

    }

    public int getLivello(){
        return (livello);
    }
    public String getEsposto(){
        return(esposto);
    }
    public String getSoluzione(){

        return(soluzione);
    }
    public String getAutore(){

        return(autore);
    }

}
