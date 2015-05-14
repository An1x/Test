package engine.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import engine.audio.AudioClip;
import engine.interfaces.GameObjects;
import game.Game;

public class Intro implements GameObjects {

	 private Image image;
	 private AudioClip clip;
	 private int giflength, currentLength;
	 
	public Intro(String musicPath, String gifPath, int gifLength) {
		this.giflength = gifLength;
		clip = new AudioClip(musicPath);
		clip.play();
		image = Toolkit.getDefaultToolkit().getImage(Image.class.getResource(gifPath));
	
		
	}

	@Override
	public void render(Graphics g) {
		if(clip.isRunning())
			g.drawImage(image, 0, 0, null);
	}

	@Override
	public State getState() {
		return State.INTRO;
	}

	@Override
	public void update() {
		if(++currentLength == giflength*60) {
			clip.stop();
			Game.setState(State.MENU);
		}
	}

}
