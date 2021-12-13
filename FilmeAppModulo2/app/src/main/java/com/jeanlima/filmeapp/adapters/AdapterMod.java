package com.jeanlima.filmeapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jeanlima.filmeapp.R;
import com.jeanlima.filmeapp.model.Filme;

import java.util.List;

public class AdapterMod extends RecyclerView.Adapter<AdapterMod.MinhaViewHolder> {

    private List<Filme> listaFilmes;


    public AdapterMod(List<Filme> lista){
        this.listaFilmes = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //1. Ao criar cada viewHolder (elemento da lista), inflar o layout definido: elemento_lista_mod.
        View elementoLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista_mod,parent,false);
        return new MinhaViewHolder(elementoLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        //preencher os elementos (view holders) com os elementos de listaFilmes

        //captura cada elemento da lista pelo parametro POSICAO
        Filme filme = listaFilmes.get(position);

        //atualizo as informações visuais de nome, ano e estrelas atraves do parametro HOLDER

        holder.tvNome.setText(filme.getNome());
        holder.tvAno.setText(String.valueOf(filme.getAno_lancamento()));
        holder.rbEstrelas.setRating(filme.getEstrelas());


    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    //representa CADA elemento da lista personalizada
    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNome;
        TextView tvAno;
        RatingBar rbEstrelas;
        CardView cvItem;

        public MinhaViewHolder(View itemView){
            super(itemView);

            tvNome = itemView.findViewById(R.id.tvNomeRV);
            tvAno = itemView.findViewById(R.id.tvAnoRV);
            rbEstrelas = itemView.findViewById(R.id.rbEstrelasRV);

            cvItem = itemView.findViewById(R.id.cvItem);

            cvItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            listener.clicouNoElemento(getLayoutPosition());

        }
    }

    //interface para identificar o elemento da lista que foi clicado
    public interface AoClicarNaLista{
        void clicouNoElemento(int position);
    }

    private AoClicarNaLista listener;

    public void implementaAoClicarNaLista(AoClicarNaLista listener){
        this.listener = listener;
    }



}
