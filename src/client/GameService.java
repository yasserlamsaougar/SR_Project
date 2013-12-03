package client;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.PlayerData;

public interface GameService extends Remote {

	public void addPlayer(int id, Point position) throws RemoteException;

	public void removePlayer(int id) throws RemoteException;

	public void updatePosition(PlayerData playerData) throws RemoteException;

	public void updateMap(Point position) throws RemoteException;
	
	public void updateScore(int score) throws RemoteException;
	
	public void win() throws RemoteException;
	
	public void lose() throws RemoteException;

}
