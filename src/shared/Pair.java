package shared;

import java.awt.Point;
import java.io.Serializable;

public class Pair implements Serializable {

	private int x;
	
	private int y;
	
	public Pair() {
		x = 0;
		y = 0;
	}
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pair(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

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
	
}
