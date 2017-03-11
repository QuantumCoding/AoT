package org.AoT.Entities;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.AoT.Input.CameraMovement;
import org.AoT.Map.Buildings.Render.BuildingRenderProperties;
import org.AoT.Map.Buildings.Render.BuildingShader;
import org.AoT.Start.AoT;
import org.AoT.Start.Engine;

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
	private CameraMovement movement;
	
	public Player(Engine engine){
		this.engine = engine;
		
		Window window = engine.getWindow();
		camera = new Camera(90, window.getWidth() / (float) window.getHeight(), 0.3f, 1000);
		
		BuildingShader shader = new BuildingShader();
		shader.getRenderer().setRenderBehind(true);
		engine.getRenderEngine().addRenderer(shader.getRenderer());
		
		model = new Model(ModelLoader.loadOBJ("res/models/basicperson.obj"));
		model.setShader(shader);
		
		Texture2D texture = null;
		try { texture = new Texture2D(ImageIO.read(AoT.class.getResource("/textures/Person.png")));
		} catch(IOException e) { }
		
		model.setTexture(texture);
		
		movement = new CameraMovement(engine, camera, .8f, .6f, .8f);
	}
	
	public void update(){
		movement.update((float) engine.getWindow().getFrameTime());
	}
	
	public void render(Camera camera){
		model.render(new BuildingRenderProperties(new Transform(new Vector3f(0, 0, 0), new Vector3f(), new Vector3f(1))), camera);
	}

	public Camera getCamera() { return camera; }
	public Model getModel() { return model; }
}
