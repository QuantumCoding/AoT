package org.AoT.Input;

import org.AoT.Start.Engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.Engine.RenderEngine.Util.Camera;
import com.Engine.Util.Vectors.Vector3f;

public class CameraMovement {
	private Engine engine;
	private Camera camera;
	
	private float movementSpeed;
	private float jumpSpeed;
	private float mouseSensetivity;
	
	private boolean escapeLastFrame;
	
	public CameraMovement(Engine engine, float movementSpeed, float jumpSpeed, float mouseSensitivity){
		this.engine = engine;
		this.camera = engine.getCamera();
		
		this.movementSpeed = movementSpeed;
		this.jumpSpeed = jumpSpeed;
		this.mouseSensetivity = mouseSensitivity;
		
		Mouse.setGrabbed(true);
	}
	
	public void update(float delta) {
		Vector3f caped = camera.getRotation().capMax(100).capMin(-100);
		camera.rotX = caped.x;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !escapeLastFrame){
			Mouse.setGrabbed(!Mouse.isGrabbed());
			escapeLastFrame = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			escapeLastFrame = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			camera.moveForward(movementSpeed * delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			camera.moveRight(-movementSpeed * delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			camera.moveForward(-movementSpeed * delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			camera.moveRight(movementSpeed * delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			camera.moveUp(jumpSpeed * delta);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			camera.moveUp(-jumpSpeed * delta);
		}
		
		if(Mouse.isGrabbed()){
			if(Math.abs(camera.rotX) <= 100){
				camera.rotateX(-Mouse.getDY() * mouseSensetivity);
			}
			camera.rotateY(Mouse.getDX() * mouseSensetivity);
		}
	}
}