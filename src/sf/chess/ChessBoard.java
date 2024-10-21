package sf.chess;

public abstract class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;
    String whitePlayerName = "Player 1";
    String blackPlayerName = "Player 1";
    
    public ChessBoard(String nowPlayer) {
	this.nowPlayer = nowPlayer;
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
	if (checkPos(bgnRow) && checkPos(bgnCol)) {
	    
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
    
    public boolean checkPos(int pos) {
	return pos >= 0 && pos <= 7;
    }
    
    public abstract void printBoard();
}
