package server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shared.Pair;

public class GameMap {

	protected int[][] gameMap;
	protected final int MapWidth;
	protected final int MapHeight;
	protected List<Point> targetArray;
	protected int numberOfPoints;

	private final Random rand = new Random();

	public GameMap(final int numberOfPoints, final int MapWidth, final int MapHeight) {
		gameMap = new int[MapWidth][MapHeight];
		targetArray = new ArrayList<>(numberOfPoints);
		this.numberOfPoints = numberOfPoints;
		this.MapHeight = MapHeight;
		this.MapWidth = MapWidth;
	}

	/**
	 * 
	 * @return The number of points in the map
	 */
	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	/**
	 * generates The map 
	 */
	public void generateMap() {
		System.out.println(getNumberOfPoints());
		for (int i = 0; i < getNumberOfPoints(); i++) {
			Point point = generateFreePoint();
			gameMap[point.x][point.y] = 1;
			targetArray.add(point);

		}
	}
	
	public void generateMap(List<Pair> targets) {
		
		for(Pair p : targets) {
			gameMap[p.getX()][p.getY()] = 1;
			targetArray.add(new Point(p.getX(), p.getY()));
		}
		
	}

	/**
	 * Tries to erase a point from the map
	 * @param x : the x coordinate of the point to erase
	 * @param y : the y coordinate of the point to erase
	 * @return was the Point erased?
	 */
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

	/**
	 * Generates a random Position 
	 * @return random Point
	 */
	public Point giveMeFreePosition() {
		return generateFreePoint();
	}

	/**
	 * Keep the players' position unique   
	 * @return a free point in the map
	 */
	private Point generateFreePoint() {
		int x = 0;
		int y = 0;
		do {
			x = rand.nextInt(MapWidth);
			y = rand.nextInt(MapHeight);
		} while (gameMap[x][y] == 1);
		return new Point(x, y);
	}
	
	/**
	 * Checks if the number of points is null
	 * @return is there any point left
	 */
	public boolean isEmpty() {
		return numberOfPoints == 0;
	}

}
