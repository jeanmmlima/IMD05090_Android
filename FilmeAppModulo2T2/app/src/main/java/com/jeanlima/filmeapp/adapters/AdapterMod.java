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

    //metodo para criar cada elemento da lista
    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //1. Ao criar cada ViewHolder (cada elemento da lista), inflar o layout definido: elemento_lista_mod.xml

        View elementoLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_mod,parent,false);
        return new MinhaViewHolder(elementoLista);
    }

    //metodo para manipular/gerenciar cada elemento da lista
    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        //preencher os elementos (view holders) com os elementos de lista filmes;

        //captura cada filme da lista
        Filme filme = listaFilmes.get(position);

        //atualizo as informações visuais de nome, ano e estrelas do parametro holder

        holder.tvNome.setText(filme.getNome());
        holder.tvAno.setText(String.valueOf(filme.getAno_lancamento()));
        holder.rbEstrelas.setRating(filme.getEstrelas());
    }

    //quantidade de elementos na lista
    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    //MinhaViewHolder é a classe (interna) que representa CADA elemento da lista personalizada
    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNome;
        TextView tvAno;
        RatingBar rbEstrelas;
        CardView cvElemento;

        public MinhaViewHolder(View itemView){
            super(itemView);

            tvNome = itemView.findViewById(R.id.tvNomeRV);
            tvAno = itemView.findViewById(R.id.tvAnoRV);
            rbEstrelas = itemView.findViewById(R.id.rbEstrelasRV);
            cvElemento = itemView.findViewById(R.id.cvElemento);

            cvElemento.setOnClickListener(this);


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
