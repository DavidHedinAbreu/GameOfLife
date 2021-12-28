/*
 * Author: David Hedin-Abreu
 * Date 12/2021
 * Description: Prepares Game of Life window, parameters window, and controls window.
 * Defines actionPerformed for the various fields and buttons, using Animation class methods.
 */
 
package com.davidhedinabreu.gameoflife;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.awt.Color;

public class Window {
	
	private JFrame lifeFrame = new JFrame("Conway's Game of Life");
	private JFrame controlsFrame = new JFrame("Controls");
	private JFrame parametersFrame = new JFrame("Startup Parameters");
	private JTextArea gameState = new JTextArea();  
	private JButton backFast = new JButton("<<<-");
	private JButton backSlow = new JButton("<-");
	private JButton stop = new JButton("STOP");
	private JButton forwardSlow = new JButton("->");
	private JButton forwardFast = new JButton("->>>");
	// private JButton edit = new JButton("EDIT");
	private JLabel genLabel = new JLabel("Generation 0 (Start)");
	private JLabel numGenLabel = new JLabel("How many generations?");
	private JTextField numGenField = new JTextField("1000", 4);
	private JLabel hLabel = new JLabel("Horizontal width?");
	private JTextField hField = new JTextField("100", 3);
	private JLabel vLabel = new JLabel("Vertical depth?");
	private JTextField vField = new JTextField("50", 3);
	
	private int pace = 100, numGen = 1000, width = 100, height = 50, time = 0;  
	private Board b;
	private Animation a;
	private String introduction = 
	"Conway's Game of Life\n\n"
		+ "(Description taken from Wikipedia, the free encyclopedia.)\n"
		+ "The Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970. \n"
		+ "It is the best-known example of a cellular automaton. The \"game\" is actually a zero-player game, meaning \n"
		+ "that its evolution is determined by its initial state, needing no input from human players. One interacts \n"
		+ "with the Game of Life by creating an initial configuration and observing how it evolves. \n"
		+ "Rules: The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each \n"
		+ "of which is in one of two possible states, live or dead. Every cell interacts with its eight neighbours, \n"
		+ "which are the cells that are directly horizontally, vertically, or diagonally adjacent. At each step in time, \n"
		+ "the following transitions occur:\n"
		+ "\t*Any live cell with fewer than two live neighbors dies, as if by loneliness.\n"
		+ "\t*Any live cell with more than three live neighbors dies, as if by overcrowding.\n"
		+ "\t*Any live cell with two or three live neighbors lives, unchanged, to the next generation.\n"
		+ "\t*Any dead cell with exactly three live neighbors comes to life.\n"
		+ "The initial pattern constitutes the seed of the system. The next generation is created by applying the \n"
		+ "above rules simultaneously to every cell in the seed - births and deaths happen simultaneously, and the \n"
		+ "discrete moment at which this happens is sometimes called a tick. (In other words, each generation is a \n"
		+ "function of the one before.) The rules continue to be applied repeatedly to create further generations.\n\n"
		+ "An accessible history can be found here: http://www.scholarpedia.org/article/Game_of_Life\n\n"
		+ "I created this game for my son Samuel over our Christmas vacation in Crestline, December 2021. God bless you. \n"
		+ "To begin the game, submit a number of generations, and dimensions for the game (or use the suggested values). \n"
		+ "Next, press -> (play forward).\n\n"
		+ "\t-- David Hedin-Abreu (proud dad)";
		
