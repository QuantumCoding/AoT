package org.AoT.Multiplayer;

import java.io.IOException;

import org.AoT.Multiplayer.Client.Client;
import org.AoT.Multiplayer.Packets.TerminationPacket;
import org.AoT.Multiplayer.Server.Server;

public class StartMultiplayer {
	public static void main(String[] args) {
		try {
			Server server = new Server("127.0.0.1", 3487, 2);
			server.start();
			Client client1 = new Client("127.0.0.1", 3487);
			client1.write(new TerminationPacket());
			client1.terminate();
//			Thread.sleep(2000);
			server.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
