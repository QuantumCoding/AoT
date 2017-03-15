package org.AoT.Sound;

import java.io.IOException;

public class AudioTester {
	public static void main(String[] args) throws IOException{
		SoundMaster.init();
		SoundMaster.setListenerData();
		
		int buffer = SoundMaster.loadSound("/sound/titan.mp3");
		SoundSource source = new SoundSource();
		
		char c = ' ';
		while(c != 's'){
			c = (char)System.in.read();
			
			if(c == 'p'){
				source.play(buffer);
			}
		}
		
		source.delte();
		SoundMaster.cleanUp();
	}
}