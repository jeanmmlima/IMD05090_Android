package com.jeanlima.ciclodevida;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvNum;
    Button btnContar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNum = findViewById(R.id.tvNumero);
        btnContar = findViewById(R.id.btnContar);

        if(savedInstanceState != null){
            int x = savedInstanceState.getInt("conta");
            tvNum.setText(String.valueOf(x));
        }

        btnContar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contar();
            }
        });



        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
    }

    public void contar(){
        int x = Integer.parseInt(tvNum.getText().toString());
        x += x;
        tvNum.setText(String.valueOf(x));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("conta", Integer.parseInt(tvNum.getText().toString()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(),"onDestroy",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(getApplicationContext(),"onRestart",Toast.LENGTH_SHORT).show();

    }
}