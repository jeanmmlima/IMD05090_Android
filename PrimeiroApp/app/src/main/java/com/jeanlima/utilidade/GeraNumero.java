package com.jeanlima.utilidade;

import java.util.Random;

public class GeraNumero {
    public int getNumero(){
        int x;
        x = new Random().nextInt(11);
        return x;
    }
}
