package com.jeanlima.consumowebretrofitt2.service;

import com.jeanlima.consumowebretrofitt2.model.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    /* cep estático

    @GET("01001000/json/")
    Call<Endereco> recuperarCEP();

     */

    //cep dinamico

    @GET("{cep}/json/")
    Call<Endereco> recuperarCEP(@Path("cep") String cep);

}
