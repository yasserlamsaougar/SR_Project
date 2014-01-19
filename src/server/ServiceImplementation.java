package server;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import shared.MapData;
import shared.Pair;
import shared.PlayerData;
import client.GameService;

public class ServiceImplementation extends UnicastRemoteObject implements
		ServerService {

	/**
	 * This Map Stores the Players By Id
	 */
	private Hashtable<Integer, GameService> playerMap;

	/**
	 * This is where the scores and Positions are stored
	 */
	private PlayerStore store;

	/**
	 * The map that will be generated
	 */
	private GameMap map;

	/**
	 * 
	 */

	private GameState lastGameState;

	int numberOfSweets = 1;

	public ServiceImplementation() throws RemoteException {
		super();

	}

	// Must be called to setUp the game properties
	public void setUpServer(int numberOfSweets) {

		File mapFile = new File("map.json");
		playerMap = new Hashtable<>();
		store = new PlayerStore();
		if (mapFile.isFile()) {
			recoverMap();
			playerMap = new Hashtable<>();

		} else {
			this.numberOfSweets = numberOfSweets;

			map = new GameMap(numberOfSweets, 20, 20);
			map.generateMap();
			saveInitialState();
		}

		try {
			UnicastRemoteObject.setLog(new FileOutputStream("GameServer.LOG"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int connect(GameService remote) throws RemoteException {

		for (Integer key : store.getPlayersRaw().keySet()) {
			if (!playerMap.containsKey(key)) {
				store.removePlayer(key);
			}
		}

		Point point = map.giveMeFreePosition();
		int oldId = store.getLastId();
		for (Entry<Integer, GameService> r : playerMap.entrySet()) {
			GameService iterable = r.getValue();
			iterable.addPlayer(oldId, point);
		}
		playerMap.put(oldId, remote);
		store.addPlayer(oldId, point);

		for (Entry<Integer, Player> r : store.getPlayers()) {
			Point player = store.getPlayerPosition(r.getKey());
			remote.addPlayer(r.getKey(), player);

		}
		saveGameState();
		return oldId;
	}

	@Override
	public void disconnect(int id) throws RemoteException {

		for (GameService remote : playerMap.values()) {
			remote.removePlayer(id);
		}
		playerMap.remove(id);
		store.removePlayer(id);

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
		for (Entry<Integer, GameService> r : playerMap.entrySet()) {
			if (r.getKey() != id) {
				if (store.getPlayerPosition(r.getKey()).equals(point)) {
					return;
				}
			}
		}
		if (map.erasePoint(point.x, point.y)) {
			store.incrementScore(id);
			for (Entry<Integer, GameService> r : playerMap.entrySet()) {
				GameService iterable = r.getValue();
				iterable.updatePosition(new PlayerData(point, store
						.getPlayerScore(r.getKey()), id));
				iterable.updateMap(point);
			}

		} else {
			for (Entry<Integer, GameService> r : playerMap.entrySet()) {
				GameService iterable = r.getValue();
				iterable.updatePosition(new PlayerData(point, store
						.getPlayerScore(r.getKey()), id));
			}
			store.setPlayerPosition(id, point);
		}
		if (map.isEmpty()) {
			int winner = store.getWinner();
			GameService remote = playerMap.remove(winner);
			for (GameService r : playerMap.values()) {
				r.lose();
				r.updateScore(0);
			}
			remote.win();
			remote.updateScore(0);
			playerMap.put(winner, remote);
			store.renitializeScores();
			map = new GameMap(numberOfSweets, 20, 20);
			map.generateMap();
			saveInitialState();
		}
		saveGameState();

	}

	@Override
	public Point getPlayerPosition(int id) throws RemoteException {

		return store.getPlayerPosition(id);
	}

	@Override
	public int testConnect() throws RemoteException {

		return playerMap.size();
	}

	synchronized private void recoverMap() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				ObjectMapper mapper = new ObjectMapper();

				InitialInfo info;
				try {
					info = mapper.readValue(new File("map.json"),
							InitialInfo.class);
					numberOfSweets = info.getTargets().size();
					map = new GameMap(info.getTargets().size(),
							info.getWidth(), info.getHeight());
					map.generateMap(info.getTargets());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
	}

	synchronized private void recoverPlayers() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			GameState state = mapper.readValue(new File("state.json"),
					GameState.class);
			store = new PlayerStore();
			store.setLastId(state.getLastId());
			store.setPlayers(state.getPlayers());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	synchronized private void saveInitialState() {
		ObjectMapper mapper = new ObjectMapper();
		InitialInfo info = new InitialInfo();

		info.setHeight(map.MapHeight);
		info.setWidth(map.MapWidth);

		info.setTargets(fromPoint2Pair(map.targetArray));

		try {
			mapper.writeValue(new File("map.json"), info);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	synchronized private void saveGameState() {

		ObjectMapper mapper = new ObjectMapper();
		GameState state = new GameState();

		state.setLastId(store.getLastId());
		state.setPlayers(store.getPlayersRaw());

		try {
			mapper.writeValue(new File("state.json"), state);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<Pair> fromPoint2Pair(List<Point> points) {

		List<Pair> result = new ArrayList<>();
		for (Point point : points) {
			Pair pair = new Pair(point);
			result.add(pair);
		}

		return result;
	}

	@Override
	public void reconnect(GameService remote) throws RemoteException {
		
	}

}
