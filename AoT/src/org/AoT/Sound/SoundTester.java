package org.AoT.Sound;

import java.io.IOException;

import com.Engine.Util.Vectors.Vector3f;

public class SoundTester {
	public static void main(String[] args) throws IOException, InterruptedException{
		SoundMaster.init();
		SoundMaster.setListenerData(new Vector3f(0,0,0));
		
		int buffer = SoundMaster.loadSound("sound/bounce.wav");
		
		SoundSource source = new SoundSource();
		source.setLooping(true);
		source.play(buffer);
		
//		float xPos = 8;
		source.setPosition(new Vector3f(0, 0, 1));
		
		char c = ' ';
		while(c != 's'){
//			xPos -= .05f;
//			source.setPosition(new Vector3f(xPos, 0, 1));
//			Thread.sleep(10);
		}
		
		source.delete();
		SoundMaster.cleanUp();
	}
}