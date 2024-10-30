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
		board[0][1] = new Horse("White");
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

			ChessPiece bgnPiece = board[bgnRow][bgnCol];
			ChessPiece endPiece = board[endRow][endCol];

			if (!nowPlayer.equals(bgnPiece.getColor()))
				return false;

			if (bgnPiece.canMoveToPosition(this, bgnRow, bgnCol, endRow, endCol)) {
				bgnPiece = endPiece;
				endPiece = null;
				nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
				return true;
			}
		}
		return false;
	}

	public boolean checkPos(int row, int col) {
		return (row >= MIN_ROW_INDEX && row <= MAX_ROW_INDEX && col >= MIN_COL_INDEX && col <= MAX_COL_INDEX);
	}

	public abstract void printBoard();
	
	public abstract boolean inputToBoard();

	public boolean isCellFree(int row, int col) {
		return (board[row][col] == null);
	}
}
