package org.AoT.Multiplayer.Server;

import org.AoT.Multiplayer.Packets.Packet;

public interface ServerPacketListener {
	public void packetReceived(ServerClient c, Packet p);
}
