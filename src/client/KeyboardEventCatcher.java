package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;

import server.ServerService;
import shared.CONSTANTS;

public class KeyboardEventCatcher extends KeyAdapter {

	private GameScreen g;
	private ServerService service;
	
	public KeyboardEventCatcher(GameScreen g, ServerService service) {
		this.g = g;
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
			String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
					+ "/" + CONSTANTS.ServiceName;
			try {
				Registry registry = LocateRegistry.getRegistry(null, CONSTANTS.servicePort);
				service = (ServerService) registry.lookup(serverObjectName);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
