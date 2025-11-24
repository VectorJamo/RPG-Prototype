package com.slith.sandbox.layers;

import com.slith.engine.graphics.Texture;
import com.slith.engine.graphics.Window;
import com.slith.engine.graphics.layers.Layer;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;
import com.slith.sandbox.entities.EntityType;
import com.slith.sandbox.entities.Player;
import com.slith.sandbox.map.Tilemap;

public class EntityLayer extends Layer {

	private Texture playerTexture;
	private Player player;
	
	public EntityLayer(Window window) {
		super(window, "EntityLayer");
				
		playerTexture = new Texture("res/images/Player.png", 4);
		player = new Player(playerTexture, new RectArea(new vec2(0.0f, 0.0f), new vec2(32.0f, 32.0f)), new vec2(40*32.0f, 8*32.0f), new vec2(32.0f, 32.0f),
				EntityType.PLAYER, batchRenderer, window);
		player.pushIntoRenderer(batchRenderer);
	}

	public void update() {
		
	}
	
	public void update(Tilemap map) {
		player.update(window.getDeltaTime(), map);
	}

	public void render() {
		batchRenderer.drawQuads();
	}
	
	public Player getPlayer() {
		return player;
	}
}
