package com.slith.sandbox.layers;

import com.slith.engine.graphics.layers.Layer;
import com.slith.engine.graphics.*;
import com.slith.engine.graphics.utils.*;
import com.slith.sandbox.entities.Player;
import com.slith.sandbox.map.Tilemap;
import com.slith.sandbox.utils.Camera;

import com.slith.engine.input.*;

public class TileLayer extends Layer {

	private Tilemap map;
	private boolean isDayTime = true;
	
	public TileLayer(Window window) {
		super(window, "TileLayer");
		
		shader = new Shader("res/shaders/custom_shaders/tile_shader/vs.glsl", "res/shaders/custom_shaders/tile_shader/fs.glsl");
		batchRenderer.setShader(shader);
		shader.setUniformVec4f("u_ColorMultiplier", new float[] {1.0f, 1.0f, 1.0f, 1.0f});
		shader.setUniformBool("u_IsNight", 0);
		
		map = new Tilemap("res/maps/map.txt", 50, 50, 32, 32, window, batchRenderer);
	}

	@Override
	public void update() {
		map.update(Camera.topLeftX, Camera.topLeftY);
		
		if(KeyManager.isKeyPressed((int)'F')) {
			isDayTime = !isDayTime;
		}
		
		if(isDayTime) {
			shader.setUniformBool("u_IsNight", 0);
		}else {
			EntityLayer entityLayer = (EntityLayer) CurrentLayers.get("EntityLayer");
			Player player = entityLayer.getPlayer();
			
			float lightScreenX = player.getPosition().getX() + player.getDimension().getX()/2;
			float lightScreenY = player.getPosition().getY() + player.getDimension().getY()/2;
			
			shader.setUniformBool("u_IsNight", 1);
			shader.setUniformVec2f("u_LightScreenPosition", new float[] {lightScreenX, lightScreenY});
		}
	}

	@Override
	public void render() {
		map.render();
	}
	
	public Tilemap getTilemap() {
		return map;
	}
}