	// Constructor
	public Window() {
				
		//add ActionListeners and FocusListeners
		backFast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(a != null) {
					a.stopAnimation();
					time = a.getTime();
				}
				pace = -100;
				if(parametersFrame.isVisible() )
					parametersFrame.setVisible(false);

				if (b == null) {
					try {
						startGOLWindow();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					a.animate(0, time, pace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		backSlow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(a != null) {
					a.stopAnimation();
					time = a.getTime();
				}
				pace = -1000;
				if(parametersFrame.isVisible() )
					parametersFrame.setVisible(false);
				if (b == null) {
					try {
						startGOLWindow();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					a.animate(0, time, pace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
				
		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(a != null) {
					a.stopAnimation();
					time = a.getTime();
				}
				pace = 0;
				if(parametersFrame.isVisible() )
					parametersFrame.setVisible(false);
				if (b == null) {
					try {
						startGOLWindow();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					a.animate(time, time, pace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		forwardSlow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(a != null) {
					a.stopAnimation();
					time = a.getTime();
				}
				pace = 1000;
				if(parametersFrame.isVisible() )
					parametersFrame.setVisible(false);
				if (b == null) {
					try {
						startGOLWindow();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					a.animate(time, numGen, pace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		forwardFast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(a != null) {
					a.stopAnimation();
					time = a.getTime();
				}
				pace = 100;
				if(parametersFrame.isVisible() ) 
					parametersFrame.setVisible(false);
				if (b == null) {
					try {
						startGOLWindow();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				try {
					a.animate(time, numGen, pace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		//edit.addActionListener(new ActionListener()
		//{
			//public void actionPerformed(ActionEvent e)
			//{
				//if(a != null) {
					//a.stopAnimation();
					//time = a.getTime();
				//}
				//if(parametersFrame.isVisible() ) 
					//parametersFrame.setVisible(false);
				//if (b == null) {
					//try {
						//startGOLWindow();
					//} catch (Exception ex) {
						//ex.printStackTrace();
					//}
				//}
				////lifeFrame.toFront();
				////lifeFrame.requestFocus();
				////lifeFrame.setAlwaysOnTop(true);
				////Editor ed = new Editor(b);

			//}
		//});
		
		
		numGenField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				int x;
				x = tryParseInt(numGenField.getText() );
				if (x <= 0 || x >10000)
					numGenField.setText("Try again.");
				else {
					numGen = x;
					numGenField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});

		numGenField.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent ev) {
				numGenField.setText("");
			}

			public void focusLost(FocusEvent ev) {
				int x;
				x = tryParseInt(numGenField.getText() );
				if (x <= 0 || x >10000)
					numGenField.setText("Try again.");
				else {
					numGen = x;
					numGenField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});
	
		hField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int x;
				x = tryParseInt(hField.getText() );
				if (x <= 3 || x > 200)
					hField.setText("Try again.");
				else {
					width = x;
					hField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});
		
		hField.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent ev) {
				hField.setText("");
			}

			public void focusLost(FocusEvent ev) {
				int x;
				x = tryParseInt(hField.getText() );
				if (x <= 3 || x > 200)
					hField.setText("Try again.");
				else {
					width = x;
					hField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});
	
		vField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int x;
				x = tryParseInt(vField.getText() );
				if (x <= 0 || x >10000)
					vField.setText("Try again.");
				else {
					height = x;
					vField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});

		vField.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent ev) {
				vField.setText("");
			}

			public void focusLost(FocusEvent ev) {
				int x;
				x = tryParseInt(vField.getText() );
				if (x <= 0 || x >10000)
					vField.setText("Try again.");
				else {
					height = x;
					vField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12) );
				}
			}
		});
	
		// frame containing controls
		genLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12) );
		backSlow.setForeground(Color.yellow);
		backFast.setBackground(Color.YELLOW);
		backFast.setOpaque(true);
		stop.setForeground(Color.red);
		forwardSlow.setForeground(Color.green);
		forwardFast.setBackground(Color.GREEN);
		forwardFast.setOpaque(true);
		controlsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlsFrame.setLayout(new GridLayout(1,8,10,15));
		controlsFrame.add(backFast);
		controlsFrame.add(backSlow);
		controlsFrame.add(stop);
		controlsFrame.add(forwardSlow);
		controlsFrame.add(forwardFast);
		controlsFrame.add(forwardFast);
		// controlsFrame.add(edit);
		controlsFrame.add(genLabel);
		controlsFrame.setLocation(10, 10);
		controlsFrame.pack();
		controlsFrame.setVisible(true);

		// frame containing parameters for startup
		numGenLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12) );
		hLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12) );
		vLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12) );
		parametersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parametersFrame.setLayout(new GridLayout(2,3,10,15));
		parametersFrame.add(numGenLabel);
		parametersFrame.add(hLabel);
		parametersFrame.add(vLabel);
		parametersFrame.add(numGenField);
		parametersFrame.add(hField);
		parametersFrame.add(vField);
		parametersFrame.setLocation(controlsFrame.getWidth() + 50, 10);
		parametersFrame.pack();
		parametersFrame.setVisible(true);
		
		// setup for Game of Life frame (set visible when forward pressed)
		// Adds mouse listeners to GOL frame.
		gameState.setLineWrap(false);
		gameState.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12) );
		lifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lifeFrame.setLocation(10, parametersFrame.getHeight() + 20);
		gameState.setText(introduction );
		lifeFrame.add(gameState);
		lifeFrame.pack();
		lifeFrame.setVisible(true);
	}
	
	private int tryParseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public void startGOLWindow() {
		b = new Board(width, height, numGen);
		a = new Animation(b, this);
		gameState.setText(b.getBoardStateString(0) );
		lifeFrame.add(gameState);
		lifeFrame.pack();
		lifeFrame.setVisible(true);

		//lifeFrame.addMouseListener(new MouseListener()
		//{
			//@Override
			//public void mouseClicked(MouseEvent ev) {
				//System.out.println(ev.getX() + ", " + ev.getY() );
			//}
			//@Override
			//public void mousePressed(MouseEvent ev) {
				//System.out.println(ev.getX() + ", " + ev.getY() );
			//}
			//@Override
			//public void mouseReleased(MouseEvent ev) {
				//System.out.println(ev.getX() + ", " + ev.getY() );
			//}
			//@Override
			//public void mouseEntered(MouseEvent ev) {
				//System.out.println("mouse entered GOL frame");
			//}
			//@Override
			//public void mouseExited(MouseEvent ev) {
				//System.out.println("mouse exited GOL frame");
			//}
		//});
		

	}
	
	public void updateGOLWindow(String contents, int time, int pace) {
		try{
			Thread.sleep(pace);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		gameState.setText(contents);
		genLabel.setText("Generation " + time);
	}
	
	public int getNumGen() {
		return numGen;
	}
}
