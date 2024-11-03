package sf.chess;

public class Bishop extends ChessPiece{

	public Bishop(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		moves.clear();
		
		seekDiagonalMoves(chessBoard, row, col);
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "B";
	}
	
}
