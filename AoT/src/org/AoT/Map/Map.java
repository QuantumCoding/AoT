package org.AoT.Map;

import java.util.ArrayList;

import org.AoT.Map.Buildings.Building;
import org.AoT.Start.Engine;

import com.Engine.RenderEngine.Util.Camera;

public abstract class Map {
	private String mapName;
	
	private Engine engine;
	protected ArrayList<Building> buildings; 
	
	private boolean loaded;
	
	public Map(String mapName, Engine engine) {
		this.mapName = mapName;
		this.engine = engine;
		
		buildings = new ArrayList<>();
	}
	
	protected abstract void initBuildings();
	
	public void load() {
		if(!loaded) {
			initBuildings();
			
			loaded = true;
		}
		
		loadPhysics();
	}
	
	public void unload() {
		unloadPhysics();
	}

	public void render(Camera camera) {
		for(Building building : buildings)
			building.render(camera);
	}
	
	public void loadPhysics() {
		for(Building building : buildings) {
			engine.getPhysicsEngine().add(building.getBody());
		}
	}
	
	public void unloadPhysics() {
		for(Building building : buildings) {
			engine.getPhysicsEngine().remove(building.getBody());
		}
	} 
	
	public String toString() { return "Name: " + mapName; }
}
