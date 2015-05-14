package engine.interfaces;

import java.awt.Graphics;

import engine.util.State;

public interface Renderable {

	void render(Graphics g);
	State getState();
}
