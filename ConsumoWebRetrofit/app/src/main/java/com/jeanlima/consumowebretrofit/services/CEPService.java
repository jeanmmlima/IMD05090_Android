package com.jeanlima.consumowebretrofit.services;

import com.jeanlima.consumowebretrofit.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    /*
    @GET("01001000/json/")
    Call<CEP> recuperarCEP();

     */
    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);
}
