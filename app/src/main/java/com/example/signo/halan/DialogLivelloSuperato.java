package com.example.signo.halan;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by signo on 22/05/2016.
 */
public class DialogLivelloSuperato extends DialogFragment {

    //livelloRaggiunto è il livello dal quale provengo
    private int livelloRaggiunto;
    private int livelloMax;
    private SharedPreferences sharedPref;
    private String soluzione2;
    private DescrittoreLivello livello;
    private int numeroTotLivelli;


    public static DialogLivelloSuperato newInstance(int livelloRaggiunto) {

        DialogLivelloSuperato f = new DialogLivelloSuperato();

        Bundle args = new Bundle();
        args.putInt("livelloRaggiunto", livelloRaggiunto);
        f.setArguments(args);
        return f;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //ricavo il livello massimo raggiunto fino ad ora
        sharedPref = getActivity().getSharedPreferences("preferenze",getActivity().MODE_PRIVATE);
        livelloMax = sharedPref.getInt("livelloRaggiunto",1);


        livelloRaggiunto = getArguments().getInt("livelloRaggiunto");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        if(livelloRaggiunto == livelloMax)
        {
            livelloMax++;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("livelloRaggiunto",livelloMax);
            editor.commit();

        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_livello_superato, null);
        GestoreLivello gestore = new GestoreLivello(getActivity());
        livello = gestore.leggiLivello(livelloRaggiunto);
        this.soluzione2 = livello.getSoluzione2();

        GestoreLivello g = new GestoreLivello(getActivity());
        numeroTotLivelli = g.getNumeroLivelli();


        TextView textSoluzione2 = (TextView)view.findViewById(R.id.soluzione2);
        textSoluzione2.setText(soluzione2);

        builder.setView(view)



                .setPositiveButton("Avanti", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {




                        if(livelloRaggiunto < numeroTotLivelli) {
                            Intent intent = new Intent(getActivity(), Partita.class);
                            livelloRaggiunto++;
                            intent.putExtra("livello", livelloRaggiunto);
                            startActivity(intent);

                        }
                        else
                        {
                            Intent intent = new Intent(getActivity(), Menu.class);

                            startActivity(intent);

                        }

                    }
                })
                .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }
}
