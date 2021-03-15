/**
 * 
 */
package com.chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brendangoldsmith
 *
 */
/**
 * @author brendangoldsmith
 *
 */
public class PieceValidator {
	private ArrayList<Tile> board;

	public PieceValidator(ArrayList<Tile> boardConfig) {
		this.board = boardConfig;
	}

	
	public int getDiagonalDirection(int startRow, int endRow, char startCol, char endCol) {
		// NE, NW, SW, SE
		int dir = 0;
		// NE
		if (startRow > endRow && Character.compare(startCol, endCol) < 0) {

			dir = 0;
			// NW
		} else if (startRow > endRow && Character.compare(startCol, endCol) > 0) {

			dir = 1;
			// SW
		} else if (startRow < endRow && Character.compare(startCol, endCol) > 0) {

			dir = 2;
			// SE
		} else if (startRow < endRow && Character.compare(startCol, endCol) < 0) {

			dir = 3;
		}
		
		return dir;
	}
	
	
	/**
	 * @param curr
	 * @param targTile
	 * @return
	 */
	public boolean validateDiagonally(Tile curr, Tile targTile) {
		int startRow = curr.getRow();
		char startCol = curr.getCol();

		char endCol = targTile.getCol();
		int endRow = targTile.getRow();

		int dir = getDiagonalDirection(startRow, endRow, startCol, endCol);

		int diff = Math.abs(Character.compare(startCol, endCol));

		int[][] dirs = { { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };

		int rowDir = dirs[dir][0];
		int colDir = dirs[dir][1];

		int targetRow = startRow + (diff * rowDir);
		char targetCol = startCol;
		targetCol += (diff * colDir);

		if (targTile.getRow() != targetRow || targTile.getCol() != targetCol) {
			return false;
		} else {

			int currRow = startRow;
			char currCol = startCol;
			for (int i = 0; i < diff; i++) {

				currRow += rowDir;
				currCol += colDir;

				Tile currTile = new Tile(currRow, currCol);

				int position = board.indexOf(currTile);
				Tile actualTile = board.get(position);
				if (!targTile.equals(actualTile) && actualTile.getPiece().getPe() != null) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @param curr
	 * @param targ
	 * @return
	 */
	public boolean validateHorizontally(Tile curr, Tile targ) {
		int startRow = curr.getRow();
		char startCol = curr.getCol();
		char endCol = targ.getCol();
		// 0 = Same
		// <-1 = right
		// >1 = left
		if (Character.compare(startCol, endCol) > 0) {

			char initChar = startCol;
			initChar += 1;
			for (char c = initChar; c < endCol; c++) {
				Tile newTile = new Tile(startRow, c);
				int position = board.indexOf(newTile);
				Tile actualTile = board.get(position);
				if (!targ.equals(actualTile) && actualTile.getPiece().getPe() != null) {
					return false;
				}
			}
		} else if (Character.compare(startCol, endCol) < 0) {
			// Systemout.println('E' - 1)
			char initChar = endCol;
			initChar -= 1;
			for (char c = initChar; c > startCol; c--) {
				Tile newTile = new Tile(startRow, c);
				int position = board.indexOf(newTile);
				Tile actualTile = board.get(position);
				if (!targ.equals(actualTile) && actualTile.getPiece().getPe() != null) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @param curr
	 * @param targ
	 * @return
	 */
	public boolean validateVertically(Tile curr, Tile targ) {
		int startRow = curr.getRow();
		char startCol = curr.getCol();
		int endRow = targ.getRow();
		
		int rowDiff = startRow - endRow;

		//End Row > Start Row
		if(Math.abs(rowDiff) == 1 && targ.getPiece().getPe() == null) {
			return true;
		}
		else if (rowDiff < 0) {

			for (int i = startRow + 1; i < endRow; i++) {
				Tile newTile = new Tile(i, startCol);
				int position = board.indexOf(newTile);
				Tile actualTile = board.get(position);
				if (!targ.equals(actualTile) && actualTile.getPiece().getPe() != null) {
					return false;
				}
			}
		//Start Row > End Row
		} else if (rowDiff > 0) {

			for (int i = startRow - 1; i > endRow; i--) {
				Tile newTile = new Tile(i, startCol);
				int position = board.indexOf(newTile);
				Tile actualTile = board.get(position);
				if (!targ.equals(actualTile) && actualTile.getPiece().getPe() != null) {
					return false;
				}
			}
		}


		return true;
	}

	/**
	 * @param curr
	 * @param t
	 * @return
	 */
	public boolean validateKingMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();

		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		}

		int newRow = t.getRow();
		char newCol = t.getCol();

		int rowDiff = currRow - newRow;

		int charComp = Character.compare(currCol, newCol);
		// If trying to move horizontal,
		// opposing piece must be there
		if (Math.abs(charComp) > 1 || Math.abs(rowDiff) > 1) {
			return false;
		}
		return true;
	}

	/**
	 * @param curr
	 * @param t
	 * @return
	 */
	public boolean validateQueenMove(Tile curr, Tile t) {

		int startRow = curr.getRow();
		char startCol = curr.getCol();

		int endRow = t.getRow();
		char endCol = t.getCol();
		
		int charComp = Character.compare(startCol, endCol);


		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		} 
		if ((charComp != 0 && startRow == endRow) && !validateHorizontally(curr, t)) {
			return false;
		} else if ((charComp == 0 && startRow != endRow) && !validateVertically(curr, t)) {
			return false;
		} else if ((charComp != 0 && startRow != endRow) && !validateDiagonally(curr, t)) {
			return false;
		}

		return true;
	}

	/**
	 * @param curr
	 * @param t
	 * @return
	 */
	public boolean validateRookMove(Tile curr, Tile t) {
		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		}

		int startRow = curr.getRow();
		char startCol = curr.getCol();

		int endRow = t.getRow();
		char endCol = t.getCol();

		int charComp = Character.compare(startCol, endCol);

		if (Math.abs(charComp) > 0 && startRow != endRow) {
			return false;
		} else if ((charComp != 0 && startRow == endRow) && !validateHorizontally(curr, t)) {
			return false;
		} else if ((charComp == 0 && startRow != endRow) && !validateVertically(curr, t)) {
			return false;
		}

		return true;
	}

	public boolean validateKnightMove(Tile curr, Tile t) {
		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		}

		char currCol = curr.getCol();
		char endCol = t.getCol();
		int currRow = curr.getRow();
		int endRow = t.getRow();

		int charComp = Character.compare(currCol, endCol);
		int rowDiff = currRow - endRow;
		if (Math.abs(rowDiff) == 0 || Math.abs(charComp) == 0 || Math.abs(rowDiff) > 2 || Math.abs(charComp) > 2) {
			return false;
		} else if (Math.abs(rowDiff) == 1 && Math.abs(charComp) != 2) {
			return false;
		} else if (Math.abs(rowDiff) == 2 && Math.abs(charComp) != 1) {
			return false;
		}

		return true;
	}

	public boolean validateBishopMove(Tile curr, Tile t) {
		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		}

		char currCol = curr.getCol();
		int currRow = curr.getRow();

		int newRow = t.getRow();
		char newCol = t.getCol();

		if (currRow == newRow || currCol == newCol) {
			return false;
		} else if (!validateDiagonally(curr, t)) {
			return false;
		}

		return true;
	}

	public boolean validatePawnMove(Tile curr, Tile t) {
		if (t.getPiece().getPe() != null && curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false;
		}

		char currCol = curr.getCol();
		char endCol = t.getCol();
		int currRow = curr.getRow();

		int newRow = t.getRow();

		int rowDiff = curr.getPiece().getColor().equals(ColorEnum.WHITE) ? newRow - currRow : currRow - newRow;

		if (rowDiff > 1 || rowDiff <= 0 || Math.abs(Character.compare(currCol, endCol)) > 1) {
			return false;
		} else if (t.getPiece().getPe() == null && Math.abs(Character.compare(currCol, endCol)) > 0) {
			return false;
		} else if(rowDiff==1 && Character.compare(currCol, endCol) == 0 && t.getPiece().getPe() != null) {
			return false;
		}

		return true;
	}

	public boolean validateMove(Tile curr, Tile t) {
		if (curr.equals(t)) {
			return false;
		} else {
			switch (curr.getPiece().getPe()) {
			case KING:
				return validateKingMove(curr, t);
			case QUEEN:
				return validateQueenMove(curr, t);
			case ROOK:
				return validateRookMove(curr, t);
			case KNIGHT:
				return validateKnightMove(curr, t);
			case BISHOP:
				return validateBishopMove(curr, t);
			case PAWN:
				return validatePawnMove(curr, t);
			default:
				return false;
			}
		}

	}

	public void setBoard(List<Tile> board) {
		this.board = (ArrayList<Tile>) board;
	}

	public String getMoves(Tile t) {
		StringBuilder sb = new StringBuilder();

		for (Tile ti : board) {
			if (validateMove(t, ti)) {
				sb.append(ti);
				sb.append("\t");
			}
		}
		return sb.toString();
	}

}
