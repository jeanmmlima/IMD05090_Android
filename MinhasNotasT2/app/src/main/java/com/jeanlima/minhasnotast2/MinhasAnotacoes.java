package com.jeanlima.minhasnotast2;

import android.content.Context;
import android.content.SharedPreferences;

public class MinhasAnotacoes {

    private SharedPreferences preferencias;
    public final String NOME_ARQUIVO = "anotacao.preferencias";
    private final String CHAVE_NOTA = "nota";

    private SharedPreferences.Editor editor;

    private Context contexto;

    public MinhasAnotacoes(Context c){
        this.contexto = c;
        preferencias = contexto.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferencias.edit();
    }

    //manipular o arquivo
    public void salvarAnotacao(String nota){
        editor.putString(CHAVE_NOTA,nota);
        editor.commit();
    }

    public String recuperarAnotacao(){
        return preferencias.getString(CHAVE_NOTA,"");
    }

}
