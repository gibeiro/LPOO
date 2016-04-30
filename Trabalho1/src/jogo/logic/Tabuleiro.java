package jogo.logic;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class Tabuleiro.
 */
//x corresponde � altura do tabuleiro, y � largura
public class Tabuleiro{

	/** The hero object. */
	Heroi heroi;
	
	/** The array of dragon objects. */
	//Dragao dragao;
	ArrayList<Dragao> dragoes;
	
	/** The sword object. */
	Espada espada;
	
	/** The end state.
	 * 0 - game is running
	 * 1 - player won
	 * 2 - player died */
	int fim;// 0 = jogo a decorrer, 1 = ganhou, 2 = jogador sucumbiu
	
	/** The game mode. 
	 * 1 - static dragon
	 * 2 - dynamic dragon
	 * 3 - sleepy dragon (mix of the previous)*/
	int modojogo; // 1 = dragao parado, 2 dragao move, 3 dragao move e dorme
	
	/** The char[][] game board. */
	private char tabuleiro[][];

	/**
	 * Gets the number of dragons.
	 *
	 * @return the number of dragons
	 */
	public int getNDragoes(){
		return dragoes.size();
	}
	
	/**
	 * Gets the size of the board.
	 *
	 * @return the size of the board
	 */
	public int getSize(){
		return tabuleiro.length;
	}
	
	/**
	 * Gets the hero object.
	 *
	 * @return the hero object
	 */
	public Heroi getHeroi(){
		return heroi;
	}
	
	/**
	 * Gets the dragon object of index i.
	 *
	 * @param i the i
	 * @return the dragao
	 */
	public Dragao getDragao(int i){
		return dragoes.get(i);
	}
	
	/**
	 * Gets the sword object.
	 *
	 * @return the sword object
	 */
	public Espada getEspada(){
		return espada;
	}
	
	/**
	 * Gets the char content on x,y position.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @return the coord
	 */
	public char getCoord(int x, int y){
		return tabuleiro[y][x];
	}
	
	/**
	 * Checks if every dragon is dead.
	 *
	 * @return true, if successful
	 */
	
	public boolean todosMortos(){
		int nmortos = 0;
		for(int i = 0;i < dragoes.size();i++){
			if(dragoes.get(i).getMorto() == 1)
				nmortos++;
		}
		if(nmortos == dragoes.size())
			return true;
		else return false;
	}
	
	/**
	 * Gets the end state.
	 *
	 * @return the end state
	 */
	public int getFim(){
		return fim;
	}
	
	/**
	 * Sets the char content in the x,y position.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param symbol the symbol
	 */
	public void setSquare(int x, int y, char symbol){
		tabuleiro[y][x] = symbol;
	}
	
	/**
	 * Sets the game mode.
	 *
	 * @param modo the new game mode
	 */
	public void setModo(int modo){
		if(modo < 1 || modo > 3)
			return;
		this.modojogo = modo;
	}
	
	/**
	 * Gets the game mode.
	 *
	 * @return the game mode
	 */
	public int getModo(){
		return modojogo;
	}
	
	/**
	 * Prints the board.
	 *
	 * @return the string
	 */
	public String printBoard(){
		
		String tmp = "";
		
		for(int i = 0;i < tabuleiro.length; i++){
			for(int j= 0;j < tabuleiro[i].length;j++){			
				tmp = tmp + getSquare(i,j) + ' ';
			}
			tmp += '\n';
		}
		return tmp;
	}
	
	/**
	 * Gets the char content in the x,y position.
	 *
	 * @param y the y position
	 * @param x the x position
	 * @return the char content
	 */
	public char getSquare(int y, int x){
		for(int i = 0;i < dragoes.size();i++){
			if(dragoes.get(i).getx() == x && dragoes.get(i).gety() == y && dragoes.get(i).getMorto() == 0){
				if(dragoes.get(i).getDormir() > 0){
					if(espada.getx() == x && espada.gety() == y && espada.getCapturada() == 0){
						return 'f';
					}
					else return 'd';
				}
				else{
					if(espada.getx() == x && espada.gety() == y && espada.getCapturada() == 0){
						return 'F';
					}
					else return 'D';
				}
			}
		}
		if(heroi.getx() == x && heroi.gety() == y && heroi.getEspada() == 1){
			return 'A';
		}
		if(heroi.getx() == x && heroi.gety() == y && heroi.getEspada() == 0){
			return 'H';
		}
		if(espada.getx() == x && espada.gety() == y && espada.getCapturada() == 0){
			return 'E';
		}
		return tabuleiro[y][x];
	}

