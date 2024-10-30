package sf.chess;

import java.util.Scanner;

public class ConsoleChessBoard extends ChessBoard {

	public ConsoleChessBoard(String nowPlayer) {
		super(nowPlayer);
		playerInput = new Scanner(System.in);
	}

	private void printPlayer(String color) {
		System.out.format("\t\t\t%s (%s)", getPlayerName(color), color);
		if (nowPlayer.equals(color)) {
			System.out.println("<-");
		} else {
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void printBoard() {
		System.out.println();
		printPlayer("Black");
		System.out.println("\tA(0)\tB(1)\tC(2)\tD(3)\tE(4)\tF(5)\tG(6)\tH(7)");

		for (int i = 7; i > -1; i--) {
			System.out.format("%d(%d)\t", i + 1, i);
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {
					System.out.print("..\t");
				} else {
					System.out.print(
							board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
				}
			}
			System.out.println();
			System.out.println();
		}
		printPlayer("White");
	}

	@Override
	public boolean inputToBoard() {
		System.out.format("%s move on: ", nowPlayerColor());
		
		String inputStr = playerInput.next();
		
		if (inputStr.equals("exit"))
			return false;
		
		if (!checkInput(inputStr)) {
			System.out.println("Wrong input!!! Please, try again");
		} else {
			// TODO move
		}
		
		return true;
	}
	
	private boolean checkInput(String txt) {
		return false;
	}
	
	private Scanner playerInput;
}
