package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
<<<<<<< HEAD
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
=======
import java.rmi.RemoteException;
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

import javax.swing.JFrame;

import server.ServerService;
<<<<<<< HEAD
import shared.CONSTANTS;
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

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
<<<<<<< HEAD
			String serverObjectName = "rmi://localhost:" + CONSTANTS.servicePort
					+ "/" + CONSTANTS.ServiceName;
			try {
				Registry registry = LocateRegistry.getRegistry(null, CONSTANTS.servicePort);
				service = (ServerService) registry.lookup(serverObjectName);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
			// TODO Auto-generated catch block
			e1.printStackTrace();
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
		}

	}
}
