package org.AoT.Map.Buildings;

import org.AoT.Map.Buildings.BuildingManager.BuildingData;
import org.AoT.Map.Buildings.Render.BuildingRenderProperties;

import com.Engine.PhysicsEngine.Bodies.StaticBody;
import com.Engine.RenderEngine.Models.Model;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;

public class Building {
	private Transform transform;
	
	private Model model;
	private StaticBody body;

	public Building(String modelPath, String texturePath) { this(modelPath, texturePath, (String) null); } 
	public Building(String modelPath, String texturePath, Transform transform) { this(modelPath, texturePath, null, transform); } 
	public Building(String modelPath, String texturePath, String colliderPath) { this(modelPath, texturePath, colliderPath, new Transform()); } 
	
	public Building(String modelPath, String texturePath, String colliderPath, Transform transform) {
		this.transform = transform;
		
		BuildingData data = BuildingManager.loadBuilding(modelPath, texturePath, colliderPath);
		this.model = data.model;
		this.body = new StaticBody(data.body);
	}
	
	public void render(Camera camera) {
		model.render(new BuildingRenderProperties(transform), camera);
	}
	
	public StaticBody getBody() { return body; }
	public Model getModel() { return model; }
}
