package jogo.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Dragao.
 */
public class Dragao extends Unidade{
	
	/** The dead state. 
	 * 0 - alive
	 * 1 - dead
	 * */
	private int morto;
	
	/** The dragon's sleep counter. */
	private int counterDormir;
	
	/**
	 * Instantiates a new dragon.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param morto the dead state
	 */
	public Dragao(int x, int y,int morto)
	{
		this.morto = morto;
		this.x = x;
		this.y = y;
		this.counterDormir = 0;
	}
	
	/**
	 * Gets the dead state.
	 *
	 * @return the morto
	 */
	public int getMorto()//obter se o dragao esta morto(0) ou vivo(1)
	{return morto;}
	
	/**
	 * Gets the sleep coutner.
	 *
	 * @return the dormir
	 */
	public int getDormir(){return counterDormir;}
	
	/**
	 * Sets the dead state.
	 *
	 * @param morto the new morto
	 */
	public void setMorto(int morto)//alterar estado do dragao
	{this.morto = morto;}

	/**
	 * Sets the dragon's position.
	 *
	 * @param x the x position
	 * @param y the y position
	 */
	public void setxy(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the sleep counter.
	 *
	 * @param dormir the new dormir
	 */
	public void setDormir(int dormir){counterDormir = dormir;}
}