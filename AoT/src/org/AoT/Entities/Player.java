package org.AoT.Entities;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.AoT.Input.CameraMovement;
import org.AoT.Input.PlayerMovement;
import org.AoT.Map.Buildings.BuildingManager;
import org.AoT.Map.Buildings.Render.BuildingRenderProperties;
import org.AoT.Start.AoT;
import org.AoT.Start.Engine;

import com.Engine.PhysicsEngine.Bodies.MovingBody;
import com.Engine.PhysicsEngine.Detection.Colliders.CollisionEllipse;
import com.Engine.RenderEngine.Models.Model;
import com.Engine.RenderEngine.Models.ModelLoader;
import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.RenderEngine.Window.Window;
import com.Engine.Util.Vectors.Vector3f;

public class Player {
	
	private Engine engine;
	private Camera camera;
	
	private Model model;
	private MovingBody body;
	
	private PlayerMovement movement;
	
	public Player(Engine engine){
		this.engine = engine;
		
		Window window = engine.getWindow();
		camera = new Camera(90, window.getWidth() / (float) window.getHeight(), 0.3f, 1000);
		
		initModel();
		initPhysics();
		
		movement = new PlayerMovement(body, .8f);
	}
	
	private void initPhysics() {
		CollisionEllipse ellipse = new CollisionEllipse(new Vector3f(1.5, 3.5, .45));
		body = new MovingBody(ellipse);
		
		engine.getPhysicsEngine().add(body);
	}
	
	private void initModel() {
		model = new Model(ModelLoader.loadOBJ("res/models/basicperson.obj"));
		model.setShader(BuildingManager.BuildingShader);
		
		Texture2D texture = null;
		try { texture = new Texture2D(ImageIO.read(AoT.class.getResource("/textures/Person.png")));
		} catch(IOException e) { }
		
		model.setTexture(texture);
	}
	
	public void update(){
		movement.update((float) engine.getWindow().getFrameTime());
		
//		body.addForce(new Vector3f(0, -5, 0));
		
		camera.x = body.getPosition().x;
		camera.y = body.getPosition().y;
		camera.z = body.getPosition().z;

		camera.rotX = body.getRotation().x;
		camera.rotY = body.getRotation().y;
		camera.rotZ = body.getRotation().z;
	}
	
	public void render(Camera camera) {
		model.render(new BuildingRenderProperties(
				new Transform(body.getPosition().subtract(0, 3.5f, -0.2f), body.getRotation(), body.getScale())),
			camera);
	}

	public Camera getCamera() { return camera; }
	public Model getModel() { return model; }
}
