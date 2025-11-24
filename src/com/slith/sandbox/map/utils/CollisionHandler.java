package com.slith.sandbox.map.utils;

import com.slith.engine.maths.vec2;
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
	
	public static CollisionDirection checkEntityWorldCollision(Entity e, vec2 worldPosition, Tilemap map, double deltaTime) {
		CollisionDirection collisionStatus = new CollisionDirection();
		
		// Check for x direction collision
		float entityXNew = (worldPosition.getX() + (float)deltaTime*e.getVelocity().getX());
		float entityY = worldPosition.getY();

		// Check, if the entity will collide in the next frame or not
		if(e.getVelocity().getX() > 0) {

			float topRightRow, topRightCol, bottomRightRow, bottomRightCol;
			
			topRightCol = (entityXNew + e.getCollideRect().getPosition().getX() + e.getCollideRect().getDimension().getX()) / 32;
			topRightRow = (entityY + e.getCollideRect().getPosition().getY())/32;
			bottomRightCol = (entityXNew + e.getCollideRect().getPosition().getX() + e.getCollideRect().getDimension().getX()) / 32;
			bottomRightRow = (entityY + e.getCollideRect().getPosition().getY() + e.getCollideRect().getDimension().getY())/32;
			
			if(map.getMap()[(int)topRightRow][(int)topRightCol].isCollideable || map.getMap()[(int)bottomRightRow][(int)bottomRightCol].isCollideable) {
				collisionStatus.xCollision = true;
			}
		}else if(e.getVelocity().getX() < 0){
			
			float topLeftRow, topLeftCol, bottomLeftRow, bottomLeftCol;
			
			topLeftCol = (entityXNew + e.getCollideRect().getPosition().getX()) / 32;
			topLeftRow = (entityY + e.getCollideRect().getPosition().getY())/32;
			bottomLeftCol = (entityXNew + e.getCollideRect().getPosition().getX()) / 32;
			bottomLeftRow = (entityY + e.getCollideRect().getPosition().getY() + e.getCollideRect().getDimension().getY())/32;
			
			if(map.getMap()[(int)topLeftRow][(int)topLeftCol].isCollideable || map.getMap()[(int)bottomLeftRow][(int)bottomLeftCol].isCollideable) {
				collisionStatus.xCollision = true;
			}
		}
		
		// Check for y direction collision
		float entityYNew = worldPosition.getY() + (float)deltaTime*e.getVelocity().getY();
		float entityX = worldPosition.getX();
		
		if(e.getVelocity().getY() > 0) {
			float bottomLeftCol, bottomLeftRow, bottomRightCol, bottomRightRow;
			
			bottomLeftCol = (entityX + e.getCollideRect().getPosition().getX()) / 32;
			bottomLeftRow = (entityYNew + e.getCollideRect().getPosition().getY() + e.getCollideRect().getDimension().getY())/32;
			bottomRightCol = (entityX + e.getCollideRect().getPosition().getX() + e.getCollideRect().getDimension().getX()) / 32;
			bottomRightRow = (entityYNew + e.getCollideRect().getPosition().getY() + e.getCollideRect().getDimension().getY())/32;
			
			if(map.getMap()[(int)bottomLeftRow][(int)bottomLeftCol].isCollideable || map.getMap()[(int)bottomRightRow][(int)bottomRightCol].isCollideable) {
				collisionStatus.yCollision = true;
			}
			
		}else if(e.getVelocity().getY() < 0) {
			float topLeftCol, topLeftRow, topRightCol, topRightRow;
			
			topLeftCol = (entityX + e.getCollideRect().getPosition().getX()) / 32;
			topLeftRow = (entityYNew + e.getCollideRect().getPosition().getY())/32;
			topRightCol = (entityX + e.getCollideRect().getPosition().getX() + e.getCollideRect().getDimension().getX()) / 32;
			topRightRow = (entityYNew + e.getCollideRect().getPosition().getY())/32;
			
			if(map.getMap()[(int)topLeftRow][(int)topLeftCol].isCollideable || map.getMap()[(int)topRightRow][(int)topRightCol].isCollideable) {
				collisionStatus.yCollision = true;
			}
		}
		
		
		return collisionStatus;
	}
}
