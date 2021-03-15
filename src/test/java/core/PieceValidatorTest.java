package core;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.drexel.edu.se567.Chess.Board.BoardFactory;
import com.drexel.edu.se567.Chess.Board.BoardInput;
@Ignore
class PieceValidatorTest {
	PieceValidator pv;
	BoardFactory bf;
	ArrayList<Tile> board = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		bf = new BoardFactory();
		ArrayList<Tile> pieces = new ArrayList<>();
		
		String data = "Ka1, Qa3, Pa4, Pb3, Bd4, Pc3, Pd3, Nc4";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  Scanner scanner = new Scanner(System.in);
		  
		  BoardInput.inputWhitePieces(pieces);
		  
		  System.out.print(data);
		} finally {
		  System.setIn(stdin);
		}
		
		String data2 = "Ka8, Rb8, Rc7, Pa7, Pb6, Pc5, Bd6";
		try {
		  System.setIn(new ByteArrayInputStream(data2.getBytes()));
		  Scanner scanner = new Scanner(System.in);
		  BoardInput.inputBlackPieces(pieces);
		  System.out.println(data2);
		} finally {
		  System.setIn(stdin);
		}
		
		board = (ArrayList<Tile>) bf.generate(pieces);
		
		pv = new PieceValidator(board);
	}

	@Test
	void testValidateDiagonally() {
		Tile tester = new Tile(1, 'c');
		//SW
		//Move blocked by queen piece
		assertFalse(pv.validateDiagonally(tester, new Tile(3, 'b')));
		assertTrue(pv.validateDiagonally(tester, new Tile(2, 'b')));
		
		//SE
		assertTrue(pv.validateDiagonally(tester, new Tile(3, 'e')));
		assertFalse(pv.validateDiagonally(new Tile(1, 'b'), new Tile(3, 'e')));
		assertTrue(pv.validateDiagonally(tester, new Tile(3, 'e')));
		
		//NE
		assertTrue(pv.validateDiagonally(new Tile(6, 'c'), new Tile(4, 'e')));
		assertFalse(pv.validateDiagonally(new Tile(6, 'b'), new Tile(4, 'e')));
		assertTrue(pv.validateDiagonally(new Tile(6, 'c'), new Tile(4, 'e')));
		
		//NW
		assertTrue(pv.validateDiagonally(new Tile(2, 'd'), new Tile(1, 'c')));
		assertFalse(pv.validateDiagonally(new Tile(3, 'd'), new Tile(1, 'c')));
		assertTrue(pv.validateDiagonally(new Tile(3, 'e'), new Tile(1, 'c')));
	}

	@Test
	void testValidateHorizontally() {
		assertTrue(pv.validateHorizontally(new Tile(1, 'c'), new Tile(1, 'e')));
		assertFalse(pv.validateHorizontally(new Tile(4, 'c'), new Tile(4, 'f')));
		assertTrue(pv.validateHorizontally(new Tile(4, 'c'), new Tile(4, 'a')));
		assertTrue(pv.validateHorizontally(new Tile(4, 'h'), new Tile(4, 'a')));
		
	}

	@Test
	void testValidateVertically() {
		assertTrue(pv.validateVertically(new Tile(1, 'c'), new Tile(2, 'c')));
		assertFalse(pv.validateVertically(new Tile(1, 'c'), new Tile(5, 'c')));
		
	}

	@Test
	void testValidateKingMove() {
		Tile tester1 = new Tile(1, 'a', new Piece(PieceEnum.KING, ColorEnum.WHITE));
		assertTrue(pv.validateKingMove(tester1, new Tile(2, 'a')));
		assertFalse(pv.validateKingMove(tester1, new Tile(3, 'a')));
		assertFalse(pv.validateKingMove(tester1, new Tile(3, 'b')));
		assertFalse(pv.validateKingMove(tester1, new Tile(3, 'b',  new Piece(PieceEnum.PAWN, ColorEnum.WHITE))));
		
		
		Tile tester2 = new Tile(8, 'a', new Piece(PieceEnum.KING, ColorEnum.BLACK));

		assertTrue(pv.validateKingMove(tester2, new Tile(7, 'b',  new Piece(PieceEnum.PAWN, ColorEnum.WHITE))));
		assertFalse(pv.validateKingMove(tester2, new Tile(6, 'b')));
		assertFalse(pv.validateKingMove(tester2, new Tile(7, 'd',  new Piece(PieceEnum.PAWN, ColorEnum.WHITE))));

	}

	@Test
	void testValidateQueenMove() {
		Tile tester1 = new Tile(3, 'a', new Piece(PieceEnum.QUEEN, ColorEnum.WHITE));

		assertTrue(pv.validateQueenMove(tester1, new Tile(2, 'b')));
		assertFalse(pv.validateQueenMove(tester1, new Tile(2, 'b', new Piece(PieceEnum.PAWN, ColorEnum.WHITE))));
		assertFalse(pv.validateQueenMove(tester1, new Tile(1, 'b')));
		assertFalse(pv.validateQueenMove(tester1, new Tile(3, 'e')));
		assertFalse(pv.validateQueenMove(tester1, new Tile(5, 'a')));

	}

	@Test
	void testValidateRookMove() {
		Tile tester1 = new Tile(8, 'b', new Piece(PieceEnum.ROOK, ColorEnum.BLACK));
		
		assertTrue(pv.validateRookMove(tester1, new Tile(8, 'h')));
		assertFalse(pv.validateRookMove(tester1, new Tile(6, 'b', new Piece(PieceEnum.PAWN, ColorEnum.BLACK))));
		assertFalse(pv.validateRookMove(tester1, new Tile(7, 'c')));
		assertFalse(pv.validateRookMove(tester1, new Tile(6, 'd')));

	}

	@Test
	void testValidateKnightMove() {
		Tile tester1 = new Tile(4, 'c', new Piece(PieceEnum.KNIGHT, ColorEnum.WHITE));
		assertTrue(pv.validateKnightMove(tester1, new Tile(2, 'd')));
		
		assertFalse(pv.validateKnightMove(tester1, new Tile(2, 'd', new Piece(PieceEnum.ROOK, ColorEnum.WHITE))));

	}

	@Test
	void testValidateBishopMove() {
		assertTrue(pv.validateBishopMove(new Tile(4, 'd', new Piece(PieceEnum.BISHOP, ColorEnum.WHITE)), new Tile(3, 'e')));
		assertTrue(pv.validateBishopMove(new Tile(4, 'd', new Piece(PieceEnum.BISHOP, ColorEnum.WHITE)), new Tile(5, 'c', new Piece(PieceEnum.PAWN, ColorEnum.BLACK))));
		assertFalse(pv.validateBishopMove(new Tile(4, 'd', new Piece(PieceEnum.BISHOP, ColorEnum.WHITE)), new Tile(6, 'c', new Piece(PieceEnum.PAWN, ColorEnum.WHITE))));

	}

	@Test
	void testValidatePawnMove() {
		Tile tester1 = new Tile(6, 'b', new Piece(PieceEnum.PAWN, ColorEnum.BLACK));
		assertTrue(pv.validatePawnMove(tester1, new Tile(5, 'b')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(7, 'b')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(5, 'c')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(3, 'd')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(5, 'a')));
		
		tester1 = new Tile(3, 'b', new Piece(PieceEnum.PAWN, ColorEnum.WHITE));
		assertTrue(pv.validatePawnMove(tester1, new Tile(4, 'b')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(4, 'b', new Piece(PieceEnum.QUEEN, ColorEnum.BLACK))));

		assertFalse(pv.validatePawnMove(tester1, new Tile(7, 'b')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(5, 'c')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(3, 'd')));
		assertFalse(pv.validatePawnMove(tester1, new Tile(5, 'a')));

	}

	@Test
	void testValidateMove() {
		assertFalse(pv.validateMove(new Tile(1, 'c'), new Tile(1, 'c')));
		assertTrue(pv.validateMove(new Tile(6, 'b', new Piece(PieceEnum.PAWN, ColorEnum.BLACK)), new Tile(5, 'b')));
		assertTrue(pv.validateMove(new Tile(8, 'b', new Piece(PieceEnum.ROOK, ColorEnum.BLACK)), new Tile(8, 'h')));
		assertTrue(pv.validateMove(new Tile(3, 'a', new Piece(PieceEnum.QUEEN, ColorEnum.WHITE)), new Tile(2, 'b')));
	}
	@Test
	void testGetMoves() {
		assertEquals("WhiteBd4", pv.getMoves(new Tile(5, 'c', new Piece(PieceEnum.PAWN, ColorEnum.BLACK))).trim());
		assertEquals("b7", pv.getMoves(new Tile(8, 'a', new Piece(PieceEnum.KING, ColorEnum.BLACK))).trim());
		assertEquals("c1	a2	b2	b4	BlackPc5", pv.getMoves(new Tile(3, 'a', new Piece(PieceEnum.QUEEN, ColorEnum.WHITE))).trim());
		assertEquals("b1	a2	b2", pv.getMoves(new Tile(1, 'a', new Piece(PieceEnum.KING, ColorEnum.WHITE))).trim());
		assertEquals("b7	c8	d8	e8	f8	g8	h8", pv.getMoves(new Tile(8, 'b', new Piece(PieceEnum.ROOK, ColorEnum.BLACK))).trim());
		assertEquals("g1	f2	e3	BlackPc5	e5	f6	g7	h8", pv.getMoves(new Tile(4, 'd', new Piece(PieceEnum.BISHOP, ColorEnum.WHITE))).trim());
		assertEquals("b2	d2	e3	a5	e5	BlackPb6	BlackBd6", pv.getMoves(new Tile(4, 'c', new Piece(PieceEnum.KNIGHT, ColorEnum.WHITE))).trim());
	}

}
