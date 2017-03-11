package org.AoT.Start;

import com.Engine.PhysicsEngine.PhysicsEngine;
import com.Engine.RenderEngine.RenderEngine;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Window.Window;

public class Engine {
	private PhysicsEngine physicsEngine;
	private RenderEngine renderEngine;
	
	private Camera camera;
	
	private Window window;
	
	protected Engine() {
		window = new Window();
	}
	
	protected void init() {
		physicsEngine = new PhysicsEngine();
		renderEngine = new RenderEngine();
		
		camera = new Camera(90, window.getWidth() / (float) window.getHeight(), 0.3f, 1000);
	}

	public PhysicsEngine getPhysicsEngine() { return physicsEngine; }
	public RenderEngine getRenderEngine() { return renderEngine; }
	public Window getWindow() { return window; }
	
	public Camera getCamera() { return camera; }
}
