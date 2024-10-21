package sf.chess;

public abstract class ChessPiece {
    String color;
    boolean check = true;
    
    protected class PossibleMove {
	public int row;
	public int col;
	
	public PossibleMove(int row, int col) {
	    this.row = row;
	    this.col = col;
	}
	
	public boolean equal(int row, int col) {
	    return ((this.row == row) && (this.col == col));
	}
    }
    
    public ChessPiece(String color) {
	this.color = color;
    }
    
    public String getColor() {
	return color;
    }
    
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, 
	    int row, int col, int toRow, int toCol);
    public abstract String getSymbol();
}
