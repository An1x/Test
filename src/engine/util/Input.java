package engine.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private static int VERTICAL, HORIZONTAL;
	private static boolean[] KEYCODE = new boolean[1024];
	private static boolean[] KEYCODE_CLICKED = new boolean[1024];
	private static boolean[] MOUSE_BUTTON = new boolean[4];
	private static boolean[] MOUSE_BUTTON_CLICKED = new boolean[4];
	
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	
	public static final int SPACE = KeyEvent.VK_SPACE;
	public static final int ENTER = KeyEvent.VK_ENTER;
	public static final int SHIFT = KeyEvent.VK_SHIFT;
	public static final int CAPSLOCK = KeyEvent.VK_CAPS_LOCK;
	public static final int CTRL = KeyEvent.VK_CONTROL;
	public static final int ALT = KeyEvent.VK_ALT;
	public static final int TAB = KeyEvent.VK_TAB;
	public static final int CLEAR = KeyEvent.VK_CLEAR;
	
	public static final int NUM_0= KeyEvent.VK_0;
	public static final int NUM_1 = KeyEvent.VK_1;
	public static final int NUM_2 = KeyEvent.VK_2;
	public static final int NUM_3 = KeyEvent.VK_3;
	public static final int NUM_4 = KeyEvent.VK_4;
	public static final int NUM_5 = KeyEvent.VK_5;
	public static final int NUM_6 = KeyEvent.VK_6;
	public static final int NUM_7 = KeyEvent.VK_7;
	public static final int NUM_8 = KeyEvent.VK_8;
	public static final int NUM_9 = KeyEvent.VK_9;
	
	public static final int A = KeyEvent.VK_A;
	public static final int B = KeyEvent.VK_B;
	public static final int C = KeyEvent.VK_C;
	public static final int D = KeyEvent.VK_D;
	public static final int E = KeyEvent.VK_E;
	public static final int F = KeyEvent.VK_F;
	public static final int G = KeyEvent.VK_G;
	public static final int H = KeyEvent.VK_H;
	public static final int I = KeyEvent.VK_I;
	public static final int J = KeyEvent.VK_J;
	public static final int K = KeyEvent.VK_K;
	public static final int L = KeyEvent.VK_L;
	public static final int M = KeyEvent.VK_M;
	public static final int N = KeyEvent.VK_N;
	public static final int O = KeyEvent.VK_O;
	public static final int P = KeyEvent.VK_P;
	public static final int Q = KeyEvent.VK_Q;
	public static final int R = KeyEvent.VK_R;
	public static final int S = KeyEvent.VK_S;
	public static final int T = KeyEvent.VK_T;
	public static final int U = KeyEvent.VK_U;
	public static final int V = KeyEvent.VK_V;
	public static final int W = KeyEvent.VK_W;
	public static final int X = KeyEvent.VK_X;
	public static final int Y = KeyEvent.VK_Y;
	public static final int Z = KeyEvent.VK_Z;
	
	
	
	
	/**
	 * @param axis either "HORIZONTAL" or "VERTICAL".
	 * @return the VERTICAL or HORIZONTAL Mouse Position as a float.
	 * @throws IllegalArgumentException.
	 */
	public static int getAxis(String axis) throws IllegalArgumentException {
		if(!axis.equals("HORIZONTAL") && !axis.equalsIgnoreCase("VERTICAL"))
			throw new IllegalArgumentException("[" + axis + "] is a wrong parameter use [HORIZONTAL] or [VERTICAL]");
		return (axis.equalsIgnoreCase("HORIZONTAL")) ? HORIZONTAL : VERTICAL;
	}
	
	/**
	 * Returns true during the frame the user pressed the given mouse button.
	 * @param button
	 * @throws IllegalArgumentException
	 */
	public static boolean isMouseButtonDown(String button) throws IllegalArgumentException {
		if(button.equals("LEFT") || button.equals("MIDDLE") || button.equals("RIGHT")) {
			switch (button) {
				case "LEFT": return MOUSE_BUTTON[1];
				case "MIDDLE": return MOUSE_BUTTON[2];
				case "RIGHT": return MOUSE_BUTTON[3];
			}
			return false;
		}
		else throw new IllegalArgumentException("[" + button + "] is a wrong parameter use [LEFT], [MIDDLE] or [RIGHT]");
	
	}
	
	/**
	 * Returns whether the given mouse button is clicked.
	 * @param button
	 * @throws IllegalArgumentException
	 */
	public static boolean isMouseButtonClicked(String button) throws IllegalArgumentException {
		if(button.equals("LEFT") || button.equals("MIDDLE") || button.equals("RIGHT")) {
			int but = -1;
			switch (button) {
			case "LEFT": but = 1;
				break;
			case "MIDDLE": but = 2;
				break;
			case "RIGHT": but = 3;
				break;
			}
		
			if(MOUSE_BUTTON_CLICKED[but]) {
				MOUSE_BUTTON_CLICKED[but] = false;
				return true;
			}
			return false;
		}
		else throw new IllegalArgumentException("[" + button + "] is a wrong parameter use [LEFT], [MIDDLE] or [RIGHT]");
	}
	
	/**
	 * Returns true while the user holds down the key identified by Input.KeyCodes or KeyEvent.KeyCode. Think auto fire.
	 * @param keyCode is a keyCode from the class KeyEvent.
	 */
	public static boolean isKeyDown(int keyCode) {
		return KEYCODE[keyCode];
	}
	
	/**
	 * Returns true if the user clicked the key identified by Input.KeyCodes or KeyEvent.KeyCode.
	 * @param keyCode is a keyCode from the class KeyEvent.
	 * @return true if the user clicked the key.
	 */
	public static boolean isKeyClicked(int keyCode) {
		if(KEYCODE_CLICKED[keyCode]) {
			KEYCODE_CLICKED[keyCode] = false;
			return true;
		}
		return false;
	}
	
	
	
	@Override public void mouseClicked(MouseEvent e) {
		MOUSE_BUTTON_CLICKED[e.getButton()] = true;
	}
	

	@Override public void mouseMoved(MouseEvent e) {
		VERTICAL = e.getX();
		HORIZONTAL = e.getY();
	}

	@Override public void mousePressed(MouseEvent e) {
		MOUSE_BUTTON[e.getButton()] = true;
	}
	
	@Override public void mouseReleased(MouseEvent e) {
		MOUSE_BUTTON[e.getButton()] = false;
	}

	@Override public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W && KEYCODE[KeyEvent.VK_S]) 
			KEYCODE[KeyEvent.VK_S] = false;
		if (e.getKeyCode() == KeyEvent.VK_S && KEYCODE[KeyEvent.VK_W]) 
			KEYCODE[KeyEvent.VK_W] = false;
		if (e.getKeyCode() == KeyEvent.VK_D && KEYCODE[KeyEvent.VK_A]) 
			KEYCODE[KeyEvent.VK_A] = false;
		if (e.getKeyCode() == KeyEvent.VK_A && KEYCODE[KeyEvent.VK_D]) 
			KEYCODE[KeyEvent.VK_D] = false;
		
		KEYCODE[e.getKeyCode()] = true;
	}
	
	@Override public void keyReleased(KeyEvent e) {
		KEYCODE[e.getKeyCode()] = false;
	}
	
	@Override public void keyTyped(KeyEvent e) {
		KEYCODE_CLICKED[e.getKeyCode()] = true;
	}
	
	
	@Override public void mouseWheelMoved(MouseWheelEvent e) {}	
	@Override public void mouseDragged(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
