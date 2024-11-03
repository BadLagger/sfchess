package sf.chess;

public abstract class ChessBoard {
	final static int MIN_INDEX = 0;
	final static int MAX_INDEX = 7;

	final static int MIN_ROW_INDEX = MIN_INDEX;
	final static int MAX_ROW_INDEX = MAX_INDEX;

	final static int MIN_COL_INDEX = MIN_INDEX;
	final static int MAX_COL_INDEX = MAX_INDEX;

	public ChessPiece[][] board = new ChessPiece[MAX_ROW_INDEX + 1][MAX_COL_INDEX + 1];
	String nowPlayer;
	String whitePlayerName = "Player 1";
	String blackPlayerName = "Player 1";

	public ChessBoard(String nowPlayer) {
		this.nowPlayer = nowPlayer;
		board[0][2] = new Horse("White");
		board[0][5] = new Horse("White");
		for (int i = 0; i <= MAX_INDEX; ++i)
			board[1][i] = new Pawn("White");
		board[0][1] = new Bishop("White");
		board[0][6] = new Bishop("White");
		
		board[7][2] = new Horse("Black");
		board[7][5] = new Horse("Black");
		for (int i = 0; i <= MAX_INDEX; ++i)
			board[6][i] = new Pawn("Black");
		board[7][1] = new Bishop("Black");
		board[7][6] = new Bishop("Black");
	}

	public String nowPlayerColor() {
		return nowPlayer;
	}

	public boolean setPlayerName(String color, String name) {
		if (color.equals("White")) {
			whitePlayerName = name;
			return true;
		}

		if (color.equals("Black")) {
			blackPlayerName = name;
			return true;
		}

		return false;
	}

	public String getPlayerName(String color) {
		if (color.equals("White")) {
			return whitePlayerName;
		}

		if (color.equals("Black")) {
			return blackPlayerName;
		}

		return "";
	}

	public boolean moveToPosition(int bgnRow, int bgnCol, int endRow, int endCol) {
		if (checkPos(bgnRow, bgnCol) && checkPos(endCol, endCol)) {

			if (board[bgnRow][bgnCol] == null || !nowPlayer.equals(board[bgnRow][bgnCol].getColor()))
				return false;

			if (board[bgnRow][bgnCol].canMoveToPosition(this, bgnRow, bgnCol, endRow, endCol)) {
			    	board[endRow][endCol] = board[bgnRow][bgnCol];
			    	board[bgnRow][bgnCol] = null;
				nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
				return true;
			}
		}
		return false;
	}

	public boolean checkPos(int row, int col) {
		return (row >= MIN_ROW_INDEX && row <= MAX_ROW_INDEX && col >= MIN_COL_INDEX && col <= MAX_COL_INDEX);
	}
	
	public boolean checkPos(Position p) {
		return checkPos(p.row, p.col);
	}


	public abstract void printBoard();
	
	public abstract boolean inputToBoard();

	public boolean isCellFree(int row, int col) {
		return (board[row][col] == null);
	}
	
	public boolean isCellFree(Position p) {
	    return isCellFree(p.row, p.col);
	}
	
	public String getCellColor(Position p) {
	    return board[p.row][p.col].color;
	}
	
	public String getCellColor(int row, int col) {
	    return getCellColor(new Position(row, col));
	}
}
