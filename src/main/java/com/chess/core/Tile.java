/**
 * 
 */
package com.chess.core;

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
			sb.append(this.getPiece().getColor().toString());
			sb.append(this.getPiece().getPe().toString() + this.getCol() + String.valueOf(this.getRow()));
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tile))
			return false;
		Tile other = (Tile) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}
}
