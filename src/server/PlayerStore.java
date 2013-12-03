package server;

import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class PlayerStore {

	private Map<Integer, LinkedList<Point>> playerStates ;
	private Map<Integer, Integer> playerScores; 
	
	private int lastId = 0;
	
	public PlayerStore() {
		playerStates = new Hashtable<>();
		playerScores = new Hashtable<>();
	}
	
	public void addPlayer(int id, Point position ) {
		++lastId;
		LinkedList<Point> list = new LinkedList<>();
		list.add(position);
		playerStates.put(id, list);
		playerScores.put(id, 0);
	}
	
	public void removePlayer(int id) {
		playerStates.remove(id);
		playerScores.remove(id);
	}
	
	public Point getPlayerPosition(int id) {
		LinkedList<Point> list = playerStates.get(id);
		return list.getLast();
	}
	
	public void setPlayerPosition(int id, Point position) {
		LinkedList<Point> list = playerStates.get(id);
		list.add(position);
	}
	
	public void setPlayerScore(int id, int score) {
		playerScores.put(id, score);
	}
	public int getPlayerScore(int id) {
		return playerScores.get(id);
	}
	
	public Set<Entry<Integer, LinkedList<Point>>>getPlayerPositions() {
		return playerStates.entrySet();
		
	}
	
	public Set<Entry<Integer, Integer>> getPlayerScores() {
		return playerScores.entrySet();
	}
	
	public void incrementScore(int id) {
		int newScore = getPlayerScore(id) + 1;
		setPlayerScore(id, newScore);
	}
	
	public int getLastId() {
		return lastId;
	}
	
	public int getWinner() {
		SortedSet<Integer> sorted = new ConcurrentSkipListSet<>();
		sorted.addAll(playerScores.keySet());
		return sorted.last();
	}
	
	
}
