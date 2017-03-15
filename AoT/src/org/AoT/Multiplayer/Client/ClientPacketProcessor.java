package org.AoT.Multiplayer.Client;

import java.io.IOException;

import org.AoT.Multiplayer.Packets.InvalidPacketIDException;
import org.AoT.Multiplayer.Packets.Packet;
import org.AoT.Multiplayer.Packets.TerminationPacket;

public class ClientPacketProcessor {
	
	public static void sendPacket(Client c, Packet p){
		try {
			p.send(c.getOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Packet readPacket(Client c) throws IOException{
		int id = c.getIn().readInt();
		
		Packet packet;
		
		switch(id){
			case TerminationPacket.ID : packet = new TerminationPacket(); break;
			default : throw new InvalidPacketIDException(id);
		}
		packet.read(c.getIn());
		return packet;
	}
}
