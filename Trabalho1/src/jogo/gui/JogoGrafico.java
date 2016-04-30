package jogo.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import jogo.logic.Tabuleiro;

// TODO: Auto-generated Javadoc
/**
 * The Class JogoGrafico.
 */
public class JogoGrafico extends JPanel {
    
    /** The Tabuleiro game's logic object. */
    private Tabuleiro t;
	
	/** The terrain texture. */
	private BufferedImage terrain;
	
	/** The wall texture. */
	private BufferedImage wall;
	
	/** The awake dragon texture. */
	private BufferedImage dragonawake;
	
	/** The sleeping dragon texture. */
	private BufferedImage dragonsleeping;
	
	/** The armed hero texture. */
	private BufferedImage heroarmed;
	
	/** The unarmed hero texture. */
	private BufferedImage herounarmed;
	
	/** The sword texture. */
	private BufferedImage sword;
	
	/** The key bindings. */
	private BufferedImage instructions;
	
	/**
	 * Instantiates a new jogo grafico.
	 *
	 * @param t the t
	 */
	/*
	 * Jogar jogo no modo janela
	 */
	public JogoGrafico(Tabuleiro t) {
		this.t = t;
        try {
			terrain =  ImageIO.read(new File("terrain.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			wall =  ImageIO.read(new File("wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			dragonawake =  ImageIO.read(new File("dragonawake.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			dragonsleeping =  ImageIO.read(new File("dragonsleeping.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			sword =  ImageIO.read(new File("sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			heroarmed =  ImageIO.read(new File("heroarmed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			herounarmed =  ImageIO.read(new File("herounarmed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
        	instructions = ImageIO.read(new File("mover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        repaint();
        
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	/*
	 * Pintar o jogo no modo janela
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0;i < t.getSize();i++){
			for(int j = 0;j <t.getSize();j++){
				g.drawImage(terrain, j*40, i*40, j*40+40, i*40+40, 0, 0, 35, 35, null);
				if(t.getSquare(i, j) == 'X'){
					g.drawImage(wall, j*40,i*40,j*40+40,i*40+40,0,0,wall.getWidth(),wall.getHeight(),null);
				}
				if(t.getEspada().gety() == i && t.getEspada().getx() == j && t.getEspada().getCapturada() == 0){
					g.drawImage(sword, j*40,i*40,j*40+40,i*40+40,0,0,sword.getWidth(),sword.getHeight(),null);
				}
				if(t.getHeroi().getx() == j && t.getHeroi().gety() == i && t.getHeroi().getEspada() == 0){
					g.drawImage(herounarmed, j*40,i*40,j*40+40,i*40+40,0,0,herounarmed.getWidth(),herounarmed.getHeight(),null);
				}
				if(t.getHeroi().getx() == j && t.getHeroi().gety() == i && t.getHeroi().getEspada() == 1){
					g.drawImage(heroarmed, j*40,i*40,j*40+40,i*40+40,0,0,heroarmed.getWidth(),heroarmed.getHeight(),null);
				}
				for(int k = 0;k < t.getNDragoes();k++){
					if(t.getDragao(k).getx() == j && t.getDragao(k).gety() == i && t.getDragao(k).getMorto() == 0 && t.getDragao(k).getDormir() == 0){
						g.drawImage(dragonawake, j*40,i*40,j*40+40,i*40+40,0,0,dragonawake.getWidth(),dragonawake.getHeight(),null);
					}
					if(t.getDragao(k).getx() == j && t.getDragao(k).gety() == i && t.getDragao(k).getMorto() == 0 && t.getDragao(k).getDormir() > 0){
						g.drawImage(dragonsleeping, j*40,i*40,j*40+40,i*40+40,0,0,dragonsleeping.getWidth(),dragonsleeping.getHeight(),null);
					}
				}
			}
		}
		g.drawImage(instructions, 0, t.getSize()*40,instructions.getWidth(),t.getSize()*40+instructions.getHeight(), 0,0,instructions.getWidth(),instructions.getHeight(),null);
	}

}
