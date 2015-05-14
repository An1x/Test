package test;

import java.awt.Graphics;

import engine.interfaces.GameObjects;
import engine.util.Input;
import engine.util.State;
import engine.util.Vector2f;

public class Player implements GameObjects{

	private Vector2f position = new Vector2f(0, 0);
	private Vector2f target = new Vector2f(0, 0);
	
	private Vector2f mouse = new Vector2f();
	

	
	public Player() {
		//img = Image.getSubImage(Image.loadBufferedImage("/image/tiles.png"), 50, 50, 1, 9, 32, 40);
		
		
		position.set(100, 100);
		
		
		
	}
	
	
	@Override
	public void update() {
		//target.smoothDamp(position, 3, 1.22f);
		
		if(Input.isKeyDown(Input.W)) position.moveY(-4);
		if(Input.isKeyDown(Input.S)) position.moveY(4);
		
		if(Input.isKeyDown(Input.D)) position.moveX(4);
		if(Input.isKeyDown(Input.A)) position.moveX(-4);
		
		
		
		mouse.set(Input.getAxis("VERTICAL"), Input.getAxis("HORIZONTAL"));
		
		
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(img, (int)position.getX()- img.getWidth()/2 , (int)position.getY()- img.getHeight()/2 , null);
		
		
	}

	@Override
	public State getState() {
		return State.GAME;
	}

	

}
