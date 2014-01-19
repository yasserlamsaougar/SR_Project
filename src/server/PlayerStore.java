package server;

import java.awt.Point;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

public class PlayerStore {

	private Map<Integer, LinkedList<Point>> playerStates;
	private Map<Integer, Integer> playerScores;
	private int lastId = 0;

	public PlayerStore() {
		playerStates = new Hashtable<>();
		playerScores = new Hashtable<>();
	}

	/**
	 * Registers the player in the store in a certain position
	 * @param id : The player's id
	 * @param position : The player's position
	 */
	public void addPlayer(int id, Point position) {
		++lastId;
		LinkedList<Point> list = new LinkedList<>();
		list.add(position);
		playerStates.put(id, list);
		playerScores.put(id, 0);
	}

	/**
	 * removes The player from the store
	 * @param id : The player's Id
	 */
	public void removePlayer(int id) {
		playerStates.remove(id);
		playerScores.remove(id);
	}

	/**
	 * returns the player's position
	 * @param id : The player's id
	 * @return Position
	 */
	public Point getPlayerPosition(int id) {
		LinkedList<Point> list = playerStates.get(id);
		return list.getLast();
	}
	/**
	 * sets the player's position
	 * @param id : The player's id
	 * @param position : new Position
	 */
	public void setPlayerPosition(int id, Point position) {
		LinkedList<Point> list = playerStates.get(id);
		list.add(position);
	}

	/**
	 * 
	 * @param id : The player's id
	 * @param score : The player's new score
	 */
	public void setPlayerScore(int id, int score) {
		playerScores.put(id, score);
	}

	/**
	 * 
	 * @param id : The player's id
	 * @return The score of the Player
	 */
	public int getPlayerScore(int id) {
		return playerScores.get(id);
	}

	/**
	 * 
	 * @return The store's positions record as a set
	 */
	public Set<Entry<Integer, LinkedList<Point>>> getPlayerPositions() {
		return playerStates.entrySet();

	}

	/**
	 * 
	 * @return the store's scores record as a set
	 */
	public Set<Entry<Integer, Integer>> getPlayerScores() {
		return playerScores.entrySet();
	}

	/**
	 * utility method
	 * @param id : The player's id
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
		for(Entry<Integer, Integer> e : playerScores.entrySet())
		{
			if(e.getValue() >= i ) {
				winner = e.getKey();
				i = e.getValue();
			}
		}
		return winner;
	}

}
