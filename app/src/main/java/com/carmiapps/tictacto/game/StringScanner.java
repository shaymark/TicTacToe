package com.carmiapps.tictacto.game;

import java.util.concurrent.Semaphore;

public class StringScanner {
    String value;

    private Semaphore mutex =  new Semaphore(0);

    String next() {
        try {
            mutex.acquire();
            return this.value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    void setValue(String value) {
        this.value = value;
        mutex.release();
    }

}
