package org.AoT.Map.Buildings;

import java.util.HashMap;

import org.AoT.Assets.ImageLoader;
import org.AoT.Map.Buildings.Render.BuildingShader;

import com.Engine.PhysicsEngine.Detection.Colliders.CollisionMesh;
import com.Engine.PhysicsEngine.Detection.Colliders.CollisionMeshLoader;
import com.Engine.RenderEngine.Models.Model;
import com.Engine.RenderEngine.Models.ModelLoader;
import com.Engine.RenderEngine.Models.ModelData.ModelData;
import com.Engine.RenderEngine.Textures.Texture2D;

public final class BuildingManager {
	public static final BuildingShader BuildingShader = new BuildingShader();

	private static HashMap<String, Texture2D> buildingTextures = new HashMap<>();
	private static HashMap<String, HashMap<Texture2D, Model>> buildingModels = new HashMap<>();
	private static HashMap<String, CollisionMesh> buildingColliders = new HashMap<>();
	
	public static BuildingData loadBuilding(String modelPath, String texturePath, String colliderPath) {
		Texture2D texture = buildingTextures.get(texturePath);
		if(texture == null) {
			texture = ImageLoader.loadTexture(texturePath);
			buildingTextures.put(texturePath, texture);
		}

		Model model = null;
		HashMap<Texture2D, Model> maped = buildingModels.get(modelPath);
		if(maped != null) model = maped.get(texture);
		
		if(model == null) {
			ModelData modelData = null;
			
			if(maped == null) {
				maped = new HashMap<>();
				buildingModels.put(modelPath, maped);
			}
			
			if(maped.isEmpty()) {
				modelData = ModelLoader.loadOBJ(modelPath);
				
			} else {
				Texture2D key = maped.keySet().iterator().next();
				modelData = maped.get(key).getModelData();
			}
			
			model = new Model(modelData);
			model.setShader(BuildingShader);
			model.setTexture(texture);
			
			maped.put(texture, model);
		}
		
		if(colliderPath == null) colliderPath = modelPath;
		CollisionMesh body = buildingColliders.get(colliderPath);
		
		if(body == null) {
			body = CollisionMeshLoader.loadObj(colliderPath);
			buildingColliders.put(colliderPath, body);
		}
		
		BuildingData data = new BuildingData();
		data.model = model;
		data.body = body;
		
		return data;
	}
	
	public static class BuildingData {
		Model model;
		CollisionMesh body;
	}
	
	private BuildingManager() {}
}
