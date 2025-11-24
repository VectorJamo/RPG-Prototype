package com.slith.sandbox.map.tiles;

import com.slith.engine.graphics.Texture;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;

// For loading tile-map created from 'Tiled'
public class TileAssets {

	public static Texture tileSprite;
	
	public static int TOTAL_UNIQUE_TILES = 6*9;
	public static int TILE_WIDTH_IN_SHEET = 16;
	public static int TILE_HEIGHT_IN_SHEET = 16;
	
	public static Tile[] tileIdToTileInfo;
	
	public static void InitAssets() {
		tileSprite = new Texture("res/images/All_Tiles.png", 4);
		
		tileIdToTileInfo = new Tile[TOTAL_UNIQUE_TILES];
		
		int numRows = 6;
		int numCols = 9;
		
		for(int i = 0; i < numRows*numCols; i++) {
			int currentRow = i / numCols;
			int currentCol = i % numCols;
			
			RectArea srcRect = new RectArea(new vec2(currentCol*TILE_WIDTH_IN_SHEET, currentRow*TILE_HEIGHT_IN_SHEET), 
					new vec2(TILE_WIDTH_IN_SHEET, TILE_HEIGHT_IN_SHEET));
			
			tileIdToTileInfo[i] = new Tile(i, tileSprite, srcRect);
			if(i == 29) {
				tileIdToTileInfo[i].isCollideable = true;
			}
		}
	}
}
