package com.slith.sandbox.states;

import com.slith.engine.graphics.Renderer;
import com.slith.engine.graphics.SimpleBatchRenderer;
import com.slith.engine.graphics.Texture;
import com.slith.engine.graphics.Window;
import com.slith.engine.input.KeyManager;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.Color;
import com.slith.engine.shapes.RectArea;
import com.slith.engine.ui.utils.UI_Utilities;
import com.slith.sandbox.entities.*;
import com.slith.sandbox.layers.EntityLayer;
import com.slith.sandbox.layers.TileLayer;
import com.slith.sandbox.layers.UILayer;
import com.slith.sandbox.map.*;

public class GameState extends StateManager {
	
	private TileLayer worldLayer;
	private EntityLayer entityLayer;
	private UILayer uiLayer;
	private boolean isGamePaused = false;
	
	public GameState(Window window, States stateName) {
		super(window, stateName);
		CurrentState = this;
		
		init();
	}

	@Override
	public void init() {
		worldLayer = new TileLayer(window);
		entityLayer = new EntityLayer(window);
		uiLayer = new UILayer(window);
	}

	@Override
	public void update() {
		if(KeyManager.isKeyPressed((int)'P')) {
			isGamePaused = !isGamePaused;
		}
		if(!isGamePaused) {
			worldLayer.update();
			entityLayer.update();
		}
		uiLayer.update(isGamePaused);
	}

	@Override
	public void render() {
		worldLayer.render();
		entityLayer.render();
		uiLayer.render();
	}
}
