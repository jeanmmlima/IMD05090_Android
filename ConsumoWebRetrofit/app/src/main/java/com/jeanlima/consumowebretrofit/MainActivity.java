package com.jeanlima.consumowebretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.consumowebretrofit.model.CEP;
import com.jeanlima.consumowebretrofit.model.Postagem;
import com.jeanlima.consumowebretrofit.services.CEPService;
import com.jeanlima.consumowebretrofit.services.PostagemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnAcessar;
    TextView tvResultado;

    private Retrofit retrofit;

    private List<Postagem> listaPostagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        btnAcessar = findViewById(R.id.btnAcessar);

        /*
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         */
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recuperarCEPRetrofit();
                //listarPostagens();

                //salvarPostagem();

                //atualizarPostagem();

                //atualizarPostagemViaPatch();

                deletarPostagem();
            }
        });



    }

    private void listarPostagens(){

        //1. Instanciar a interface service da entidade
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //2. Instanciar chamada do tipo definido no service
        Call<List<Postagem>> chamada = postagemService.recuperarPostagens();

        //parametro response no metodo onResponse representa a resposta da chamada (requisicao ao web service)
        chamada.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if(response.isSuccessful()){
                    listaPostagens = response.body();
                    for(int i = 0; i < listaPostagens.size(); i++){
                        Postagem post = listaPostagens.get(i);
                        Log.i("Resultado: ", "ID: "+post.getId() + "/ Titulo: "+post.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });


    }

    private void salvarPostagem(){

        //1. criar a postagem que eu quero salvar
        Postagem post = new Postagem("1234","Postagem Salva via Retrofit","Corpo da mensagem");

        //2. Instanciar a interface service da entidade
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar chamada do tipo definido no service
        Call<Postagem> chamada = postagemService.salvarPostagem(post);

        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem = response.body();
                    tvResultado.setText(
                            "Código: "+response.code() +
                                    " id: " + postagem.getId()+
                                    " titulo: " + postagem.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });

    }

    private void atualizarPostagem(){

        Postagem postagem = new Postagem("1234","Novo Titulo","Novo Corpo");

        //2. Instanciar a interface service da entidade
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar chamada do tipo definido no service
        Call<Postagem> chamada = postagemService.atualizarPostagem(2,postagem);

        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem = response.body();
                    tvResultado.setText(
                            "Status: "+response.code() +
                                    " id: " + postagem.getId()+
                                    " titulo: " + postagem.getTitle()+
                                    " corpo: " + postagem.getBody() +
                                    " id user: " + postagem.getUserId()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });

    }

    private void atualizarPostagemViaPatch(){
        Postagem postagem = new Postagem("1234", null,"Novo Corpo");

        //2. Instanciar a interface service da entidade
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar chamada do tipo definido no service
        Call<Postagem> chamada = postagemService.atualizarPostagemPatch(2,postagem);

        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem = response.body();
                    tvResultado.setText(
                            "Status: "+response.code() +
                                    " id: " + postagem.getId()+
                                    " titulo: " + postagem.getTitle()+
                                    " corpo: " + postagem.getBody() +
                                    " id user: " + postagem.getUserId()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void deletarPostagem(){


        PostagemService postagemService = retrofit.create(PostagemService.class);


        Call<Void> chamada = postagemService.deletarPostagem(2);
        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResultado.setText("Status: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

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