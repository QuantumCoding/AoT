package org.AoT.Entities;

import java.util.ArrayList;

import org.AoT.Sound.SoundSource;
import org.AoT.Start.Engine;
import org.lwjgl.util.vector.Matrix4f;

import com.Engine.PhysicsEngine.Bodies.PhysicsBody;
import com.Engine.PhysicsEngine.Detection.Colliders.CollisionBody;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.Util.Vectors.Vector3f;

public abstract class Entity {
	protected PhysicsBody physicsBody;
	protected Vector3f position;
	protected SoundSource soundSource;
	protected Engine engine;	
	
	/**
	 * Generic Entity Constructor
	 * Only Used For Internal Construction
	 * Call Constructor Of More Specific Entity Type 
	 */
	protected Entity(Engine engine){
		this.engine = engine;
	}
	
	public PhysicsBody getPhysicsBody(){return physicsBody;}
	public SoundSource getSoundSource(){return soundSource;}
	public ArrayList<CollisionBody> getBodies(){return physicsBody.getBodies();}
	public Vector3f getPosition(){return position;}
	public Vector3f getRotation(){return physicsBody.getRotation();}
	public Vector3f getScale(){return physicsBody.getScale();}
	public Matrix4f getTransform(){return physicsBody.getTransform();}
	public Matrix4f getInverseTransform(){return physicsBody.getInverseTransform();}
	
	public void addCollisionBody(CollisionBody body){physicsBody.addBody(body);}
	
	public abstract void update(float delta);
	public abstract void render(Camera camera);
}
