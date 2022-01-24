package com.jeanlima.consumowebretrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.consumowebretrofit.model.CEP;
import com.jeanlima.consumowebretrofit.services.CEPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnAcessar;
    TextView tvResultado;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        btnAcessar = findViewById(R.id.btnAcessar);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarCEPRetrofit();
            }
        });



    }

    private void recuperarCEPRetrofit(){

        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP("01001000");

        //cria tarefa assíncrona dentro de uma thread secundária para fazer o acesso ao serviço web!
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){

                    CEP cep =  response.body();
                    tvResultado.setText(cep.getLogradouro() + " / " +cep.getLocalidade());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }

}