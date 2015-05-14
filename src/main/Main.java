package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import engine.Engine;

public class Main {
	
	public static final int WIDTH = 1366, HEIGHT = WIDTH /16 * 9;
	private static final String TITLE = "2D Engine";

	private JFrame frame;
	
	public Main() {
		
		frame = new JFrame(TITLE);
		Engine engine = new Engine(this);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(engine);
		frame.setVisible(true);
		engine.start();
	}
	
	public void setTitle(String title) {
		frame.setTitle(TITLE + " " + title);
	}

	public static void main(String[] args) {
		new Main();
	}
}
