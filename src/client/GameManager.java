package client;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
<<<<<<< HEAD
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
=======
import java.rmi.RemoteException;
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import server.ServerService;
<<<<<<< HEAD
import shared.CONSTANTS;
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
import shared.MapData;
import shared.PlayerData;

public class GameManager extends UnicastRemoteObject implements GameService,
		Serializable {

	private JFrame frame;
	private GameScreen gameScreen;
	private ServerService server;
<<<<<<< HEAD
=======
	private LobbyScreen lobbyScreen;
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

	public GameManager(ServerService server) throws RemoteException {
		super();
		try {
			UnicastRemoteObject.setLog(new FileOutputStream(new File(
					"ClientLog.log")));
			this.server = server;
			MapData mapData = server.getMap();
			this.frame = new JFrame();
<<<<<<< HEAD
=======
			lobbyScreen = new LobbyScreen(mapData.getMapWidth(), 20, this);
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
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
	public void updatePlayer(Point position) throws RemoteException {
		gameScreen.updateMap(position);
		frame.repaint();
	}

	@Override
	public void win() throws RemoteException {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int n = JOptionPane.showConfirmDialog(gameScreen,
						" YOU WIN Would you like to replay?",
						"An Inane Question", JOptionPane.YES_NO_OPTION);

				try {
					if (n == JOptionPane.YES_OPTION) {

						gameScreen
								.generateMap(server.getMap().getTargetArray());
						frame.repaint();
					}

					else {
						server.disconnect(gameScreen.getOwnId());
						System.exit(0);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void lose() throws RemoteException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int n = JOptionPane
						.showConfirmDialog(
								gameScreen,
								" YOU LOSE WOULD YOU LIKE TO REPLAY Would you like to replay?",
								"An Inane Question", JOptionPane.YES_NO_OPTION);

				try {
					if (n == JOptionPane.YES_OPTION) {
						gameScreen
								.generateMap(server.getMap().getTargetArray());
						frame.repaint();
					}

					else {
						server.disconnect(gameScreen.getOwnId());
						System.exit(0);
					}

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void updateScore(int score) throws RemoteException {
		gameScreen.updateScore(score);
	}

	public void ready() {
		try {
			gameScreen.setOwnId(server.connect(this));
<<<<<<< HEAD
=======
			lobbyScreen.addToModel("User" + gameScreen.getOwnId());
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			server.disconnect(gameScreen.getOwnId());
<<<<<<< HEAD
=======
			lobbyScreen.removeFromModel("User" + gameScreen.getOwnId());
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	
	public static void reconnect(ServerService service) {
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
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

}
