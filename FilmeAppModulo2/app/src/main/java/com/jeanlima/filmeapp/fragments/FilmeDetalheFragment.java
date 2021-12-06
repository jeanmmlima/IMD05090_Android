package com.jeanlima.filmeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jeanlima.filmeapp.R;
import com.jeanlima.filmeapp.model.Filme;

public class FilmeDetalheFragment extends Fragment {

    public static final String TAG_DETALHE = "tagDetalhe";
    public static final String FILME = "filme";

    Filme mFilme;

    TextView tvNomeFilme;
    TextView tvDuracao;
    TextView tvAno;
    RatingBar rbEstrelas;

    public FilmeDetalheFragment() {
        // Required empty public constructor
    }


    public static FilmeDetalheFragment novaInstancia(Filme filme) {

        //1. instancia do fragmento que vai ser iniciado
        FilmeDetalheFragment fragment = new FilmeDetalheFragment();

        //2. instancia de parametros que é tipo bundle
        Bundle parametros = new Bundle();

        //3. passar filme para o bundle
        parametros.putSerializable(FILME,filme);

        //4. setar parametros no fragmento
        fragment.setArguments(parametros);

        //5. retorna o fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFilme = (Filme) getArguments().getSerializable(FILME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_filme_detalhe, container, false);

        tvNomeFilme = layout.findViewById(R.id.tvNome);
        tvDuracao = layout.findViewById(R.id.tvDuracao);
        tvAno = layout.findViewById(R.id.tvAno);
        rbEstrelas = layout.findViewById(R.id.rbEstrelas);

        if(mFilme != null){
            tvNomeFilme.setText(mFilme.getNome());
            tvDuracao.setText(mFilme.getDuracao());
            tvAno.setText(String.valueOf(mFilme.getAno_lancamento()));
            rbEstrelas.setRating(mFilme.getEstrelas());
        }

        return layout;
    }
}