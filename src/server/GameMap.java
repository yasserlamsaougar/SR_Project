package server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {

	protected int[][] gameMap;
	protected final int MapWidth;
	protected final int MapHeight;
	protected List<Point> targetArray;
	protected int numberOfPoints;

	private final Random rand = new Random();

	public GameMap(int numberOfPoints, final int MapWidth, final int MapHeight) {
		gameMap = new int[MapWidth][MapHeight];
		targetArray = new ArrayList<>(numberOfPoints);
		this.numberOfPoints = numberOfPoints;
		this.MapHeight = MapHeight;
		this.MapWidth = MapWidth;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void generateMap() {
		System.out.println(getNumberOfPoints());
		for (int i = 0; i < getNumberOfPoints(); i++) {
			Point point = generateFreePoint();
			gameMap[point.x][point.y] = 1;
			targetArray.add(point);

		}
		System.out.println("map generated");
	}

	public boolean erasePoint(int x, int y) {
		int i = 0;
		for (i = 0; i < getNumberOfPoints(); i++) {
			Point p = targetArray.get(i);
			if (p.x == x && p.y == y) {
				break;
			}
		}
		if(i == getNumberOfPoints()) {
			return false;
		}
		
		targetArray.remove(i);
		gameMap[x][y] = 0;
		numberOfPoints--;
		return true;
	}

	public Point giveMeFreePosition() {
		return generateFreePoint();
	}

	private Point generateFreePoint() {
		int x = 0;
		int y = 0;
		do {
			x = rand.nextInt(MapWidth);
			y = rand.nextInt(MapHeight);
		} while (gameMap[x][y] == 1);
		return new Point(x, y);
	}
	
	public boolean isEmpty() {
		return numberOfPoints == 0;
	}

}
