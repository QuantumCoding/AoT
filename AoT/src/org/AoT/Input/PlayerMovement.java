package org.AoT.Input;

import org.AoT.Entities.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.Engine.PhysicsEngine.Bodies.MovingBody;
import com.Engine.Util.Vectors.Vector3f;

public class PlayerMovement {
	private Player player;
	private MovingBody movingBody;
	private float speed;
	private float mouseSensetivity;
	
	private boolean escapeLastFrame;
	private boolean spaceLastFrame;
	
	public PlayerMovement(Player player, MovingBody movingBody, float mouseSensetivity){
		this.player = player;
		this.movingBody = movingBody;
		this.mouseSensetivity = mouseSensetivity;
		speed = 1;
		Mouse.setGrabbed(true);
	}
	
	public void update(float delta){
		float shift = (float) Math.PI/2;
		float rotY = (float) Math.toRadians(movingBody.getRotation().y);
		
		Vector3f amount = new Vector3f(speed * delta, 0, speed * delta);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !escapeLastFrame){
			Mouse.setGrabbed(!Mouse.isGrabbed());
			escapeLastFrame = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			escapeLastFrame = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			amount = amount.multiply(2,0,2);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			movingBody.addForce(amount.multiply((float) -Math.cos(rotY + shift), 0, (float) -Math.sin(rotY + shift)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			movingBody.addForce(amount.multiply((float) Math.cos(rotY + shift), 0, (float)  Math.sin(rotY + shift)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			movingBody.addForce(amount.multiply((float) -Math.cos(rotY), 0, (float) -Math.sin(rotY)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			movingBody.addForce(amount.multiply((float) Math.cos(rotY), 0, (float) Math.sin(rotY)));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !spaceLastFrame){
			movingBody.addForce(new Vector3f(0, 3, 0));
			spaceLastFrame = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			spaceLastFrame = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			movingBody.setVelocity(new Vector3f());
			movingBody.setPosition(new Vector3f(0, 50, 0));
			movingBody.setRotation(new Vector3f());
		}
		
		if(Mouse.isGrabbed()){
			if(Math.abs(player.getCamera().rotX) <= 100){
				player.getCamera().rotateX((-Mouse.getDY() * mouseSensetivity));
//				movingBody.addRotation(new Vector3f(-Mouse.getDY() * mouseSensetivity, 0, 0));
			}
			
			Vector3f caped = player.getCamera().getRotation().capMax(100).capMin(-100);
			player.getCamera().rotX = caped.x;
			movingBody.addRotation(new Vector3f(0, Mouse.getDX() * mouseSensetivity, 0));
		}
	}
}
