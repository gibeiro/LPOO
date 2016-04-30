package jogo.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class editor.
 */
public class editor extends JPanel {

	/** The selected texture.
	 * 1 - relva
	 * 2 - parede
	 * 3 - heroi
	 * 4 - espada
	 * 5 - dragao
	 *  */
	private char selecionado;//qual o tipo de bloco selecionado 1 relva 2 parede 3 heroi 4 espada 5 dragao
	
	/** The terrain texture. */
	private BufferedImage terrain;
	
	/** The wall texture. */
	private BufferedImage wall;
	
	/** The awake dragon texture. */
	private BufferedImage dragonawake;
	
	/** The unarmed hero texture. */
	private BufferedImage herounarmed;
	
	/** The sword texture. */
	private BufferedImage sword;
	
	/** The armed hero texture. */
	private BufferedImage heroarmed;
	
	/** The key bindings. */
	private BufferedImage instructions;
	
	/** The board array of array of chars. */
	private char[][] tabuleiro;
	
	/** The number of dragons. */
	private int nr_dragoes;

	/**
	 * Gets the tabuleiro.
	 *
	 * @return the tabuleiro
	 */
	public char[][] getTabuleiro(){
		int nherois = 0;
		int ndragoes = 0;
		int nespadas = 0;
		int nsaidas = 0;
		for(int i = 0;i < tabuleiro.length;i++){
			for(int j = 0;j < tabuleiro.length;j++){
				if(tabuleiro[i][j] == 'A'){
					nherois++;
					nespadas++;
				}
				if(tabuleiro[i][j] == 'H'){
					nherois++;
				}
				if(tabuleiro[i][j] == 'E'){
					nespadas++;
				}
				if(tabuleiro[i][j] == 'D'){
					ndragoes++;
				}
				if(tabuleiro[i][j] == 'S'){
					nsaidas++;
				}
				
			}
		}
		if(nespadas == 0 || ndragoes > nr_dragoes || nherois == 0 || nherois > 1 || nsaidas == 0)
			return null;
		else return tabuleiro;
	}
	
	/**
	 * Instantiates a new editor.
	 *
	 * @param dimensao the dimension of the board
	 * @param nr_dragoes the number of dragons
	 */
	public editor(final int dimensao, int nr_dragoes) {

		this.selecionado = 0;
		this.nr_dragoes = nr_dragoes;
		tabuleiro = new char[dimensao][dimensao];

		for(int i = 0; i < dimensao; i++)
			for(int j = 0; j < dimensao; j++)
				if(
						i == 0 ||
						j == 0 ||
						i == dimensao - 1 ||
						j == dimensao - 1
						)
					tabuleiro[i][j] = 'X';
				else
					tabuleiro[i][j] = ' ';				

		try {
			terrain =  ImageIO.read(new File("terrain.png"));
			wall =  ImageIO.read(new File("wall.png"));
			dragonawake =  ImageIO.read(new File("dragonawake.png"));
			sword =  ImageIO.read(new File("sword.png"));
			herounarmed =  ImageIO.read(new File("herounarmed.png"));
			heroarmed = ImageIO.read(new File("heroarmed.png"));
			instructions = ImageIO.read(new File("editor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint(); 
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if((e.getY()/40 == 0 || e.getY()/40 == dimensao-1 || e.getX()/40 == 0 || e.getX()/40 == dimensao-1) && (selecionado == 'S' || selecionado == 'X'))
					tabuleiro[e.getY() / 40][e.getX() / 40] = selecionado;
				if(e.getY()/40 > 0 && e.getY()/40 < dimensao-1 &&e.getX()/40 > 0 && e.getX()/40 < dimensao-1 && selecionado != 'S')
					if((selecionado == 'H' && tabuleiro[e.getY()/40][e.getX()/40] == 'E') || (selecionado == 'E' && tabuleiro[e.getY()/40][e.getX()/40] == 'H'))
						tabuleiro[e.getY() / 40][e.getX() / 40] = 'A';
					else tabuleiro[e.getY() / 40][e.getX() / 40] = selecionado;
				repaint();
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		});
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_1:
					selecionado = ' ';
					break;
				case KeyEvent.VK_2:
					selecionado = 'X';
					break;
				case KeyEvent.VK_3:
					selecionado = 'H';
					break;
				case KeyEvent.VK_4:
					selecionado = 'E';
					break;
				case KeyEvent.VK_5:
					selecionado = 'D';
					break;
				case KeyEvent.VK_6:
					selecionado = 'S';
					break;
				
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
										
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("paint editor");
		
		super.paintComponent(g);
		BufferedImage tmp = null;
		for(int i = 0;i < tabuleiro.length;i++)
			for(int j = 0;j < tabuleiro.length;j++)
		         g.drawImage(terrain, j*40, i*40, j*40+40, i*40+40, 0, 0, 35, 35, null);
		
		for(int i = 0;i < tabuleiro.length;i++){
			for(int j = 0;j < tabuleiro.length;j++){
				
				switch(tabuleiro[i][j]){
				case ' ':
					break;
				case 'X':
					tmp = wall;
					g.drawImage(tmp, j*40, i*40, j*40+40, i*40+40, 0, 0, tmp.getWidth(), tmp.getHeight(), null);
					break;
				case 'E':
					tmp = sword;
					g.drawImage(tmp, j*40, i*40, j*40+40, i*40+40, 0, 0, tmp.getWidth(),  tmp.getHeight(), null);
					break;
				case 'H':
					tmp = herounarmed;
					g.drawImage(tmp, j*40, i*40, j*40+40, i*40+40, 0, 0, tmp.getWidth(),  tmp.getHeight(), null);
					break;
				case 'D':
					tmp = dragonawake;
					g.drawImage(tmp, j*40, i*40, j*40+40, i*40+40, 0, 0, tmp.getWidth(),  tmp.getHeight(), null);
					break;
				case 'A':
					tmp = heroarmed;
					g.drawImage(tmp, j*40, i*40, j*40+40, i*40+40, 0, 0, tmp.getWidth(),  tmp.getHeight(), null);
					break;
				}
				
			}
		}
		g.drawImage(instructions, 0, tabuleiro.length*40,instructions.getWidth(),tabuleiro.length*40+instructions.getHeight(), 0,0,instructions.getWidth(),instructions.getHeight(),null);
	}
	

}
