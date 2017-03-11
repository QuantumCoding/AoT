package org.AoT.Start;

import org.lwjgl.LWJGLException;

import com.Engine.RenderEngine.Util.Camera;

public class AoT {
	private Engine engine;
	
	public void init() throws LWJGLException {
		engine = new Engine();
		
		engine.getWindow().initDisplay();
		engine.init();
	}
	
	public void run() {
		Camera camera = engine.getPlayer().getCamera();
		while(!engine.getWindow().isCloseRequested()) {
			update();
			render(camera);
			engine.getWindow().update();
		}
	}
	
	public void update(){
		engine.getPlayer().update();
	}
	
	public void render(Camera camera){
		engine.getPlayer().render(camera);
		engine.getRenderEngine().render(camera);
	}
	
	public void cleanUp() {
		engine.getWindow().destroy();
	}
}
