package com.jeanlima.consumowebretrofitt2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanlima.consumowebretrofitt2.model.Endereco;
import com.jeanlima.consumowebretrofitt2.model.Postagem;
import com.jeanlima.consumowebretrofitt2.service.CEPService;
import com.jeanlima.consumowebretrofitt2.service.PostagemService;

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
                //recuperarCEPREtrofit();

                //listarPostagens();

                //salvarPostagem();
                //atualizarPost();
                //atualizarPostPatch();
                deletarPost();
            }
        });

    }

    private void listarPostagens(){

        //1. Instancia a interface do service da entidade (postagem)
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //2. Instanciar uma chamada (call) do tipo definido no service
        Call<List<Postagem>> chamada = postagemService.recuperarPostagens();

        //parametro response no metodo onResponse representa a resposta da chamada (requisicao ao web service)
        chamada.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if(response.isSuccessful()){
                    listaPostagens = response.body();
                    for(int i = 0 ; i < listaPostagens.size(); i++){
                        Postagem post = listaPostagens.get(i);
                        Log.i("Resultado", "ID: "+post.getId()+" / Titulo: "+post.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });

    }

    private void salvarPostagem(){
        //1. criar a postagem
        Postagem post = new Postagem("1234","Postagem salva com retorfit","Corpo da MSG");

        //2. Instancia a interface do service da entidade (postagem)
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar uma chamada (call) do tipo definido no service
        Call<Postagem> chamada = postagemService.salvarPostagem(post);

        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem =response.body();
                    tvResultado.setText(
                            "Status: "+response.code() +
                                    " id: " + postagem.getId() +
                                    " titulo: " +postagem.getTitle() +
                                    " id user: " + postagem.getUserId()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void atualizarPost(){

        //1. criar a postagem
        Postagem post = new Postagem("1234","Novo Titulo","Novo Corpo");

        //2. Instancia a interface do service da entidade (postagem)
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar uma chamada (call) do tipo definido no service
        Call<Postagem> chamada = postagemService.atualizarPostagem(3,post);

        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem = response.body();
                    tvResultado.setText(
                            "Status: "+response.code() +
                                    " id: " + postagem.getId() +
                                    " titulo: " +postagem.getTitle() +
                                    " id user: " + postagem.getUserId()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void atualizarPostPatch(){
        //1. criar a postagem
        Postagem post = new Postagem("1234",null,"Novo Corpo");

        //2. Instancia a interface do service da entidade (postagem)
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar uma chamada (call) do tipo definido no service
        Call<Postagem> chamada = postagemService.atualizarPostagemPatch(2,post);
        chamada.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagem = response.body();
                    tvResultado.setText(
                            "Status: "+response.code() +
                                    " id: " + postagem.getId() +
                                    " titulo: " +postagem.getTitle() +
                                    " id user: " + postagem.getUserId()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void deletarPost(){

        //2. Instancia a interface do service da entidade (postagem)
        PostagemService postagemService = retrofit.create(PostagemService.class);

        //3. Instanciar uma chamada (call) do tipo definido no service
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