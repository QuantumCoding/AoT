package org.AoT.Map.Buildings.Render;

import org.lwjgl.util.vector.Matrix4f;

import com.Engine.RenderEngine.Shaders.Shader;

public class BuildingShader extends Shader {
	private static final String VERTEX_SHADER_LOC = "res/shaders/buildings/BuildingShader.vsh";
	private static final String FRAGMENT_SHADER_LOC = "res/shaders/buildings/BuildingShader.fsh";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	private int location_texture0;
	
	public BuildingShader() {
		super(VERTEX_SHADER_LOC, FRAGMENT_SHADER_LOC, BuildingRenderer.class);
	}

	protected void initUniformLocations() {
		bind();
		
		
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix =  super.getUniformLocation("viewMatrix");

		location_texture0 = super.getUniformLocation("texture0");
	}

	protected void bindAttributies() {
		bind();
		
		super.bindAttribute(ATTRIBUTE_LOC_POSITIONS, "position");
		super.bindAttribute(ATTRIBUTE_LOC_TEXCOORDS, "texCoord");
//		super.bindAttribute(ATTRIBUTE_LOC_NORMALS,   "normal");
		
		super.loadInt(location_texture0, 0);
	}
	
	public void loadTransformationMatrix(Matrix4f transformationMatrix) {
		super.loadMatrix(location_transformationMatrix, transformationMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projectionMatrix) {
		super.loadMatrix(location_projectionMatrix, projectionMatrix);
	}
	
	public void loadViewMatrix(Matrix4f viewMatrix) {
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
}
