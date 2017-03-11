package com.Engine.RenderEngine.Window;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL20.GL_SHADING_LANGUAGE_VERSION;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import com.Engine.Util.Time;

public class Window {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final int DEFAULT_FPS = 60;
		
	private int fps;
	private String title;
	private ByteBuffer[] icon;
	
	private double frameTime;
	private long lastFrameTime;
	
	public Window() {
		fps = DEFAULT_FPS;
	}
	
	public void initDisplay() throws LWJGLException {
		initDisplay(DEFAULT_WIDTH, DEFAULT_HEIGHT);}
	public void initDisplay(int width, int height) throws LWJGLException {
		if(Display.isCreated()) { destroy(); }
		
		ContextAttribs attribs = new ContextAttribs(3, 3)
		.withForwardCompatible(true).withProfileCore(true);
		
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create(new PixelFormat(), attribs);
		
//		AL.create();
		Mouse.create();
		Keyboard.create();
//		Controllers.create();
	}
	
	public void update() {
		Display.update();
		Display.sync(fps);
		
		frameTime = (double)(Time.getSystemTime() - lastFrameTime) / (double)Time.SECONDS;
		lastFrameTime = Time.getSystemTime();
	}
	
	public double getFrameTime() {
		return frameTime;
	}
	
	public void destroy() {
		Display.destroy();
		
		Mouse.destroy();
		Keyboard.destroy();
		Controllers.destroy();
	}

	public void setFullscreen(boolean fullscreen) throws LWJGLException { Display.setFullscreen(fullscreen) ;}
	public void setTitle(String title) { this.title = title; Display.setTitle(title); }
	public void setFPS(int fps) { this.fps = fps; }
	public void setIcon(BufferedImage image) {
		icon = IconLoader.load(image);
		Display.setIcon(icon);
	}
	
	public boolean isCloseRequested() {return Display.isCloseRequested(); }
	public boolean isFullscreen() {return Display.isFullscreen(); }	
	public String getTitle() { return title; }
	public int getWidth() { return Display.getWidth(); }
	public int getHeight() { return Display.getHeight(); }
	public int getFPS() { return fps; }
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}
	
	public static String getGLSLVersion() {
		return glGetString(GL_SHADING_LANGUAGE_VERSION);
	}
}
