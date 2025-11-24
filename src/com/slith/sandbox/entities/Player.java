package com.slith.sandbox.entities;

import com.slith.engine.graphics.RenderableQuad;
import com.slith.engine.graphics.Renderer;
import com.slith.engine.graphics.SimpleBatchRenderer;
import com.slith.engine.graphics.Texture;
import com.slith.engine.graphics.Window;
import com.slith.engine.input.KeyManager;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;
import com.slith.sandbox.map.Tilemap;
import com.slith.sandbox.map.utils.CollisionDirection;
import com.slith.sandbox.map.utils.CollisionHandler;
import com.slith.sandbox.utils.Camera;

public class Player extends Entity {
	
	private Window window;
	public String direction = "idle";
	public vec2 worldPosition;
	
	public Player(Texture texture, RectArea srcRect, vec2 position, vec2 dimension, EntityType type, SimpleBatchRenderer renderer, Window window) {
		super(texture, srcRect, position, dimension, type, renderer);
		
		this.window = window;
		worldPosition = new vec2(position.getX(), position.getY());
		
		this.collisionRect.setPosition(new vec2(8, 8));
		this.collisionRect.setDimension(new vec2(16, 16));
	}

	@Override
	public void update(double deltaTime, Tilemap map) {
		if(KeyManager.isKeyPressed((int)'W')) {
			direction = "up";
		}
		if(KeyManager.isKeyPressed((int)'A')) {
			direction = "left";
		}
		if(KeyManager.isKeyPressed((int)'S')) {
			direction = "down";
		}
		if(KeyManager.isKeyPressed((int)'D')) {
			direction = "right";
		}
		
		if(direction == "up" && KeyManager.isKeyReleased((int)'W')) {
			direction = "idle";
		}
		if(direction == "down" && KeyManager.isKeyReleased((int)'S')) {
			direction = "idle";
		}
		if(direction == "left" && KeyManager.isKeyReleased((int)'A')) {
			direction = "idle";
		}
		if(direction == "right" && KeyManager.isKeyReleased((int)'D')) {
			direction = "idle";
		}
		
		switch(direction) {
		case "right":
			velocity.setX(100);
			break;
		case "left":
			velocity.setX(-100);
			break;
		case "up":
			velocity.setY(-100);
			break;
		case "down":
			velocity.setY(100);
			break;
		}
		
		CollisionDirection status = CollisionHandler.checkEntityWorldCollision(this, worldPosition, map, deltaTime);
		if(status.xCollision) {
			velocity.setX(0);
		}
		if(status.yCollision) {
			velocity.setY(0);
		}
		
		worldPosition.setX((float)(worldPosition.getX() + deltaTime*velocity.getX()));
		worldPosition.setY((float)(worldPosition.getY() + deltaTime*velocity.getY()));
		
		Camera.topLeftX = (worldPosition.getX() + dimension.getX()/2) - window.getWidth()/2;
		Camera.topLeftY = (worldPosition.getY() + dimension.getY()/2) - window.getHeight()/2;
		
		int worldWidth = 50*32;
		int worldHeight = 50*32;
		
		if(Camera.topLeftX < 0.0f) {
			Camera.topLeftX = 0.0f;
		}else if(Camera.topLeftX + window.getWidth() > worldWidth) {
			Camera.topLeftX = worldWidth - window.getWidth();
		}
		if(Camera.topLeftY < 0.0f) {
			Camera.topLeftY = 0.0f;
		}else if(Camera.topLeftY + window.getHeight() > worldHeight) {
			Camera.topLeftY = worldHeight - window.getHeight();
		}
		
		position.setX(worldPosition.getX() - Camera.topLeftX);
		position.setY(worldPosition.getY() - Camera.topLeftY);
			
		quad.calculateFinalVertices();
		velocity.setX(0);
		velocity.setY(0);
	}

	@Override
	public void pushIntoRenderer(SimpleBatchRenderer batchRenderer) {
		batchRenderer.pushQuad(quad);
	}
	
	@Override
	public void removeFromRenderer(SimpleBatchRenderer batchRenderer) {
		batchRenderer.removeQuad(quad);
	}
	
	public vec2 getWorldPosition() {
		return worldPosition;
	}
}
