package sf.chess;

//import java.util.ArrayList;

public class Horse extends ChessPiece {

	public Horse(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
	    final Position[] posiblePos = {
		new Position(row - 2, col - 1),
		new Position(row - 2, col + 1),
		new Position(row - 1, col - 2),
		new Position(row - 1, col + 2),
		new Position(row + 2, col - 1),
		new Position(row + 2, col + 1),
		new Position(row + 1, col - 2),
		new Position(row + 1, col + 2)
	    };
	    	moves.clear();
	    	
	    	for (var p : posiblePos) {
	    	    if (chessBoard.checkPos(p)) {
	    		if (!chessBoard.isCellFree(p)) {
	    		    if (color.equals(chessBoard.getCellColor(p))) {
	    			continue;
	    		    }
	    		}
	    		
	    		moves.add(new PossibleMove(p));
	    	    }
	    	}

		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "H";
	}

}
