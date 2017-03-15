package org.AoT.Sound;

import org.lwjgl.openal.AL10;

import com.Engine.Util.Vectors.Vector3f;

public class SoundSource {
	private int sourceId;
	
	public SoundSource(){
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}
	
	/*
	 * Plays the sound
	 * Will start from the beginning of the sound file
	 */
	public void play(int buffer){
		stop();
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		resumePlaying();
	}
	
	/*
	 * Pauses the source sound, does not delete it
	 * Implies that it will be resumed later on, and will start from where it is now paused (resumePlayeing)
	 */
	public void pause(){
		AL10.alSourcePause(sourceId);
	}
	
	/*
	 * Resumes playing the source sound
	 * Implies that the sound was paused, and is starting from where it was paused
	 */
	public void resumePlaying(){
		AL10.alSourcePlay(sourceId);
	}
	
	/*
	 * Fully stops the source sound
	 * If started again, will start from beginning
	 */
	public void stop(){
		AL10.alSourceStop(sourceId);
	}
	
	public void delete(){
		stop();
		AL10.alDeleteSources(sourceId);
	}
	
	public void setVolume(float volume){
		AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
	}
	
	public void setPitch(float pitch){
		AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
	}
	
	public void setPosition(Vector3f position){
		AL10.alSource3f(sourceId, AL10.AL_POSITION, position.getX(), position.getY(), position.getZ());
	}
	
	public void setVelocity(Vector3f velocity){
		AL10.alSource3f(sourceId, AL10.AL_VELOCITY, velocity.getX(), velocity.getY(), velocity.getZ());
	}
	
	public void setLooping(boolean loopSound){
		AL10.alSourcei(sourceId, AL10.AL_LOOPING, loopSound ? AL10.AL_TRUE : AL10.AL_FALSE);
	}
	
	public void setDirection(Vector3f direction){
		AL10.alSource3f(sourceId, AL10.AL_DIRECTION, direction.getX(), direction.getY(), direction.getZ());
	}
	
	public boolean isPlaying(){
		return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}
}
