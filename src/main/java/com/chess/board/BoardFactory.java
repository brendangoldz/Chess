package com.chess.board;


import java.util.ArrayList;
import java.util.List;

import com.chess.core.Tile;

public class BoardFactory {
	protected ArrayList<Tile> board = new ArrayList<>();
	private final char[] columns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
	private final int[] rows = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public BoardFactory() {
		this.resetBoard();
	}
	
	public BoardFactory(List<Tile> t) {
		this.board = (ArrayList<Tile>)t;
	}
	
	public List<Tile> generate(List<Tile> tiles) {
		for (Tile t : tiles) {
			for (Tile p : board) {
				if (p.equals(t)) {
					int index = board.indexOf(p);
					board.set(index, t);
				}
			}
		}		
		return board;
	}
	
	public void resetBoard() {
		board = new ArrayList<>();
		//No Pieces
		for (int row : rows) {
			for (char column : columns) {
				int r = row;
				char c = column;
				Tile t = new Tile(r, c);
				board.add(t);

			}
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int colCounter = 0;
		for (Tile p : board) {
			if(colCounter==7) {
				sb.append("\n");
				colCounter = 0;
			}
			sb.append(p.toString() + "\t");
			colCounter++;
		}
		
		return sb.toString();
	}

	public static void main(String[] args) {
//		BoardFactory bf = new BoardFactory();
//		bf.generate();
	}
}
