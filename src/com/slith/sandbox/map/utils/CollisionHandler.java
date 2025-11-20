package com.slith.sandbox.map.utils;

import com.slith.sandbox.entities.Entity;
import com.slith.sandbox.objects.Object;
import com.slith.sandbox.map.Tilemap;

public class CollisionHandler {
	public static boolean checkEntityCollision(Entity e1, Entity e2) {
		boolean status = false;
		
		return status;
	}
	public static boolean checkObjectCollision(Object e1, Object e2) {
		boolean status = false;
		
		return status;
	}
	public static boolean checkEntityObjectCollision(Entity e, Object o) {
		boolean status = false;
		
		return status;
	}
	
	public static CollisionDirection checkEntityWorldCollision(Entity e, Tilemap map) {
		CollisionDirection collisionStatus = new CollisionDirection();
		
		// Check for x direction collision
		int entityXNew = (int) (e.getPosition().getX() + e.getVelocity().getX());
		int entityY = (int)e.getPosition().getY();
		int entityWidth = (int)e.getDimension().getX();
		int entityHeight = (int)e.getDimension().getY();
		
		// Check, if the entity will collide in the next frame or not
		if(e.getVelocity().getX() > 0) {
			int topRightRow, topRightCol, bottomRightRow, bottomRightCol;
			
			topRightCol = entityXNew + entityWidth;
			topRightRow = entityY
			
		}else if(e.getVelocity().getX() < 0) {
			int topLeftRow, topLeftCol, bottomLeftRow, bottomLeftCol;
			
		}
		
		
		
		
		return collisionStatus;
	}
}
