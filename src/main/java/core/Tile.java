/**
 * 
 */
package core;

/**
 * @author brendangoldsmith
 *
 */
public class Tile {

	protected int row;
	protected char col;
	protected Piece piece;

	public Tile(int row, char col) {
		this.piece = new Piece();
		this.row = row;
		this.col = col;
	}

	public Tile(int row, char col, Piece p) {
		this.piece = p;
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public char getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(char col) {
		this.col = col;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.getPiece().getPe() == null) {
			sb.append(this.getCol() + String.valueOf(this.getRow()));
		} else {
			String col = this.getPiece().getColor().toString();
			sb.append(col);
			String piece = this.getPiece().getPe().toString();
			sb.append(piece + this.getCol() + String.valueOf(this.getRow()));
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Tile) {
			Tile check = (Tile) o;
			if (this.getCol() != check.getCol() || this.getRow() != check.getRow()) {
				return false;
			}
		} else {
			return false;

		}
		return true;
	}
}
