package sf.chess;

public class Queen extends ChessPiece{

	public Queen(String color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		moves.clear();
		
		seekDiagonalMoves(chessBoard, row, col);
		seekVerticalHorizontalMoves(chessBoard, row, col);
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "Q";
	}
	

}
