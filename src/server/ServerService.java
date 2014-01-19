package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.MapData;
import client.GameService;

public interface ServerService extends Remote {

	/**
	 * initiates the server client relationship
	 * @param remote : The Player to be added
	 * @return The player's id
	 * @throws RemoteException
	 */
	public int connect(GameService remote) throws RemoteException;
	/**
	 * Disconnects the player with the id from the server
	 * @param id : The player's id
	 * @throws RemoteException
	 */
	public void disconnect(int id) throws RemoteException;
	
	/**
	 * 
	 * @return MapData : {@link MapData}
	 * @throws RemoteException
	 */
	public MapData getMap() throws RemoteException;
	
	/**
	 * Translates The player using a direction parameter
	 * @param id The player's id
	 * @param direction : The Direction to take Example: {-1, 0} for left
	 * @throws RemoteException
	 */
	public void move(int id, int[] direction) throws RemoteException;
	
	/**
	 * Test Utility Method
	 * @param id
	 * @return player position
	 */
	public Point getPlayerPosition(int id) throws RemoteException;
	
	/**
	 * Test Utility Method
	 * @return {@link GameService} list size
	 */
	public int testConnect() throws RemoteException;
	
}
