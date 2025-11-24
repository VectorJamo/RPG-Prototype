package com.slith.sandbox.layers;

import com.slith.engine.graphics.SimpleBatchRenderer;
import com.slith.engine.graphics.Window;
import com.slith.engine.graphics.layers.Layer;
import com.slith.engine.maths.vec2;
import com.slith.engine.shapes.Color;
import com.slith.engine.ui.BMPFontRenderer;
import com.slith.engine.ui.Text;

public class UILayer extends Layer {
	
	private BMPFontRenderer fontRenderer;
	private Text pauseScreenText;
	private Text gameVersionText;
	private boolean firstFramePaused = true;

	public UILayer(Window window) {
		super(window, "UILayer");
		
		fontRenderer = new BMPFontRenderer(window, "res/images/ConsolasBMP.bmp");
		pauseScreenText = new Text("PAUSED", new vec2(200.0f, 200.0f), 48, new Color(1.0f, 1.0f, 1.0f, 1.0f), fontRenderer);
		gameVersionText = new Text("Farm Life v. Alpha", new vec2(0, window.getHeight()-14), 14, new Color(1.0f, 1.0f, 0.0f, 1.0f),
				fontRenderer);
		fontRenderer.pushText(gameVersionText);
	}

	@Override
	public void update() {
		
	}
	
	public void update(boolean isGamePaused) {
		if(isGamePaused) {
			if(firstFramePaused) {
				fontRenderer.pushText(pauseScreenText);
				firstFramePaused = false;
			}
		}else {
			if(!firstFramePaused) {
				fontRenderer.removeText(pauseScreenText);
			}
			firstFramePaused = true;
		}
	}

	@Override
	public void render() {
		fontRenderer.renderText();
	}

}
