package com.jeanlima.filmeapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.fragment.app.DialogFragment;

import com.jeanlima.filmeapp.R;
import com.jeanlima.filmeapp.model.Filme;

public class FilmeDialogFragment extends DialogFragment {

    public static final String DIALOG_TAG = "addDialog";

    private EditText etNome;
    private EditText etDuracao;
    private EditText etAno;
    private RatingBar rbEstrelas;

    private Button btnSalvar;

    //variável que representa o filme que será adicionado

    Filme mFilme;

    public FilmeDialogFragment() {
        // Required empty public constructor
    }

    public static FilmeDialogFragment novaInstancia(){

        //1. instancia do fragmento que vai ser iniciado
        FilmeDialogFragment filmeDialogFragment = new FilmeDialogFragment();

        return filmeDialogFragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFilme = new Filme();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_filme_dialog, container, false);

        etNome = layout.findViewById(R.id.etNome);
        etDuracao = layout.findViewById(R.id.etDuracao);
        etAno = layout.findViewById(R.id.etAno);
        rbEstrelas = layout.findViewById(R.id.rbEstrelas);

        btnSalvar = layout.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFilme();
            }
        });

        getDialog().setTitle("Novo Filme");

        return layout;
    }

    public void salvarFilme(){

        //1. Atividade onde o fragmento está rodando
        Activity activity = getActivity();

        //2. A Atividade Implementa a interface?
        if(activity instanceof AoSalvarFilme){

            
            mFilme.setNome(etNome.getText().toString());
            mFilme.setDuracao(etDuracao.getText().toString());
            mFilme.setAno_lancamento(Integer.parseInt(etAno.getText().toString()));
            mFilme.setEstrelas(rbEstrelas.getRating());

        }

        AoSalvarFilme listener = (AoSalvarFilme) activity;
        listener.salvouFilme(mFilme);

        //fecha o dialog
        dismiss();

    }

    public interface AoSalvarFilme{
        void salvouFilme(Filme filme);
    }
}