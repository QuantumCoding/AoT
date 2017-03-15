package org.AoT.Multiplayer.Client;

import org.AoT.Multiplayer.Packets.Packet;

public interface ClientPacketListener {
	public void packetReceived(Client c, Packet p);
}
