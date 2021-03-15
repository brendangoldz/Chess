/**
 * 
 */
package com.chess.core;

/**
 * @author brendangoldsmith
 *
 */
public enum PieceEnum {
	KING("K"), QUEEN("Q"), ROOK("R"), KNIGHT("N"), BISHOP("B"), PAWN("P");

	private String name;

	PieceEnum(String string) {
		this.name = string;
	}
	@Override
	public String toString() {
		return name;
	}

	public static PieceEnum fromString(String s) {
		for (PieceEnum p : PieceEnum.values()) {
			if (s.equalsIgnoreCase(p.name)) {
				return p;
			}
		}
		return null;
	}
}
