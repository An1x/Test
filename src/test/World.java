package test;

import java.awt.Graphics;
import java.util.List;
import java.awt.image.BufferedImage;

import main.Main;
import engine.Engine;
import engine.image.Image;
import engine.interfaces.GameObjects;
import engine.util.State;
import engine.util.Vector2f;

public class World implements GameObjects {

	private BufferedImage img;
	private Vector2f pos;
	
	
	public World() {
	
		img = Image.loadBufferedImage("/image/hintergrund.jpg");
		pos = new Vector2f(0, 0);
	}
	
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)pos.x, (int)pos.y, null);
	}

	@Override
	public void update() {
		List<GameObjects> go = Engine.getEngine().getObjectList();
		for(GameObjects g : go) {
			if(g instanceof Player) {
				Player p = (Player)g;
				Vector2f delta = Vector2f.substract(new Vector2f(Main.WIDTH/2, Main.HEIGHT/2), p.getPosition());
				p.setPosition(Vector2f.add(p.getPosition(), delta));
				pos = Vector2f.add(pos, delta);
				
				break;
			}
		}
	}
	@Override
	public State getState() {
		
		return State.GAME;
	}


}
