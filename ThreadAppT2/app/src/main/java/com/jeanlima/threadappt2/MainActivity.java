package com.jeanlima.threadappt2;

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

    //Handler leva dados para thread da classe onde o handler foi declarado
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
        /*
        //Utilizando UI Thread (thread principal) para executar uma tarefa específica
        for(int i = 0; i <= 15; i++){
            Log.d("thread","contador: "+i);
            try {
                //Thread tá no contexto da UI Thread
               Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

         */

        MinhaThread minhaThread = new MinhaThread();
        minhaThread.start();

        //MinhaRunnable minhaRunnable = new MinhaRunnable();
        //new Thread(minhaRunnable).start();

    }

    //Classe MinhaThread pode ser interna ou externa
    class MinhaThread extends Thread{

        @Override
        public void run() {
            //super.run();
            for(int i = 0; i <= 15; i++){

                if(pararExecucao){
                    return;
                }

                Log.d("thread","contador: "+i);
                numero = i;
                //Não é recomendado/possível fazer alterações na Thread princiapl UI Thread a partir das threads criadas/secundárias
                //btnIniciar.setText("contato: "+i);

                //Enviar comando/dado para UI Thread e APENAS UI Thread a partir de uma thread secundária
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("contato: "+numero);
                    }
                });

                 */

                //Outro forma de enviar dados/comandos para a thread principal e para outras threads
                //Handler: habilita a troca de dados entre threads
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("contato: "+numero);
                    }
                });



                try{
                    //Thread está no contexto de MinhaThread. Não representa a UI Thread. Representa uma NOVA thread.
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class MinhaRunnable implements Runnable{

        @Override
        public void run() {
            for(int i = 0; i <= 15; i++){
                Log.d("thread", "contador RUN"+i);
                try{
                    //Thread está no contexto de MinhaRunnable
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}