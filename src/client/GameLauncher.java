package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import server.ServiceImplementation;
import shared.CONSTANTS;

public class GameLauncher {

	public static void main(String[] args) {

		System.out.println("Connecting...");
		server.ServerService service;
		String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
				+ "/" + CONSTANTS.ServiceName;
		try {
			service = (server.ServerService) Naming.lookup(serverObjectName);
			GameManager game = new GameManager(service);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// the next two lines allow the server to call updateBoard:
		//
	}
}
