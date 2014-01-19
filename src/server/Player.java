package server;

public class Player {

	private int x;

	private int y;

	private int score;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {

		return String.format("User %s score: %d position: x :%d y : %d", score,
				x, y);
	}

}
