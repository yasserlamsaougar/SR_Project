package server;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import shared.Pair;

public class GameState implements Serializable {

	private int lastId;
	
	private Map<Integer, Player> players;
	
	public int getLastId() {
		return lastId;
	}
	
	public void setLastId(int lastId) {
		this.lastId = lastId;
	}
	
	public Map<Integer, Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(Map<Integer, Player> players) {
		this.players = players;
	}
	
}

class InitialInfo  implements Serializable{
	
	private List<Pair> targets;
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<Pair> getTargets() {
		return targets;
	}
	
	public void setTargets(List<Pair> targets) {
		this.targets = targets;
	}
	
	
	
}