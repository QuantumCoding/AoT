package org.AoT.Multiplayer.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TerminationPacket extends Packet {

	public static final int ID = 0;
	
	public TerminationPacket(){
		
	}
	
	public void send(DataOutputStream out) throws IOException {
		out.writeInt(ID);
		
	}

	public void read(DataInputStream in) {
		System.out.println("STAHP");
	}

}
