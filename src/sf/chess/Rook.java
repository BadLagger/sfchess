package sf.chess;

public class Rook extends ChessPiece{

	public Rook(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		moves.clear();
		
		seekVerticalHorizontalMoves(chessBoard, row, col);
		
		dbgPrintMoves();
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "R";
	}

}
