package com.jeanlima.filmeapp2.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jeanlima.filmeapp2.R;
import com.jeanlima.filmeapp2.model.Filme;


public class FilmeDetalheFragment extends Fragment {

    TextView tvNome;
    TextView tvDuracao;
    TextView tvAno;
    RatingBar rbEstrelas;

    public static final String TAG_DETALHE = "tagDetalhes";
    public static final String FILME = "filme";

    Filme mFilme;

    public static FilmeDetalheFragment novaInstancia(Filme filme){

        //1. Criar uma instancia do detalhe fragmento
        FilmeDetalheFragment fragmentoDetalhe = new FilmeDetalheFragment();

        //2. Colocando dados nos parametros da instancia de Fragmento Detalhe
        //2.1 nova instancia de parametros
        Bundle parametros = new Bundle();
        parametros.putSerializable(FILME,filme);

        //3. Passando parametros (bundle de dados) para o fragmento de detalhe
        fragmentoDetalhe.setArguments(parametros);

        //4. Retorna a instancia de fragmento detalhe criada COM os dados (com o filme clicado pelo o usuario)
        return fragmentoDetalhe;

    }

    public FilmeDetalheFragment() {
        // Required empty public constructor
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

        tvNome = layout.findViewById(R.id.tvNome);
        tvDuracao = layout.findViewById(R.id.tvDuracao);
        tvAno = layout.findViewById(R.id.tvAno);
        rbEstrelas = layout.findViewById(R.id.rbEstrelas);

        if(mFilme != null){
            tvNome.setText(mFilme.getNome());
            tvDuracao.setText(mFilme.getDuracao());
            tvAno.setText(String.valueOf(mFilme.getAno_lancamento()));
            rbEstrelas.setRating(mFilme.getEstrelas());
        }

        return layout;
    }
}