package sf.chess;

public class Position {
    public int row;
    public int col;
    
    public Position() {
	row = -1;
	col = -1;
    }
    
    public Position(int row, int col) {
	this.row = row;
	this.col = col;
    }
}
