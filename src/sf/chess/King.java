package sf.chess;

public class King extends ChessPiece{

	public King(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		final Position[] possiblePos = { new Position(row + 1, col), 
				new Position(row + 1, col + 1),
				new Position(row + 1, col - 1),
				new Position(row, col + 1), 
				new Position(row, col - 1), 
				new Position(row - 1, col),
				new Position(row - 1, col + 1),
				new Position(row - 1, col - 1) };
		
		moves.clear();
		
		for (var pos : possiblePos) {
			if (chessBoard.checkPos(pos)) {
				if (!chessBoard.isCellFree(pos)) {
					if (color.equals(chessBoard.getCellColor(pos))) {
						continue;
					}
				}
				
				//!TODO: Проверка ячейки под атакой
				//!TODO: Ракировка
				moves.add(new PossibleMove(pos));
			}
		}
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "K";
	}

}
