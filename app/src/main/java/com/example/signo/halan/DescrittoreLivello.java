package com.example.signo.halan;

/**
 * Created by signo on 07/05/2016.
 */
public class DescrittoreLivello {

    private int livello;
    private String esposto;
    private String soluzione;
    private String autore;
    private String soluzione2;

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
    public void setSoluzione2(String soluzione2){
        this.soluzione2 = soluzione2;
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
    public String getSoluzione2(){
        return (soluzione2);
    }

}
