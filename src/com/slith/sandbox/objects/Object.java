package com.slith.sandbox.objects;

import com.slith.engine.graphics.RenderableQuad;
import com.slith.engine.graphics.Renderer;
import com.slith.engine.graphics.SimpleBatchRenderer;
import com.slith.engine.graphics.Texture;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;

public abstract class Object {

	protected ObjectType type;
	protected Texture texture;
	protected RectArea srcRect;
	
	protected vec2 position, dimension;
	protected vec2 velocity;
	
	protected RenderableQuad quad = null;
	
	public Object(Texture texture, vec2 position, vec2 dimension, ObjectType type, SimpleBatchRenderer renderer) {
		this.texture = texture;
		this.position = position;
		this.dimension = dimension;
		this.type = type;
		
		velocity = new vec2(0.0f, 0.0f);
		srcRect = new RectArea(new vec2(0.0f, 0.0f), new vec2(texture.getWidth(), texture.getHeight()));

		quad = new RenderableQuad(new RectArea(this.position, this.dimension), this.texture, srcRect, renderer);
	}
	public Object(Texture texture, RectArea srcRect, vec2 position, vec2 dimension, ObjectType type, SimpleBatchRenderer renderer) {
		this.texture = texture;
		this.position = position;
		this.dimension = dimension;
		this.type = type;
		
		velocity = new vec2(0.0f, 0.0f);
		this.srcRect = srcRect;

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
	public ObjectType getObjectType() {
		return type;
	}
	public abstract void update(double deltaTime);
	public abstract void pushIntoRenderer(SimpleBatchRenderer batchRenderer);
	public abstract void removeFromRenderer(SimpleBatchRenderer batchRenderer);
	
}
