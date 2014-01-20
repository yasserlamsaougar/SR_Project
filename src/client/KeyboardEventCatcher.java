package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.ServerService;
import shared.CONSTANTS;

public class KeyboardEventCatcher extends KeyAdapter {

	private GameScreen g;
	private GameManager gameManager;
	private ServerService service;
	
	public KeyboardEventCatcher(GameScreen g, GameManager gameManager, ServerService service) {
		this.g = g;
		this.gameManager = gameManager;
		this.service = service;
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		int[] direction = g.getKeyCode(keyCode);
		
		if (direction == null)
			return;
		
		try {
			service.move(g.getOwnId(), direction);
			g.repaint();
		} catch (RemoteException e1) {
			String serverObjectName = "rmi://localhost:" + CONSTANTS.b_servicePort
					+ "/" + CONSTANTS.b_ServiceName;
			try {
				Registry registry = LocateRegistry.getRegistry(null, CONSTANTS.b_servicePort);
				service = (ServerService) registry.lookup(serverObjectName);
				service.reconnect(gameManager, g.getOwnId());
			} catch (RemoteException | NotBoundException e) {
				
			}
		}

	}
}
