package com.slith.sandbox.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.slith.engine.graphics.*;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.RectArea;
import com.slith.sandbox.map.tiles.*;

public class Tilemap {
	
	private Window window;
	private int worldRows, worldCols, tileWidth, tileHeight;
	private Tile[][] tileMap;
	private RenderableQuad[][] renderableTiles;
	
	private SimpleBatchRenderer tileRenderer;
	
	private boolean first = true;
	
	public Tilemap(String mapPath, int worldRows, int worldCols, int tileWidth, int tileHeight, Window window, SimpleBatchRenderer tileRenderer) {
		this.worldRows = worldRows;
		this.worldCols = worldCols;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.window = window;
		this.tileRenderer = tileRenderer;
		
		tileMap = new Tile[worldRows][worldCols];
		renderableTiles = new RenderableQuad[worldRows][worldCols];
		
		TileAssets.InitAssets();
		loadMap(mapPath);
	}
	
	public void loadMap(String path) {
		File fileObj = new File(path);
		try {
			Scanner fileReader = new Scanner(fileObj);
			int row = 0;
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				int col = 0;
				String[] tileIDs = line.split("\\s*,\\s*");
				for(int i = 0; i < tileIDs.length; i++) {
					int tileID = Integer.parseInt(tileIDs[i]);
					
					tileMap[row][col] = TileAssets.tileIdToTileInfo[tileID];

					renderableTiles[row][col] = new RenderableQuad(new RectArea(new vec2(col*tileWidth, row*tileHeight), 
							new vec2(tileWidth, tileHeight)), tileMap[row][col].texture, tileMap[row][col].sourceRect, tileRenderer);
					col++;
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		tileRenderer.clearQuads();
		
		for(int i = 0; i < worldRows; i++) {
			for(int j = 0; j < worldCols; j++) {
				int tileX = j*tileWidth;
				int tileY = i*tileHeight;
				
				// Figure out which tiles fall in the view space
				if(tileX < window.getWidth() && tileX+tileWidth > 0 && tileY < window.getHeight() && tileY+tileHeight > 0) {
					tileRenderer.pushQuad(renderableTiles[i][j]);
				}
			}
		}
	}
	public void update(float camTopLeftX, float camTopLeftY) {
		tileRenderer.clearQuads();
		
		for(int i = 0; i < worldRows; i++) {
			for(int j = 0; j < worldCols; j++) {
				int worldX = j*tileWidth;
				int worldY = i*tileHeight;
				int screenX = worldX - (int)camTopLeftX;
				int screenY = worldY - (int)camTopLeftY;

				// Figure out which tiles fall in the view space
				if(screenX < window.getWidth() && screenX+tileWidth > 0 && screenY < window.getHeight() && screenY+tileHeight > 0) {
					renderableTiles[i][j].setPosition(new vec2(screenX, screenY));
					
					tileRenderer.pushQuad(renderableTiles[i][j]);
				}
			}
		}
	}
	
	public void render() {
		
		tileRenderer.drawQuads();
	}
	
	public Tile[][] getMap() {
		return tileMap;
	}
}
