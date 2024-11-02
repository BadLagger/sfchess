package sf.chess;

public class Horse extends ChessPiece {

	public Horse(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		final Position[] possiblePos = { new Position(row - 2, col - 1), 
				new Position(row - 2, col + 1),
				new Position(row - 1, col - 2), 
				new Position(row - 1, col + 2), 
				new Position(row + 2, col - 1),
				new Position(row + 2, col + 1), 
				new Position(row + 1, col - 2), 
				new Position(row + 1, col + 2) };
		
		moves.clear();

		for (var pos : possiblePos) {
			if (chessBoard.checkPos(pos)) {
				if (!chessBoard.isCellFree(pos)) {
					if (color.equals(chessBoard.getCellColor(pos))) {
						continue;
					}
				}

				moves.add(new PossibleMove(pos));
			}
		}

		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "H";
	}

}
