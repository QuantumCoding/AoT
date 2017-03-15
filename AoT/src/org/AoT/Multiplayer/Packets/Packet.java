package org.AoT.Multiplayer.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
	protected Packet(){}
	
	public abstract void send(DataOutputStream out) throws IOException;
	public abstract void read(DataInputStream in) throws IOException;
}
