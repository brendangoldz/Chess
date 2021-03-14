/**
 * 
 */
package core;

/**
 * @author brendangoldsmith
 *
 */
public class Piece {
	protected PieceEnum pe;
	protected ColorEnum color;
	
	public Piece() {
		this.pe = null;
		this.color = null;
	}

	public Piece(PieceEnum piece, ColorEnum color) {
		this.pe = piece;
		this.color = color;
	}

	/**
	 * @return the pe
	 */
	public PieceEnum getPe() {
		return pe;
	}

	/**
	 * @param pe the pe to sets
	 */
	public void setPe(PieceEnum pe) {
		this.pe = pe;
	}

	/**
	 * @return the color
	 */
	public ColorEnum getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(ColorEnum color) {
		this.color = color;
	}
	
	public boolean oppositeColor(Piece p) {
		return p.getColor().equals(this.getColor());
	}

}
