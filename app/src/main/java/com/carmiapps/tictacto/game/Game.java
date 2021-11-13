package com.carmiapps.tictacto.game;

public class Game {

	GameView gameView;
	StringScanner scanner = new StringScanner();

	public Game(GameView gameView) {
		this.gameView = gameView;
	}

	public void onInput(String inputString) {
		scanner.setValue(inputString);
	}

	public void newGame() {
		new Thread(
				() -> startGame()
		).start();
	}

	private void startGame() {
		Renderer renderer = new Renderer(gameView);
		Board board= new Board(gameView);
		boolean gameEnded = false;
		renderer.renderBoard(board);

		Player player1 = new Player(gameView, scanner, board, Mark.X, "Jacob");
		Player player2 = new Player(gameView, scanner, board, Mark.O, "Yoni");

		while (!gameEnded) {

			if (board.isGameEnded()) {
				break;
			}

			for (int player = 1; player < 3; player++) {
				if(player==1) {
					if(!player1.playTurn()){
						gameEnded=true;
					};
				}
				if(player==2) {
					if(!player2.playTurn()){
						gameEnded=true;
					};
				}
			}

		}
	}
}