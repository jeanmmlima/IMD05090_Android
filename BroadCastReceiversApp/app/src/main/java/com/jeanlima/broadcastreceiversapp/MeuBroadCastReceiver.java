package com.jeanlima.broadcastreceiversapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MeuBroadCastReceiver extends BroadcastReceiver {

    private static final String TAG = "MeuBroadcastReceiver";
    //vai executar quando o evento de broadcast escolhido (ex: conexao mudar) acontecer
    @Override
    public void onReceive(Context context, Intent intent) {

        StringBuilder sb = new StringBuilder();
        sb.append("Acao: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        sb.append("DADO: "+ intent.getExtras().getString("dado"));
        String log = sb.toString();
        Log.d(TAG,log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

    }
}
