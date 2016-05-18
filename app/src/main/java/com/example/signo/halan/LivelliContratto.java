package com.example.signo.halan;

import android.provider.BaseColumns;

/**
 * Created by signo on 07/05/2016.
 * Codice presente nella documentazione ufficiale modificato ed adattato
 */
public final class LivelliContratto {
    // Creo un costruttore vuoto per evitare instanze accidentali della classe
    public LivelliContratto() {}

    /* Classe annidata con i dettagli della tabella e delle varie colonne
    *  _________________________
    *  |#######livelli##########|
    *  |esposto|soluzione|autore|
    *  |       |         |      |
    *  |       |         |      |
    *  -------------------------
    *
    */
    public static abstract class Livelli implements BaseColumns {
        public static final String TABELLA_NOME = "livelli";
        public static final String COLONNA_ESPOSTO = "esposto";
        public static final String COLONNA_SOLUZIONE = "soluzione";
        public static final String COLONNA_AUTORE = "autore";
    }
}
