package jogo.testes;
import static org.junit.Assert.*;
import org.junit.Test;

import jogo.cli.Game;
import jogo.logic.Dragao;
import jogo.logic.Espada;
import jogo.logic.Heroi;
import jogo.logic.Tabuleiro;

// TODO: Auto-generated Javadoc
/**
 * The Class testes.
 */
public class testes {


	/** The m1. */
	char [][] m1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
	};

	/**
	 * Test_a.
	 */
	@Test
	public void test_a() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		assertEquals(tabuleiro.moverHeroi(2),1);
		assertEquals(tabuleiro.moverHeroi(1),1);
	}
	
	/**
	 * Test_b.
	 */
	@Test
	public void test_b() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		assertEquals(tabuleiro.moverHeroi(4),0);
		assertEquals(tabuleiro.moverHeroi(1),0);
	}
	
	/**
	 * Test_c.
	 */
	@Test
	public void test_c() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(2);
		tabuleiro.moverHeroi(2);
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.getHeroi().getEspada(),1);
	}
	
	/**
	 * Test_d.
	 */
	@Test
	public void test_d() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(2);
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.getFim(),2);
	}
	
	/**
	 * Test_e.
	 */
	@Test
	public void test_e() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(2);
		tabuleiro.moverHeroi(2);
		tabuleiro.verificarJogo();
		tabuleiro.moverHeroi(4);
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.todosMortos(),true);
	}
	
	/**
	 * Test_f.
	 */
	@Test
	public void test_f() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(2);
		tabuleiro.moverHeroi(2);
		tabuleiro.verificarJogo();
		tabuleiro.moverHeroi(4);
		tabuleiro.verificarJogo();            
		tabuleiro.moverHeroi(4);
		tabuleiro.moverHeroi(1);
		tabuleiro.moverHeroi(1);
		tabuleiro.moverHeroi(4);
		tabuleiro.verificarJogo(); 
		assertEquals(tabuleiro.getFim(),1);
	}
	
	/**
	 * Test_g.
	 */
	@Test
	public void test_g() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(4);
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.getFim(),0);
	}
	
	/**
	 * Test_h.
	 */
	@Test
	public void test_h() {
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverHeroi(2);
		tabuleiro.moverHeroi(2);
		tabuleiro.verificarJogo();
		tabuleiro.moverHeroi(1);
		tabuleiro.moverHeroi(1);
		tabuleiro.moverHeroi(4);
		tabuleiro.moverHeroi(4);
		tabuleiro.moverHeroi(4);
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.getFim(),0);
	}
	/**
	 * Test_j.
	 */
	@Test
	public void test_j(){
		
		
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.setModo(3);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverDragao();
		tabuleiro.verificarJogo();
		tabuleiro.moverHeroi(3);
		tabuleiro.moverDragao();
		tabuleiro.verificarJogo();
		System.out.println(tabuleiro.printBoard());
	}
	/**
	 * Test_k.
	 */
	@Test
	public void test_k(){
		Tabuleiro tabuleiro = new Tabuleiro(m1);
		tabuleiro.setModo(1);
		tabuleiro.moverHeroi(3);
		tabuleiro.moverDragao();
		tabuleiro.verificarJogo();
		tabuleiro.moverHeroi(3);
		tabuleiro.moverDragao();
		tabuleiro.verificarJogo();
		assertEquals(tabuleiro.getSquare(1,1),'H');
		assertEquals(tabuleiro.getSquare(3, 3),'D');
	}
//	@Test
//	public void testRandBoard(){
//		Tabuleiro tabuleiro = new Tabuleiro(11,5);
//	}




}
