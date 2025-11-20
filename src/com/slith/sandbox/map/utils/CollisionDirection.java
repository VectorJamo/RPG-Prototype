package com.slith.sandbox.map.utils;

public class CollisionDirection {
	public boolean xCollision, yCollision;
	
	public CollisionDirection() {
		xCollision = false;
		yCollision = false;
	}
	
	public CollisionDirection(boolean xCollision, boolean yCollision) {
		this.xCollision = xCollision;
		this.yCollision = yCollision;
	}
}
