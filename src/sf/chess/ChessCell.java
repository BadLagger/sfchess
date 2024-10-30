package sf.chess;

public class ChessCell {
	private int rowIndex;
	private int colIndex;

	public ChessCell(int rowIndex, int colIndex) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public char getRow() {
		return (char) ('A' + rowIndex);
	}

	public char getCol() {
		return (char) ('1' + colIndex);
	}

	public Color getColor() {
		return (((rowIndex + 1) % 2) == (colIndex % 2)) ? Color.Black : Color.White;
	}
}
