package org.AoT.Multiplayer.Server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.AoT.Multiplayer.Packets.Packet;
import org.AoT.Multiplayer.Packets.TerminationPacket;

public class Server implements ServerPacketListener{
	
	private Inet4Address ip;
	private int port;
	private int max;
	
	private ServerSocket server;
	
	private Thread thread;
	
	private ServerClient[] clients;
	private int connections;
	
	private ArrayList<ServerPacketListener> listeners;
	
	public Server(String ip, int port, int max) throws UnknownHostException{
		this.port = port;
		this.max = max;
		
		this.ip = (Inet4Address) Inet4Address.getByName(ip);
		
		clients = new ServerClient[max];
		listeners = new ArrayList<ServerPacketListener>();
		addPacketListener(this);
	}
	
	public void addPacketListener(ServerPacketListener pl){
		listeners.add(pl);
	}
	
	public void removePacketListener(ServerPacketListener pl){
		listeners.remove(pl);
	}
	
	public void notifyPacketListeners(ServerClient c, Packet p){
		for(ServerPacketListener pl: listeners){
			pl.packetReceived(c, p);
		}
	}
	
	public void start() throws IOException{
		server = new ServerSocket(port, max, ip);
		thread = new Thread(() -> {
			while(!server.isClosed()){
				try {
					Socket clientConnection = server.accept();
					ServerClient client = new ServerClient(this, clientConnection);
					if(connections >= max){
						client.terminate();
						continue;
					}
					connections++;
					for(int i = 0; i < clients.length; i++){
						if(clients[i] == null){
							clients[i] = client;
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "Server Socket Thread");
		thread.start();
	}
	
	public void stop(){
		for(ServerClient client : clients){
			if(client != null){
				client.terminate();
			}
		}

		try {
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			thread.interrupt();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clientTerminated(ServerClient client){
		for(int i = 0; i < clients.length; i++){
			if(clients[i] == client){
				clients[i] = null;
				connections--;
				System.out.println("Client Terminated, :(");
				return;
			}
		}
	}
	
	public void packetReceived(ServerClient c, Packet p) {
		if(p instanceof TerminationPacket){
			c.terminate();
		}
	}
}
