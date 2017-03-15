package org.AoT.Sound;

import org.lwjgl.openal.AL10;

public class SoundSource {
	private int sourceId;
	
	public SoundSource(){
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}
	
	public void play(int buffer){
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceId);
	}
	
	public void delte(){
		AL10.alDeleteSources(sourceId);
	}
}
