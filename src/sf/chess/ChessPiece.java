package sf.chess;

public abstract class ChessPiece {
    String color;
    boolean check = true;
    
    public ChessPiece(String color) {
	this.color = color;
    }
    
    public String getColor() {
	return color;
    }
    
    // ! TODO arguments
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, 
	    int line, int column, int toLine, int toColumn);
    public abstract String getSymbol();
}
