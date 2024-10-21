package sf.chess;

public class Horse extends ChessPiece {

    public Horse(String color) {
	super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
	
	PossibleMove[] moves = { new PossibleMove(row + 2, col + 1), 
		                 new PossibleMove(row + 1, col + 2),
		                 new PossibleMove(row + 2, col - 1),
		                 new PossibleMove(row + 1, col - 2),
		                 new PossibleMove(row - 2, col + 1),
		                 new PossibleMove(row - 1, col + 2),
		                 new PossibleMove(row - 2, col - 1),
		                 new PossibleMove(row - 2, col - 2) };
	
	for (PossibleMove pm : moves) {
	    if (pm.equal(toRow, toCol)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public String getSymbol() {
	return "H";
    }

}
