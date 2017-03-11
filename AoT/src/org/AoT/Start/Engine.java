package org.AoT.Start;

import org.AoT.Entities.Player;

import com.Engine.PhysicsEngine.PhysicsEngine;
import com.Engine.RenderEngine.RenderEngine;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Window.Window;

public class Engine {
	private PhysicsEngine physicsEngine;
	private RenderEngine renderEngine;
	private Window window;
	
	private Player player;
	
	protected Engine() {
		window = new Window();
	}
	
	protected void init() {
		physicsEngine = new PhysicsEngine();
		renderEngine = new RenderEngine();
		player = new Player(this);
	}

	public PhysicsEngine getPhysicsEngine() { return physicsEngine; }
	public RenderEngine getRenderEngine() { return renderEngine; }
	public Player getPlayer() { return player; }
	public Window getWindow() { return window; }
}
