package com.slith.sandbox;

import com.slith.engine.graphics.*;
import com.slith.engine.maths.*;
import com.slith.engine.shapes.*;
import com.slith.engine.ui.utils.*;

import com.slith.sandbox.states.*;

public class Sandbox {
	
	private Window window;
	
	public Sandbox() {
		window = new Window(800, 600, "Farm-Life");
		
		StateManager.CurrentState = new GameState(window, States.GAME);
		UI_Utilities.Init(window);
		
		while(!window.windowShouldClose()) {
			window.pollEvents();
			window.clear();
			
			UI_Utilities.Update();
			
			StateManager.CurrentState.update();
			StateManager.CurrentState.render();
			
			UI_Utilities.RenderFPS();
			
			window.show();
		}
	}
	public static void main(String[] args) {
		new Sandbox();
	}
}
