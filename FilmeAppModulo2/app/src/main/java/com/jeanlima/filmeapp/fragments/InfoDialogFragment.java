package com.jeanlima.filmeapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class InfoDialogFragment extends DialogFragment
implements DialogInterface.OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Informações");
        builder.setMessage("Sobre Autores");

        builder.setPositiveButton("Ver site",this);
        builder.setNegativeButton("SAIR",this);

        return builder.create();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        Activity activity = getActivity();

        if(activity instanceof AoClicarEmInfo){
            AoClicarEmInfo listener = (AoClicarEmInfo) activity;
            listener.aoClicar(which);
        }

    }

    public interface AoClicarEmInfo{
        public void aoClicar(int botao);
    }
}