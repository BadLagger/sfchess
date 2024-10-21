package sf.chess;

public enum Color {
    
    Black("Black"),
    White("White");
    
    private String clr;
    
    Color(String color) { clr = color; }
    
    String getStr() { return clr; }
}
