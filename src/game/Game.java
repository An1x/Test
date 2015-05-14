package game;

import test.Player;
import engine.Engine;
import engine.util.Intro;
import engine.util.State;

public class Game {
	
	private Engine engine;
	
	private static State currentState;
	
	
	public Game(Engine engine) {
		this.engine = engine;
		initialize();
	}

	private void initialize() {
		currentState = State.INTRO;
		engine.addGameObject(new Intro("/music/level1-1.mp3", "/image/test.gif", 9));
		engine.addGameObject(new Player());
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	

}
