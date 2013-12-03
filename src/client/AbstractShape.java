package client;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public abstract class AbstractShape extends JComponent {

	public int x = 0;
	public int y = 0;
	private GameScreen dis;

	public AbstractShape(GameScreen display) {
		dis = display;
		dis.add(this);
	}

	public void setGridPos(Point p ) {
		x = p.x;
		y = p.y;
	}

	abstract public void drawShape(Graphics g, int x, int y, int w, int h);

	/*
	 * delegates drawing proper to drawShape. Transform the grid coordinates of
	 * the shape into pixel coordinates, using the cell size of the
	 * ExampleDisplay associated with the AbstractGridShape
	 */
	public void paint(Graphics g) {
		this.drawShape(g, dis.getCellSize() / 2 + x * dis.getCellSize(),
				dis.getCellSize() / 2 + y * dis.getCellSize(),
				dis.getCellSize(), dis.getCellSize());
	}

	public void moveRect(int[] delta) {
		 x = (x+delta[0]+dis.getGridSize())%dis.getGridSize() ;
		    y = (y+delta[1]+dis.getGridSize())%dis.getGridSize() ;
	}
}
