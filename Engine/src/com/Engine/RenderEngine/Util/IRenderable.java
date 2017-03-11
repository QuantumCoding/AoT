package com.Engine.RenderEngine.Util;

import com.Engine.RenderEngine.Models.ModelData.ModelData;
import com.Engine.RenderEngine.Shaders.Shader;

public interface IRenderable<T extends RenderProperties> {
	public void render(T properties, Camera camera);
	
	public Shader getShader();
	public ModelData getModelData();

	public boolean equals(Object other);
	public int hashCode();
}
