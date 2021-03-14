package com.drexel.edu.se567.Chess.Board;

import java.io.Console;
import java.util.ArrayList;

import core.PieceValidator;
import core.Tile;

public class BoardController {
	protected BoardFactory bf;

	public BoardController() {
		this.play();
	}

	public void play() {
		System.out.println("\nStarting Chess\n");

		bf = new BoardFactory();

		ArrayList<Tile> pieces = new ArrayList<>();

		try {
			BoardInput.inputWhitePieces(pieces);
		} catch (Exception e) {
			System.out.println("Invalid input, restarting white position entry.");
			BoardInput.inputWhitePieces(pieces);
		}
		try {
			BoardInput.inputBlackPieces(pieces);
		} catch (Exception e) {
			System.out.println("Invalid input, restarting black position entry.");
			BoardInput.inputBlackPieces(pieces);
		}

		ArrayList<Tile> board = (ArrayList<Tile>) bf.generate(pieces);

		System.out.println("Board after generation:\n" + bf.toString());

		Tile t = null;
		while (t == null)
			t = BoardInput.getNextMove(board);

		PieceValidator pv = new PieceValidator(board);
		String possibleMoves = pv.getMoves(t);
				possibleMoves = possibleMoves.equals("") ? "No possible moves for this piece." : possibleMoves;

		System.out.println("Output: " + possibleMoves);

	}

	@SuppressWarnings(value = { "unused" })
	public static void main(String[] args) {
		//Ka1, Qa3, Pa4, Pb3, Nc2
		//Ka8, Rb8, Pa7, Pb6, Bc7

		BoardController bc = new BoardController();
	}

}
