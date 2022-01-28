package com.jeanlima.serviceapp;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MensagemAtrasadaService extends IntentService {

    public static final String MSG = "mensagem";
    //public String msg;

    public static final String CHANNEL_ID = "5463";
    public static final int NOTIFICATION_ID = 5463;


    public MensagemAtrasadaService(){
        super("MensagemAtrasadaService");
    }

    //execução do service
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //exeucta no contexto de uma thread secundária
        synchronized (this){
            try{
                //faz a thread secundaria esperar 10s
                //informando para o service esperar 10s para executar
                wait(10000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        String mensagem = intent.getExtras().getString(MSG);
        showMsg(mensagem);

    }

    private void criarCanalDeNotificacao(){
        //a partir da API 26+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "meu_canal";
            String description = "canalparaNotificacoes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    public void showMsg(String mensagem){
        //Log.v("MSG", "A mensagem é: "+mensagem);

        //NOTIFICAÇÃO

        this.criarCanalDeNotificacao();

        NotificationCompat.Builder noteBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("A mensagem chegou!")
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 1000}) //espera 0 mili para vibrar por 1 mili
                .setAutoCancel(true) //notificao desaparece quando usuario clica uma vez
                .setSmallIcon(android.R.drawable.sym_def_app_icon);

        Intent acaoAoClicarNaNotificacao = new Intent(this,MainActivity.class);

        PendingIntent acaoPendente = PendingIntent.getActivity(
                this,
                0,
                acaoAoClicarNaNotificacao,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        noteBuilder.setContentIntent(acaoPendente);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,noteBuilder.build());
    }
}
