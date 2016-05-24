package com.example.signo.halan;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by signo on 22/05/2016.
 */
public class DialogLivelloSuperato extends DialogFragment {

    private int livelloRaggiunto;

    public static DialogLivelloSuperato newInstance(int livelloRaggiunto) {

        DialogLivelloSuperato f = new DialogLivelloSuperato();

        Bundle args = new Bundle();
        args.putInt("livelloRaggiunto", livelloRaggiunto);
        f.setArguments(args);
        return f;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        livelloRaggiunto = getArguments().getInt("livelloRaggiunto");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_livello_superato, null))



                .setPositiveButton("Avanti", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Intent intent = new Intent(getActivity(), Partita.class);
                        livelloRaggiunto++;
                        intent.putExtra("livello",livelloRaggiunto);
                        startActivity(intent);


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