	/**
	 * Instantiates a new Tabuleiro.
	 *
	 * @param board the board
	 */
	public Tabuleiro(char[][] board){

		fim = 0;
		tabuleiro = board;
        dragoes = new ArrayList<Dragao>();
		for(int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++){
				
				if(board[i][j] == 'H'){
					heroi = new Heroi(j,i,0);
					setSquare(j,i,' ');
				}
				
				if(board[i][j] == 'D'){
					Dragao dragao = new Dragao(j,i,0);
					dragoes.add(dragao);
					setSquare(j,i,' ');
				}
				
				if(board[i][j] == 'E'){
					espada = new Espada(j,i);
					setSquare(j,i,' ');
				}
				if(board[i][j] == 'A'){
					heroi = new Heroi(j,i,1);
					espada = new Espada(j,i);
					espada.setCapturada(1);
					setSquare(j,i,' ');
				}
			}
		if(heroi.getEspada() == 1)
			espada.setCapturada(1);
		setModo(2);
	}
	
	/**
	 * Move the dragons in a arbitrary direction.
	 */
	public void moverDragao(){
        if(modojogo == 1)
        	return;
		for (int i = 0; i < dragoes.size(); i++) {
			int dir, dx, dy;
			int dormirOuMover = 0;
			if(modojogo == 2)
				dormirOuMover = 99;
			else  if(modojogo == 3) dormirOuMover = (int) (Math.random() * 100 + 0.5);// se <= 30 o
																	            // dragao
																	           // adormece
																	           // , se
																	           // entre 30
																	           // e 100
																	           // move-se
			if (dormirOuMover <= 30 && dragoes.get(i).getDormir() == 0) {
				dragoes.get(i).setDormir((int) (Math.random() * 10 + 0.5));
			} else
				while (dragoes.get(i).getDormir() == 0) {

					dx = 0;
					dy = 0;

					dir = (int) (Math.random() * 3 + 0.5);// gera numero entre 0
															// e 3

					if (dir == 0)
						dx -= 1;
					if (dir == 1)
						dx += 1;
					if (dir == 2)
						dy -= 1;
					if (dir == 3)
						dy += 1;

					// como os limites do tabuleiro s�o paredes,
					// n�o � necess�rio verificar se a nova posi��o
					// do drag�o sai fora dos limites do tabuleiro

					if (tabuleiro[dragoes.get(i).gety() + dy][dragoes.get(i).getx() + dx] != 'X'
							&& tabuleiro[dragoes.get(i).gety() + dy][dragoes.get(i).getx() + dx] != 'S') {
						dragoes.get(i).setxy(dragoes.get(i).getx() + dx, dragoes.get(i).gety() + dy);
						break;
					}

				}
		}
	}
	
	/**
	 * Updates the game state.
	 */
	public void verificarJogo(){
		
		if(tabuleiro[heroi.gety()][heroi.getx()] == 'S' && todosMortos())
		{
			fim = 1;
		}
		if(heroi.getx() == espada.getx() && heroi.gety() == espada.gety())
		{
			espada.setCapturada(1);
			heroi.setEspada(1);
		}
		for (int i = 0; i < dragoes.size(); i++) {
			if (((Math.abs(heroi.getx() - dragoes.get(i).getx()) <= 1 && heroi.gety() == dragoes.get(i).gety()) || (Math.abs(heroi.gety() - dragoes.get(i).gety()) <= 1 && heroi.getx() == dragoes.get(i).getx()))
					&& heroi.getEspada() == 0 && dragoes.get(i).getDormir() == 0) {
				fim = 2;
			}
		}
		for (int i = 0; i < dragoes.size(); i++) {
			if (((Math.abs(heroi.getx() - dragoes.get(i).getx()) <= 1 && heroi.gety() == dragoes.get(i).gety()) || (Math.abs(heroi.gety() - dragoes.get(i).gety()) <= 1 && heroi.getx() == dragoes.get(i).getx()))
					&& heroi.getEspada() == 1) {
				dragoes.get(i).setMorto(1);
				System.out.println("DRAGON SLAIN");
			}
		}
		for (int i = 0; i < dragoes.size(); i++) {
			if (dragoes.get(i).getDormir() > 0) {
				dragoes.get(i).setDormir(dragoes.get(i).getDormir() - 1);
			}
		}
	}
	
	/**
	 * Moves the hero in a certain direction.
	 * 1 - up
	 * 2 - down
	 * 3 - left
	 * 4 - right
	 *
	 * @param r the r
	 * @return the int
	 */
	public int moverHeroi(int r){
		if(r > 0 && r <= 4){

			int dx = 0;
			int dy = 0;

			if( r == 1)	
				dy -= 1;
			if( r == 2)
				dy += 1;
			if(r == 3)
				dx -=1;
			if(r == 4)
				dx +=1;

			int jogadaterminada = 0;


			if(tabuleiro[heroi.gety()+dy][heroi.getx()+dx] == ' ' && jogadaterminada == 0 && fim == 0){					
				heroi.moverHeroi(r);
				jogadaterminada = 1;
			}

			if(tabuleiro[heroi.gety()+dy][heroi.getx()+dx] == 'S' && todosMortos() && jogadaterminada == 0 && fim == 0)
			{
				heroi.moverHeroi(r);
				jogadaterminada = 1;
			}
			if(jogadaterminada == 1)
				return 1;
			return 0;
		}

		else
			return 0;

	}		
}
