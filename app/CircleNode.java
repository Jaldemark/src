package app;

import java.awt.*;

import java.awt.geom.*;

import testProject.*;

/**
 * A circular node that is filled with a color.
 */
public abstract class CircleNode implements Node {

	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 20;
	public CircleNode(Color aColor) {

		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException exception) {
			return null;
		}
	}

	public void draw(Graphics2D g2) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, size, size);
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.fill(circle);
		g2.setColor(oldColor);
		g2.draw(circle);
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, size, size);
	}
	@Override
	public boolean contains(Point2D aPoint) {
		Rectangle2D cir = new Rectangle2D.Double(x, y, size, size);
		return cir.contains(aPoint);
	}
	@Override
	public void setX(double x) {
		this.x=x;	
	}
	@Override
	public void setY(double y) {
		this.y=y;
	}
	@Override
	public double getX() {	
		return x;
	}
	@Override
	public double getY() {
		return y;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}


	/*@Override
	public Ellipse2D getGates(int n) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	
}