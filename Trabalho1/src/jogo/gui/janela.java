package jogo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jogo.logic.Labirinto;
import jogo.logic.Tabuleiro;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.OutputStream;
//import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class janela.
 */
public class janela extends JFrame{

	/** The content pane. */
	private JPanel contentPane;
	
	/** The board dimension text field. */
	private JTextField dimensao_textField;
	
	/** The nummber of dragons text field. */
	private JTextField nrDragoes_textField;
	
	/** The Tabuleiro game logic object. */
	private Tabuleiro tabuleiro;

	/** The game's graphics JFrame. */
	private JFrame jogografico;
	
	/** The game's editor JFrame. */
	private JFrame editor;
	
	/** The playing editor. */
	private static int playingEditor = 0;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						
					}
					janela frame = new janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Instantiates a new janela.
	 */
	
	public janela() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dimensao_label = new JLabel("Dimensão:");
		dimensao_label.setBounds(22, 24, 124, 14);
		contentPane.add(dimensao_label);

		
		dimensao_textField = new JTextField();
		dimensao_textField.setBounds(138, 21, 86, 20);
		contentPane.add(dimensao_textField);
		dimensao_textField.setColumns(10);
		dimensao_textField.setText("11");

		nrDragoes_textField = new JTextField();
		nrDragoes_textField.setBounds(138, 52, 86, 20);
		contentPane.add(nrDragoes_textField);
		nrDragoes_textField.setColumns(10);
		nrDragoes_textField.setText("1");		

		JLabel nrDragoes_label = new JLabel("Nº Dragoes:");
		nrDragoes_label.setBounds(22, 55, 105, 14);
		contentPane.add(nrDragoes_label);

		JLabel tipoDragao_label = new JLabel("Comportamento:");
		tipoDragao_label.setBounds(22, 86, 97, 14);
		contentPane.add(tipoDragao_label);

		final JComboBox tipoDragao_comboBox = new JComboBox<String>();
		tipoDragao_comboBox.setBounds(138, 83, 86, 20);
		contentPane.add(tipoDragao_comboBox);
		tipoDragao_comboBox.addItem("Estatico");
		tipoDragao_comboBox.addItem("Dinamico");
		tipoDragao_comboBox.addItem("Misto");

		final JTextArea tabuleiro_textArea = new JTextArea();
		tabuleiro_textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		tabuleiro_textArea.setBounds(22, 158, 0, 0);		
		contentPane.add(tabuleiro_textArea);	

		final JLabel estado_label = new JLabel("Estado");
		estado_label.setBounds(22, 133, 387, 14);
		estado_label.setText("Insira os dados pertendidos e selecione uma opção.");
		contentPane.add(estado_label);

		JButton terminar_botao = new JButton("Terminar");
		terminar_botao.setBounds(250, 20, 99, 23);
		contentPane.add(terminar_botao);
		terminar_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		final JButton cima_botao = new JButton("Cima");
		cima_botao.setBounds(310, 192, 89, 23);
		contentPane.add(cima_botao);
		cima_botao.setVisible(false);


		final JButton baixo_botao = new JButton("Baixo");
		baixo_botao.setBounds(310, 243, 89, 23);
		contentPane.add(baixo_botao);
		baixo_botao.setVisible(false);


		final JButton esquerda_botao = new JButton("Esquerda");
		esquerda_botao.setVerticalAlignment(SwingConstants.TOP);
		esquerda_botao.setBounds(262, 218, 84, 23);
		contentPane.add(esquerda_botao);	
		esquerda_botao.setVisible(false);

		final JButton direita_botao = new JButton("Direita");
		direita_botao.setBounds(356, 218, 72, 23);
		contentPane.add(direita_botao);
		direita_botao.setVisible(false);

		baixo_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabuleiro.moverHeroi(2);
				tabuleiro.moverDragao();
				tabuleiro.verificarJogo();
				if(tabuleiro.getFim() == 0){
					tabuleiro_textArea.setText(tabuleiro.printBoard());
					jogografico.repaint();
				}
				else{
					tabuleiro_textArea.setText("");
					jogografico.dispose();
					if(tabuleiro.getFim() == 1)
						estado_label.setText("Ganhou o jogo!");
					else
						estado_label.setText("Faleceu!");
					cima_botao.setEnabled(false);
					baixo_botao.setEnabled(false);
					esquerda_botao.setEnabled(false);
					direita_botao.setEnabled(false);
				}	
			}
		});



		cima_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabuleiro.moverHeroi(1);
				tabuleiro.moverDragao();
				tabuleiro.verificarJogo();
				if(tabuleiro.getFim() == 0){
					tabuleiro_textArea.setText(tabuleiro.printBoard());
					jogografico.repaint();
				}
				else{
					tabuleiro_textArea.setText("");
					jogografico.dispose();
					if(tabuleiro.getFim() == 1){
						estado_label.setText("Ganhou o jogo!");
					}
					else
						estado_label.setText("Faleceu!");
					cima_botao.setEnabled(false);
					baixo_botao.setEnabled(false);
					esquerda_botao.setEnabled(false);
					direita_botao.setEnabled(false);
				}	


			}
		});

		esquerda_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabuleiro.moverHeroi(3);
				tabuleiro.moverDragao();
				tabuleiro.verificarJogo();
				if(tabuleiro.getFim() == 0){
					tabuleiro_textArea.setText(tabuleiro.printBoard());
					jogografico.repaint();
				}
				else{
					tabuleiro_textArea.setText("");
					jogografico.dispose();
					if(tabuleiro.getFim() == 1)
						estado_label.setText("Ganhou o jogo!");
					else
						estado_label.setText("Faleceu!");
					cima_botao.setEnabled(false);
					baixo_botao.setEnabled(false);
					esquerda_botao.setEnabled(false);
					direita_botao.setEnabled(false);
				}				

			}
		});

		direita_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabuleiro.moverHeroi(4);
				tabuleiro.moverDragao();
				tabuleiro.verificarJogo();
				if(tabuleiro.getFim() == 0){
					tabuleiro_textArea.setText(tabuleiro.printBoard());
					jogografico.repaint();
				}
				else{
					tabuleiro_textArea.setText("");
					jogografico.dispose();
					if(tabuleiro.getFim() == 1)
						estado_label.setText("Ganhou o jogo!");
					else
						estado_label.setText("Faleceu!");
					cima_botao.setEnabled(false);
					baixo_botao.setEnabled(false);
					esquerda_botao.setEnabled(false);
					direita_botao.setEnabled(false);
				}


			}
		});

		final JButton novoJogo_botao = new JButton("Novo Jogo");
		novoJogo_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(
						(Integer.parseInt(dimensao_textField.getText()) >= 7 &&
						Integer.parseInt(dimensao_textField.getText()) < 26 &&
						Integer.parseInt(dimensao_textField.getText()) % 2 != 0 &&
						Integer.parseInt(nrDragoes_textField.getText()) > 0 &&
						Integer.parseInt(nrDragoes_textField.getText()) < Math.pow(Integer.parseInt(dimensao_textField.getText()), 2) / 40) || 
						(playingEditor == 1)
					)
				{
					if(jogografico != null)
						jogografico.dispose();
					if(editor != null)
						editor.dispose();
					if(playingEditor == 0)
					    tabuleiro = new Tabuleiro(Labirinto.geraLabirinto(Integer.parseInt(dimensao_textField.getText()), Integer.parseInt(nrDragoes_textField.getText())));
					else playingEditor = 0;
					tabuleiro_textArea.setBounds(22, 158, tabuleiro.getSize()*14, tabuleiro.getSize()*16);
					esquerda_botao.setBounds(262+tabuleiro.getSize()*14-200, 218, 84, 23);
					direita_botao.setBounds(356+tabuleiro.getSize()*14-200, 218, 72, 23);
					cima_botao.setBounds(310+tabuleiro.getSize()*14-200, 192, 89, 23);
					baixo_botao.setBounds(310+tabuleiro.getSize()*14-200, 243, 89, 23);
					setBounds(100, 100, 450+tabuleiro.getSize()*14-170, 400+tabuleiro.getSize()*16-185);
					tabuleiro_textArea.setText(tabuleiro.printBoard());
					tabuleiro.setModo(tipoDragao_comboBox.getSelectedIndex()+1);
					estado_label.setText("Pronto a jogar!");
					cima_botao.setVisible(true);
					baixo_botao.setVisible(true);
					esquerda_botao.setVisible(true);
					direita_botao.setVisible(true);
					cima_botao.setEnabled(true);
					baixo_botao.setEnabled(true);
					esquerda_botao.setEnabled(true);
					direita_botao.setEnabled(true);
					//desenha nova janela grafica para jogo

					jogografico = new JFrame();
					jogografico.setBounds(100, 100, 100+tabuleiro.getSize()*15, 100+tabuleiro.getSize()*15);
					jogografico.setPreferredSize(new Dimension(100+tabuleiro.getSize()*40, 100+tabuleiro.getSize()*40+100));
					//jogografico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JogoGrafico panel = new JogoGrafico(tabuleiro);
					jogografico.getContentPane().add(panel);
					panel.addKeyListener(new KeyListener() {
						@Override
						public void keyTyped(KeyEvent e) {

						}

						@Override
						public void keyPressed(KeyEvent e) {
							//System.out.println("x=" + x);
							switch(e.getKeyCode()){
							case KeyEvent.VK_LEFT: 
								esquerda_botao.doClick(1);
								break;

							case KeyEvent.VK_RIGHT:
								direita_botao.doClick(1);
								break;

							case KeyEvent.VK_UP: 
								cima_botao.doClick(1);	
								break;

							case KeyEvent.VK_DOWN: 
								baixo_botao.doClick(1);
								break;
							}



						}

						@Override
						public void keyReleased(KeyEvent e) {
						}			
					});
					jogografico.pack();
					jogografico.setVisible(true);
					panel.requestFocus();
				}
				else{
					estado_label.setText("Input Invalido!");
				}
			}
		});
		novoJogo_botao.setBounds(250, 51, 99, 23);
		contentPane.add(novoJogo_botao);

		

		final JButton gerarLabirinto_botao = new JButton("Criar Mapa");
		gerarLabirinto_botao.setBounds(250, 83, 99, 23);
		contentPane.add(gerarLabirinto_botao);
		gerarLabirinto_botao.setVisible(true);
		gerarLabirinto_botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(editor != null)
					editor.dispose();

				if(jogografico != null)
					jogografico.dispose();
				editor = new JFrame();
				final editor frame = new editor(Integer.parseInt(dimensao_textField.getText()), Integer.parseInt(nrDragoes_textField.getText()));

				editor.setBounds(100, 100, 100+Integer.parseInt(dimensao_textField.getText())*15, 100+Integer.parseInt(dimensao_textField.getText())*15);
				editor.setPreferredSize(new Dimension(100+Integer.parseInt(dimensao_textField.getText())*40, 100+Integer.parseInt(dimensao_textField.getText())*40+100));
				//editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				editor.getContentPane().add(frame);

				frame.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {

					}

					@Override
					public void keyPressed(KeyEvent e) {
						switch(e.getKeyCode()){
						case KeyEvent.VK_ENTER:
							if(frame.getTabuleiro() != null){
								tabuleiro = new Tabuleiro(frame.getTabuleiro());
								playingEditor = 1;
								editor.dispose();
								novoJogo_botao.doClick(1);
							}
							
							
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
												
					}
				});

				
			
				editor.pack();
				editor.setVisible(true);
				frame.requestFocus();

			}
		});

		
		

	}
}
