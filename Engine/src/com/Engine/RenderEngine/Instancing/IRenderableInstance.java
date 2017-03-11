package com.Engine.RenderEngine.Instancing;

import com.Engine.RenderEngine.Util.IRenderable;
import com.Engine.RenderEngine.Util.RenderProperties;

public interface IRenderableInstance<T extends RenderProperties> extends IRenderable<T> {
	public int getInstanceLength();
	public int getInstanceCount();
	
	public void addInstanceAttributes(InstanceVBO vbo);
}
