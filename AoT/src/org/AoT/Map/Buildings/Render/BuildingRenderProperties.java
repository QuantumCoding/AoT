package org.AoT.Map.Buildings.Render;

import com.Engine.RenderEngine.Util.RenderProperties;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;

public class BuildingRenderProperties extends RenderProperties {

	public BuildingRenderProperties(Transform transform) {
		super(transform);
	}
	
	public RenderProperties clone() {
		return new BuildingRenderProperties(super.getTransform());
	}
}
