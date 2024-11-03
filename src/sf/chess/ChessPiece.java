package sf.chess;

import java.util.ArrayList;

public abstract class ChessPiece {
	String color;
	boolean check = true;

	protected class PossibleMove {
	    Position pos = new Position();

		public PossibleMove(int row, int col) {
			pos.row = row;
			pos.col = col;
		}
		
		public PossibleMove(Position p) {
		    pos = p;
		}
		
		
		public Position getPos() {
			return pos;
		}

		public boolean equal(int row, int col) {
			return ((pos.row == row) && (pos.col == col));
		}
		
		public boolean equal(Position p) {
			return equal(p.row, p.col);
		}
	}
	
	protected ArrayList<PossibleMove> moves = new ArrayList<>();

	public ChessPiece(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	

	public abstract boolean canMoveToPosition(ChessBoard chessBoard, int row, int col, int toRow, int toCol);

	public abstract String getSymbol();
	
	public void dbgPrintMoves() {
		for (var move : moves) {
			System.out.format("ROW: %d COL: %d\n", move.getPos().row, move.getPos().col);
		}
	}
	
	protected boolean checkMoveList(int destRow, int destCol) {
		for (var move : moves) {
			if (move.equal(destRow, destCol))
				return true;
		}
		return false;
	}
	
	protected void seekDiagonalMoves(ChessBoard chessBoard, int row, int col) {
		int count = 1;
		boolean colMaxFlag = false;
		boolean colMinFlag = false;
		
		while ((row + count) <= ChessBoard.MAX_ROW_INDEX) {
			boolean colMaxResult = (!colMaxFlag) ? chessBoard.checkPos(row + count, col + count) : false;
			boolean colMinResult = (!colMinFlag) ? chessBoard.checkPos(row + count, col - count) : false;
			
			if (colMaxResult) {
				if (chessBoard.isCellFree(row + count, col + count)) {
					moves.add(new PossibleMove(row + count, col + count));
				} else {
					if (!chessBoard.getCellColor(row + count, col + count).equals(getColor()))
						moves.add(new PossibleMove(row + count, col + count));
					colMaxFlag = true;
				}
			} else
				colMaxFlag = true;
			
			if (colMinResult) {
				if (chessBoard.isCellFree(row + count, col - count)) {
					moves.add(new PossibleMove(row + count, col - count));
				} else {
					if (!chessBoard.getCellColor(row + count, col - count).equals(getColor()))
						moves.add(new PossibleMove(row + count, col - count));
					colMinFlag = true;
				}
			} else
				colMinFlag = true;
			
			if (colMaxFlag & colMinFlag)
				break;
			
			count++;
		}
		
		count = 1;
		colMaxFlag = false;
		colMinFlag = false;
		
		while ((row - count) >= ChessBoard.MIN_ROW_INDEX) {
			boolean colMaxResult = (!colMaxFlag) ? chessBoard.checkPos(row - count, col + count) : false;
			boolean colMinResult = (!colMinFlag) ? chessBoard.checkPos(row - count, col - count) : false;
			
			if (colMaxResult) {
				if (chessBoard.isCellFree(row - count, col + count)) {
					moves.add(new PossibleMove(row - count, col + count));
				} else {
					if (!chessBoard.getCellColor(row - count, col + count).equals(getColor()))
						moves.add(new PossibleMove(row - count, col + count));
					colMaxFlag = true;
				}
			} else
				colMaxFlag = true;
			
			if (colMinResult) {
				if (chessBoard.isCellFree(row - count, col - count)) {
					moves.add(new PossibleMove(row - count, col - count));
				} else {
					if (!chessBoard.getCellColor(row - count, col - count).equals(getColor()))
						moves.add(new PossibleMove(row - count, col - count));
					colMinFlag = true;
				}
			} else
				colMinFlag = true;
			
			if (colMaxFlag & colMinFlag)
				break;
			
			count++;
		}
	}
	
	protected void seekVerticalHorizontalMoves(ChessBoard chessBoard, int row, int col) {
		
		int count = 1;
		
		while((col + count) <= ChessBoard.MAX_COL_INDEX) {
			if (chessBoard.checkPos(row, col + count)) {
				if (chessBoard.isCellFree(row, col + count)) {
					moves.add(new PossibleMove(row, col + count));
				} else {
					if (!chessBoard.getCellColor(row, col + count).equals(getColor()))
						moves.add(new PossibleMove(row, col + count));
					break;
				}
			} else
				break;
			count++;
		}
		
		count = 1;
		
		while((col - count) >= ChessBoard.MIN_COL_INDEX) {
			if (chessBoard.checkPos(row, col - count)) {
				if (chessBoard.isCellFree(row, col - count)) {
					moves.add(new PossibleMove(row, col - count));
				} else {
					if (!chessBoard.getCellColor(row, col - count).equals(getColor()))
						moves.add(new PossibleMove(row, col - count));
					break;
				}
			} else
				break;
			count++;
		}
		
		count = 1;
		
		while((row + count) <= ChessBoard.MAX_ROW_INDEX) {
			if (chessBoard.checkPos(row + count, col)) {
				if (chessBoard.isCellFree(row + count, col)) {
					moves.add(new PossibleMove(row + count, col));
				} else {
					if (!chessBoard.getCellColor(row + count, col).equals(getColor()))
						moves.add(new PossibleMove(row + count, col));
					break;
				}
			} else
				break;
			count++;
		}
		
		count = 1;
		
		while((row - count) >= ChessBoard.MIN_ROW_INDEX) {
			if (chessBoard.checkPos(row - count, col)) {
				if (chessBoard.isCellFree(row - count, col)) {
					moves.add(new PossibleMove(row - count, col));
				} else {
					if (!chessBoard.getCellColor(row - count, col).equals(getColor()))
						moves.add(new PossibleMove(row - count, col));
					break;
				}
			} else
				break;
			count++;
		}
	}
}
