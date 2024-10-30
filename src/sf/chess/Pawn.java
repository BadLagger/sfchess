package sf.chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

	public Pawn(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {

		ArrayList<PossibleMove> moves = new ArrayList<>();
		boolean freeCell = chessBoard.isCellFree(toRow, toCol);

		if (color.equals("White")) {
			
			if ((row + 1) > 7)
				return false;
			
			if (freeCell) {
				if (row == 1) {
					moves.add(new PossibleMove(3, col));
				}
				moves.add(new PossibleMove(row+1, col));
			} else {
				if ((col + 1) <= 7)
					moves.add(new PossibleMove(row+1, col+1));
				
				if ((col + 1) >= 0)
				moves.add(new PossibleMove(row+1, col-1));
			}
		} else {
			
			if ((row - 1) < 0 )
				return false;
			
			if (freeCell) {
				if (row == 6) {
					moves.add(new PossibleMove(4, col));
				}
				moves.add(new PossibleMove(row-1, col));
			} else {
				moves.add(new PossibleMove(row-1, col+1));
				moves.add(new PossibleMove(row-1, col-1));
			}
		}

		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "P";
	}

}
