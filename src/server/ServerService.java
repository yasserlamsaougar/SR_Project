package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.MapData;
import client.GameService;

public interface ServerService extends Remote {

	public int connect(GameService remote) throws RemoteException;
	
	public void disconnect(int id) throws RemoteException;
	
	public MapData getMap() throws RemoteException;
	
	public void move(int id, int[] direction) throws RemoteException;
	
}
