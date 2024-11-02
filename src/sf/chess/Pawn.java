package sf.chess;

public class Pawn extends ChessPiece {

	public Pawn(String color) {
		super(color);
	}

	@Override
	public boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		moves.clear();
		
		// Проверяем цвет фигур, чтобы знать с какого конца считать
		if (color.equals("White")) {
			// Проверяем первый ход пешки и свободную клетку для этого, понятно что если у пешки первый ряд, то за пределы доски она выйти не может
			if ((row == 1) && chessBoard.isCellFree(row + 2, col)) {
				moves.add(new PossibleMove(row + 2, col));
			} 
			// Проверяем стандартный ход пешки
			if (chessBoard.checkPos(row + 1, col) && chessBoard.isCellFree(row + 1, col)) {
				moves.add(new PossibleMove(row + 1, col));
			}
			// Проверяем клетки для атаки, они обязательно должны быть заняты чёрными фигурами
			if (chessBoard.checkPos(row + 1, col + 1)
			&& !chessBoard.isCellFree(row + 1, col + 1)
			&& chessBoard.getCellColor(row + 1, col + 1).equals("Black")) {
				moves.add(new PossibleMove(row + 1, col + 1));
			}
			if (chessBoard.checkPos(row + 1, col - 1)
			&& !chessBoard.isCellFree(row + 1, col - 1)
			&& chessBoard.getCellColor(row + 1, col - 1).equals("Black")) {
				moves.add(new PossibleMove(row + 1, col - 1));
			}
		} else {
			// То же самое только для чёрных
			if ((row == 6) && chessBoard.isCellFree(row - 2, col)) {
				moves.add(new PossibleMove(row - 2, col));
			}
			
			if (chessBoard.checkPos(row - 1, col) && chessBoard.isCellFree(row - 1, col)) {
				moves.add(new PossibleMove(row - 1, col));
			}
			
			if (chessBoard.checkPos(row - 1, col + 1)
			&& !chessBoard.isCellFree(row - 1, col + 1)
			&& chessBoard.getCellColor(row - 1, col + 1).equals("White")) {
				moves.add(new PossibleMove(row - 1, col + 1));
			}
			if (chessBoard.checkPos(row - 1, col - 1)
			&& !chessBoard.isCellFree(row - 1, col - 1)
			&& chessBoard.getCellColor(row - 1, col - 1).equals("White")) {
				moves.add(new PossibleMove(row - 1, col - 1));
			}
		}
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "P";
	}

}
