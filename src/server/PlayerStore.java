package server;

import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerStore {

	private Map<Integer, Player> players;
	private int lastId = 0;

	public PlayerStore() {

		players = new ConcurrentHashMap<>();
	}

	public void setPlayers(Map<Integer, Player> players) {
		this.players = players;
	}

	public void setLastId(int lastId) {
		this.lastId = lastId;
	}

	/**
	 * Registers the player in the store in a certain position
	 * 
	 * @param id
	 *            : The player's id
	 * @param position
	 *            : The player's position
	 */
	public void addPlayer(int id, Point position) {
		++lastId;

		Player player = new Player();
		player.setScore(0);
		player.setX(position.x);
		player.setY(position.y);
		players.put(id, player);
	}

	/**
	 * removes The player from the store
	 * 
	 * @param id
	 *            : The player's Id
	 */
	public void removePlayer(int id) {

		players.remove(id);
	}

	/**
	 * returns the player's position
	 * 
	 * @param id
	 *            : The player's id
	 * @return Position
	 */
	public Point getPlayerPosition(int id) {
		Player player = players.get(id);
		return new Point(player.getX(), player.getY());
	}

	/**
	 * sets the player's position
	 * 
	 * @param id
	 *            : The player's id
	 * @param position
	 *            : new Position
	 */
	public void setPlayerPosition(int id, Point position) {
		Player player = players.get(id);
		player.setX(position.x);
		player.setY(position.y);
	}

	/**
	 * 
	 * @param id
	 *            : The player's id
	 * @param score
	 *            : The player's new score
	 */
	public void setPlayerScore(int id, int score) {
		Player player = players.get(id);
		player.setScore(score);
	}

	/**
	 * 
	 * @param id
	 *            : The player's id
	 * @return The score of the Player
	 */
	public int getPlayerScore(int id) {
		return players.get(id).getScore();
	}

	/**
	 * 
	 * @return
	 * @return The store's positions record as a set
	 */
	public Set<Entry<Integer, Player>> getPlayers() {

		return players.entrySet();

	}
	
	public Map<Integer, Player> getPlayersRaw() {
		return players;
	}

	/**
	 * utility method
	 * 
	 * @param id
	 *            : The player's id
	 */
	public void incrementScore(int id) {
		int newScore = getPlayerScore(id) + 1;
		setPlayerScore(id, newScore);
	}

	public int getLastId() {
		return lastId;
	}

	/**
	 * 
	 * @return The player's id with the highest score
	 */
	public int getWinner() {
		int i = 0;
		int winner = 0;
		for (Entry<Integer, Player> p : players.entrySet()) {
			if (p.getValue().getScore() >= i) {
				winner = p.getKey();
				i = p.getValue().getScore();
			}
		}
		return winner;
	}

	public void renitializeScores() {
		for (Player p : players.values()) {
			p.setScore(0);
		}

	}

}
