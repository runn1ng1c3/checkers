package muzakkir.checkers;

import java.util.Scanner;

public class App {

	final static int boardHeight = 8;
	final static int boardLength = 8;
	static Cell[][] cells = new Cell[boardHeight][boardLength];
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {	
		
		// initializations
		Cell cell;
		int turn = 0;
		Board board = new Board(boardHeight, boardLength, cells);

		board.drawBoard(cells, boardHeight, boardLength, turn);
		//TODO change turn to until one side have no piece anymore
		while (turn < 20) {

			// Player 1 turn BLACK topside
			if (turn % 2 == 0) {
				System.out.print("Black turn to move. ");
//				do {
//					cell = getInput(board.cells);
//
//					if (cell.getType() == Type.EMPTY)
//						System.out.print("The selected piece is empty. ");
//					if (cell.getType() == Type.RED)
//						System.out.print("The selected piece is red. ");
//				} while (cell.getType() != Type.BLACK || !checkMovePossible(cell, board.cells));
				cell = getCell(board, Type.BLACK, Type.RED);
				
				// Getting direction to move
				Direction dir = getDirection(cell, board.cells);
				moveType(cell, dir, board.cells);

				turn++;
				board.drawBoard(cells, boardHeight, boardLength, turn);
			}

			// Player 2 turn RED downside
			else {
				System.out.print("Red turn to move. ");
//				do {
//					cell = getInput(board.cells);
//
//					if (cell.getType() == Type.EMPTY)
//						System.out.print("The selected piece is empty. ");
//					if (cell.getType() == Type.BLACK)
//						System.out.print("The selected piece is black. ");
//				} while (cell.getType() != Type.RED || !checkMovePossible(cell, board.cells));
				cell = getCell(board, Type.RED, Type.BLACK);
				
				// Getting direction to move
				Direction dir = getDirection(cell, board.cells);
				moveType(cell, dir, board.cells);

				turn++;
				board.drawBoard(cells, boardHeight, boardLength, turn);
			}
		}
		scanner.close();
	}
	
	public static Cell getCell (Board board, Type colorTurnNow, Type colorOpps) {
		Cell cell;
		do {
			cell = getInput(board.cells);

			if (cell.getType() == Type.EMPTY)
				System.out.print("The selected piece is empty. ");
			if (cell.getType() == colorOpps) {
				String s = "The selected piece is %s.";
				s = String.format(s, colorOpps == Type.BLACK ? "black" : "red"); 
				System.out.print(s);
				}
		} while (cell.getType() != colorTurnNow || !checkMovePossible(cell, board.cells));
		
		return cell;
	}
	
	public static Cell getInput(Cell[][] cells) {
		String input = "";

		do {
			System.out.println("Select a piece to move.");
			input = scanner.nextLine();
			if (!input.matches("^[A-Ha-h][1-8]$"))
				System.out.println("Select a piece between A1 - H8");
		} while (!input.matches("^[A-Ha-h][1-8]$"));
		
		input = input.toUpperCase();
		int x = input.charAt(0) - 65;
		int y = Character.getNumericValue(input.charAt(1)) - 1;
		return cells[x][y];
	}

