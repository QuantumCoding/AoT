package org.AoT.Entities.Moving;

import java.util.LinkedList;

import org.AoT.Entities.Entity;
import org.AoT.Start.Engine;

import com.Engine.PhysicsEngine.Bodies.MovingBody;
import com.Engine.PhysicsEngine.Detection.Intersection.IntersectionResult;
import com.Engine.Util.Vectors.Vector3f;

public abstract class MovingEntity extends Entity{

	protected MovingBody movingBody;
	
	protected MovingEntity(Engine engine) {
		super(engine);
		
		movingBody = (MovingBody)physicsBody;
	}

	public MovingBody getMovingBody(){
		return movingBody;
	}
	
	public LinkedList<Vector3f> getAllForces(){
		return movingBody.getAllforce();
	}
	
	public IntersectionResult getIntersection(){
		return movingBody.getIntersection();
	}
	
	public Vector3f getVelocity(){
		return movingBody.getVelocity();
	}
	
	public void addForce(Vector3f force){
		movingBody.addForce(force);
	}
	
	public void addRotation(Vector3f rot){
		movingBody.addRotation(rot);
	}
	
	public void setPosition(Vector3f position){
		movingBody.setPosition(position);
	}
	
	public void setRotation(Vector3f rot){
		movingBody.setRotation(rot);
	}
	
	public void setVelocity(Vector3f vel){
		movingBody.setVelocity(vel);
	}
	
	public void setIntersection(IntersectionResult intersection){
		movingBody.setIntersection(intersection);
	}
}
