package test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Main;
import engine.image.Image;
import engine.interfaces.GameObjects;
import engine.util.Input;
import engine.util.State;
import engine.util.Vector2f;

public class Player implements GameObjects{

	private Vector2f position = new Vector2f(Main.WIDTH/2, Main.HEIGHT/2);
	private BufferedImage img = Image.getSubImage(Image.loadBufferedImage("/image/playersprites.gif"), 30, 30, 1, 1, 30, 30);
	
	public Vector2f getPosition() {
		return position;
	}


	public void setPosition(Vector2f position) {
		this.position = position;
	}


	public Player() {
		
		
		
		
		
		
		
	}
	
	
	@Override
	public void update() {
		//target.smoothDamp(position, 3, 1.22f);
		
		if(Input.isKeyDown(Input.W)) position.moveY(-4);
		if(Input.isKeyDown(Input.S)) position.moveY(4);
		
		if(Input.isKeyDown(Input.D)) position.moveX(4);
		if(Input.isKeyDown(Input.A)) position.moveX(-4);
		
		
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)position.getX(), (int)position.getY(), null);
		
		
	}

	@Override
	public State getState() {
		return State.GAME;
	}

	

}
