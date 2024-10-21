package sf.chess;

public class Pawn extends ChessPiece{

    public Pawn(String color) {
	super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
	
	PossibleMove[] possible_move = new PossibleMove[4];
	boolean freeCell = chessBoard.isCellFree(toRow, toCol);
	
	if (color.equals("White")) {
	    if (freeCell) {
		
	    } else {
		//PossibleMove
	    }
	} else {
	    
	}
	
	return false;
    }

    @Override
    public String getSymbol() {
	return "P";
    }

}
