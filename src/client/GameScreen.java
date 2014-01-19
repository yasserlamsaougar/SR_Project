package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

<<<<<<< HEAD
import shared.CONSTANTS;
=======
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
import shared.PlayerData;

public class GameScreen extends JPanel {

	private Target[][] gameMap;
	private Map<Integer, Player> playerMap;
	private int cellSize;
	private int gridSize;
	private int targetNumber;
	private int own_id;
	private int score = 0;
	private Map<Integer, int[]> moveTable;
	private List<Point> positions;

	public GameScreen() {
		super();
		playerMap = new HashMap<>();
	}
<<<<<<< HEAD

	public void build() {
		setPreferredSize(new Dimension((gridSize + 3) * cellSize, cellSize
				* (gridSize + 3)));
=======
	
	public void build() {
		setPreferredSize(new Dimension((gridSize+3)*cellSize, cellSize*(gridSize + 3)));
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
		gameMap = new Target[gridSize][gridSize];
		generateMap(positions);
	}

	public int getOwnId() {
		return own_id;
	}

	public GameScreen setOwnId(int own_id) {
		this.own_id = own_id;
		return this;
	}

	public int getScore() {
		return score;
	}

	public GameScreen setScore(int score) {
		this.score = score;
		return this;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public GameScreen setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
		return this;
	}

	public GameScreen setCellSize(int cellSize) {
		this.cellSize = cellSize;
		return this;
	}

	public GameScreen setGridSize(int gridSize) {
		this.gridSize = gridSize;
		return this;
	}

	public Target[][] getGameMap() {
		return gameMap;
	}

	public int getCellSize() {
		return cellSize;
	}

	public GameScreen setMoveTable(Map<Integer, int[]> moveTable) {
		this.moveTable = moveTable;
		return this;
	}

	public int getGridSize() {
		return gridSize;
	}

	public GameScreen setTargetPositions(List<Point> positions) {
		this.positions = positions;
		return this;

	}

	public int[] getKeyCode(int key) {
		return moveTable.get(key);
	}

	public void generateMap(List<Point> positions) {

		List<Point> list = positions;
		for (Point p : list) {
			gameMap[p.x][p.y] = new Target(this);
			gameMap[p.x][p.y].setGridPos(p);
		}
	}

	public void addPlayer(final int id, final Point position) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
<<<<<<< HEAD
				Random rand = new Random();
				Color c = new Color(rand.nextInt(255), rand.nextInt(255),
						rand.nextInt(255));
				Player player = new Player(GameScreen.this, c);
=======
				Player player = new Player(GameScreen.this, Color.blue);
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
				player.setGridPos(position);
				playerMap.put(id, player);
			}
		};
		SwingUtilities.invokeLater(runnable);
	}

	public void removePlayer(int id) {
		Player player = playerMap.get(id);
		this.remove(player);
		playerMap.remove(id);
	}

	public void updatePlayer(PlayerData playerData) {
		Player player = playerMap.get(playerData.getId());
		player.setGridPos(playerData.getPosition());
	}
<<<<<<< HEAD

=======
	
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
	public void updateMap(Point position) {
		Target target = gameMap[position.x][position.y];
		this.remove(target);
		gameMap[position.x][position.y] = null;
	}

	public void updateScore(int score) {
		this.score = score;
	}
<<<<<<< HEAD

	@Override
	protected void paintComponent(Graphics g) {
		g.drawString("Score: " + score, getWidth() - 60, getHeight() - 50);
		for (Component comp : getComponents()) {
			comp.paint(g);
		}
	}

	
=======
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawString("Score: "+score, getWidth() - 60, getHeight() - 50);
		for(Component comp : getComponents()) {
			comp.paint(g);
		}
	}
>>>>>>> 7520917c9bc477575e479127a09169c1a281c8de
}
