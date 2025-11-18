package com.slith.sandbox.states;

import com.slith.engine.graphics.Window;

public abstract class StateManager {
	public static StateManager CurrentState = null;

	protected static Window window;
	protected States stateName;
	
	public StateManager(Window window, States stateName) {
		this.window = window;
		this.stateName = stateName;
	}
	
	public States getStateName() {
		return stateName;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void render();
}
