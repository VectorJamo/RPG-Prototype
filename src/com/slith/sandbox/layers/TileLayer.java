package com.slith.sandbox.layers;

import com.slith.engine.graphics.layers.Layer;
import com.slith.engine.graphics.*;
import com.slith.engine.graphics.utils.*;
import com.slith.sandbox.map.Tilemap;
import com.slith.sandbox.utils.Camera;

public class TileLayer extends Layer {

	private Tilemap map;
	
	public TileLayer(Window window) {
		super(window);
		
		shader = new Shader("res/shaders/custom_shaders/tile_shader/vs.glsl", "res/shaders/custom_shaders/tile_shader/fs.glsl");
		batchRenderer.setShader(shader);
		shader.setUniformVec4f("u_ColorMultiplier", new float[] {1.0f, 1.0f, 1.0f, 1.0f});
		
		map = new Tilemap("res/maps/map.txt", 50, 50, 32, 32, window, batchRenderer);
	}

	@Override
	public void update() {
		map.update(Camera.topLeftX, Camera.topLeftY);
	}

	@Override
	public void render() {
		map.render();
	}
}
