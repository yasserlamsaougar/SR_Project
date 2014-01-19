package client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.ServerService;
import shared.CONSTANTS;

public class GameLauncher {

	public static void main(String[] args) {

		System.out.println("Connecting...");
		ServerService service;
		String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
				+ "/" + CONSTANTS.ServiceName;
		try {
			Registry registry = LocateRegistry.getRegistry(null,
					CONSTANTS.servicePort);
			service = (server.ServerService) registry.lookup(serverObjectName);
			GameManager game = new GameManager(service);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}