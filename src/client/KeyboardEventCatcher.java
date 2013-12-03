package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import server.ServerService;

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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
