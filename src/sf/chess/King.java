package sf.chess;

public class King extends ChessPiece{

	public King(String color) {
		super(color);
	}
	
	private boolean isRoque(ChessBoard chessBoard, int row, int col, int toRow, int toCol) {
		// определяем ряд для цвета
		int clrRow = (color.equals("White")) ? 0 : 7;
		
		// Двигался ли этот Король?
		if (anyMove)
			return false;
		
		// Под атакой ли он?
		if (isUnderAtack(chessBoard, row, col, getColor())) {
			return false;
		}
		
		// Под атакой ли то куда он стремится?
		if (isUnderAtack(chessBoard, toRow, toCol, getColor())) {
			return false;
		}
		
		// Является ли этот ход ракировкой
		if ((row != clrRow) && (col != 4) && (toRow != clrRow) && (toCol != 6) && (toCol != 2))
			return false;
		
		// Свободно ли место куда он стремится?
		if(!chessBoard.isCellFree(toRow, toCol)) {
			return false;
		}
		
		// Проверка ладьи
		int rookCol = (toCol == 6) ? 7 : 0;
		
		// Если ладьи нет на месте
		if (chessBoard.isCellFree(clrRow, rookCol))
			return false;
		
		// Если на месте ладьи фигура не нашего цвета
		if (!chessBoard.getCellColor(clrRow, rookCol).equals(color)) {
			return false;
		}
		
		// Если фигура и вовсе не Ладья
		if (!chessBoard.getPieceName(clrRow, rookCol).equals("R")) {
			return false;
		}
		
		// Или если Ладья то она не двигалась ли
		if (chessBoard.getPieceAnyMove(clrRow, rookCol)) {
			return false;
		}
		
		// Проверка на сводобные клетки между ладьёй и королём
		// А так же проверка на битое поле для короля
		// Справа
		if (toCol == 6) {
			if (!chessBoard.isCellFree(clrRow, 5) 
			|| isUnderAtack(chessBoard, clrRow, 5, getColor()) 
			|| !chessBoard.isCellFree(clrRow, 6)
			|| isUnderAtack(chessBoard, clrRow, 6, getColor()))
				return false;
		} else {
			// Слева
			if (!chessBoard.isCellFree(clrRow, 3) 
			|| isUnderAtack(chessBoard, clrRow, 3, getColor()) 
			|| !chessBoard.isCellFree(clrRow, 2)
			|| isUnderAtack(chessBoard, clrRow, 2, getColor())
			|| !chessBoard.isCellFree(clrRow, 1))
				// Ладья через битое поле ходить может
				return false;
		}
		
		// Одобрение рокировки
		return true;
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
		
		if (isRoque(chessBoard, row, col, toRow, toCol)) {
			chessBoard.roque = true;
			return true;
		}
		
		moves.clear();
		
		for (var pos : possiblePos) {
			if (chessBoard.checkPos(pos)) {
				if (!chessBoard.isCellFree(pos)) {
					if (color.equals(chessBoard.getCellColor(pos))) {
						continue;
					}
				}
				
				//!TODO: Проверка ячейки под атакой
				if (!isUnderAtack(chessBoard, pos.row, pos.col, getColor()))
					moves.add(new PossibleMove(pos));
			}
		}
		
		//!TODO: Ракировка
		
		return checkMoveList(toRow, toCol);
	}

	@Override
	public String getSymbol() {
		return "K";
	}
}
