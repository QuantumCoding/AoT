package org.AoT.Sound;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.util.WaveData;

import com.Engine.Util.Vectors.Vector3f;

public class SoundMaster {
private static ArrayList <Integer> buffers = new ArrayList<Integer>();
	
	public static void init(){
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
	}
	
	public static void setListenerData(Vector3f position){
		AL10.alListener3f(AL10.AL_POSITION, position.getX(), position.getY(), position.getZ());
//		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String file){
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = WaveData.create(file);
		System.out.println(file);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp(){
		for(int buffer : buffers)
			AL10.alDeleteBuffers(buffer);
		AL.destroy();
	}

}
