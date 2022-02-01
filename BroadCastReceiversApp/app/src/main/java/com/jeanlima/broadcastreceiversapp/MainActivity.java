package com.jeanlima.broadcastreceiversapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //responsável por implementar O QUE FAZER quando um evento específico acontecer
        BroadcastReceiver br = new MeuBroadCastReceiver();

        //filtro as ações via intent - filtra ações para saber quando o evento específico aconteceu
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        //registrar para ouvir os eventos da intent (filtro)
        this.registerReceiver(br,filter);

        //registrando para escutar o meu sinal de broadcast (MEU_AVISO)
        IntentFilter filter2 = new IntentFilter("com.example.broadcast.MEU_AVISO");
        this.registerReceiver(br,filter2);

    }
    //enviando o broadcast
    public void enviaBC(View v){
        Intent intent = new Intent();
        intent.setAction("com.example.broadcast.MEU_AVISO");
        intent.putExtra("dado","Texto enviado via broadcast do meu app para meu ou app OU para outros apps que escutem o meu broadcast");
        sendBroadcast(intent);
    }
}