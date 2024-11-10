package sf.chess;

public abstract class ChessBoard {
	final static int MIN_INDEX = 0;
	final static int MAX_INDEX = 7;

	final static int MIN_ROW_INDEX = MIN_INDEX;
	final static int MAX_ROW_INDEX = MAX_INDEX;

	final static int MIN_COL_INDEX = MIN_INDEX;
	final static int MAX_COL_INDEX = MAX_INDEX;
	
	
	public ChessPiece[][] board = new ChessPiece[MAX_ROW_INDEX + 1][MAX_COL_INDEX + 1];
	String nowPlayer;
	String whitePlayerName = "Player 1";
	String blackPlayerName = "Player 1";
	Position whiteKing = new Position(0, 0);
	Position blackKing = new Position(0, 0);
	public boolean roque = false;

	public ChessBoard(String nowPlayer) {
		this.nowPlayer = nowPlayer;
		setInitialState();
	}
	
	private void setInitialState() {
		cleanDesc();
		board[0][1] = new Horse("White");
		board[0][6] = new Horse("White");

		board[0][2] = new Bishop("White");
		board[0][5] = new Bishop("White");

		board[0][0] = new Rook("White");
		board[0][7] = new Rook("White");

		board[0][4] = new King("White");
		board[0][3] = new Queen("White");

		for (int i = 0; i <= MAX_INDEX; ++i)
			board[1][i] = new Pawn("White");

		board[7][1] = new Horse("Black");
		board[7][6] = new Horse("Black");

		board[7][2] = new Bishop("Black");
		board[7][5] = new Bishop("Black");

		board[7][0] = new Rook("Black");
		board[7][7] = new Rook("Black");

		board[7][4] = new King("Black");
		board[7][3] = new Queen("Black");

		for (int i = 0; i <= MAX_INDEX; ++i)
			board[6][i] = new Pawn("Black");
		
		whiteKing.row = 0;
		whiteKing.col = 4;
		
		blackKing.row = 7;
		blackKing.col = 4;
	}
	
	private void cleanDesc() {
		for(int i = MIN_ROW_INDEX; i <= MAX_ROW_INDEX; ++i) {
			for (int j = MIN_COL_INDEX; j <= MAX_COL_INDEX; ++j) {
				board[i][j] = null;
			}
		}
	}
	
	public String getDescState() {
		String ret = "";
		for(int i = MIN_ROW_INDEX; i <= MAX_ROW_INDEX; ++i) {
			for (int j = MIN_COL_INDEX; j <= MAX_COL_INDEX; ++j) {
				if(board[i][j] != null) {
					ret += String.format("%s %s %d %d\n", board[i][j].getSymbol(), board[i][j].getColor(), i, j);
				}
			}
		}
		ret += String.format("ActivePlayer %s\n", nowPlayer);
		return  ret;
	}
	
	public boolean setDescState(String state) {
		ChessPiece[][] backupBoard = new ChessPiece[MAX_ROW_INDEX + 1][MAX_COL_INDEX + 1];
		String backupActivePlayer = new String(nowPlayer);
		Position backupWhiteKing = new Position(whiteKing);
		Position backupBlackKing = new Position(blackKing);
		boolean error = false;
		boolean activePlayerField = false;
		
		// Создание текущей копии доски
		for(int i = MIN_ROW_INDEX; i <= MAX_ROW_INDEX; ++i) {
			for (int j = MIN_COL_INDEX; j <= MAX_COL_INDEX; ++j) { 
				backupBoard[i][j] = board[i][j];
			} 
		}
		
		// Очистка доски
		cleanDesc();
		
		// Разбор входящего состояния
		for (String line : state.split("\n")) {
			String[] fgState = line.split(" ");
			
			// Положения фигур хранятся в 4-х полях, а активный игрок - в 2-х
			if ((fgState.length != 4) && (fgState.length != 2)) {
				error = true;
				break;
			}
			
			// Поле активного игрока
			if (fgState.length == 2) {
				if (fgState[0].equals("ActivePlayer") 
				&& ((fgState[1].equals("White")) || (fgState[1].equals("Black")))) {
					nowPlayer = fgState[1];
					activePlayerField = true;
					continue;
				}
				error = true;
				break;
			}
			
			int row = Integer.parseInt(fgState[2]);
			int col = Integer.parseInt(fgState[3]);
			
			if (!checkPos(row, col)) {
				error = true;
				break;
			}
			
			String color = fgState[1];
			
			if (!color.equals("White") && !color.equals("Black")) {
				error = true;
				break;
			}
			
			switch(fgState[0]) {
			case "P":
				board[row][col] = new Pawn(color);
				break;
			case "R":
				board[row][col] = new Rook(color);
				break;
			case "H":
				board[row][col] = new Horse(color);
				break;
			case "B":
				board[row][col] = new Bishop(color);
				break;
			case "Q":
				board[row][col] = new Queen(color);
				break;
			case "K":
				board[row][col] = new King(color);
				if (color.equals("White")) {
					whiteKing.set(row, col);
				} else {
					blackKing.set(row, col);
				}
				break;
			default: error = true;
			}
			
			if (error)
				break;
		}
		
		// Если в состоянии нету признака активного игрока, то ошибка состояния 
		if (!activePlayerField)
			error = true;
		
		// Если переданное состояние доски с ошибкой то необходимо восстановить предыдущее состояние доски
		if (error) {
			for(int i = MIN_ROW_INDEX; i <= MAX_ROW_INDEX; ++i) {
				for (int j = MIN_COL_INDEX; j <= MAX_COL_INDEX; ++j) { 
					board[i][j] = backupBoard[i][j];
				} 
			}
			nowPlayer = backupActivePlayer;
			whiteKing.set(backupWhiteKing);
			blackKing.set(backupBlackKing);
			return false;
		}
		
		return true;
	}

