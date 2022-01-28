package com.jeanlima.consumowebretrofitt2.service;

import com.jeanlima.consumowebretrofitt2.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostagemService {

    //declarar as requisições necessárias

    //1. recuperar a lista de postagens
    //1.1 CALL - a chamada - recebe o tipo de RETORNO da chamada
    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);

    //No PUT passo o id do dado que quero atualizar
    //PUT substitui todos os campos do objeto
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);

    //Não substitui os campos passados como NULL
    @PATCH("/posts/{id}")
    Call<Postagem> atualizarPostagemPatch(@Path("id") int id, @Body Postagem postagem);

    @DELETE("/posts/{id}")
    Call<Void> deletarPostagem(@Path("id") int id);

}
