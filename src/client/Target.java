package client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Target extends AbstractShape {

	public Target(GameScreen frame) {
		super(frame);
	}

	@Override
	public void drawShape(Graphics g, int x, int y, int w, int h) {
		g.setColor(Color.RED);
		g.fillOval(x, y, w, h);
	}
	
	

	
}
