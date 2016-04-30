package jogo.cli;

import java.util.Scanner;

import jogo.logic.Labirinto;
import jogo.logic.Tabuleiro;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {
	
	/** The scanner. */
	static Scanner sc;
	
	
	/**
	 * Play game random.
	 */
	public static void playGameRandom(){
		int size,ndragoes,jogada,modo;
		System.out.println("Insere o tamanho do tabuleiro(impar)");
		size = sc.nextInt();
		System.out.println("Insere o n de dragoes");
		ndragoes = sc.nextInt();
		System.out.println("Insere o modo de jogo(1 dragao parado, 2 dragao move-se, 3 dragao move-se e dorme);");
		modo = sc.nextInt();
		char[][] board = Labirinto.geraLabirinto(size,ndragoes);
		Tabuleiro tabuleiro = new Tabuleiro(board);
		tabuleiro.setModo(modo);
		while(tabuleiro.getFim() == 0){
			System.out.println(tabuleiro.printBoard());
			jogada = -1;
			System.out.println("INSERE A DIRECAO(1 CIMA, 2 BAIXO, 3 ESQ, 4 DIR)");
			jogada = sc.nextInt();
			tabuleiro.moverHeroi(jogada);
			tabuleiro.moverDragao();
			tabuleiro.verificarJogo();
		}
		System.out.println(tabuleiro.printBoard());
		if(tabuleiro.getFim() == 2)	
			System.out.println("YOU DIED");
		if(tabuleiro.getFim() == 1)
			System.out.println("VICTORY");
	    menu();
	}
	
	/**
	 * Menu.
	 */
    public static void menu(){
		System.out.println("0-Sair,1-Jogar");
		sc = new Scanner(System.in);
		int opcao = -1;
		opcao = sc.nextInt();
		if(opcao == 1){
			playGameRandom();
		}
		if(opcao == 0)
		{
			return;
		}
		
		
	}
	
	
}
