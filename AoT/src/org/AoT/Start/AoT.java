package org.AoT.Start;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.AoT.Input.CameraMovement;
import org.AoT.Map.Buildings.Render.BuildingRenderProperties;
import org.AoT.Map.Buildings.Render.BuildingShader;
import org.lwjgl.LWJGLException;

import com.Engine.RenderEngine.Lights.Light;
import com.Engine.RenderEngine.Models.Model;
import com.Engine.RenderEngine.Models.ModelLoader;
import com.Engine.RenderEngine.Shaders.DefaultRenderProperties;
import com.Engine.RenderEngine.Shaders.DefaultShader;
import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.Util.Vectors.Vector3f;

import sun.net.www.content.text.plain;

public class AoT {
	private Engine engine;
	
	private CameraMovement movement;
	
	public void init() throws LWJGLException {
		engine = new Engine();
		
		engine.getWindow().initDisplay();
		engine.init();
		
		movement = new CameraMovement(engine, .8f, .6f, .8f);
	}
	
	public void run() {
		Camera camera = engine.getCamera();
		
		BuildingShader shader = new BuildingShader();
		shader.getRenderer().setRenderBehind(true);
		engine.getRenderEngine().addRenderer(shader.getRenderer());
		
		Model model = new Model(ModelLoader.loadOBJ("res/models/TexturedCube.obj"));
		model.setShader(shader);
		
		Texture2D texture = null;
		try { texture = new Texture2D(ImageIO.read(AoT.class.getResource("/textures/Gold.png")));
		} catch(IOException e) { }
		
		model.setTexture(texture);
		
		while(!engine.getWindow().isCloseRequested()) {
			movement.update((float) engine.getWindow().getFrameTime());
			
//			if(engine.getWindow().getFrameTime() < 1)
//				camera.moveForward((float) (-0.1f * engine.getWindow().getFrameTime()));
			
//			System.out.println(camera.getPosition());
			
			model.render(new BuildingRenderProperties(new Transform(new Vector3f(0, 0, 0), new Vector3f(), new Vector3f(1))), camera);
			
			engine.getRenderEngine().render(camera);
			engine.getWindow().update();
		}
	}
	
	public void cleanUp() {
		engine.getWindow().destroy();
	}
}
