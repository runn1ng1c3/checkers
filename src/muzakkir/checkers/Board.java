package muzakkir.checkers;

public class Board {
	
	int boardHeight;
	int boardLength;
	Cell[][] cells = new Cell[boardHeight][boardLength];
	
	public Board(int boardHeight, int boardLength, Cell[][] cells) {
		this.boardHeight = boardHeight;
		this.boardLength = boardLength;
		this.cells = cells;
		
		
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardLength; j++) {
				if (i != 3 && i != 4) {
					if (i % 2 == 0) {
						if (j % 2 == 0) {
							cells[i][j] = i > 4 ? new Cell(i, j, Type.RED) : new Cell(i, j, Type.BLACK);
						} else {
							cells[i][j] = new Cell(i, j, Type.EMPTY);
						}
					} else {
						if (j % 2 == 0) {
							cells[i][j] = new Cell(i, j, Type.EMPTY);
						} else {
							cells[i][j] = i > 4 ? new Cell(i, j, Type.RED) : new Cell(i, j, Type.BLACK);
						}
					}
				} else {
					cells[i][j] = new Cell(i, j, Type.EMPTY);
				}
			}
		}
		
	}
	
	public  void drawBoard(Cell[][] cells, int boardHeight, int boardLength, int turn) {
		int redCount = 0;
		int blackCount = 0;
		System.out.println("Turn = " + turn);
		char[] label = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
		System.out.println("#################### BLACK ########################");
		System.out.println("  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |");
		System.out.println("---------------------------------------------------");
		for (int i = 0; i < boardHeight; i++) {
			System.out.print(label[i] + " ");
			for (int j = 0; j < boardLength; j++) {
				System.out.print("|");
				switch (cells[i][j].getType()) {
				case EMPTY:
					System.out.print("     ");
					break;

				case RED:
					System.out.print(" (@) ");
					redCount++;
					break;

				case BLACK:
					System.out.print(" (#) ");
					blackCount++;
					break;
				}
			}
			System.out.print("|\n");
			System.out.println("---------------------------------------------------");

		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@ RED @@@@@@@@@@@@@@@@@@@@@@@@@@");
		int redScore = 12 - blackCount;
		int blackScore = 12 - redCount;
		System.out.println("BLACK: " + blackScore + "       RED: " + redScore);
	}
	
	

//	public static void main(String[] args) {
//
//		// initializations
//		Cell cell;
//		Scanner scanner = new Scanner(System.in);
//		int turn = 0;
//		Board board = new Board();
//		
//		drawBoard(board.cells, turn);
//		String input = "";
//
//		while (turn < 20) {
//
//			// Player 1 turn BLACK topside
//			if (turn % 2 == 0) {
//				System.out.print("Black turn to move. ");
//				do {
//					input = getInput(scanner);
//					cell = convertInput(board.cells, input);
//
//					if (cell.getType() == Type.EMPTY)
//						System.out.print("The selected piece is empty. ");
//					if (cell.getType() == Type.RED)
//						System.out.print("The selected piece is red. ");
//				} while (cell.getType() != Type.BLACK || !checkMovePossible(cell, board.cells));
//
//				// Getting direction to move
//				Direction dir = getDirection(scanner, cell, board.cells);
//				moveType(cell, dir, board.cells);
//
//				turn++;
//				drawBoard(board.cells, turn);
//			}
//
//			// Player 2 turn RED downside
//			else {
//				System.out.print("Red turn to move. ");
//				do {
//					input = getInput(scanner);
//					cell = convertInput(board.cells, input);
//
//					if (cell.getType() == Type.EMPTY)
//						System.out.print("The selected piece is empty. ");
//					if (cell.getType() == Type.BLACK)
//						System.out.print("The selected piece is black. ");
//				} while (cell.getType() != Type.RED || !checkMovePossible(cell, board.cells));
//				
//				// Getting direction to move
//				Direction dir = getDirection(scanner, cell, board.cells);
//				moveType(cell, dir, board.cells);
//				
//				
//				
//				turn++;
//				drawBoard(board.cells, turn);
//			}
//		}
//		scanner.close();
//	}

//	public static boolean checkMovePossible(Cell cell, Cell[][] cells) {
//		boolean check = true;
//		int y = cell.getY();
//
//		if (cell.getType() == Type.BLACK) {
//
//			if (y == 0) {
//				check = checkBorderPieceMoveValid(cell, cells, 0);
//			}
//
//			else if (y == boardLength - 1) {
//				check = checkBorderPieceMoveValid(cell, cells, boardLength - 1);
//			}
//
//			else {
//				// check to the right
//				boolean rightCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT);
//
//				// check to the left
//				boolean leftCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT);
//				check = rightCheck | leftCheck;
//			}
//		}
//
//		// Red check
//		else if (cell.getType() == Type.RED) {
//			if (y == 0) {
//				check = checkBorderPieceMoveValid(cell, cells, 0);
//			}
//
//			else if (y == boardLength - 1) {
//				check = checkBorderPieceMoveValid(cell, cells, boardLength - 1);
//			}
//
//			else {
//				// check to the right
//				boolean rightCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT);
//
//				// check to the left
//				boolean leftCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT);
//				check = rightCheck | leftCheck;
//			}
//		}
//		System.out.println("Check if move is possible: " + check);
//		if (!check)
//			System.out.print("Move is not possible. ");
//		return check;
//	}
//
//	public static boolean checkBorderPieceMoveValid(Cell cell, Cell[][] cells, int border) {
//		Type type = cell.getType();
//		int x = cell.getX();
//		int x1 = type == Type.BLACK ? x + 1 : x - 1;
//		int x2 = type == Type.BLACK ? x + 2 : x - 2;
//		int border1 = border == 0 ? 1 : border - 1;
//		int border2 = border == 0 ? 2 : border - 2;
//
//		// check diagonal empty or not
//		if (cells[x1][border1].getType() == Type.EMPTY) {
//			return true;
//			// check the diagonal is same color or not
//		} else if (cells[x1][border1].getType() == type) {
//			return false;
//			// check for overtake possible or not
//		} else {
//			if (cells[x2][border2].getType() != Type.EMPTY) {
//				return false;
//			}
//		}
//		return true;
//
//	}
//
//	public static boolean checkLeftRightPieceMoveValid(Cell cell, Cell[][] cells, Direction dir) {
//		
//		Type type = cell.getType();
//		int x = cell.getX();
//		int y = cell.getY();
//		int x1 = type == Type.BLACK ? x + 1 : x - 1;
//		int x2 = type == Type.BLACK ? x + 2 : x - 2;
//		int y1 = dir == Direction.RIGHT ? y + 1 : y - 1;
//		int y2 = dir == Direction.RIGHT ? y + 2 : y - 2;
//		int border = dir == Direction.RIGHT ? boardLength - 1 : 0;
//
//		// check the diagonal piece whether empty or not
//		if (cells[x1][y1].getType() == Type.EMPTY) {
//			return true;
//			// check if the piece is at border or is the same color
//		} else if (y1 == border || cells[x1][y1].getType() == type) {
//			return false;
//		} else {
//			// check if overtake is possible
//			if (cells[x2][y2].getType() != Type.EMPTY) {
//				return false;
//			}
//		}
//		return true;
//	}

	

//	public static Cell convertInput(Cell[][] cells, String input) {
//
//		int x = input.charAt(0) - 65;
//		int y = Character.getNumericValue(input.charAt(1)) - 1;
//
//		return cells[x][y];
//	}
//
//	public static String getInput(Scanner scanner) {
//		String input = "";
//
//		do {
//			System.out.println("Select a piece to move.");
//			input = scanner.nextLine();
//			if (!input.matches("^[A-Ha-h][1-8]$"))
//				System.out.println("Select a piece between A1 - H8");
//		} while (!input.matches("^[A-Ha-h][1-8]$"));
//
//		return input.toUpperCase();
//	}

//	public static Direction getDirection(Scanner scanner, Cell cell, Cell[][] cells) {
//		Direction dir = null;
//		if (cell.getY() == 0 || !checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT)) {
//			return dir = Direction.RIGHT;
//		} else if (cell.getY() == boardLength - 1 || !checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT)) {
//			return dir = Direction.LEFT;
//		} else {
//			do {
//				System.out.println("Which direction :\n" + "1 : Left\n" + "2 : Right");
//				String input = scanner.nextLine();
//				if (input.equalsIgnoreCase("left") || input.equals("1")) {
//					dir = Direction.LEFT;
//				} else if (input.equalsIgnoreCase("right") || input.equals("2")) {
//					dir = Direction.RIGHT;
//				}
//			} while (dir == null);
//
//			return dir;
//		}
//	}

//	public static void moveType(Cell cell, Direction dir, Cell[][] cells) {
//		int x = cell.getX();
//		int y = cell.getY();
//		if (cell.getType() == Type.BLACK) {
//			if (dir == Direction.RIGHT) {
//				moveDecision(cell, cells, dir, (x + 1), (y + 1));
//			} else if (dir == Direction.LEFT) {
//				moveDecision(cell, cells, dir, (x + 1), (y - 1));
//			}
//		}
//
//		if (cell.getType() == Type.RED) {
//			if (dir == Direction.RIGHT) {
//				moveDecision(cell, cells, dir, (x - 1), (y + 1));
//			} else if (dir == Direction.LEFT) {
//				moveDecision(cell, cells, dir, (x - 1), (y - 1));
//			}
//		}
//	}
//
//	public static void moveDecision(Cell cell, Cell[][] cells, Direction dir, int x, int y) {
//		if (cells[x][y].getType() == Type.EMPTY) {
//			move(cell, dir, cells);
//		} else {
//			overtake(cell, dir, cells);
//		}
//	}
//
//	public static void move(Cell cell, Direction dir, Cell[][] cells) {
//		int x = cell.getX();
//		int y = cell.getY();
//		if (cell.getType() == Type.BLACK) {
//			switch (dir) {
//			case RIGHT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x + 1][y + 1].setType(Type.BLACK);
//				break;
//			case LEFT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x + 1][y - 1].setType(Type.BLACK);
//				break;
//			}
//		} else if (cell.getType() == Type.RED) {
//			switch (dir) {
//			case RIGHT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x - 1][y + 1].setType(Type.RED);
//				break;
//			case LEFT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x - 1][y - 1].setType(Type.RED);
//				break;
//			}
//		}
//	}
//
//	public static void overtake(Cell cell, Direction dir, Cell[][] cells) {
//		int x = cell.getX();
//		int y = cell.getY();
//		if (cell.getType() == Type.BLACK) {
//			switch (dir) {
//			case RIGHT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x + 1][y + 1].setType(Type.EMPTY);
//				cells[x + 2][y + 2].setType(Type.BLACK);
//				break;
//			case LEFT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x + 1][y - 1].setType(Type.EMPTY);
//				cells[x + 2][y - 2].setType(Type.BLACK);
//				break;
//			}
//		} else if (cell.getType() == Type.RED) {
//			switch (dir) {
//			case RIGHT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x - 1][y + 1].setType(Type.EMPTY);
//				cells[x - 2][y + 2].setType(Type.RED);
//				break;
//			case LEFT:
//				cells[x][y].setType(Type.EMPTY);
//				cells[x - 1][y - 1].setType(Type.EMPTY);
//				cells[x - 2][y - 2].setType(Type.RED);
//				break;
//			}
//		}
//	}
}
