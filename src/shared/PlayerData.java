package shared;

import java.awt.Point;
import java.io.Serializable;

public class PlayerData implements Serializable {

	private Point position;
	private int score;
	private int id;

	public PlayerData(Point position, int score, int id) {
		this.position = position;
		this.score = score;
		this.id = id;
	}

	protected void setPosition(Point position) {
		this.position = position;
	}

	protected void setScore(int score) {
		this.score = score;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Point getPosition() {
		return position;
	}

	public int getScore() {
		return score;
	}

	public int getId() {
		return id;
	}

}
