package client;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.PlayerData;

public interface GameService extends Remote {
	
	/**
	 * Adds a player to the map
	 * @param id : The id of the player
	 * @param position : The position of the player
	 * @throws RemoteException 
	 */
	public void addPlayer(int id, Point position) throws RemoteException;

	/**
	 * Removes the player from the map
	 * @param id : The player's id
	 * @throws RemoteException
	 */
	public void removePlayer(int id) throws RemoteException;

	/**
	 * 
	 * @param playerData : {@link PlayerData}
	 * @throws RemoteException
	 */
	public void updatePosition(PlayerData playerData) throws RemoteException;

	/**
	 * updates the The player's position
	 * @param position
	 * @throws RemoteException
	 */
	public void updateMap(Point position) throws RemoteException;
	
	/**
	 * updates the player's score
	 * @param score
	 * @throws RemoteException
	 */
	public void updateScore(int score) throws RemoteException;
	
	/**
	 * Win behavior
	 * @throws RemoteException
	 */
	public void win() throws RemoteException;
	
	/**
	 * lose Behavior
	 * @throws RemoteException
	 */
	public void lose() throws RemoteException;

}
