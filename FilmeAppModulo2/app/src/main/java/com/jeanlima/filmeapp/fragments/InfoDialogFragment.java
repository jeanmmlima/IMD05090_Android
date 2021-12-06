package com.jeanlima.filmeapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class InfoDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Informações");
        builder.setMessage("Sobre Autores");

        return builder.create();

    }

}