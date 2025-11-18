package com.slith.sandbox.map.tiles;

import com.slith.engine.graphics.Texture;
import com.slith.engine.shapes.RectArea;

public class Tile {
	
	public Texture texture; // To be set from the asset setter
	public RectArea sourceRect;
	
	public int idInMap;
	public boolean isCollideable = false;
	
	public Tile(int idInMap, Texture texture, RectArea sourceRect) {
		this.idInMap = idInMap;
		this.texture = texture;
		this.sourceRect = sourceRect;
	}
}
