package sf.chess;

public class Position {
    public int row;
    public int col;
    
    public Position() {
    	set(-1, -1);
    }
    
    public Position(int row, int col) {
    	set(row, col);
    }
    
    public Position(Position p) {
    	set(p);
    }
    
    public void set(int row, int col) {
    	this.row = row;
    	this.col = col;
    }
    
    public void set(Position p) {
    	set(p.row, p.col);
    }
}
