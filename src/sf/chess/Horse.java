package sf.chess;

//import java.util.ArrayList;

public class Horse extends ChessPiece {

	public Horse(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		
		for (int rowIndex = row - 2; rowIndex <= row + 2; rowIndex++) {
			if (rowIndex == 0)
				continue;
			for (int colIndex = col - 2; colIndex <= col + 2; colIndex++) {
				if (colIndex == 0)
					continue;
				if ( (rowIndex == toRow) && (colIndex == toCol))
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
