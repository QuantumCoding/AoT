package org.AoT.Multiplayer.Packets;

public class InvalidPacketIDException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidPacketIDException(int id){
		super("Ivalid Id: " + id);
	}
}
