package jogo.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Espada.
 */
public class Espada extends Unidade{

	/** The captured state. 
	 * 0 - on the ground
	 * 1 - captured
	 * */
	private int capturada;
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param x the x position
	 * @param y the y position
	 */
	public Espada(int x, int y)
	{
		this.capturada = 0;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the captured state.
	 *
	 * @return the capturada
	 */
	public int getCapturada(){
		return capturada;
	}
	
	/**
	 * Sets the captured state.
	 *
	 * @param b the new captured state
	 */
	public void setCapturada(int b){
		this.capturada = b;
	}
}
