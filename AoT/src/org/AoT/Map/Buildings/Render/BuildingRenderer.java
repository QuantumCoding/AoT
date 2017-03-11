package org.AoT.Map.Buildings.Render;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import com.Engine.PhysicsEngine.Render.PhysicsShader;
import com.Engine.RenderEngine.RenderEngine;
import com.Engine.RenderEngine.Models.Model;
import com.Engine.RenderEngine.Shaders.Renderer;
import com.Engine.RenderEngine.Shaders.Shader;

public class BuildingRenderer extends Renderer<Model, BuildingRenderProperties> {

	public BuildingRenderer(Shader shader) {
		super(shader);
	}

	public void renderModels() {
		for(Model renderSection : renders.keySet()) {
			BuildingShader shader = (BuildingShader) super.getShader();
			shader.bind();
			
			shader.loadProjectionMatrix(Shader.getProjectionMatrix());
			shader.loadViewMatrix(Shader.getViewMatrix());

			renderSection.getTexture().bind();
			
			for(BuildingRenderProperties property : renders.get(renderSection)) {
				shader.loadTransformationMatrix(property.getTransformMatrix());

				glBindVertexArray(renderSection.getVAOId());
				glEnableVertexAttribArray(Shader.ATTRIBUTE_LOC_POSITIONS);
				glEnableVertexAttribArray(Shader.ATTRIBUTE_LOC_TEXCOORDS);
				
				glDrawElements(GL_TRIANGLES, renderSection.getIndiceCount(), GL_UNSIGNED_INT, 0);
				
				glDisableVertexAttribArray(Shader.ATTRIBUTE_LOC_POSITIONS);
				glDisableVertexAttribArray(Shader.ATTRIBUTE_LOC_TEXCOORDS);
			}

			Shader.unbind();
		}
	}

	public boolean isAcceptedShader(Shader shader) { return true; }
	public int getRenderStage() { return RenderEngine.RENDER_STEP_PRIMARY; }
}
