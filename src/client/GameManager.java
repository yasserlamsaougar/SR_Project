package client;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFrame;

import server.ServerService;
import shared.MapData;
import shared.PlayerData;

public class GameManager extends UnicastRemoteObject implements GameService,
		Serializable {

	private JFrame frame;
	private GameScreen gameScreen;
	private ServerService server;

	public GameManager(ServerService server) throws RemoteException {
		super();
		try {
			UnicastRemoteObject.setLog(new FileOutputStream(new File(
					"ClientLog.log")));
			this.server = server;
			MapData mapData = server.getMap();
			this.frame = new JFrame();
			gameScreen = new GameScreen();
			gameScreen.setOwnId(server.connect(this))
					.setTargetNumber(mapData.getTargetArray().size())
					.setGridSize(mapData.getMapWidth()).setCellSize(20)
					.setTargetPositions(mapData.getTargetArray())
					.setMoveTable(setUpMoveTable()).build();
			setUpFrame();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setUpFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(30, 30);
		frame.addWindowListener(new WindowEventCatcher(gameScreen, server));
		gameScreen.setVisible(true);
		frame.add(gameScreen);
		frame.pack();
		frame.setVisible(true);
	}

	private Map<Integer, int[]> setUpMoveTable() {
		Map<Integer, int[]> moveTable = new Hashtable<>();
		moveTable.put(KeyEvent.VK_DOWN, new int[] { 0, +1 });
		moveTable.put(KeyEvent.VK_UP, new int[] { 0, -1 });
		moveTable.put(KeyEvent.VK_LEFT, new int[] { -1, 0 });
		moveTable.put(KeyEvent.VK_RIGHT, new int[] { +1, 0 });
		frame.addKeyListener(new KeyboardEventCatcher(gameScreen, server));
		return moveTable;
	}

	@Override
	public void addPlayer(final int id, final Point position)
			throws RemoteException {
		gameScreen.addPlayer(id, position);
		frame.repaint();
	}

	@Override
	public void removePlayer(int id) throws RemoteException {

		gameScreen.removePlayer(id);
		frame.repaint();
	}

	@Override
	public void updatePosition(PlayerData playerData) throws RemoteException {
		gameScreen.updatePlayer(playerData);
		frame.repaint();
	}

	@Override
	public void updateMap(Point position) throws RemoteException {
		gameScreen.updateMap(position);
		frame.repaint();
	}

	@Override
	public void win() throws RemoteException {
		System.out.println(" YOU WIN ");
	}

	@Override
	public void lose() throws RemoteException {
		System.out.println("YOU SUCK MAN");
	}

	@Override
	public void updateScore(int score) throws RemoteException {
		gameScreen.updateScore(score);
	}
}
