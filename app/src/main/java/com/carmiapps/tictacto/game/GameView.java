package com.carmiapps.tictacto.game;

public interface GameView {
    void addInstraction(String instraction);
    void setBoard(String board);
    void onGameEnd();
}

