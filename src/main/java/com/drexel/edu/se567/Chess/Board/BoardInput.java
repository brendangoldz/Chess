/**
 * 
 */
package com.drexel.edu.se567.Chess.Board;

import java.util.List;
import java.util.Scanner;

import core.ColorEnum;
import core.Piece;
import core.PieceEnum;
import core.Tile;

/**
 * @author brendangoldsmith
 *
 */
public class BoardInput {

	private BoardInput() {
		throw new IllegalStateException("Utility Class");
	}

	public static void inputWhitePieces(List<Tile> pieces) {
		System.out.println("**WHITE POSITION ENTRY**\n");
		final ColorEnum col = ColorEnum.WHITE;
		
		try {
			Scanner s = new Scanner(System.in);
			String input = s.nextLine();

			String[] inputs = input.split(",");
			Tile t = null;
			for (String str : inputs) {
				char[] piece = str.trim().toCharArray();
				PieceEnum pe = PieceEnum.fromString(String.valueOf(piece[0]));
				char column = piece[1];
				int row = Integer.parseInt(String.valueOf(piece[2]));
				t = new Tile(row, column, new Piece(pe, col));
				if (t != null)
					pieces.add(t);
			}
		} catch (Exception e) {
			System.out.println("Invalid input, restarting white position entry.");
			BoardInput.inputWhitePieces(pieces);
		}
	}

	public static void inputBlackPieces(List<Tile> pieces) {
		System.out.println("**BLACK POSITION ENTRY**\n");
		final ColorEnum col = ColorEnum.BLACK;
		try { 
			Scanner s = new Scanner(System.in);
			String input = s.nextLine();
			String[] inputs = input.split(",");
			Tile t = null;
			for (String str : inputs) {
				char[] piece = str.trim().toCharArray();
				PieceEnum pe = PieceEnum.fromString(String.valueOf(piece[0]));
				char column = piece[1];
				int row = Integer.parseInt(String.valueOf(piece[2]));
				t = new Tile(row, column, new Piece(pe, col));

				if (t != null)
					pieces.add(t);
			}
		} catch (Exception e) {
			System.out.println("Invalid input, restarting black position entry.");
			BoardInput.inputBlackPieces(pieces);
		}
		
	}

	public static Tile getNextMove(List<Tile> pieces) {
		Scanner s = new Scanner(System.in);
		System.out.println("Which piece would you like to move? [Example: `Ka8` or `Pa1`]");
		String input = s.next();
		char[] arr = input.toCharArray();

		char col = arr[1];
		int row = Integer.parseInt(String.valueOf(arr[2]));

		for (Tile p : pieces) {
			if (p.equals(new Tile(row, col)) && p.getPiece() != null) {
				return p;
			}
		}

		return null;
	}

}
