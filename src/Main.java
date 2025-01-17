import sf.chess.ConsoleChessBoard;

public class Main {

	public static void main(String[] args) {
		ConsoleChessBoard board = new ConsoleChessBoard("White");
		board.setPlayerName("White", "Ivan");
		board.setPlayerName("Black", "Jim");
		
		do {
			if (!board.getSkipPrintBoard())
				board.printBoard();
		} while(board.inputToBoard());
		
		System.out.println("Game Over");
	}

}
