package org.AoT.Input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.Engine.PhysicsEngine.Bodies.MovingBody;
import com.Engine.Util.Vectors.Vector3f;

public class PlayerMovement {
	private MovingBody movingBody;
	private float speed;
	private boolean spaceLastFrame;
	private float mouseSensetivity;
	private boolean escapeLastFrame;
	
	public PlayerMovement(MovingBody movingBody, float mouseSensetivity){
		this.movingBody = movingBody;
		this.mouseSensetivity = mouseSensetivity;
		speed = 1;
		Mouse.setGrabbed(true);
	}
	
	public void update(float delta){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !escapeLastFrame){
			Mouse.setGrabbed(!Mouse.isGrabbed());
			escapeLastFrame = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			escapeLastFrame = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			movingBody.addForce(new Vector3f(0,0,speed * delta).rotate(new Vector3f(0,movingBody.getRotation().y+90,0)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			movingBody.addForce(new Vector3f(0,0,-speed * delta).rotate(new Vector3f(0,movingBody.getRotation().y+90,0)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			movingBody.addForce(new Vector3f(-speed * delta,0,0).rotate(new Vector3f(0,movingBody.getRotation().y,0)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			movingBody.addForce(new Vector3f(speed * delta,0,0).rotate(new Vector3f(0,movingBody.getRotation().y,0)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !spaceLastFrame){
			movingBody.addForce(new Vector3f(100,0,0));
			spaceLastFrame = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			spaceLastFrame = false;
		}
		
		if(Mouse.isGrabbed()){
			if(Math.abs(movingBody.getRotation().x) <= 100){
				movingBody.addRotation(new Vector3f(-Mouse.getDY() * mouseSensetivity, 0, 0));
			}
			movingBody.addRotation(new Vector3f(0, Mouse.getDX() * mouseSensetivity, 0));
		}
	}
}