	public static Direction getDirection(Cell cell, Cell[][] cells) {
		Direction dir = null;
		if (cell.getY() == 0 || !checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT)) {
			return dir = Direction.RIGHT;
		} else if (cell.getY() == boardLength - 1 || !checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT)) {
			return dir = Direction.LEFT;
		} else {
			do {
				System.out.println("Which direction :\n" + "1 : Left\n" + "2 : Right");
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("left") || input.equals("1")) {
					dir = Direction.LEFT;
				} else if (input.equalsIgnoreCase("right") || input.equals("2")) {
					dir = Direction.RIGHT;
				}
			} while (dir == null);

			return dir;
		}
	}

	public static boolean checkMovePossible(Cell cell, Cell[][] cells) {
		boolean check = true;
		int y = cell.getY();

		if (cell.getType() == Type.BLACK) {

			if (y == 0) {
				check = checkBorderPieceMoveValid(cell, cells, 0);
			}

			else if (y == boardLength - 1) {
				check = checkBorderPieceMoveValid(cell, cells, boardLength - 1);
			}

			else {
				// check to the right
				boolean rightCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT);

				// check to the left
				boolean leftCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT);
				check = rightCheck | leftCheck;
			}
		}

		// Red check
		else if (cell.getType() == Type.RED) {
			if (y == 0) {
				check = checkBorderPieceMoveValid(cell, cells, 0);
			}

			else if (y == boardLength - 1) {
				check = checkBorderPieceMoveValid(cell, cells, boardLength - 1);
			}

			else {
				// check to the right
				boolean rightCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.RIGHT);

				// check to the left
				boolean leftCheck = checkLeftRightPieceMoveValid(cell, cells, Direction.LEFT);
				check = rightCheck | leftCheck;
			}
		}
		System.out.println("Check if move is possible: " + check);
		if (!check)
			System.out.print("Move is not possible. ");
		return check;
	}

	public static boolean checkBorderPieceMoveValid(Cell cell, Cell[][] cells, int border) {
		Type type = cell.getType();
		int x = cell.getX();
		int x1 = type == Type.BLACK ? x + 1 : x - 1;
		int x2 = type == Type.BLACK ? x + 2 : x - 2;
		int border1 = border == 0 ? 1 : border - 1;
		int border2 = border == 0 ? 2 : border - 2;

		// check diagonal empty or not
		if (cells[x1][border1].getType() == Type.EMPTY) {
			return true;
			// check the diagonal is same color or not
		} else if (cells[x1][border1].getType() == type) {
			return false;
			// check for overtake possible or not
		} else {
			if (cells[x2][border2].getType() != Type.EMPTY) {
				return false;
			}
		}
		return true;

	}

	public static boolean checkLeftRightPieceMoveValid(Cell cell, Cell[][] cells, Direction dir) {

		Type type = cell.getType();
		int x = cell.getX();
		int y = cell.getY();
		int x1 = type == Type.BLACK ? x + 1 : x - 1;
		int x2 = type == Type.BLACK ? x + 2 : x - 2;
		int y1 = dir == Direction.RIGHT ? y + 1 : y - 1;
		int y2 = dir == Direction.RIGHT ? y + 2 : y - 2;
		int border = dir == Direction.RIGHT ? boardLength - 1 : 0;

		// check the diagonal piece whether empty or not
		if (cells[x1][y1].getType() == Type.EMPTY) {
			return true;
			// check if the piece is at border or is the same color
		} else if (y1 == border || cells[x1][y1].getType() == type) {
			return false;
		} else {
			// check if overtake is possible
			if (cells[x2][y2].getType() != Type.EMPTY) {
				return false;
			}
		}
		return true;
	}
	
	public static void moveType(Cell cell, Direction dir, Cell[][] cells) {
		int x = cell.getX();
		int y = cell.getY();
		if (cell.getType() == Type.BLACK) {
			if (dir == Direction.RIGHT) {
				moveDecision(cell, cells, dir, (x + 1), (y + 1));
			} else if (dir == Direction.LEFT) {
				moveDecision(cell, cells, dir, (x + 1), (y - 1));
			}
		}

		if (cell.getType() == Type.RED) {
			if (dir == Direction.RIGHT) {
				moveDecision(cell, cells, dir, (x - 1), (y + 1));
			} else if (dir == Direction.LEFT) {
				moveDecision(cell, cells, dir, (x - 1), (y - 1));
			}
		}
	}

	public static void moveDecision(Cell cell, Cell[][] cells, Direction dir, int x, int y) {
		if (cells[x][y].getType() == Type.EMPTY) {
			move(cell, dir, cells);
		} else {
			overtake(cell, dir, cells);
			//TODO
			//After overtake check for the next piece
			//Only overtake need to return new cell
//			if(checkMovePossible(cell, cells)) {
//				overtake(cell, dir, cells);
//			}
		}
	}

	public static void move(Cell cell, Direction dir, Cell[][] cells) {
		int x = cell.getX();
		int y = cell.getY();

		if (cell.getType() == Type.BLACK) {
			switch (dir) {
			case RIGHT:
				cells[x][y].setType(Type.EMPTY);
				cells[x + 1][y + 1].setType(Type.BLACK);
				break;
			case LEFT:
				cells[x][y].setType(Type.EMPTY);
				cells[x + 1][y - 1].setType(Type.BLACK);
				break;
			}
		} else if (cell.getType() == Type.RED) {
			switch (dir) {
			case RIGHT:
				cells[x][y].setType(Type.EMPTY);
				cells[x - 1][y + 1].setType(Type.RED);
				break;
			case LEFT:
				cells[x][y].setType(Type.EMPTY);
				cells[x - 1][y - 1].setType(Type.RED);
				break;
			}
		}
	}

	public static void overtake(Cell cell, Direction dir, Cell[][] cells) {
		int x = cell.getX();
		int y = cell.getY();
		Type cellclr = cell.getType();
		cells[x][y].setType(Type.EMPTY);
		cells[cellclr == Type.BLACK ? x + 2 : x - 2][dir == Direction.RIGHT ? y + 2 : y - 2].setType(cellclr);
		cells[cellclr == Type.BLACK ? x + 1 : x - 1][dir == Direction.RIGHT ? y + 1 : y - 1].setType(Type.EMPTY);
		
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
	}
}
