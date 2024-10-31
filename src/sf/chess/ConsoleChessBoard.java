package sf.chess;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleChessBoard extends ChessBoard {
	
	private final String regPattern = "^[a-h][1-8]-[a-h][1-8]$";
	private Scanner      playerInput;
	private Pattern      reMoveInputPattern;
	private boolean      skipBoardPrint = false;

	public ConsoleChessBoard(String nowPlayer) {
		super(nowPlayer);
		playerInput = new Scanner(System.in);
		reMoveInputPattern = Pattern.compile(regPattern);
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
		
		String inputStr = playerInput.next().toLowerCase();
		
		if (inputStr.equals("exit"))
			return false;
		
		if (!checkInput(inputStr)) {
			System.out.println("Wrong input!!! Please, try again");
			skipBoardPrint = true;
		} else {
			
			String[] cellCoordsStr = inputStr.split("-");
			int bgnRow = -1, bgnCol = -1;
			int endRow = -1, endCol = -1;
			
			getNumsFromStr(cellCoordsStr[0], bgnRow, bgnCol);
			getNumsFromStr(cellCoordsStr[1], endRow, endCol);
			
			if (moveToPosition(bgnRow, bgnCol, endRow, endCol)) {
				skipBoardPrint = false;
				System.out.println("Good move!");
			} else {
				skipBoardPrint = true;
				System.out.println("Bad move!");
			}
		}
		
		return true;
	}
	
	public boolean getSkipPrintBoard() {
		return skipBoardPrint;
	}
	
	private void getNumsFromStr(String cellCoordsStr, int outRow, int outCol) {
		final char strCol[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		final char strRow[] = {'1', '2', '3', '4', '5', '6', '7', '8'};
		
		for (int i = 0; i < strCol.length; i++) {
			if (strCol[i] == cellCoordsStr.charAt(0)) {
				outCol = i;
				break;
			}
		}
		
		for (int i = 0; i < strRow.length; i++) {
			if (strRow[i] == cellCoordsStr.charAt(1)) {
				outRow = i;
				break;
			}
		}
	}
	
	private boolean checkInput(String txt) {
		Matcher match = reMoveInputPattern.matcher(txt);
		
		if (match.find()) {
			return true;
		}
		
		return false;
	}
}
