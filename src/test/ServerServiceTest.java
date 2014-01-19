package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.Point;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import server.ServerService;
import server.ServiceImplementation;
import client.GameService;
public class ServerServiceTest {

	static ServerService service;
	@Before
	public void initiateServer() {
		
		try {
			service = new ServiceImplementation();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testConnect() {
			GameService remote1 = mock(GameService.class);
			GameService remote2 = mock(GameService.class);
			try {
				service.connect(remote1);
				service.connect(remote2);
				assertEquals(2, service.testConnect());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@Test
	public void testMove() {
		GameService remote1 = mock(GameService.class);
		try {
			int id = service.connect(remote1);
			Point position = service.getPlayerPosition(id);
			position = new Point(position.x + 1, position.y);
			service.move(id, new int[] {1, 0});
			assertEquals(position.x, service.getPlayerPosition(id).x);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDisconnect() {
		GameService remote1 = mock(GameService.class);
		GameService remote2 = mock(GameService.class);
		try {
			int id1 = service.connect(remote1);
			int id2 = service.connect(remote2);
			
			service.disconnect(id1);
			
			assertEquals(1, service.testConnect());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
