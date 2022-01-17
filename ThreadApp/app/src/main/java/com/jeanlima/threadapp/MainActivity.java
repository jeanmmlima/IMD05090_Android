package com.jeanlima.threadapp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private Button btnParar;

    private boolean pararExecucao = false;
    private int numero;


    //Handler leva os dados/comandos para thread onde foi declarado
    //nesse caso, leva para UIThread que é a thread que implementa as atividades
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnParar = findViewById(R.id.btnParar);
    }

    public void pararThread(View view){
        pararExecucao = true;
    }

    public void iniciarThread(View view){

        pararExecucao = false;

        /* Utilizando thread principal para realizar atividades - erro
        for(int i = 0; i <= 15; i++){
            Log.d("thread","contador: "+i);
            try {
                //Thread está no contexto da UIThread (Thread principal do app)
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

         */

        MinhaThread minhaThread = new MinhaThread();
        minhaThread.start();

        //MinhaRunnable minhaRunnable = new MinhaRunnable();
        //new Thread(minhaRunnable).start();

    }

    //classe que implementa thread
    class MinhaThread extends Thread{

        @Override
        public void run() {
            //super.run();
            for(int i = 0; i <= 15; i++){

                if(pararExecucao){
                    return;
                }
                numero = i;
                Log.d("thread","contador: "+i);

                //Não é possível fazer alterações na Thread principal (UIThread) a partir de threads criadas/secundarias
                //btnIniciar.setText("Contato: "+i);

                //Enviar comando/dado para UI thread a partir de uma thread secundária/criada
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("Contato: "+numero);
                    }
                });

                 */

                //Outra forma de enviar dados/comandos para a thread principal (UI thread)
                //é utilizando handler
                //Handler: envia um código para ser executado em uma thread
                //habilita a troca de dados entre threads (secundárias ou secundária e princiapl)
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("Contato: "+numero);
                    }
                });

                try {
                    //MinhaThread está num contexto DIFERENTE da thread principal (Thread secundária do app)
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class MinhaRunnable implements Runnable{

        @Override
        public void run() {
            for(int i = 0; i <= 15; i++){
                Log.d("thread","contador RUN: "+i);

                try {
                    //MinhaThread está num contexto DIFERENTE da thread principal (Thread secundária do app)
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}