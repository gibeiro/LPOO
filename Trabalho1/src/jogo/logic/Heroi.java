package jogo.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroi.
 */
public class Heroi extends Unidade{
	
	/** The sword state. 
	 * 0 - doesn't have sword
	 * 1 - has sword
	 * */
	private int espada;//0 se heroi nao tem espada, 1 se tem
	
	

	/**
	 * Instantiates a new hero object.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param espada the sword state
	 */
	public Heroi(int x,int y,int espada){
		this.espada = espada;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move hero in one od four directions.
	 * 0 - up
	 * 1 - down
	 * 2 - left
	 * 3 - right
	 *
	 * @param r the direction
	 */
	public void moverHeroi(int r){
		if(r == 1)
			y -= 1;
		if(r == 2)
			y += 1;
		if(r == 3)
			x -= 1;
		if(r == 4)
			x += 1;
	}
	
	/**
	 * Gets the sword state.
	 *
	 * @return the espada
	 */
	public int getEspada(){
		return espada;
	}
	
	/**
	 * Sets the sword state.
	 *
	 * @param espada the new espada
	 */
	public void setEspada(int espada){
		
		this.espada = espada;
	}
}