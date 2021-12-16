package com.jeanlima.calccashback;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvPorcentagem;
    SeekBar sbPorcentagem;

    EditText etValor;
    TextView tvCashback;
    TextView tvTotal;

    Switch swDesconto;
    EditText etDesconto;

    float porcentagem = 0f;
    float valor = 0f;
    float cashback = 0f;
    float total = 0f;
    float desconto = 0f;
    boolean cupom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPorcentagem = findViewById(R.id.tvPorcentagem);
        sbPorcentagem = findViewById(R.id.sbPorcentagem);

        tvCashback = findViewById(R.id.tvCashback);
        tvTotal = findViewById(R.id.tvTotal);
        etValor = findViewById(R.id.etValor);

        swDesconto = findViewById(R.id.swDesconto);
        etDesconto = findViewById(R.id.etDesconto);

        swDesconto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etDesconto.setEnabled(true);
                    cupom = true;

                } else {
                    etDesconto.setEnabled(false);
                    cupom = false;

                }
                atualizaValores();
            }
        });

        etDesconto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizaValores();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizaValores();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        sbPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                tvPorcentagem.setText(Math.round(porcentagem) + "%");

                atualizaValores();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void atualizaValores(){

        valor = Float.valueOf(etValor.getText().toString());

        if(cupom && !etDesconto.getText().toString().equals("") && etDesconto.isEnabled()){
            desconto = Float.valueOf(etDesconto.getText().toString());
            valor = valor - desconto;
        }

        cashback = valor * porcentagem/100;
        total = valor - cashback;

        tvCashback.setText("R$ "+String.valueOf(cashback));
        tvTotal.setText("R$ "+String.valueOf(total));
    }
    

}