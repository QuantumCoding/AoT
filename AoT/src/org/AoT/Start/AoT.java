package org.AoT.Start;

import org.AoT.Map.Map;
import org.AoT.Map.Buildings.Building;
import org.AoT.Map.Buildings.BuildingManager;
import org.lwjgl.LWJGLException;

import com.Engine.PhysicsEngine.Detection.Intersection.Tests.MovingEllipsoidMeshIntersectionTest;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.Util.Vectors.Vector3f;

public class AoT {
	private Engine engine;
	
	public void init() throws LWJGLException {
		engine = new Engine();
		
		engine.getWindow().initDisplay();
		engine.init();
		
		engine.getPhysicsEngine().addIntersectionTest(new MovingEllipsoidMeshIntersectionTest());
		engine.getRenderEngine().addRenderer(BuildingManager.BuildingShader.getRenderer());
	}
	
	public void run() {
		Camera camera = engine.getPlayer().getCamera();
		
		Map map = new Map("Test Map", engine) {
			protected void initBuildings() {
				buildings.add(new Building("res/models/myPerson.obj", "/textures/personTexture.png",
						new Transform(new Vector3f(10, 12, 0), new Vector3f(), new Vector3f(1))));
				buildings.add(new Building("res/models/Wall.obj", "/textures/Walls Texture.png"));
			}
		};
		
		map.load();
		
		while(!engine.getWindow().isCloseRequested()) {
			update();
			
			map.render(camera);
			render(camera);
			engine.getWindow().update();
		}
	}
	
	public void update() {
		engine.getPlayer().update((float) engine.getWindow().getFrameTime());
		engine.getPhysicsEngine().simulate((float) engine.getWindow().getFrameTime());
	}
	
	public void render(Camera camera){
//		engine.getPlayer().render(camera);
		engine.getRenderEngine().render(camera);
	}
	
	public void cleanUp() {
		engine.getWindow().destroy();
	}
}
