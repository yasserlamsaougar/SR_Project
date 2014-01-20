package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.ServerService;
import shared.CONSTANTS;

public class WindowEventCatcher extends WindowAdapter {

	private GameScreen g;
	private ServerService service;
	private GameManager gameManager;
	public WindowEventCatcher(GameScreen g, GameManager gameManager ,ServerService service) {
		this.g = g;
		this.gameManager = gameManager;
		this.service = service;
	}

	@Override
	public void windowClosing(WindowEvent event) {
		try {
			System.out.println("disconnecting");
			service.disconnect(g.getOwnId());
		} catch (RemoteException e) {
			String serverObjectName = "rmi://localhost:" + CONSTANTS.b_servicePort
					+ "/" + CONSTANTS.b_ServiceName;
			try {
				Registry registry = LocateRegistry.getRegistry(null, CONSTANTS.b_servicePort);
				service = (ServerService) registry.lookup(serverObjectName);
				service.reconnect(gameManager, g.getOwnId());
				service.disconnect(g.getOwnId());
			} catch (RemoteException | NotBoundException exception) {
				
			}
		}
	}

}
