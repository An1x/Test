package engine.interfaces;

import engine.util.State;

public interface Updateable {
	void update();
	State getState();
}
