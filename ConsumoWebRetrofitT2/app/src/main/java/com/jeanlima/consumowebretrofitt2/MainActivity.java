package com.jeanlima.consumowebretrofitt2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.consumowebretrofitt2.model.Endereco;
import com.jeanlima.consumowebretrofitt2.service.CEPService;

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
                recuperarCEPREtrofit();
            }
        });

    }

    private void recuperarCEPREtrofit(){

        CEPService cepService = retrofit.create(CEPService.class);
        Call<Endereco> chamada = cepService.recuperarCEP("01001000");

        //cria tarefa assíncrona dentro de uma thread secundária para fazer o acesso ao serviço web!
        chamada.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful()){

                    Endereco endereco = response.body();
                    tvResultado.setText(endereco.getBairro() + " / " + endereco.getLocalidade() +
                            " / " + endereco.getUf());

                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {

            }
        });

    }
}