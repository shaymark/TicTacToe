package com.carmiapps.tictacto.game;


public class Player {
    public final Board board;
    public final Mark mark;
    public final String player;
    final InputNumber inp;
    final Renderer renderer;
    private int Score;
    public GameView gameView;
    private StringScanner scanner;

    public Player(GameView gameView, StringScanner scanner, Board board, Mark mark, String player){
        this.gameView = gameView;
        this.scanner = scanner;
        inp = new InputNumber(gameView);
        renderer = new Renderer(gameView);
        this.board = board;
        this.mark=mark;
        this.player=player;
        this.Score=1;
    }

    public boolean playTurn(){
        int col;
        int row;
        boolean endloop= false;
        while(!endloop) {
            gameView.addInstraction(this.player + " where to put mark? ");
            gameView.addInstraction("Write row number  and press enter.");

            row = inp.getNuber(gameView, scanner);

            gameView.addInstraction("Write column number  and press enter.");
            col = inp.getNuber(gameView, scanner);

            if (this.board.getMark(row - 1, col - 1) == Mark.BLANK) {
                endloop=true;
                board.putMark(this.mark, row - 1, col - 1);
                renderer.renderBoard(board);

                if (this.board.isGameEnded()) {
                    gameView.addInstraction("Very Good "+this.player + ",  You Win" +
                            " !!!!"+ "Your Score is: "+this.Score++);

                    gameView.onGameEnd();
                    return false;
                }
            }else{
                gameView.addInstraction("The cell is occupied , Try again ");
            }

        }
        return true;
    }

}
