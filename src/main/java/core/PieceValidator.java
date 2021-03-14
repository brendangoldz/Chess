/**
 * 
 */
package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author brendangoldsmith
 *
 */
public class PieceValidator {
	private ArrayList<Tile> board;

	public PieceValidator(ArrayList<Tile> boardConfig) {
		this.board = boardConfig;
	}

	public boolean validateKingMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();
		
		int newRow = t.getRow();
		char newCol = t.getCol();

		int rowDiff = currRow - newRow;
		
		if (curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false; //If piece in position is a friendly piece
		}

		int charComp = Character.compare(currCol, newCol);
		// If trying to move horizontal,
		// opposing piece must be there
		if(Math.abs(charComp) > 1) {
			return false;
		} else if(Math.abs(rowDiff) > 1) {
			return false;
		}
		return true;
	}

	public boolean validateQueenMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();
		return true;
	}

	public boolean validateRookMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();
		
		int newRow = t.getRow();
		char newCol = t.getCol();

		int charComp = Character.compare(currCol, newCol);
		
		if (curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false; //If piece in position is a friendly piece
		}
		
		// If trying to move horizontally (colCompare not 0),
		// row must not be changed
		if (charComp != 0 && currRow!=newRow) {
			return false;
		}
		
		return true;
	}

	public boolean validateKnightMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();
		return true;
	}

	public boolean validateBishopMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();
		
		int newRow = t.getRow();
		char newCol = t.getCol();
		
		int charComp = Character.compare(currCol, newCol);
		// If trying to move horizontally (colCompare not 0),
		// row must be changed
		if (charComp != 0 && currRow==newRow) {
			return false;
		}
		return true;
	}

	public boolean validatePawnMove(Tile curr, Tile t) {
		char currCol = curr.getCol();
		int currRow = curr.getRow();
		ColorEnum ce = curr.getPiece().getColor();
		PieceEnum pe = curr.getPiece().getPe();

		int newRow = t.getRow();
	
		int rowDiff = curr.getPiece().getColor().equals(ColorEnum.WHITE) ? newRow-currRow : currRow-newRow;
		
		char newCol = t.getCol();
	
		int charComp = Character.compare(currCol, newCol);
		
		if (curr.getPiece().getColor().equals(t.getPiece().getColor())) {
			return false; //If piece in position is a friendly piece
		} else if (Math.abs(charComp) == 1 && t.getPiece() == null) {
			return false;
		} 
		// If trying to move horizontal,
		// opposing piece must be there
		if(Math.abs(charComp) > 1) {
			return false;
		} else if(rowDiff <= 0 || Math.abs(rowDiff) > 1) {
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

	public void setBoard(ArrayList<Tile> board) {
		this.board = board;
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
