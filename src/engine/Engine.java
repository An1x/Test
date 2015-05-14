package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.List;

import main.Main;
import engine.interfaces.GameObjects;
import engine.interfaces.Renderable;
import engine.interfaces.Updateable;
import engine.util.Input;
import game.Game;

public class Engine extends Canvas {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean isRunning;
	private boolean debug = true;
	private long deltaTime = (long)(1e9 / 60);
	
	private int FPS;
	private int UPS;

	private List<Renderable> renderList = new LinkedList<>();
	private List<Updateable> updateList = new LinkedList<>();
	private List<GameObjects> objectList = new LinkedList<>();
	
	public List<GameObjects> getObjectList() {
		return objectList;
	}

	private Main main;
	private Game game;
	private Input input;

	private static Engine engine;
	
	
	public Engine(Main main) {
		this.main = main;
		this.engine = this;
	}
	
	private void initialized() {
		game = new Game(this);
		input = new Input();

		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}
	
	private void update() {
		for (Updateable updateObject : updateList) {
			if(game.getCurrentState() == updateObject.getState())
				updateObject.update();
		}
		
		for (GameObjects gameObjects : objectList) {
			if(game.getCurrentState() == gameObjects.getState())
				gameObjects.update();
		}
		
		if(debug)
			main.setTitle(" |  fps: " + FPS + " | ups: " + UPS);
	}
	
	
	private void render(Graphics g) {
		for (Renderable renderObject : renderList) {
			if(game.getCurrentState() == renderObject.getState())
				renderObject.render(g);
		}
		
		for (GameObjects gameObjects : objectList) {
			if(game.getCurrentState() == gameObjects.getState())
				gameObjects.render(g);
		}
	}
	
	public void addRenderObject(Renderable r) {
		renderList.add(r);
	}
	
	public void addUpdateObject(Updateable u) {
		updateList.add(u);
	}
	
	public void addGameObject(GameObjects g) {
		objectList.add(g);
	}


	private void work() {
		initialized();
		
		int fps = 0;
		int ups = 0;
		
		long nowTime = System.nanoTime();
		long lastTime = nowTime;
		
		double updatesLeft = 0;
		double debugReset = 0;
		while(isRunning) {
			nowTime = System.nanoTime();
			
			// Update
			updatesLeft += (double)(nowTime - lastTime) / deltaTime;
			while(updatesLeft >=1) {
				update();
				updatesLeft--;
				ups++;
			}
			
			// Render
			BufferStrategy bs = getBufferStrategy();
			if(bs == null) {
				createBufferStrategy(2);
				continue;
			}
			
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			render(g);
			bs.show();
			fps++;
			
			// Debug
			debugReset += (nowTime - lastTime) / 1e9;
			if(debugReset >=1) {
				FPS = fps;
				UPS = ups;
				fps = ups = 0;
				debugReset %= 1;
			}
			
			lastTime = nowTime;
		}
	}
	
	public void start() {
		if(!isRunning && isDisplayable()) {
			isRunning = true;
			thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					work();
				}
			});
			thread.start();
		}
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public void stopAndWait() {
        stop();
        try {
            if (thread != null) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public int getUPS() {
        return UPS;
    }
	
	public void setUPS(int ups) {
        deltaTime = (long)1e9 / ups;
    }
	
	public int getFPS() {
	        return FPS;
	}
	
	public static Engine getEngine() {
		return engine;
	}
}
