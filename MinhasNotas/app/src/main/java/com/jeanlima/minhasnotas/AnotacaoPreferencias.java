package com.jeanlima.minhasnotas;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {

    private SharedPreferences preferencias;
    public final String NOME_ARQUIVO = "anotacao.preferencias";
    private final String CHAVE_NOTA = "nota";

    private Context contexto;

    private SharedPreferences.Editor editor;

    public AnotacaoPreferencias(Context c){
        this.contexto = c;
        preferencias = contexto.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferencias.edit();
    }

    public void salvarAnotacao(String nota){
        editor.putString(CHAVE_NOTA,nota);
        editor.commit();
    }

    public String recuperarAnotacao(){
        return preferencias.getString(CHAVE_NOTA,"");
    }



}
