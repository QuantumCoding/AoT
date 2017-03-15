package org.AoT.Multiplayer.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.AoT.Multiplayer.Packets.Packet;
import org.AoT.Multiplayer.Packets.TerminationPacket;

public class Client implements Runnable, ClientPacketListener{
	private Socket serverConnection;
	private DataInputStream in;
	private DataOutputStream out;
	private Thread thread;
	private ArrayList<ClientPacketListener> listeners;
	private boolean running = true;
	
	public Client(String ip, int port) throws UnknownHostException, IOException{
		serverConnection = new Socket(Inet4Address.getByName(ip), port);
		in = new DataInputStream(serverConnection.getInputStream());
		out = new DataOutputStream(serverConnection.getOutputStream());
		thread = new Thread(this, "Client Thread - " + ip);
		thread.start();
		listeners = new ArrayList<ClientPacketListener>();
		addPacketListener(this);
	}
	
	public void run() { // TODO handshake
		while(!serverConnection.isClosed()){
			try {
				notifyPacketListeners(this, read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void terminate(){
//		while(thread != null){
//			try {
//				thread.interrupt();
//				thread.join();
//				thread = null;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		running = false;
		
		try {
			in.close();
			out.close();
			serverConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public void addPacketListener(ClientPacketListener pl){
		listeners.add(pl);
	}
	
	public void removePacketListener(ClientPacketListener pl){
		listeners.remove(pl);
	}
	
	public void notifyPacketListeners(Client c, Packet p){
		for(ClientPacketListener pl: listeners){
			pl.packetReceived(c, p);
		}
	}
	
	public void packetReceived(Client c, Packet p) {
		if(p instanceof TerminationPacket){
			terminate();
		}
	}
	
	public Packet read() throws IOException{
		try {
			return ClientPacketProcessor.readPacket(this);
		} catch (Exception e) {
			if(!serverConnection.isClosed()){
				throw e;
			}
			else{
				return null;
			}
		}
	}
	
	public void write(Packet p) throws IOException{
		ClientPacketProcessor.sendPacket(this, p);
	}
	
	public DataInputStream getIn(){return in;}
	public DataOutputStream getOut(){return out;}
}
