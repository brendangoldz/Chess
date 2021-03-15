package com.drexel.edu.se567.Chess.Board;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Tile;
@Ignore
class BoardControllerTest {
	BoardController bc;
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<Tile> pieces = new ArrayList<>();
		
		String data = "Ka1, Qa3, Pa4, Pb3, Bd4, Pc3, Pd3, Nc2";
		String data2 = "Ka8, Rb8, Rc7, Pa7, Pb6, Pc5, Bd6";

		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  Scanner scanner = new Scanner(System.in);
		  bc = new BoardController();
		  System.out.print(data);
		  Thread.sleep(500);
		  System.out.println(data2);
		} finally {
		  System.setIn(stdin);
		}
		
//		String data2 = "Ka8, Rb8, Rc7, Pa7, Pb6, Pc5, Bd6";
//		try {
//		  System.setIn(new ByteArrayInputStream(data2.getBytes()));
//		  Scanner scanner = new Scanner(System.in);
//		  System.out.println(data2);
//		} finally {
//		  System.setIn(stdin);
//		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertNotNull(bc.bf.board);
	}

}
