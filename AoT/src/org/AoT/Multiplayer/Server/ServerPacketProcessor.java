package org.AoT.Multiplayer.Server;

import java.io.IOException;

import org.AoT.Multiplayer.Packets.InvalidPacketIDException;
import org.AoT.Multiplayer.Packets.Packet;
import org.AoT.Multiplayer.Packets.TerminationPacket;

public class ServerPacketProcessor {
	
	public static void sendPacket(ServerClient sc, Packet p){
		try {
			p.send(sc.getOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Packet readPacket(ServerClient sc) throws IOException{
		int id = sc.getIn().readInt();
		
		Packet packet;
		
		switch(id){
			case TerminationPacket.ID : packet = new TerminationPacket(); break;
			default : throw new InvalidPacketIDException(id);
		}
		packet.read(sc.getIn());
		return packet;
	}
}

