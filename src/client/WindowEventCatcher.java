package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import server.ServerService;

public class WindowEventCatcher extends WindowAdapter {

	private GameScreen g;
	private ServerService service;
	public WindowEventCatcher(GameScreen g, ServerService service) {
		this.g = g;
		this.service = service;
	}

	@Override
	public void windowClosing(WindowEvent event) {
		try {
			System.out.println("disconnecting");
			service.disconnect(g.getOwnId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
