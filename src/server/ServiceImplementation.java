package server;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;

import shared.MapData;
import shared.PlayerData;
import client.GameService;

public class ServiceImplementation extends UnicastRemoteObject implements
		ServerService {

	private final Hashtable<Integer, GameService> table;
	private final PlayerStore store;
	protected GameMap map;

	protected ServiceImplementation() throws RemoteException {
		super();
		table = new Hashtable<>();
		map = new GameMap(100, 20, 20);
		store = new PlayerStore();
		map.generateMap();
		try {
			UnicastRemoteObject.setLog(new FileOutputStream("GameServer.LOG"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int connect(GameService remote) throws RemoteException {

		Point point = map.giveMeFreePosition();
		int oldId = store.getLastId();
		for (Entry<Integer, GameService> r : table.entrySet()) {
			GameService iterable = r.getValue();
			iterable.addPlayer(oldId, point);
		}
		table.put(oldId, remote);
		store.addPlayer(oldId, point);
		for (Entry<Integer, LinkedList<Point>> r : store.getPlayerPositions()) {
			Point player = store.getPlayerPosition(r.getKey());
			remote.addPlayer(r.getKey(), player);
		}
		return oldId;
	}

	@Override
	public void disconnect(int id) throws RemoteException {
		table.remove(id);
		store.removePlayer(id);
		for (GameService remote : table.values()) {
			remote.removePlayer(id);
		}
	}

	@Override
	public MapData getMap() throws RemoteException {
		MapData mapData = new MapData(map.MapWidth, map.MapHeight,
				map.targetArray);
		return mapData;
	}

	@Override
	public void move(int id, int[] direction) throws RemoteException {
		Point point = store.getPlayerPosition(id);
		point.x = (point.x + direction[0] + map.MapWidth) % map.MapWidth;
		point.y = (point.y + direction[1] + map.MapWidth) % map.MapWidth;
		if (map.erasePoint(point.x, point.y)) {
			store.incrementScore(id);
			for (Entry<Integer, GameService> r : table.entrySet()) {
				GameService iterable = r.getValue();
				iterable.updatePosition(new PlayerData(point, store
						.getPlayerScore(r.getKey()), id));
				iterable.updateMap(point);
				table.get(id).updateScore(store.getPlayerScore(id));
			}
			if (map.isEmpty()) {
				int winner = store.getWinner();
				GameService remote = table.remove(winner);
				for(GameService r : table.values())
					r.lose();
				remote.win();
				table.put(winner, remote);
			}
		} else {
			for (Entry<Integer, GameService> r : table.entrySet()) {
				GameService iterable = r.getValue();
				iterable.updatePosition(new PlayerData(point, store
						.getPlayerScore(r.getKey()), id));
			}
		}

	}
}