	public String nowPlayerColor() {
		return nowPlayer;
	}

	public boolean setPlayerName(String color, String name) {
		if (color.equals("White")) {
			whitePlayerName = name;
			return true;
		}

		if (color.equals("Black")) {
			blackPlayerName = name;
			return true;
		}

		return false;
	}

	public String getPlayerName(String color) {
		if (color.equals("White")) {
			return whitePlayerName;
		}

		if (color.equals("Black")) {
			return blackPlayerName;
		}

		return "";
	}
	
	
	public boolean isCheck(String color) {
		if (color.equals("White")) {
			return board[whiteKing.row][whiteKing.col].isUnderAtack(this, whiteKing.row, whiteKing.col, color);
		} else if (color.equals("Black")) {
			return board[blackKing.row][blackKing.col].isUnderAtack(this, blackKing.row, blackKing.col, color);
		}
		return false;
	}

	public boolean moveToPosition(int bgnRow, int bgnCol, int endRow, int endCol) {
		if (checkPos(bgnRow, bgnCol) && checkPos(endCol, endCol)) {

			if (board[bgnRow][bgnCol] == null || !nowPlayer.equals(board[bgnRow][bgnCol].getColor()))
				return false;

			if (board[bgnRow][bgnCol].canMoveToPosition(this, bgnRow, bgnCol, endRow, endCol)) {
				ChessPiece tmpPiece = board[endRow][endCol];
				board[endRow][endCol] = board[bgnRow][bgnCol];
				board[bgnRow][bgnCol] = null;
				
				// Если фигура была Королём, то меняем значение переменной позиции короля
				if (board[endRow][endCol].getSymbol().equals("K")) {
					if (board[endRow][endCol].getColor().equals("White")) {
						whiteKing.set(endRow, endCol);
					} else {
						blackKing.set(endRow, endCol);
					}
					// Проверка на рокировку
					if (roque) {
						// Перемещение ладьи при рокировке
						if (endCol == 6) {
							board[endRow][5] = board[endRow][7];
							board[endRow][7] = null;
						} else {
							board[endRow][3] = board[endRow][0];
							board[endRow][0] = null;
						}
						roque = false;
					}
				} else {
				// Проверка на Короля под ударом. Если в результате хода собственный король оказывается под ударом, то ходить нельзя
					int row = (board[endRow][endCol].getColor().equals("White")) ? whiteKing.row : blackKing.row;
					int col = (board[endRow][endCol].getColor().equals("White")) ? whiteKing.col : blackKing.col;
					
					if (board[row][col].isUnderAtack(this, row, col, board[endRow][endCol].getColor())) {
						board[bgnRow][bgnCol] = board[endRow][endCol];
						board[endRow][endCol] = tmpPiece;
						return false;
					}
				}
				
				board[endRow][endCol].setAnyMove();
				nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
				return true;
			}
		}
		return false;
	}

	public boolean checkPos(int row, int col) {
		return (row >= MIN_ROW_INDEX && row <= MAX_ROW_INDEX && col >= MIN_COL_INDEX && col <= MAX_COL_INDEX);
	}

	public boolean checkPos(Position p) {
		return checkPos(p.row, p.col);
	}

	public abstract void printBoard();

	public abstract boolean inputToBoard();

	public boolean isCellFree(int row, int col) {
		return (board[row][col] == null);
	}

	public boolean isCellFree(Position p) {
		return isCellFree(p.row, p.col);
	}

	public String getCellColor(Position p) {
		return board[p.row][p.col].color;
	}

	public String getCellColor(int row, int col) {
		return getCellColor(new Position(row, col));
	}
	
	public String getPieceName(int row, int col) {
		if (!isCellFree(row, col)) {
			return board[row][col].getSymbol();
		}
		return null;
	}
	
	public String getPieceName(Position p) {
		return getPieceName(p.row, p.col);
	}

	public boolean getPieceAnyMove(int row, int col) {
		return board[row][col].getAnyMove();
	}
}
