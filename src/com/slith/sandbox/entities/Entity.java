package com.slith.sandbox.entities;

import com.slith.engine.graphics.RenderableQuad;
import com.slith.engine.graphics.Renderer;
import com.slith.engine.graphics.SimpleBatchRenderer;
import com.slith.engine.graphics.Texture;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;
import com.slith.sandbox.map.Tilemap;

public abstract class Entity {

	protected EntityType type;
	protected Texture texture;
	protected RectArea srcRect;
	protected RectArea collisionRect;
	
	protected vec2 position, dimension;
	protected vec2 velocity = new vec2(0.0f, 0.0f);
	
	protected RenderableQuad quad = null;
	
	public Entity(Texture texture, vec2 position, vec2 dimension, EntityType type, SimpleBatchRenderer renderer) {
		this.texture = texture;
		this.position = position;
		this.dimension = dimension;
		this.type = type;
		
		velocity = new vec2(0.0f, 0.0f);
		srcRect = new RectArea(new vec2(0.0f, 0.0f), new vec2(texture.getWidth(), texture.getHeight()));
		collisionRect = new RectArea(new vec2(0.0f, 0.0f), dimension);

		quad = new RenderableQuad(new RectArea(this.position, this.dimension), this.texture, srcRect, renderer);
	}
	public Entity(Texture texture, RectArea srcRect, vec2 position, vec2 dimension, EntityType type, SimpleBatchRenderer renderer) {
		this.texture = texture;
		this.position = position;
		this.dimension = dimension;
		this.type = type;
		
		velocity = new vec2(0.0f, 0.0f);
		this.srcRect = srcRect;
		collisionRect = new RectArea(new vec2(0.0f, 0.0f), dimension);

		quad = new RenderableQuad(new RectArea(this.position, this.dimension), this.texture, srcRect, renderer);
	}

	public void setPosition(vec2 position) {
		this.position = position;
		quad.calculateFinalVertices();
	}
	public void setDimension(vec2 dimension) {
		this.dimension = dimension;
		quad.calculateFinalVertices();
	}
	public void setVelocity(vec2 velocity) {
		this.velocity = velocity;
	}
	
	public vec2 getPosition() {
		return position;
	}
	public vec2 getDimension() {
		return dimension;
	}
	public vec2 getVelocity() {
		return velocity;
	}
	public RectArea getCollideRect() {
		return collisionRect;
	}
	public EntityType getEntityType() {
		return type;
	}
	public abstract void update(double deltaTime, Tilemap map);
	public abstract void pushIntoRenderer(SimpleBatchRenderer batchRenderer);
	public abstract void removeFromRenderer(SimpleBatchRenderer batchRenderer);
	
}
