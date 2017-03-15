package org.AoT.Multiplayer.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.AoT.Multiplayer.Packets.Packet;

public class ServerClient implements Runnable{
	private Socket clientConnection;
	private DataOutputStream out;
	private DataInputStream in;
	private Thread thread;
	private Server server;
	
	public ServerClient(Server server, Socket socket) throws IOException{
		clientConnection = socket;
		this.server = server;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		thread = new Thread(this, "ServerClient Thread - " + socket.getInetAddress().getHostName());
		thread.start();
	}

	public void run() { // TODO handshake
		while(!clientConnection.isClosed()){
			try {
				server.notifyPacketListeners(this, read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void terminate(){
		try {
			in.close();
			out.close();
			clientConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		server.clientTerminated(this);
	}
	
	public Packet read() throws IOException{
		return ServerPacketProcessor.readPacket(this);
	}
	
	public void write(Packet p) throws IOException{
		ServerPacketProcessor.sendPacket(this, p);
	}
	
	public DataInputStream getIn(){return in;}
	public DataOutputStream getOut(){return out;}
}
