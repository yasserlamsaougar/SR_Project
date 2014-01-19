package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Player extends AbstractShape {

	private Color color;

	public Player(GameScreen display, Color color) {
		super(display);

		this.color = color;
	}

	@Override
	public void drawShape(Graphics g, int x, int y, int w, int h) {

		g.setColor(color);
		g.fillRect(x, y, w, h);

	}

	public Point getPosition() {
		return new Point(x, y);

	}

}