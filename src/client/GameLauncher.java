package client;

<<<<<<< HEAD
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.ServerService;
=======
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import server.ServiceImplementation;
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
import shared.CONSTANTS;

public class GameLauncher {

	public static void main(String[] args) {

		System.out.println("Connecting...");
<<<<<<< HEAD
		ServerService service;
		String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
				+ "/" + CONSTANTS.ServiceName;
		try {
			Registry registry = LocateRegistry.getRegistry(null, CONSTANTS.servicePort);
			service = (server.ServerService) registry.lookup(serverObjectName);
			GameManager game = new GameManager(service);
=======
		server.ServerService service;
		String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
				+ "/" + CONSTANTS.ServiceName;
		try {
			service = (server.ServerService) Naming.lookup(serverObjectName);
			GameManager game = new GameManager(service);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
<<<<<<< HEAD
		
=======
		//
		// the next two lines allow the server to call updateBoard:
		//
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
	}
}
