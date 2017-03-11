package org.AoT.Start;

import org.lwjgl.LWJGLException;

public class AoT_Launcher {
	public static void main(String[] args) throws LWJGLException {
		AoT game = new AoT();
		
		try { 
			game.init();
			game.run();
		} catch(Throwable t) { t.printStackTrace(); }
		
		game.cleanUp();
		System.exit(0);
	}
}
